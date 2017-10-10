package edu.mum.cs.cs525.labs.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {
	
	private Deque<Command> commandHistory = new ArrayDeque<>();
	private Deque<Command> redoStack = new ArrayDeque<>();
	
	public void invoke(Command command) {
		command.execute();
		commandHistory.push(command);
		redoStack.clear();
	}
	
	public boolean undo() {
		if (commandHistory.isEmpty()) {
			return false;
		}
		Command command = commandHistory.pop();
		command.undo();
		redoStack.push(command);
		return true;
	}
	
	public boolean redo() {
		if (redoStack.isEmpty()) {
			return false;
		}
		
		Command command = redoStack.pop();
		command.execute();
		commandHistory.push(command);
		return true;
	}

}
