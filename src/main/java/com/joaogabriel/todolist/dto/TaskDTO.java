package com.joaogabriel.todolist.dto;

import java.io.Serializable;
import java.util.UUID;

import com.joaogabriel.todolist.domain.Task;
import com.joaogabriel.todolist.domain.enums.PriorityEnum;

public class TaskDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private PriorityEnum priority;
	private UUID idUser;
	
	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}

	public TaskDTO(Task task) {
		super();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.priority = task.getPriority();
		this.idUser = task.getIdUser().getId();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public PriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(PriorityEnum priority) {
		this.priority = priority;
	}

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}
}
