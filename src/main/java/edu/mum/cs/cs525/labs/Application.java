package edu.mum.cs.cs525.labs;

import edu.mum.cs.cs525.labs.DAO.AccountDAOFactory;
import edu.mum.cs.cs525.labs.DAO.MockAccountDAOFactoryImpl;
import edu.mum.cs.cs525.labs.domain.Account;
import edu.mum.cs.cs525.labs.domain.AccountEntry;
import edu.mum.cs.cs525.labs.domain.CheckingInterest;
import edu.mum.cs.cs525.labs.domain.Customer;
import edu.mum.cs.cs525.labs.domain.Interestable;
import edu.mum.cs.cs525.labs.domain.P1;
import edu.mum.cs.cs525.labs.domain.P2;
import edu.mum.cs.cs525.labs.domain.P3;
import edu.mum.cs.cs525.labs.domain.SavingsInterest;
import edu.mum.cs.cs525.labs.observers.EmailSender;
import edu.mum.cs.cs525.labs.observers.Logger;
import edu.mum.cs.cs525.labs.observers.SMSSender;
import edu.mum.cs.cs525.labs.service.AccountService;
import edu.mum.cs.cs525.labs.service.AccountServiceImpl;

public class Application {
	public static void main(String[] args) {
		AccountDAOFactory accountDAOFactory = new MockAccountDAOFactoryImpl();
		AccountService accountService = new AccountServiceImpl(accountDAOFactory.getAccountDAO());
		accountService.on(AccountService.ACCOUNT_CHANGED, new Logger());
		accountService.on(AccountService.ACCOUNT_CHANGED, new SMSSender());
		accountService.on(AccountService.ACCOUNT_CREATED, new EmailSender());

		// create 2 accounts;
		Interestable interest1 = new SavingsInterest();
		interest1 = new P1(interest1);
		interest1 = new P3(interest1);
		accountService.createAccount("1263862", "Frank Brown", interest1);
		
		Interestable interest2 = new CheckingInterest();
		interest2 = new P2(interest2);
		interest2 = new P3(interest2);
		accountService.createAccount("4253892", "John Doe", interest2);
		
		// use account 1;
		accountService.deposit("1263862", 240);
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		// use account 2;
		accountService.deposit("4253892", 12450);
		accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");

		// add interests
		accountService.addInterest();
		
		// show balances
		for (Account account : accountService.getAllAccounts()) {
			Customer customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());
			
			System.out.println("-Date-------------------------" 
					+ "-Description------------------" 
					+ "-Amount-------------");
			
			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", 
						entry.getDate().toString(), 
						entry.getDescription(),
						entry.getAmount());
			}
			
			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}

}
