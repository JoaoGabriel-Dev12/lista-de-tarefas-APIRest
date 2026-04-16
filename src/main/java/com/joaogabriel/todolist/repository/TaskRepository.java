package com.joaogabriel.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaogabriel.todolist.domain.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{

}
