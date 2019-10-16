package com.assessment.test.gtassessment.service;

import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.DepartmentDto;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    ApiResponse createDepartment(DepartmentDto request);

    ApiResponse getAll();

    ApiResponse getDepartmentById(Long departmentId);

    ApiResponse update(DepartmentDto request);

    ApiResponse delete(Long id);

}
