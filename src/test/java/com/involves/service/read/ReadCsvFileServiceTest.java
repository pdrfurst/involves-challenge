package com.involves.service.read;

import com.involves.service.exception.CsvNotFoundException;
import com.involves.dto.ObjectDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ReadCsvFileServiceTest {

	private static final String CSV_NAME_TEST = "cidades_test.csv";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void read() throws Exception {
		ReadFile reader = new ReadCsvFileService( CSV_NAME_TEST );
		List<ObjectDTO> read = reader.read();
		ObjectDTO objectDTO = read.get( 0 );
		assertThat( read.size(), equalTo( 1 ) );
		assertThat( objectDTO.getValues().get( "uf" ), equalTo( "SC" ) );
	}

	@Test
	public void readWhenCsvNotFound() {
		thrown.expect( CsvNotFoundException.class );
		thrown.expectMessage( "Csv [ other.csv ] not found" );
		ReadFile reader = new ReadCsvFileService( "other.csv" );
		reader.read();
	}

	@Test
	public void readWhenIsInvalidFile() {
		ReadFile reader = new ReadCsvFileService( CSV_NAME_TEST );
		reader.read();
	}

}
