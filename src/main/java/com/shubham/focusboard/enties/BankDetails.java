package com.shubham.focusboard.enties;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professional_details")
public class BankDetails {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String department;

	    private String designation;

	    private LocalDate joiningDate;

	    private String workLocation;

	    private String reportingManager;

	    private String employmentType;

}
