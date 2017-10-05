package edu.mum.cs.cs525.labs.domain;

public class CheckingInterest implements Interestable {
	@Override
	public double computeInterest(double balance) {
		return balance * (balance < 1000.0 ? 0.015 : 0.025);
	}
}
