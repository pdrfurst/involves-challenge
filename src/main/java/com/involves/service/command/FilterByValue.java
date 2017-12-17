package com.involves.service.command;

import com.involves.dto.ObjectDTO;
import com.involves.service.exception.InvalidPropertyException;

import java.util.*;

import static com.involves.service.command.Commands.getProperties;
import static com.involves.service.command.Commands.validateProperty;
import static java.util.Arrays.asList;

class FilterByValue implements Command {

	private static final int PROPERTY_INDEX = 1;
	private static final int VALUE_INDEX = 2;
	private static final int MIN_COMMAND_SIZE = 3;

	@Override
	public List<String> run(List<ObjectDTO> objects, String fullCommand) {
		final List<String> filterProperties = getProperties( fullCommand );

		if (filterProperties.size() < MIN_COMMAND_SIZE) {
			return asList(
					"Help: to use filter you need to enter the parameters like this",
					"$ filter <property> <value>" );
		}

		return filterByPropertyAndValue( objects, filterProperties );
	}

	private List<String> filterByPropertyAndValue(List<ObjectDTO> objects, List<String> filterProperties) {
		try {
			return filterByProperties( objects, filterProperties );
		} catch (InvalidPropertyException e) {
			return Collections.singletonList( e.getMessage() );
		}
	}

	private List<String> filterByProperties(List<ObjectDTO> objects, List<String> filterProperties)
			throws InvalidPropertyException {
		final String property = filterProperties.get( PROPERTY_INDEX );
		final String value = filterProperties.get( VALUE_INDEX );

		validateProperty( property, objects );

		final LinkedList<String> listToShow = new LinkedList<>();

		objects.forEach( object -> filter( property, value, object, listToShow ) );

		addHeader( objects, listToShow );

		return listToShow;
	}

	private void addHeader(List<ObjectDTO> objects, LinkedList<String> listToShow) {
		objects.stream().findAny().ifPresent( object -> listToShow.addFirst( object.getHeader() ) );
	}

	private void filter(String property, String value, ObjectDTO object, List<String> listToShow) {
		String objectValue = object.getValues().get( property );
		if (objectValue.toLowerCase().equals( value.toLowerCase() )) {
			listToShow.add( object.toString() );
		}
	}
}
