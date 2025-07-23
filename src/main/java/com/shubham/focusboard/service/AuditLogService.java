package com.shubham.focusboard.service;

import com.shubham.focusboard.exception.ReqProcessingException;

public interface AuditLogService {
	public void logAction(Long taskId, String action, String username)throws ReqProcessingException;

}
