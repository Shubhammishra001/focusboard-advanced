package com.shubham.focusboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shubham.focusboard.enties.BankDetails;
@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long>{

}
