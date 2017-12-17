package com.involves.service.exception;

public class CsvNotFoundException extends RuntimeException {

	public CsvNotFoundException(String fileName) {
		super( "Csv [ " + fileName + " ] not found" );
	}
}
