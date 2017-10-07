package edu.mum.cs.cs525.labs.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.mum.cs.cs525.labs.DAO.AccountDAO;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.domain.Customer;
import edu.mum.cs.cs525.labs.domain.Interestable;
import edu.mum.cs.cs525.labs.util.Observer;

public class AccountServiceImpl implements AccountService {
	
	private Map<String, Collection<Observer<? super Account>>> observers = new HashMap<>();
	private AccountDAO accountDAO;
	
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
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		notifyAll(ACCOUNT_CHANGED, account);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		notifyAll(ACCOUNT_CHANGED, account);
	}

	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		notifyAll(ACCOUNT_CHANGED, fromAccount);
		notifyAll(ACCOUNT_CHANGED, toAccount);
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
