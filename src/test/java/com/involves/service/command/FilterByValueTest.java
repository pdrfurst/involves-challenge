package com.involves.service.command;

import com.involves.dto.ObjectDTO;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class FilterByValueTest {

	private static final String COMMAND = "filter";
	private static final String UF = "uf";
	private static final String SC = "SC";
	private static final String HELP_MESSAGE_1 = "Help: to use filter you need to enter the parameters like this";
	private static final String HELP_MESSAGE_2 = "$ filter <property> <value>";
	private static final String INVALID_PROPERTY_MESSAGE = "Invalid property [ a ]";
	private final Command command = new FilterByValue();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		System.setOut( new PrintStream( outContent ) );
	}

	@After
	public void cleanUp() {
		System.setOut( null );
	}

	@Test
	public void run() {
		List<String> result = command.run( buildObjects(), COMMAND + " uf sc" );
		assertThat( result.size(), equalTo( 2 ) );
		assertThat( result.get( 0 ), equalTo( UF ) );
		assertThat( result.get( 1 ), equalTo( SC ) );
	}

	@Test
	public void runWhenIsEmpty() {
		List<String> result = command.run( buildObjects(), COMMAND + " uf bla" );
		assertThat( result.size(), equalTo( 1 ) );
		assertThat( result.get( 0 ), equalTo( UF ) );
	}

	@Test
	public void runWhenHasCaseSensitive() {
		List<String> result = command.run( buildObjects(), COMMAND + " uf SC" );
		assertThat( result.size(), equalTo( 2 ) );
		assertThat( result.get( 0 ), equalTo( UF ) );
		assertThat( result.get( 1 ), equalTo( SC ) );
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
		List<String> result = command.run( buildObjects(), COMMAND + " a a" );
		assertThat( result.size(), equalTo( 1 ) );
		assertThat( result.get( 0 ), equalTo( INVALID_PROPERTY_MESSAGE ) );
	}

	private List<ObjectDTO> buildObjects() {
		String[] header = new String[] { "uf" };
		String[] line = new String[] { "SC" };
		return singletonList( new ObjectDTO( line, header ) );
	}

}
