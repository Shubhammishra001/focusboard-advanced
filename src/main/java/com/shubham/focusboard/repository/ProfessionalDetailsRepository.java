package com.shubham.focusboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.ProfessionalDetails;
@Repository
public interface ProfessionalDetailsRepository extends JpaRepository<ProfessionalDetails, Long> {

}
