package com.jwtauthentication.token.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtauthentication.token.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String>{
	//can add custom methods
	
	Optional<RefreshToken> findByRefreshToken(String token);

}
