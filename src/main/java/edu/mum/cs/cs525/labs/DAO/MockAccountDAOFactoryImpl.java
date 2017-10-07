package edu.mum.cs.cs525.labs.DAO;

public class MockAccountDAOFactoryImpl extends AccountDAOFactory {
	@Override
	public AccountDAO getAccountDAO() {
		return new MockAccountDAO();
	}
}
