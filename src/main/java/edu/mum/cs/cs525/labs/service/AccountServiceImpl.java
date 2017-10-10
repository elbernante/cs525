package edu.mum.cs.cs525.labs.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.mum.cs.cs525.labs.DAO.AccountDAO;
import edu.mum.cs.cs525.labs.command.Command;
import edu.mum.cs.cs525.labs.command.CommandInvoker;
import edu.mum.cs.cs525.labs.command.DepositCommand;
import edu.mum.cs.cs525.labs.command.TransferFundsCommand;
import edu.mum.cs.cs525.labs.command.WithdrawCommand;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.domain.Customer;
import edu.mum.cs.cs525.labs.domain.Interestable;
import edu.mum.cs.cs525.labs.util.Observer;

public class AccountServiceImpl implements AccountService {
	
	private Map<String, Collection<Observer<? super Account>>> observers = new HashMap<>();
	private AccountDAO accountDAO;
	private CommandInvoker commandInvoker = new CommandInvoker();
	
	public AccountServiceImpl(AccountDAO accountDAO){
		this.accountDAO = accountDAO;
	}

	public Account createAccount(String accountNumber, String customerName, Interestable interestable) {
		Account account = new Account(accountNumber, interestable);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		notifyAll(ACCOUNT_CREATED, account);
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Command depositCommand = new DepositCommand(this, accountDAO, accountNumber, amount);
		commandInvoker.invoke(depositCommand);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Command withdrawCommand = new WithdrawCommand(this, accountDAO, accountNumber, amount);
		commandInvoker.invoke(withdrawCommand);
	}

	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Command transferFundsCommand = new TransferFundsCommand(this, accountDAO, fromAccountNumber, toAccountNumber, amount, description);
		commandInvoker.invoke(transferFundsCommand);
	}
	
	@Override
	public void undo() {
		commandInvoker.undo();
	}
	
	@Override
	public void addInterest() {
		Collection<Account> accounts = getAllAccounts();
		for (Account account : accounts) {
			account.addInterest();
			notifyAll(ACCOUNT_CHANGED, account);
		}
	}

	@Override
	public void on(String event, Observer<? super Account> observer) {
		Collection<Observer<? super Account>> obs = observers.get(event);
		if (obs == null) {
			obs = new HashSet<>();
			observers.put(event, obs);
		}
		obs.add(observer);
	}

	@Override
	public void off(String event, Observer<?> observer) {
		observers.getOrDefault(event, new HashSet<>()).remove(observer);
	}

	@Override
	public void notifyAll(String event, Account arg) {
		observers.getOrDefault(event, new HashSet<>()).stream().forEach(o -> {
			o.update(arg);
		});		
	}
}
