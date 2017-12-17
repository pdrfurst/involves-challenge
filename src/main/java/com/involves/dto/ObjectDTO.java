package com.involves.dto;

import java.util.*;

public class ObjectDTO {

	private static final String DELIMITER = ",";
	private final LinkedHashMap<String, String> values = new LinkedHashMap<>();

	public ObjectDTO(String[] line, String[] header) {
		for (int i = 0; i < header.length; i++)
			this.values.put( header[i], line[i] );
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner( DELIMITER );
		this.values.forEach( (key, value) -> joiner.add( value ) );
		return joiner.toString();
	}

	public String getHeader() {
		StringJoiner joiner = new StringJoiner( DELIMITER );
		this.values.forEach( (key, value) -> joiner.add( key ) );
		return joiner.toString();
	}

	public Map<String, String> getValues() {
		return values;
	}
}
