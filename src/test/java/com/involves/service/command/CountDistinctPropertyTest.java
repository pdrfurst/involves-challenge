package com.involves.service.command;

import com.involves.dto.ObjectDTO;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CountDistinctPropertyTest {

	private static final String COMMAND = "count distinct";
	private static final String COUNT = "1";
	private static final String HELP_MESSAGE_1 = "Help: to use count distinct you need to enter the parameters like this";
	private static final String HELP_MESSAGE_2 = "$ count distinct <property>";
	private static final String INVALID_PROPERTY_MESSAGE = "Invalid property [ a ]";

	private final Command command = new CountDistinctProperty();

	@Test
	public void run() {
		List<String> result = command.run( buildObjects(), COMMAND + " uf" );
		assertThat( result.size(), equalTo( 1 ) );
		assertThat( result.get( 0 ), equalTo( COUNT ) );
	}

	@Test
	public void runWhenNoHasCorrectParameters() {
		List<String> result = command.run( buildObjects(), COMMAND );
		assertThat( result.size(), equalTo( 2 ) );
		assertThat( result.get( 0 ), equalTo( HELP_MESSAGE_1 ) );
		assertThat( result.get( 1 ), equalTo( HELP_MESSAGE_2 ) );
	}

	@Test
	public void runWhenIsInvalidProperty() {
		List<String> result = command.run( buildObjects(), COMMAND + " a" );
		assertThat( result.size(), equalTo( 1 ) );
		assertThat( result.get( 0 ), equalTo( INVALID_PROPERTY_MESSAGE ) );
	}

	private List<ObjectDTO> buildObjects() {
		String[] header = new String[] { "uf" };
		String[] line = new String[] { "sc" };
		return singletonList( new ObjectDTO( line, header ) );
	}

}
