package com.shubham.focusboard.service;

import java.util.List;

import com.shubham.focusboard.dto.EmployeeDto;
import com.shubham.focusboard.enties.Employee;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface EmployeeService {

	public EmployeeDto createEmployee(EmployeeDto employeeReq)throws ReqProcessingException;

	public List<Employee> getAllEmployees()throws ReqProcessingException;

}
