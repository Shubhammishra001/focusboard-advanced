package com.shubham.focusboard.dto;

import java.util.List;

public class EmployeeDto {
	private Long id;
    private PersonalDetailsDto personalDetails;
    private ProfessionalDetailsDto professionalDetails;
    private BankDetailsDto bankDetails;
    private List<AcademicDetailsDto> academicDetails;
    private String tenantId;
    private String isActive;
    private String createdBy;
    private String createdDate;
    private String status;
    private byte[] employeePhoto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonalDetailsDto getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDetailsDto personalDetails) {
		this.personalDetails = personalDetails;
	}
	public ProfessionalDetailsDto getProfessionalDetails() {
		return professionalDetails;
	}
	public void setProfessionalDetails(ProfessionalDetailsDto professionalDetails) {
		this.professionalDetails = professionalDetails;
	}
	public BankDetailsDto getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(BankDetailsDto bankDetails) {
		this.bankDetails = bankDetails;
	}
	public List<AcademicDetailsDto> getAcademicDetails() {
		return academicDetails;
	}
	public void setAcademicDetails(List<AcademicDetailsDto> academicDetails) {
		this.academicDetails = academicDetails;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public byte[] getEmployeePhoto() {
		return employeePhoto;
	}
	public void setEmployeePhoto(byte[] employeePhoto) {
		this.employeePhoto = employeePhoto;
	}

}
