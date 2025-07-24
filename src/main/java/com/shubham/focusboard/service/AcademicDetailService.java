package com.shubham.focusboard.service;

import com.shubham.focusboard.dto.AcademicDetailsDto;
import com.shubham.focusboard.enties.AcademicDetail;
import com.shubham.focusboard.exception.ReqProcessingException;

public interface AcademicDetailService {

    public AcademicDetail createAcademicDetail(AcademicDetailsDto dto)throws ReqProcessingException;
    public AcademicDetailsDto toDTO(AcademicDetail academicDetail)throws ReqProcessingException;

}
