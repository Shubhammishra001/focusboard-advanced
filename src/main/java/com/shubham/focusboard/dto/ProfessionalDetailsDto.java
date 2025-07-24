package com.shubham.focusboard.dto;

public class ProfessionalDetailsDto {
	 private Long id;
	    private String designation;
	    private String department;
	    private String employeeId;
	    private String joiningDate;

	    private String workLocation;
	    private String reportingManager;
	    private String employmentType;
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
	    
	    
}