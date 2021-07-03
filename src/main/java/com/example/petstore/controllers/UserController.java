package com.example.petstore.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.petstore.models.User;
import com.example.petstore.repository.UsersRepository;
import com.example.petstore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UsersRepository repository;
	@Autowired
	private UserService service;

//	@Autowired
//	public UserController(UsersRepository repository) {
//		this.repository = repository;
//		this.service = new UserService();
//	}

	@GetMapping
	public ResponseEntity<?> get() {
		return new ResponseEntity<>(this.repository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") long id) {
		Optional<User> user = this.repository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item não encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putUser(@PathVariable(value = "id") long id, @RequestBody User newUser) {
		Optional<User> userFound = this.repository.findById(id);
		if (userFound.isPresent() && userFound.get().getId() == id) {
			User user = userFound.get();
			user.setValues(newUser);
			this.repository.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Item não encontrado", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody User user) {
//		User savedUser = this.repository.save(user);
		User savedUser = service.create(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

}
