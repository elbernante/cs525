package edu.mum.cs.cs525.labs.domain;

public class SavingsInterest implements Interestable {
	@Override
	public double computeInterest(double balance) {
		return balance * ((balance < 1000.0) ? 0.01 :
					  	  (balance < 5000.0) ? 0.02 : 0.04);
	}
}
