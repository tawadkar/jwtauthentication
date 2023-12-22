package com.jwtauthentication.token.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthentication.token.entities.RefreshToken;
import com.jwtauthentication.token.entities.User;
import com.jwtauthentication.token.models.JwtRequest;
import com.jwtauthentication.token.models.JwtResponse;
import com.jwtauthentication.token.models.RefreshTokenRequest;
import com.jwtauthentication.token.security.JwtHelper;
import com.jwtauthentication.token.services.RefreshTokenService;
import com.jwtauthentication.token.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/login")
		
		public ResponseEntity<JwtResponse> login (@RequestBody JwtRequest request){
			
			this.doAuthenticate(request.getEmail(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        String token = this.helper.generateToken(userDetails);
	        
	        RefreshToken refreshToken  = refreshTokenService.createRefreshToken(userDetails.getUsername());

	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .refreshToken(refreshToken.getRefreshToken())
	                .username(userDetails.getUsername()).build();
	        return new ResponseEntity<>(response, HttpStatus.OK);
		}
	
	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
	
	@PostMapping("/refresh")
	public JwtResponse  refreshJwtToken(@RequestBody RefreshTokenRequest request ){
		RefreshToken refreshToken  = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
		
		User user = refreshToken.getUser();
		
		String token  = this.helper.generateToken(user);
		
		return JwtResponse.builder().refreshToken(refreshToken.getRefreshToken())
				.jwtToken(token)
				.username(user.getEmail())
				.build();
	}
	
	 @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }

	
	  
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

		
	}
	
	


