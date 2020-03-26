package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User; //

@RestController //
@RequestMapping(value = "/users") //
public class UserResource {
	
	@GetMapping //
	public ResponseEntity<User> findall(){ //resposta da entidade usuário, encontre todos
		User u = new User(1L, "Maria", "maria@gmail.com", "123456", "12345"); //instanciando e inicializando dados de um usuario
		return ResponseEntity.ok().body(u); //resposta da entidade 
	}
	

}
