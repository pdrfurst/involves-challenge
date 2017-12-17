package com.involves.service.command;

import com.involves.dto.ObjectDTO;

import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Collections.singletonList;

public class CountAll implements Command {

	@Override
	public List<String> run(List<ObjectDTO> objects, String fullCommand) {
		return singletonList( valueOf( objects.size() ) );
	}
}
