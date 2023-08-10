package com.jwtauthentication.token.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/users")
	public String getUsers() {
		
		System.out.println("getting users");
		
		return "Users";
		
	}
	
	
	

}
