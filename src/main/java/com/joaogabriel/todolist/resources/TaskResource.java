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

@RestController
@RequestMapping("/task")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody TaskDTO dto){
		
		if(dto.getTitle() == null || dto.getDescription() == null ||
				dto.getPriority() == null || dto.getIdUser() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		if(dto.getTitle().isEmpty() || dto.getDescription().isEmpty() || dto.getIdUser().toString().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		Task obj = service.fromDTO(dto);
		
		obj.setCreatedAt(OffsetDateTime.now());
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
