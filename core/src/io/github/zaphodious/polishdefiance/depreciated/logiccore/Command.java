package io.github.zaphodious.polishdefiance.depreciated.logiccore;

public enum Command {
	UP("w"),
	DOWN("s"),
	LEFT("a"),
	RIGHT("d"),
	EXIT("e"),
	INVALID("");

	String	commandKey;

	Command(String commandKey) {
		this.commandKey = commandKey;
	}

	public static Command getCommand(String isKey) {
		Command toReturn = Command.INVALID;
		for (Command command : Command.values()) {
			if (command.commandKey.equals(isKey)) {
				toReturn = command;
			}
		}
		return toReturn;
	}
}
