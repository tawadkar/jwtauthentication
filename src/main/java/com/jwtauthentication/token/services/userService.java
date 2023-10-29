package com.jwtauthentication.token.services;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.jwtauthentication.token.models.User;

@Service
public class userService {
	
	public List<User> store = new ArrayList<>();
	
	
	public userService() {
		
		store.add(new User(UUID.randomUUID().toString(),"Test1","test1@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test2","test2@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test3","test3@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(),"Test4","test4@gmail.com"));
	}
	
	public List<User> getUsers(){
		
		return this.store;
	}

}
