package edu.mum.cs.cs525.labs.observers;

import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.util.Observer;

public class SMSSender implements Observer<Account> {

	@Override
	public void update(Account account) {
		System.out.println("SMS Sender: Account changed: " + account.getAccountNumber() +
			"; New balance: " + account.getBalance());
	}

}
