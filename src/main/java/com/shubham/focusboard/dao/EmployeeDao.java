package com.shubham.focusboard.dao;

import com.shubham.focusboard.enties.Employee;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface EmployeeDao {

	public Employee saveEmployee(Employee savedEmployee)throws ReqProcessingException;

	public Employee findById(Long employeeId)throws ReqProcessingException;

}
