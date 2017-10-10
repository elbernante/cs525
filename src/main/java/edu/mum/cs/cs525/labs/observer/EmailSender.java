package edu.mum.cs.cs525.labs.observer;

import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.util.Observer;

public class EmailSender implements Observer<Account> {

	@Override
	public void update(Account account) {
		System.out.println("Email Sender: New account created for " + 
				account.getCustomer().getName() + ": " + account.getAccountNumber());
	}

}
