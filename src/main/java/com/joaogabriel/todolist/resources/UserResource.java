package com.joaogabriel.todolist.resources;

import java.net.URI;
import java.time.OffsetDateTime;
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

import com.joaogabriel.todolist.domain.User;
import com.joaogabriel.todolist.dto.UserDTO;
import com.joaogabriel.todolist.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody UserDTO dto){
		
		if(dto.getName() == null || dto.getEmail() == null || dto.getPassword() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		if(dto.getName().isEmpty() || dto.getEmail().isEmpty() || dto.getPassword().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		User obj = service.fromDTO(dto);
		
		obj.setCreated_at(OffsetDateTime.now());
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable UUID id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
