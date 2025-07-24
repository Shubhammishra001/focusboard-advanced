package com.shubham.focusboard.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "professional_details")
public class ProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private String department;
    private String employeeId;
    private String joiningDate;

    // New fields to add
    private String workLocation;
    private String reportingManager;
    private String employmentType;

    // Constructors

    public ProfessionalDetails() {}

    public ProfessionalDetails(Long id, String designation, String department, String employeeId, String joiningDate,
                               String workLocation, String reportingManager, String employmentType) {
        this.id = id;
        this.designation = designation;
        this.department = department;
        this.employeeId = employeeId;
        this.joiningDate = joiningDate;
        this.workLocation = workLocation;
        this.reportingManager = reportingManager;
        this.employmentType = employmentType;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

    // Getters & Setters ...
}