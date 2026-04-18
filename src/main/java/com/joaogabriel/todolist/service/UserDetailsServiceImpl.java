package com.joaogabriel.todolist.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaogabriel.todolist.auth.UserAuthenticated;
import com.joaogabriel.todolist.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository repo;
	
	public UserDetailsServiceImpl(UserRepository repo) {
		this.repo= repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repo.findByEmail(username)
				.map(UserAuthenticated::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
	}

}
