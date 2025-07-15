package com.shubham.focusboard.service;

import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.User;
@Service
public interface JWTService {
	   String generateToken(User user);
	    String extractLoginId(String token);
	    boolean validateToken(String token, User user);

}
