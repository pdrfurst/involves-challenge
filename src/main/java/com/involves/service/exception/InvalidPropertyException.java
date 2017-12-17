package com.involves.service.exception;

public class InvalidPropertyException extends Exception {

	public InvalidPropertyException(String property) {
		super( "Invalid property [ " + property + " ]" );
	}
}
