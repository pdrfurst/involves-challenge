package com.involves.service.command;

import com.involves.service.exception.CommandNotFoundException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class CommandFactoryTest {

	private static final String COMMAND_FILTER = "filter";
	private static final String COMMAND_COUNT_ALL = "count *";
	private static final String COMMAND_COUNT_DISTINCT = "count distinct";
	private static final String INVALID_COMMAND = "blabla";
	private static final String ERROR_MESSAGE = "Command [ blabla ] not found";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void getCommandWhenIsFilter() throws Exception{
		Command command = CommandFactory.getCommand( COMMAND_FILTER );
		assertTrue( command instanceof FilterByValue );
	}

	@Test
	public void getCommandWhenIsCountAll() throws Exception{
		Command command = CommandFactory.getCommand( COMMAND_COUNT_ALL );
		assertTrue( command instanceof CountAll );
	}

	@Test
	public void getCommandWhenIsCountDistinct() throws Exception{
		Command command = CommandFactory.getCommand( COMMAND_COUNT_DISTINCT );
		assertTrue( command instanceof CountDistinctProperty );
	}

	@Test
	public void constructor() {
		assertThat( new CommandFactory(), notNullValue() );
	}

	@Test
	public void getCommandWhenNotFound() throws Exception{
		thrown.expect( CommandNotFoundException.class );
		thrown.expectMessage( ERROR_MESSAGE );
		CommandFactory.getCommand( INVALID_COMMAND );
	}

}
