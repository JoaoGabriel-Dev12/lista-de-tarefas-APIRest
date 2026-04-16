package com.joaogabriel.todolist.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.joaogabriel.todolist.domain.enums.PriorityEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "task_tb")
public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String tile;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private PriorityEnum priority;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(UUID id, String tile, String description, PriorityEnum priority) {
		super();
		this.id = id;
		this.tile = tile;
		this.description = description;
		this.priority = priority;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}
}
