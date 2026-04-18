package com.joaogabriel.todolist.service;

import java.time.OffsetDateTime;
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
	private TaskRepository repoTask;
	
	@Autowired
	private UserService serviceUser;
	
	public Task insert(Task u) {
		return repoTask.save(u);
	}
	
	public void update(UUID idTask, TaskDTO dto) {
		
		Task obj = repoTask.findById(idTask)
				.orElseThrow(() -> new ObjectNotFoundException("Tarefa não enocntrada"));
		
		obj.setTitle(dto.getTitle());
		obj.setDescription(dto.getDescription());
		obj.setPriority(dto.getPriority());
		
		repoTask.save(obj);
		
	}
	
	public List<Task> findAll(){
		return repoTask.findAll();
	}
	
	public Task findById(UUID id) {
		
		Optional<Task> task = repoTask.findById(id);
		
		return task.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
	}
	
	public void delete(UUID id) {
		
		Task task = repoTask.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
		
		repoTask.delete(task);
	}
	
	public Task fromDTO(UUID idUser, TaskDTO dto) {

		User u = serviceUser.findById(idUser);
		
		Task obj = new Task(null, dto.getTitle(), dto.getDescription(), 
				dto.getPriority(), u);
		obj.setCreatedAt(OffsetDateTime.now());
		
		return obj;
	}
	
	public List<Task> findByUser(UUID idUser){
		User u = serviceUser.findById(idUser);
		return u.getTasks();
	}
}
