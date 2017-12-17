package com.involves.service.read;

import com.involves.service.exception.CsvNotFoundException;
import com.involves.dto.ObjectDTO;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class ReadCsvFileService implements ReadFile {

	private final String fileName;
	private static final String DELIMITER = ",";
	private static final Charset ENCODING = StandardCharsets.UTF_8;

	public ReadCsvFileService(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public final List<ObjectDTO> read() {
		List<ObjectDTO> objects = new ArrayList<>();
		String filePath = getPath();
		try (Scanner scanner = new Scanner( Paths.get( filePath ), ENCODING.name() )) {
			convertLines( objects, scanner );
		} catch (IOException e) {
			throw new RuntimeException( "Unable to read" );
		}
		return objects;

	}

	private String getPath() {
		URL resource = getClass().getClassLoader().getResource( fileName );

		validateResource( resource );

		return resource.getPath();
	}

	private void validateResource(URL resource) {
		if (resource == null)
			throw new CsvNotFoundException( fileName );
	}

	private void convertLines(List<ObjectDTO> objects, Scanner scanner) {
		String[] header = scanner.nextLine().split( DELIMITER );
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			objects.add( new ObjectDTO( line.split( DELIMITER ), header ) );
		}
	}

}
