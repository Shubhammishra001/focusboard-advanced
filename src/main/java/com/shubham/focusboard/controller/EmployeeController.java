package com.shubham.focusboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.focusboard.dto.EmployeeDto;
import com.shubham.focusboard.enties.Employee;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.service.AuditLogService;
import com.shubham.focusboard.service.EmployeeService;
import com.shubham.focusboard.util.SecurityUtil;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	 private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	 
	 @Autowired private EmployeeService employeeService;
 	 @Autowired private SecurityUtil securityUtil;
	 @Autowired private AuditLogService auditLogService;

	 @PostMapping("/create")
	    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeReq) throws ReqProcessingException {
	        try {
	        	System.err.println(" test create ");
	            User loggedInUser = securityUtil.getLoggedInUser();
	            String loginId = loggedInUser.getLoginId();
	            String tenantId = loggedInUser.getTenantId();
	            logger.info("API: createEmployee with loginId {}", loginId);

	            if (tenantId != null) {
	                EmployeeDto savedEmployee = employeeService.createEmployee(employeeReq); // or with loginId, tenantId

	                if (savedEmployee != null && savedEmployee.getId() != null) {
	                    logger.info("API: createEmployee successful");
	                    
	                    auditLogService.logAction(savedEmployee.getId(), "CREATED", loginId);
		    			
	                    return ResponseEntity.ok(savedEmployee);
	                }
	            }
	            return ResponseEntity.badRequest().body("Invalid employee creation request");
	        } catch (Exception e) {
	            logger.error("Error creating employee", e);
	            return ResponseEntity.internalServerError().body("Internal Server Error");
	        }
	    }
	    @GetMapping
	    public ResponseEntity<List<Employee>> getAll()throws ReqProcessingException {
	        return ResponseEntity.ok(employeeService.getAllEmployees());
	    }

	}
	 

	


