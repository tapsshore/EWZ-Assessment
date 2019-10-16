package com.assessment.test.gtassessment.service;

import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    ApiResponse createEmployee(EmployeeDto employeeDto);

}
