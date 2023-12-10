package com.jwtauthentication.token.services;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtauthentication.token.entities.RefreshToken;
import com.jwtauthentication.token.entities.User;
import com.jwtauthentication.token.repositories.RefreshTokenRepository;
import com.jwtauthentication.token.repositories.UserRepository;

@Service
public class RefreshTokenService {
	
	public long refreshTokenValidity = 5*60*60*10000;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public RefreshToken createRefreshToken(String userName) {
		
		User user  =  userRepository.findByName(userName).get();
		RefreshToken refreshToken =  user.getRefreshToken();
		
		if(refreshToken==null) {
			 refreshToken = RefreshToken.builder()
					.refreshToken(UUID.randomUUID().toString())
					.expiry(Instant.now().plusMillis(refreshTokenValidity))
					.user(user)
					.build();
			
		}else {
			refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
		

		//Save refreshToken to Db
		refreshTokenRepository.save(refreshToken);
		
		return refreshToken;
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) {
		
		RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(()-> new RuntimeException("Given Token does exist in Db"));
		
		if(refreshTokenOb.getExpiry().compareTo(Instant.now())<0) {
			//Delete expired token from DB
			refreshTokenRepository.delete(refreshTokenOb);
			throw new RuntimeException("Refresh Token Expried");
		}
		return refreshTokenOb;
	}


}
