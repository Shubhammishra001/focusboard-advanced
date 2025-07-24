package com.shubham.focusboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
