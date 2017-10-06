package edu.mum.cs.cs525.labs.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account {
	private Customer customer;

	private String accountNumber;
	
	private Interestable interestable;

	private List<AccountEntry> entries = new ArrayList<AccountEntry>();

	public Account(String accountNumber, Interestable interestable) {
		this.accountNumber = accountNumber;
		this.interestable = interestable;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entries) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
		entries.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
		entries.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entries.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		
		entries.add(fromEntry);
		
		toAccount.addEntry(toEntry);
	}
	
	public void addInterest() {
		double interest = interestable.computeInterest(getBalance());
		AccountEntry entry = new AccountEntry(interest, "interest", "", "");
		entries.add(entry);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Interestable getInterestable() {
		return interestable;
	}
	
	public void setInterestable(Interestable interestable) {
		this.interestable = interestable;
	}
	
	public Collection<AccountEntry> getEntryList() {
		return entries;
	}

}
