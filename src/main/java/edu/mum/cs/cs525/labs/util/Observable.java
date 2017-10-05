package edu.mum.cs.cs525.labs.util;

public interface Observable <T> {
	public void on(String event, Observer<? super T> observer);
	public void off(String event, Observer<?> observer);
	public void notifyAll(String event, T arg);
}
