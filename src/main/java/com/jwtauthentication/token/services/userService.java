package com.jwtauthentication.token.services;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jwtauthentication.token.entities.User;
import com.jwtauthentication.token.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private  UserRepository userRepository;
	
	//public List<User> store = new ArrayList<>();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public UserService() {
		
	/*	store.add(new User(UUID.randomUUID().toString(),"Test1","test1@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test2","test2@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test3","test3@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test4","test4@gmail.com"));*/
	}
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {	
		user.setUserId(UUID.randomUUID().toString());	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

}
