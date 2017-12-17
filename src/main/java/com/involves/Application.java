package com.involves;

import com.involves.service.command.*;
import com.involves.service.exception.CommandNotFoundException;
import com.involves.service.read.ReadCsvFileService;
import com.involves.dto.ObjectDTO;

import java.util.List;
import java.util.Scanner;

public class Application {

	private static final String FILE_NAME = "cidades.csv";

	public static void main(String[] args) {
		run();
	}

	private static void run() {
		List<ObjectDTO> objects = readCsv();
		while (true) {
			listenCommands( objects );
		}
	}

	private static void listenCommands(List<ObjectDTO> objects) {
		Scanner scanner = new Scanner( System.in );
		System.out.println( "Enter your command:" );
		String fullCommand = scanner.nextLine();
		try {
			Command command = CommandFactory.getCommand( fullCommand );
			List<String> result = command.run( objects, fullCommand );
			result.forEach( System.out::println );
		} catch (CommandNotFoundException e) {
			System.out.println( e.getMessage() );
		}

	}

	private static List<ObjectDTO> readCsv() {
		ReadCsvFileService readCsvFileService = new ReadCsvFileService( FILE_NAME );
		return readCsvFileService.read();
	}
}
