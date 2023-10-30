package com.jwtauthentication.token.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		//UserDetails is a Spring Security Interface and User implements the interface
		UserDetails user = User.builder().username("test1").password(passwordEncoder().encode("test123")).roles("ADMIN").build();
		UserDetails user1 = User.builder().username("test2").password(passwordEncoder().encode("test1234")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user,user1);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	

}
