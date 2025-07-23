package com.shubham.focusboard.enties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_details_id", referencedColumnName = "id")
    private PersonalDetails personalDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professional_details_id", referencedColumnName = "id")
    private ProfessionalDetails professionalDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_details_id", referencedColumnName = "id")
    private BankDetails bankDetails;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AcademicDetail> academicDetails = new ArrayList();

    private String tenantId;
    private String isActive;
    private String createdBy;
    private String createdDate; // Format: "YYYY-MM-DD HH:mm:ss"
    private String status;
	
    @Lob
    @Column(name = "employee_photo")
    private byte[] employeePhoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public ProfessionalDetails getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(ProfessionalDetails professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public List<AcademicDetail> getAcademicDetails() {
		return academicDetails;
	}

	public void setAcademicDetails(List<AcademicDetail> academicDetails) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", personalDetails=" + personalDetails + ", professionalDetails="
				+ professionalDetails + ", bankDetails=" + bankDetails + ", academicDetails=" + academicDetails
				+ ", tenantId=" + tenantId + ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", status=" + status + ", employeePhoto=" + Arrays.toString(employeePhoto) + "]";
	}

	public Employee(Long id, PersonalDetails personalDetails, ProfessionalDetails professionalDetails,
			BankDetails bankDetails, List<AcademicDetail> academicDetails, String tenantId, String isActive,
			String createdBy, String createdDate, String status, byte[] employeePhoto) {
		super();
		this.id = id;
		this.personalDetails = personalDetails;
		this.professionalDetails = professionalDetails;
		this.bankDetails = bankDetails;
		this.academicDetails = academicDetails;
		this.tenantId = tenantId;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.status = status;
		this.employeePhoto = employeePhoto;
	}

	public Employee() {
		
	}

    
    
    
    
}
