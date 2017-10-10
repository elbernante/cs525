package edu.mum.cs.cs525.labs.service;

import java.util.Collection;

import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.domain.Interestable;
import edu.mum.cs.cs525.labs.util.Observable;

public interface AccountService extends Observable<Account> {
	public static final String ACCOUNT_CREATED = "create";
	public static final String ACCOUNT_CHANGED = "change";
	
	Account createAccount(String accountNumber, String customerName, Interestable interestable);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
    void undo();
}
