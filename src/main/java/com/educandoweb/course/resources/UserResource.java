package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService; //

@RestController //
@RequestMapping(value = "/users") //
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping //
	public ResponseEntity<List<User>> findAll(){ //resposta da entidade com lista
		List<User> list = service.findAll(); //lista de usuários vai receber a referencia "service" do tipo UserService chamando método buscar todos
		return ResponseEntity.ok().body(list); //retornar resposta da entidade usuário, encontre todos 
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){

		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) { 
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
		/** DESCRIÇÃO
		 * 1 - Resposta de entidade usuário recebe como parâmetro um Objeto em jSON
		 * 2 - A referencia "obj" recebe a referencia "service" chamando o método para inserir o próprio JSON
		 * 3 - Chamamos um tipo URI que vai receber um gerador de componente uri da requisição atual do caminho/chave id
		 * 4 - Que vai construir e expandir o id do objeto para o formato URI
		 * 5 - Vai retornar uma resposta de entidade com o código de request 201(criado) se referindo ao corpo do objeto obj
		 * 
		 */
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}  
	
}
