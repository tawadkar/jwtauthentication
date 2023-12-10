package com.jwtauthentication.token.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauthentication.token.entities.User;
import com.jwtauthentication.token.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	User user =	userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		return user;
	}
	
	

}
