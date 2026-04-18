package com.joaogabriel.todolist.resources;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaogabriel.todolist.service.AuthenticationService;

@RestController
@RequestMapping
public class AuthenticationResource {

	private final AuthenticationService service;

	public AuthenticationResource(AuthenticationService service) {
		this.service = service;
	}
	
	@PostMapping("/authenticate")
	public String authenticate(Authentication auth) {
		return service.authenticate(auth);
	}
}
