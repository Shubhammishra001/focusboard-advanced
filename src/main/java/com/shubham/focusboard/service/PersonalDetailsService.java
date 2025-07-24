package com.shubham.focusboard.service;

import com.shubham.focusboard.dto.PersonalDetailsDto;
import com.shubham.focusboard.enties.PersonalDetails;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface PersonalDetailsService {

	public PersonalDetails createPersonalDetail(PersonalDetailsDto personalDetails)throws ReqProcessingException;

	public PersonalDetailsDto toDTO(PersonalDetails personalDetails)throws ReqProcessingException;

}
