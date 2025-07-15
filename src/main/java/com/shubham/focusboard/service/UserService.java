package com.shubham.focusboard.service;

import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;

@Service
public interface UserService {
	    User registerUser(User user) throws ReqProcessingException;
	    User authenticateUser(String loginId, String password) throws ReqProcessingException;
	    User findUserByLoginId(String loginId) throws ReqProcessingException;

}
