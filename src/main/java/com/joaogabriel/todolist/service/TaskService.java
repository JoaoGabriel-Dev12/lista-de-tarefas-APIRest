package com.joaogabriel.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaogabriel.todolist.domain.Task;
import com.joaogabriel.todolist.domain.User;
import com.joaogabriel.todolist.dto.TaskDTO;
import com.joaogabriel.todolist.repository.TaskRepository;
import com.joaogabriel.todolist.service.exception.ObjectNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repo;
	
	public Task insert(Task u) {
		return repo.save(u);
	}
	
	public List<Task> findAll(){
		return repo.findAll();
	}
	
	public Task findById(UUID id) {
		
		Optional<Task> task = repo.findById(id);
		
		return task.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
	}
	
	public Task fromDTO(TaskDTO dto) {
		
		return new Task(null, dto.getTitle(), dto.getDescription(), 
				dto.getPriority(), new User(dto.getIdUser(), null, null, null));
	}
}
