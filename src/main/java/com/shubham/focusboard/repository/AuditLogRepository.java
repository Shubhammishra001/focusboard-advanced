package com.shubham.focusboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> { }
