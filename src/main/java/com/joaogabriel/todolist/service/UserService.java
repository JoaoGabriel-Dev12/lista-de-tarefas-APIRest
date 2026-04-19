package com.joaogabriel.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaogabriel.todolist.domain.User;
import com.joaogabriel.todolist.dto.UserDTO;
import com.joaogabriel.todolist.repository.UserRepository;
import com.joaogabriel.todolist.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User insert(User u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return repo.save(u);
	}
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(UUID id) {
		
		Optional<User> user = repo.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public User update(UserDTO dto, UUID id) {
		
		User u = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		u.setName(dto.getName());
		u.setEmail(dto.getEmail());
		u.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		return repo.save(u);
	}
	
	public void deleteById(UUID id) {
		
		User u = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		repo.delete(u);
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());
	}
}
