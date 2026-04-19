package com.joaogabriel.todolist.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaogabriel.todolist.service.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(System.currentTimeMillis(), 
				status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleInvalidBody(HttpMessageNotReadableException ex,  HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(System.currentTimeMillis(), 
				status.value(), "Requisição inválida", "Valor inválido no corpo da requisição.", request.getRequestURI());
		
        return ResponseEntity.status(status).body(error);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> accessDenied(AccessDeniedException e, HttpServletRequest request) {
	    HttpStatus status = HttpStatus.FORBIDDEN;
	    StandardError error = new StandardError(System.currentTimeMillis(),
	            status.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
	    return ResponseEntity.status(status).body(error);
	}
}
