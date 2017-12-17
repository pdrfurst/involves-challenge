package com.involves.service.command;

import com.involves.dto.ObjectDTO;

import java.util.List;

public interface Command {
	List<String> run(List<ObjectDTO> objects, String fullCommand);
}
