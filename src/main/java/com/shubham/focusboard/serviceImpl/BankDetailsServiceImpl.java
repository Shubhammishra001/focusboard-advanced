package com.shubham.focusboard.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.dto.BankDetailsDto;
import com.shubham.focusboard.enties.BankDetails;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.BankDetailsRepository;
import com.shubham.focusboard.service.BankDetailsService;
@Service
public class BankDetailsServiceImpl implements BankDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(BankDetailsServiceImpl.class);

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetails createBankDetails(BankDetailsDto dto) throws ReqProcessingException{
        try {
            BankDetails bank = new BankDetails();
            bank.setBankName(dto.getBankName());
            bank.setAccountNumber(dto.getAccountNumber());
            bank.setIfscCode(dto.getIfscCode());
            bank.setBranchName(dto.getBranchName());
            bank.setTaxId(dto.getTaxId());

            return bankDetailsRepository.save(bank);
        } catch (Exception e) {
            logger.error("Error creating bank details", e);
            return null;
        }
    }

    @Override
    public BankDetailsDto toDTO(BankDetails entity)throws ReqProcessingException {
        try {
            BankDetailsDto dto = new BankDetailsDto();
            dto.setId(entity.getId());
            dto.setBankName(entity.getBankName());
            dto.setAccountNumber(entity.getAccountNumber());
            dto.setIfscCode(entity.getIfscCode());
            dto.setBranchName(entity.getBranchName());
            dto.setTaxId(entity.getTaxId());

            return dto;
        } catch (Exception e) {
            logger.error("Error converting BankDetails to DTO", e);
            return null;
        }
    }
}
