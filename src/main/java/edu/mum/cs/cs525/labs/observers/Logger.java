package edu.mum.cs.cs525.labs.observers;

import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.util.Observer;

public class Logger implements Observer<Account> {

	@Override
	public void update(Account account) {
		System.out.println("LOGGER    : Account changed: " + account.getAccountNumber() +
				"; New balance: " + account.getBalance());
	}

}
