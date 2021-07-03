package com.example.petstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.petstore.repository.UsersRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		com.example.petstore.models.User user = userRepository.findByEmail(email);

		if (user.getEmail().equals(email)) {
			return new User(email, user.getPassword(), user.isEnabled(), true, true, true, this.getAuthorities(user));
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(com.example.petstore.models.User user) {

		List<GrantedAuthority> authorities = new ArrayList();
		if (user.isAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return authorities;
	}
}
