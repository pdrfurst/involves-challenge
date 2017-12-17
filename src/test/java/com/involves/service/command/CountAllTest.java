package com.involves.service.command;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CountAllTest {

	private static final String COMMAND = "count *";
	private static final String COUNT = "0";
	private final Command command = new CountAll();

	@Test
	public void run() {
		List<String> result = command.run( new ArrayList<>(), COMMAND );
		assertThat( result.size(), equalTo( 1 ) );
		assertThat( result.get( 0 ), equalTo( COUNT ) );
	}

}
