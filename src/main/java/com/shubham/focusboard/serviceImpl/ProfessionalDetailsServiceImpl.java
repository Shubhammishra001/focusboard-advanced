package com.shubham.focusboard.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dto.ProfessionalDetailsDto;
import com.shubham.focusboard.enties.ProfessionalDetails;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.ProfessionalDetailsRepository;
import com.shubham.focusboard.service.ProfessionalDetailsService;

@Service
public class ProfessionalDetailsServiceImpl implements ProfessionalDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessionalDetailsServiceImpl.class);

    @Autowired
    private ProfessionalDetailsRepository professionalDetailsRepository;

    @Override
    public ProfessionalDetails createProfessionalDetails(ProfessionalDetailsDto dto)throws ReqProcessingException {
        try {
            ProfessionalDetails entity = new ProfessionalDetails();
            entity.setDesignation(dto.getDesignation());
            entity.setDepartment(dto.getDepartment());
            entity.setEmployeeId(dto.getEmployeeId());
            entity.setJoiningDate(dto.getJoiningDate());

            return professionalDetailsRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error creating professional details", e);
            return null;
        }
    }

    @Override
    public ProfessionalDetailsDto toDTO(ProfessionalDetails entity)throws ReqProcessingException {
        try {
            ProfessionalDetailsDto dto = new ProfessionalDetailsDto();
            dto.setId(entity.getId());
            dto.setDesignation(entity.getDesignation());
            dto.setDepartment(entity.getDepartment());
            dto.setEmployeeId(entity.getEmployeeId());
            dto.setJoiningDate(entity.getJoiningDate());

            return dto;
        } catch (Exception e) {
            logger.error("Error converting ProfessionalDetails to DTO", e);
            return null;
        }
    }
}