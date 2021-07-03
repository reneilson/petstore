package com.example.petstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.petstore.models.User;
import com.example.petstore.repository.UsersRepository;

@Service
public class UserService {
	@Autowired
	private UsersRepository userRepository;

	public User create(User user) {
		String generatedSecuredPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(generatedSecuredPasswordHash);
		return userRepository.save(user);
	}

	public Optional<User> get(Long id) {
		return userRepository.findById(id);
	}

	public User getByEmail(String email) {
		System.out.println(email);
		User user = userRepository.findByEmail(email);
		System.out.print(user);
		return user;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}
}
