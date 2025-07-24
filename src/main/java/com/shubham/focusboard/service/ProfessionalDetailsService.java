package com.shubham.focusboard.service;

import com.shubham.focusboard.dto.ProfessionalDetailsDto;
import com.shubham.focusboard.enties.ProfessionalDetails;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface ProfessionalDetailsService {

	public ProfessionalDetails createProfessionalDetails(ProfessionalDetailsDto professionalDetails)throws ReqProcessingException;

	public ProfessionalDetailsDto toDTO(ProfessionalDetails professionalDetails)throws ReqProcessingException;

}
