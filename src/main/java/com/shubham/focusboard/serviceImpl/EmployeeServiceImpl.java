package com.shubham.focusboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.controller.EmployeeController;
import com.shubham.focusboard.dao.EmployeeDao;
import com.shubham.focusboard.dto.AcademicDetailsDto;
import com.shubham.focusboard.dto.EmployeeDto;
import com.shubham.focusboard.enties.AcademicDetail;
import com.shubham.focusboard.enties.BankDetails;
import com.shubham.focusboard.enties.Employee;
import com.shubham.focusboard.enties.PersonalDetails;
import com.shubham.focusboard.enties.ProfessionalDetails;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.EmployeeRepository;
import com.shubham.focusboard.service.AcademicDetailService;
import com.shubham.focusboard.service.BankDetailsService;
import com.shubham.focusboard.service.EmployeeService;
import com.shubham.focusboard.service.PersonalDetailsService;
import com.shubham.focusboard.service.ProfessionalDetailsService;
import com.shubham.focusboard.util.ProdConts;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	 private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
    private EmployeeRepository employeeDao;

    @Autowired
    private PersonalDetailsService personalDetailsService;

    @Autowired
    private ProfessionalDetailsService professionalDetailsService;

    @Autowired
    private BankDetailsService bankDetailsService;

    @Autowired
    private AcademicDetailService academicDetailService;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) throws ReqProcessingException {
        try {
            logger.info("Creating employee for: {}", dto.getPersonalDetails().getFullName());

            // 1. Save personal details
            PersonalDetails personal = personalDetailsService.createPersonalDetail(dto.getPersonalDetails());

            // 2. Save professional details
            ProfessionalDetails professional = professionalDetailsService.createProfessionalDetails(dto.getProfessionalDetails());

            // 3. Save bank details
            BankDetails bank = bankDetailsService.createBankDetails(dto.getBankDetails());

            // 4. Build base employee entity
            Employee employee = new Employee();
            employee.setPersonalDetails(personal);
            employee.setProfessionalDetails(professional);
            employee.setBankDetails(bank);
            employee.setTenantId(dto.getTenantId());
            employee.setIsActive(dto.getIsActive());
            employee.setCreatedBy(dto.getCreatedBy());
            employee.setCreatedDate(dto.getCreatedDate());
            employee.setStatus(dto.getStatus());
            employee.setEmployeePhoto(dto.getEmployeePhoto());

            // 5. Save employee first to generate ID
            Employee savedEmployee = employeeDao.save(employee);
            Long empId = savedEmployee.getId();

            // 6. Create and save academic details
            List<AcademicDetail> academicEntities = dto.getAcademicDetails().stream()
                    .map(detailDTO -> {
                        detailDTO.setEmployeeId(empId);
                        return academicDetailService.createAcademicDetail(detailDTO);
                    })
                    .collect(Collectors.toList()); // ✅ mutable list

            // 7. Fix orphanRemoval issue: update collection safely
            if (savedEmployee.getAcademicDetails() != null) {
                savedEmployee.getAcademicDetails().clear(); // ✅ Hibernate can track changes
                savedEmployee.getAcademicDetails().addAll(academicEntities);
            } else {
                savedEmployee.setAcademicDetails(academicEntities);
            }

            // 8. Save updated employee with academic details
            savedEmployee = employeeDao.save(savedEmployee);

            logger.info("Employee created with ID: {}", savedEmployee.getId());

            return mapToDTO(savedEmployee);

        } catch (Exception e) {
            logger.error("Error creating employee", e);
            throw new ReqProcessingException("Failed to create employee", e);
        }
    }

    private EmployeeDto mapToDTO(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setTenantId(employee.getTenantId());
        dto.setIsActive(employee.getIsActive());
        dto.setCreatedBy(employee.getCreatedBy());
        dto.setCreatedDate(employee.getCreatedDate());
        dto.setStatus(employee.getStatus());
        dto.setEmployeePhoto(employee.getEmployeePhoto());

        dto.setPersonalDetails(personalDetailsService.toDTO(employee.getPersonalDetails()));
        dto.setProfessionalDetails(professionalDetailsService.toDTO(employee.getProfessionalDetails()));
        dto.setBankDetails(bankDetailsService.toDTO(employee.getBankDetails()));

        if (employee.getAcademicDetails() != null) {
            List<AcademicDetailsDto> academicDetailDTOs = employee.getAcademicDetails().stream()
                    .map(academicDetailService::toDTO)
                    .collect(Collectors.toList());
            dto.setAcademicDetails(academicDetailDTOs);
        }

        return dto;
    }

	@Override
	public List<Employee> getAllEmployees() throws ReqProcessingException {
		// TODO Auto-generated method stub
		return employeeDao.findAll(ProdConts.TRUE);
	}
}









