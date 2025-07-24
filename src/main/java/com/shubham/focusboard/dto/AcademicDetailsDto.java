package com.shubham.focusboard.dto;

public class AcademicDetailsDto {
	    private Long id;
	    private String degree;
	    private String institution;
	    private int passingYear;
	    private String grade;
	    private Long employeeId;
	    
	    
		public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getInstitution() {
			return institution;
		}
		public void setInstitution(String institution) {
			this.institution = institution;
		}
		public int getPassingYear() {
			return passingYear;
		}
		public void setPassingYear(int passingYear) {
			this.passingYear = passingYear;
		}
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
	

}
