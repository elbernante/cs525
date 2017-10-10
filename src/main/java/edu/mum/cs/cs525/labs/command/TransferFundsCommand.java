package edu.mum.cs.cs525.labs.command;

import edu.mum.cs.cs525.labs.DAO.AccountDAO;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.service.AccountService;

public class TransferFundsCommand implements Command {
	
	private AccountService accountService;
	private AccountDAO accountDAO;
	private String fromAccountNumber;
	private String toAccountNumber;
	private double amount;
	private String description;
	
	public TransferFundsCommand(AccountService accountService, 
			AccountDAO accountDAO, String fromAccountNumber, String toAccountNumber,
			double amount, String description) {

		this.accountService = accountService;
		this.accountDAO = accountDAO;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
		this.description = description;
	}

	@Override
	public void execute() {
		transferFunds(fromAccountNumber, toAccountNumber, amount, description);
	}

	@Override
	public void undo() {
		transferFunds(toAccountNumber, fromAccountNumber, amount, "Undo transfer: " + description);
	}
	
	private void transferFunds(String from, String to, double amt, String desc) {
		Account fromAccount = accountDAO.loadAccount(from);
		Account toAccount = accountDAO.loadAccount(to);
		fromAccount.transferFunds(toAccount, amt, desc);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		accountService.notifyAll(AccountService.ACCOUNT_CHANGED, fromAccount);
		accountService.notifyAll(AccountService.ACCOUNT_CHANGED, toAccount);
	}

}
