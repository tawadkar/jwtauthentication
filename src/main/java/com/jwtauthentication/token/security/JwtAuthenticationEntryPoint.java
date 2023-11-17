package com.jwtauthentication.token.security;


import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Create Class JWTAthenticationEntryPoint that implement AuthenticationEntryPoint. 
 * Method of this class is called whenever an exception is thrown due to unauthenticated user trying to access the resource that required authentication.
 * */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Override
	
	public void commence (HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException,ServletException {
    
	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	
	PrintWriter writer =  response.getWriter();
	
	writer.println("Access Denied !! " + authException.getMessage());
	
	}
}
