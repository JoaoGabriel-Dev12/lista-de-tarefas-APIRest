package com.joaogabriel.todolist.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joaogabriel.todolist.domain.enums.PriorityEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private OffsetDateTime createdAt;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User idUser;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(UUID id, String tile, String description, PriorityEnum priority, OffsetDateTime createdAt, User idUser) {
		super();
		this.id = id;
		this.tile = tile;
		this.description = description;
		this.priority = priority;
		this.createdAt = createdAt;
		this.idUser = idUser;
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
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
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
