package com.joaogabriel.todolist.resources;

import java.net.URI;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaogabriel.todolist.domain.Task;
import com.joaogabriel.todolist.dto.TaskDTO;
import com.joaogabriel.todolist.service.TaskService;
import com.joaogabriel.todolist.service.UserService;

@RestController
@RequestMapping("/task")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody TaskDTO dto){
		
		Task obj = new Task(null, dto.getTitle(), dto.getDescription(),
				dto.getPriority(), OffsetDateTime.now(), userService.findById(dto.getIdUser()));
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
}
