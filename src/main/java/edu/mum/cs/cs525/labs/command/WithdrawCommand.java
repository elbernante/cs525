package edu.mum.cs.cs525.labs.command;

import edu.mum.cs.cs525.labs.DAO.AccountDAO;
import edu.mum.cs.cs525.labs.domain.Account;

public class WithdrawCommand implements Command {
	
	private AccountDAO accountDAO;
	private String accountNumber;
	private double amount;
	
	public WithdrawCommand(AccountDAO accountDAO, String accountNumber, double amount) {
		this.accountDAO = accountDAO;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	
	@Override
	public void execute() {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}

	@Override
	public void undo() {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
	}

}
