package com.joaogabriel.todolist.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/tasks")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@Autowired
	private UserService serviceUser;
	
	@PostMapping("/user/{idUser}")
	public ResponseEntity<Void> save(@PathVariable UUID idUser , @RequestBody TaskDTO dto){
		
		if (dto.getTitle() == null || dto.getTitle().isBlank() ||
				dto.getDescription() == null || dto.getDescription().isBlank() ||
			    dto.getPriority() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Task obj = service.fromDTO(idUser, dto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/user/{idUser}")
	public ResponseEntity<List<TaskDTO>> findAll(@PathVariable UUID idUser){
		List<TaskDTO> list = serviceUser.findById(idUser).getTasks()
				.stream().map(t -> new TaskDTO(t)).toList();
		
		return ResponseEntity.ok().body(list);
	}
}
