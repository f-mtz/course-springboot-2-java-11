package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
/**serve para interceptar as exceções para que esse objeto/classe possa executar o tratamento (é tipo um catch)*/
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest resquest){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
		/**
		1 - public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest resquest){
			
			A linha acima declara a interface ResponseEntity que recebe os parâmetros mostrados
			
		2 -	String error = "Resource not found"; //mensagem de erro personalizada
		3 - HttpStatus status = HttpStatus.NOT_FOUND; //vai lançar o status 404 ao invés do 500 do servidor
		4 -	StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), resquest.getRequestURI());
		5 -	return ResponseEntity.status(status).body(err); //o método vai retornar: um response chamando o status
		 e chamando o corpo do erro*/
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest resquest){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
