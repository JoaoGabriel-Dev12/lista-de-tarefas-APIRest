package com.joaogabriel.todolist.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaogabriel.todolist.domain.Task;

public interface TaskRepository extends JpaRepository<Task, UUID>{

	List<Task> findByIdUser_Id(UUID idUserId);
}
