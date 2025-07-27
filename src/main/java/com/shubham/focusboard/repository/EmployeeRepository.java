package com.shubham.focusboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query("SELECT e FROM Employee e WHERE e.isActive = :isActive")
	public List<Employee> findAll(@Param("isActive") String isActive);


}
