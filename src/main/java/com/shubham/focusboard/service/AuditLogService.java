package com.shubham.focusboard.service;

import org.springframework.stereotype.Service;

import com.shubham.focusboard.exception.ReqProcessingException;
@Service
public interface AuditLogService {
	public void logAction(Long taskId, String action, String username)throws ReqProcessingException;

}
