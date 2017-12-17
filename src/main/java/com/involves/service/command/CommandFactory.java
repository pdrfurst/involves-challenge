package com.involves.service.command;

import com.involves.service.exception.CommandNotFoundException;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CommandFactory {

	public static Command getCommand(String fullCommand) throws CommandNotFoundException {
		return searchCommand( fullCommand ).get();

	}

	private static Supplier<Command> searchCommand(String fullCommand) throws CommandNotFoundException {
		return Stream.of( Commands.values() )
				.filter( command -> fullCommand.contains( command.getTemplate() ) )
				.map( Commands::getInstance )
				.findFirst()
				.orElseThrow( () -> new CommandNotFoundException( fullCommand ) );
	}

}
