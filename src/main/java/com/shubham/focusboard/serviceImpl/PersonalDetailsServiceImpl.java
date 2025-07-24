package com.shubham.focusboard.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dto.PersonalDetailsDto;
import com.shubham.focusboard.enties.PersonalDetails;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.PersonalDetailsRepository;
import com.shubham.focusboard.service.PersonalDetailsService;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsServiceImpl.class);

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Override
    public PersonalDetails createPersonalDetail(PersonalDetailsDto dto)throws ReqProcessingException {
        try {
            PersonalDetails entity = new PersonalDetails();
            entity.setFullName(dto.getFullName());
            entity.setGender(dto.getGender());
            entity.setDateOfBirth(dto.getDateOfBirth());
            entity.setContactNumber(dto.getContactNumber());
            entity.setEmail(dto.getEmail());
            entity.setAddress(dto.getAddress());
            entity.setNationalId(dto.getNationalId());

            return personalDetailsRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error creating personal details", e);
            return null;
        }
    }

    @Override
    public PersonalDetailsDto toDTO(PersonalDetails entity) throws ReqProcessingException{
        try {
            PersonalDetailsDto dto = new PersonalDetailsDto();
            dto.setId(entity.getId());
            dto.setFullName(entity.getFullName());
            dto.setGender(entity.getGender());
            dto.setDateOfBirth(entity.getDateOfBirth());
            dto.setContactNumber(entity.getContactNumber());
            dto.setEmail(entity.getEmail());
            dto.setAddress(entity.getAddress());
            dto.setNationalId(entity.getNationalId());

            return dto;
        } catch (Exception e) {
            logger.error("Error converting PersonalDetails to DTO", e);
            return null;
        }
    }
}
