package com.involves.service.command;

import com.involves.dto.ObjectDTO;
import com.involves.service.exception.InvalidPropertyException;

import java.util.*;

import static com.involves.service.command.Commands.getProperties;
import static com.involves.service.command.Commands.validateProperty;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class CountDistinctProperty implements Command {

	private static final int DISTINCT_PROPERTY_INDEX = 2;
	private static final int MIN_COMMAND_SIZE = 3;

	@Override
	public List<String> run(List<ObjectDTO> objects, String fullCommand) {

		List<String> distinctProperty = getProperties( fullCommand );

		if (distinctProperty.size() < MIN_COMMAND_SIZE) {
			return asList( "Help: to use count distinct you need to enter the parameters like this",
					"$ count distinct <property>" );
		}

		return countDistinctByProperty( objects, distinctProperty );
	}

	private List<String> countDistinctByProperty(List<ObjectDTO> objects, List<String> distinctProperty) {
		try {
			return countDistinct( objects, distinctProperty );
		} catch (InvalidPropertyException e) {
			return singletonList( e.getMessage() );
		}
	}

	private List<String> countDistinct(List<ObjectDTO> objects, List<String> distinctProperty)
			throws InvalidPropertyException {
		String property = distinctProperty.get( DISTINCT_PROPERTY_INDEX );

		validateProperty( property, objects );

		Set<String> distinct = filterDistinctProperty( property, objects );

		return singletonList( valueOf( distinct.size() ) );
	}

	private Set<String> filterDistinctProperty(String property, List<ObjectDTO> objects) {
		Set<String> distinct = new HashSet<>();
		objects.forEach( object -> distinct.add( object.getValues().get( property ) ) );
		return distinct;
	}

}
