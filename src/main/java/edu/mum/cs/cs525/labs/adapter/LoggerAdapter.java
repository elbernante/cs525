package edu.mum.cs.cs525.labs.adapter;

import org.apache.logging.log4j.LogManager;

public class LoggerAdapter implements Logger {
	
	private static final LoggerAdapter instance = new LoggerAdapter();
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	public static LoggerAdapter getInstance() {
		return instance;
	}

	@Override
	public void log(LogLevel logLevel, String message) {
		switch (logLevel) {
		case DEBUG:
			logger.debug(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		case FATAL:
			logger.fatal(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case TRACE:
			logger.trace(message);
			break;
		case WARNING:
			logger.warn(message);
			break;
		default:
			System.out.println(message);;
		}
	}
}
