/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.petstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.petstore.models.User;

/**
 *
 * @author reneilson
 */
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	User findByEmail(@Param("email") String email);

	User findByUsername(@Param("username") String username);
}
