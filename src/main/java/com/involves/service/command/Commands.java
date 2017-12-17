package com.involves.service.command;

import com.involves.dto.ObjectDTO;
import com.involves.service.exception.InvalidPropertyException;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Commands {

	COUNT_ALL( "count *", CountAll::new ),
	COUNT_DISTINCT( "count distinct", CountDistinctProperty::new ),
	FILTER( "filter", FilterByValue::new );

	Commands(String template, Supplier<Command> instance) {
		this.template = template;
		this.instance = instance;
	}

	private final String template;
	private final Supplier<Command> instance;

	public String getTemplate() {
		return template;
	}

	public Supplier<Command> getInstance() {
		return instance;
	}

	static List<String> getProperties(String fullCommand) {
		return Stream.of( fullCommand.split( " " ) ).collect( Collectors.toList() );
	}

	static void validateProperty(String property, List<ObjectDTO> objects) throws InvalidPropertyException {
		objects.stream()
				.filter( objectDTO -> objectDTO.getValues().get( property ) != null )
				.findFirst()
				.orElseThrow( () -> new InvalidPropertyException( property ) );
	}
}
