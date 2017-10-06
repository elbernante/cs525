package edu.mum.cs.cs525.labs.domain;

public abstract class InterestPromotionDecorator implements Interestable {
	
	protected Interestable interestble;
	
	public InterestPromotionDecorator(Interestable interestable) {
		this.interestble = interestable;
	}
}
