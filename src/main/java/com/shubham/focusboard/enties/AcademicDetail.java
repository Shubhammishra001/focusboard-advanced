package com.shubham.focusboard.enties;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AcademicDetail {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String degree;

	    private String institution;

	    private int passingYear;

	    private String grade;

	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private Employee employee;

}
