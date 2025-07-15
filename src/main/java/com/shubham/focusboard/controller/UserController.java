package com.shubham.focusboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.service.JWTService;
import com.shubham.focusboard.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	 private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

	    @Autowired  UserService userService;
	    
	    @Autowired  JWTService jwtService;

	  
	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        try {
	            logger.info("Register request for loginId: {}", user.getLoginId());
	            User savedUser = userService.registerUser(user);
	            savedUser.setPassword(null);  // Hide password in response
	            return ResponseEntity.ok(savedUser);
	        } catch (Exception  e) {
	            ((org.slf4j.Logger) logger).error("Error in registerUser: {}", e.getMessage(), e);
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
	        try {
	            String loginId = loginRequest.get("loginId");
	            String password = loginRequest.get("password");

	            logger.info("Login request for loginId: {}", loginId);

	            User user = userService.authenticateUser(loginId, password);

	            String token = jwtService.generateToken(user);

	            Map<String, String> response = new HashMap<>();
	            response.put("token", token);
	            return ResponseEntity.ok(response);

	        } catch (Exception e) {
	            ((org.slf4j.Logger) logger).error("Unexpected error in loginUser", e);
	            return ResponseEntity.internalServerError().body("Something went wrong!");
	        }
	    }
	}
