package com.joaogabriel.todolist.auth;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joaogabriel.todolist.domain.User;

public class UserAuthenticated implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	public UserAuthenticated(User user) {
		this.user = user;
	}
	
	public UUID getId() {
		return user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

}
