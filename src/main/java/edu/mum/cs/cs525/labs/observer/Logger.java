package edu.mum.cs.cs525.labs.observer;

import edu.mum.cs.cs525.labs.adapter.Logger.LogLevel;
import edu.mum.cs.cs525.labs.adapter.LoggerAdapter;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.util.Observer;

public class Logger implements Observer<Account> {

	@Override
	public void update(Account account) {
		String message = "LOGGER    : Account changed: " + account.getAccountNumber() +
				"; New balance: " + account.getBalance();
		System.out.println(message);
		LoggerAdapter.getInstance().log(LogLevel.INFO, message);
	}

}
