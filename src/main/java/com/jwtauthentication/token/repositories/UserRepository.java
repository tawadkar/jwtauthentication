package com.jwtauthentication.token.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauthentication.token.entities.User;


public interface UserRepository extends JpaRepository<User,String> {
	
	//Implementation for below method will be automatically done by jpa
	public Optional <User> findByEmail(String email); 
   
}
