package edu.mum.cs.cs525.labs.domain;

public class P3 extends InterestPromotionDecorator {
	
	public P3(Interestable interestable) {
		super(interestable);
	}
	
	@Override
	public double computeInterest(double balance) {
		return 0.03 * balance + interestble.computeInterest(balance);
	}

}
