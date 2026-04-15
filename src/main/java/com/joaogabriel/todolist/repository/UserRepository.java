package com.joaogabriel.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaogabriel.todolist.domain.User;

public interface UserRepository extends JpaRepository<User, UUID>{

}
