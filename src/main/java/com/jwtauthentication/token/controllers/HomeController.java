package com.jwtauthentication.token.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthentication.token.entities.*;
import com.jwtauthentication.token.services.*;
@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	public UserService UserService;
	
	@GetMapping("/users")
	//http://localhost:8081/home/users
	public List<User> getUsers() {
		
		System.out.println("getting users");
		
		return UserService.getUsers();
		
	}
	
	@GetMapping("/getCurrentUser")
	public String getLoggedInUser(Principal principal) {
			
			return principal.getName();
		}
	}
	
