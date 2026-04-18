package com.joaogabriel.todolist.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	private final JwtService service;

	public AuthenticationService(JwtService service) {
		this.service = service;
	}
	
	public String authenticate(Authentication auth) {
		return service.generateToken(auth);
	}
}
