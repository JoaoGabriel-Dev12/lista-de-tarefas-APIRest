package com.joaogabriel.todolist.dto;

import java.io.Serializable;
import java.util.UUID;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String name;
	private String email;
	private String password;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(UUID id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
