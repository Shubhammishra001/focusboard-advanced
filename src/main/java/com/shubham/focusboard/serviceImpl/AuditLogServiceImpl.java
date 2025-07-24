package com.shubham.focusboard.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.AuditLog;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.AuditLogRepository;
import com.shubham.focusboard.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService{
    @Autowired
    private AuditLogRepository auditRepo;

    public void logAction(Long taskId, String action, String username)throws ReqProcessingException {
        AuditLog log = new AuditLog();
        log.setTaskId(taskId);
        log.setAction(action);
        log.setPerformedBy(username);
        log.setTimestamp(LocalDateTime.now());
        auditRepo.save(log);
    }
}
