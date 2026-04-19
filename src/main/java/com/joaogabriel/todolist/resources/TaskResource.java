package com.joaogabriel.todolist.resources;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaogabriel.todolist.domain.Task;
import com.joaogabriel.todolist.dto.TaskDTO;
import com.joaogabriel.todolist.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody TaskDTO dto, @AuthenticationPrincipal Jwt jwt){
		
		if (dto.getTitle() == null || dto.getTitle().isBlank() ||
				dto.getDescription() == null || dto.getDescription().isBlank() ||
			    dto.getPriority() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		UUID idUser = UUID.fromString(jwt.getClaim("id"));
		
		Task obj = service.fromDTO(idUser, dto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> findAll(@AuthenticationPrincipal Jwt jwt){
		
		UUID idUser = UUID.fromString(jwt.getClaim("id"));
		
		List<TaskDTO> list = service.findByUser(idUser).stream()
				.map(TaskDTO::new)
				.toList();
		
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping("/{idTask}")
	public ResponseEntity<Void> update(@PathVariable UUID idTask, @RequestBody TaskDTO dto,
			@AuthenticationPrincipal Jwt jwt){
		
		if (dto.getTitle() == null || dto.getTitle().isBlank() ||
				dto.getDescription() == null || dto.getDescription().isBlank() ||
			    dto.getPriority() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		UUID idUser = UUID.fromString(jwt.getClaim("id"));
		
		service.update(idTask, idUser, dto);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{idTask}")
	public ResponseEntity<Void> deleteById(@PathVariable UUID idTask, @AuthenticationPrincipal Jwt jwt){
		
		UUID idUser = UUID.fromString(jwt.getClaim("id"));
		service.deleteById(idTask, idUser);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<Void> deleteAll(@AuthenticationPrincipal Jwt jwt){
		UUID idUser = UUID.fromString(jwt.getClaim("id"));
		service.deleteAll(idUser);
		
		return ResponseEntity.noContent().build();
	}
}
