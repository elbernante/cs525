package edu.mum.cs.cs525.labs.DAO;

public class DBAccountDAOFactory extends AccountDAOFactory {

	@Override
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}

}
