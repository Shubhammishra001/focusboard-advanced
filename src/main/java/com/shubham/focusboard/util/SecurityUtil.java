package com.shubham.focusboard.util;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.shubham.focusboard.enties.User;

@Component
public class SecurityUtil {

	/**
     * Returns the currently logged-in User object from SecurityContext.
     */
	public User getLoggedInUser() {
		Authentication authentication=(Authentication) SecurityContextHolder.getContext().getAuthentication();
		if(authentication==null || !authentication.isAuthenticated()) {
			return null;
		}
		return (User)authentication.getPrincipal();
	}
	public String getLoginId() {
		User user=getLoggedInUser();
		if(Objects.nonNull(user) && user.getId()!=null) {
		    String logInId=user.getLoginId();
		    if(logInId!=null) {
		    	return logInId;
		    }
		}
		return null;
	}
	public String getTenantId() {
		User user=getLoggedInUser();
		return user!=null?user.getTenantId():null;
	}
	
}
