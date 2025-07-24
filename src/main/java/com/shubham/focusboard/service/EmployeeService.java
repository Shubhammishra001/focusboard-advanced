package com.shubham.focusboard.service;

import com.shubham.focusboard.dto.EmployeeDto;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface EmployeeService {

	public EmployeeDto createEmployee(EmployeeDto employeeReq)throws ReqProcessingException;

}
