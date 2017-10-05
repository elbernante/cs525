package edu.mum.cs.cs525.labs.DAO;

import java.util.Collection;

import edu.mum.cs.cs525.labs.domain.Account;

public interface AccountDAO {
	void saveAccount(Account account);
	void updateAccount(Account account);
	Account loadAccount(String accountnumber);
	Collection<Account> getAccounts();
}
