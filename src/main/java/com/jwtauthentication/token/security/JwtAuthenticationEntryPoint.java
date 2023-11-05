package com.jwtauthentication.token.security;


import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	public void commence (HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException,ServletException {
    
	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	
	PrintWriter writer =  response.getWriter();
	
	writer.println("Access Denied !! " + authException.getMessage());
	
	}
}
