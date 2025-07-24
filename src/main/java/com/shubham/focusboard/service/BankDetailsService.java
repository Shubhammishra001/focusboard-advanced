package com.shubham.focusboard.service;

import com.shubham.focusboard.dto.BankDetailsDto;
import com.shubham.focusboard.enties.BankDetails;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface BankDetailsService {

	public BankDetails createBankDetails(BankDetailsDto bankDetails)throws ReqProcessingException;

	public BankDetailsDto toDTO(BankDetails bankDetails)throws ReqProcessingException;

}
