package com.shubham.focusboard.enties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "academic_details")
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

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		@Override
		public String toString() {
			return "AcademicDetail [id=" + id + ", degree=" + degree + ", institution=" + institution + ", passingYear="
					+ passingYear + ", grade=" + grade + ", employee=" + employee + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

		public AcademicDetail(Long id, String degree, String institution, int passingYear, String grade,
				Employee employee) {
			super();
			this.id = id;
			this.degree = degree;
			this.institution = institution;
			this.passingYear = passingYear;
			this.grade = grade;
			this.employee = employee;
		}

		public AcademicDetail() {
			super();
		}

		
		
}
