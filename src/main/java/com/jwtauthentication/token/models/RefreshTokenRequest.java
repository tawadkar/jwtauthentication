package com.jwtauthentication.token.models;

import lombok.Data;

@Data
public class RefreshTokenRequest {
	
	private String refreshToken;

}
