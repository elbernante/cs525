package edu.mum.cs.cs525.labs.domain;

public class P1 extends InterestPromotionDecorator {
	
	public P1(Interestable interestable) {
		super(interestable);
	}
	
	@Override
	public double computeInterest(double balance) {
		return 0.01 * balance + interestble.computeInterest(balance);
	}

}
