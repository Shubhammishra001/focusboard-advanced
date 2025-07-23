package com.shubham.focusboard.enties;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal_details")
public class PersonalDetails {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String fullName;

	    private String gender;

	    private LocalDate dateOfBirth;

	    private String contactNumber;

	    private String email;

	    private String address;

	    private String nationalId;

}
