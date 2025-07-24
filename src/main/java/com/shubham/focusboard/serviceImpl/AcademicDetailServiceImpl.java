package com.shubham.focusboard.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dao.EmployeeDao;
import com.shubham.focusboard.dto.AcademicDetailsDto;
import com.shubham.focusboard.enties.AcademicDetail;
import com.shubham.focusboard.enties.Employee;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.AcademicDetailRepository;
import com.shubham.focusboard.repository.EmployeeRepository;
import com.shubham.focusboard.service.AcademicDetailService;
@Service
public class AcademicDetailServiceImpl implements AcademicDetailService {

    private static final Logger logger = LoggerFactory.getLogger(AcademicDetailServiceImpl.class);

    @Autowired
    private AcademicDetailRepository academicDetailRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AcademicDetail createAcademicDetail(AcademicDetailsDto dto) {
        try {
            if (dto.getEmployeeId() == null) {
                logger.warn("Employee ID is missing in AcademicDetailsDto");
                return null;
            }

            Employee employee = employeeRepository.findById(dto.getEmployeeId()).get();
            if (employee == null) {
                logger.warn("Employee not found for ID: {}", dto.getEmployeeId());
                return null;
            }

            AcademicDetail academicDetail = new AcademicDetail();
            academicDetail.setDegree(dto.getDegree());
            academicDetail.setInstitution(dto.getInstitution());
            academicDetail.setPassingYear(dto.getPassingYear());
            academicDetail.setGrade(dto.getGrade());
            academicDetail.setEmployee(employee);

            AcademicDetail saved = academicDetailRepository.save(academicDetail);
            logger.info("Academic detail saved for employee ID {}", dto.getEmployeeId());
            return saved;

        } catch (Exception e) {
            logger.error("Failed to create AcademicDetail", e);
            return null;
        }
    }

    @Override
    public AcademicDetailsDto toDTO(AcademicDetail academicDetail) {
        try {
            if (academicDetail == null) {
                logger.warn("AcademicDetail entity is null");
                return null;
            }

            AcademicDetailsDto dto = new AcademicDetailsDto();
            dto.setId(academicDetail.getId());
            dto.setDegree(academicDetail.getDegree());
            dto.setInstitution(academicDetail.getInstitution());
            dto.setPassingYear(academicDetail.getPassingYear());
            dto.setGrade(academicDetail.getGrade());

            if (academicDetail.getEmployee() != null) {
                dto.setEmployeeId(academicDetail.getEmployee().getId());
            }

            return dto;

        } catch (Exception e) {
            logger.error("Error converting AcademicDetail to DTO", e);
            return null;
        }
    }
}