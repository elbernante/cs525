package edu.mum.cs.cs525.labs.domain;

public class P2 extends InterestPromotionDecorator {
	
	public P2(Interestable interestable) {
		super(interestable);
	}
	
	@Override
	public double computeInterest(double balance) {
		return 0.02 * balance + interestble.computeInterest(balance);
	}

}
