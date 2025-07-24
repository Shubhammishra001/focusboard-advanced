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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getNationalId() {
			return nationalId;
		}

		public void setNationalId(String nationalId) {
			this.nationalId = nationalId;
		}

		@Override
		public String toString() {
			return "PersonalDetails [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth="
					+ dateOfBirth + ", contactNumber=" + contactNumber + ", email=" + email + ", address=" + address
					+ ", nationalId=" + nationalId + "]";
		}

		public PersonalDetails(Long id, String fullName, String gender, LocalDate dateOfBirth, String contactNumber,
				String email, String address, String nationalId) {
			super();
			this.id = id;
			this.fullName = fullName;
			this.gender = gender;
			this.dateOfBirth = dateOfBirth;
			this.contactNumber = contactNumber;
			this.email = email;
			this.address = address;
			this.nationalId = nationalId;
		}

		public PersonalDetails() {
			super();
		}
	    
	    

}
