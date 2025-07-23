package com.shubham.focusboard.enties;

import java.util.ArrayList;
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

}
