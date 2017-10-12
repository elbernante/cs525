package edu.mum.cs.cs525.labs.adapter;

public interface Logger {
	
	public static enum LogLevel {DEBUG, ERROR, FATAL, INFO, TRACE, WARNING}
	
	public void log(LogLevel logLevel, String message);

}
