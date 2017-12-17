package com.involves.service.exception;

public class CommandNotFoundException extends Exception {

	public CommandNotFoundException(String fullCommand) {
		super( "Command [ " + fullCommand + " ] not found" );
	}

}
