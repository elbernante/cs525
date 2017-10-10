package edu.mum.cs.cs525.labs.command;

import edu.mum.cs.cs525.labs.DAO.AccountDAO;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.service.AccountService;

public class WithdrawCommand implements Command {
	
	private AccountService accountService;
	private AccountDAO accountDAO;
	private String accountNumber;
	private double amount;
	
	public WithdrawCommand(AccountService accountServcie,
			AccountDAO accountDAO, String accountNumber, double amount) {
		
		this.accountService = accountServcie;
		this.accountDAO = accountDAO;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
		accountService.notifyAll(AccountService.ACCOUNT_CHANGED, account);
	}

	@Override
	public void undo() {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		accountService.notifyAll(AccountService.ACCOUNT_CHANGED, account);
	}

}
