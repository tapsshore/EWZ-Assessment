package com.assessment.test.gtassessment.service;

import com.assessment.test.gtassessment.domain.Employee;
import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.EmployeeDto;
import com.assessment.test.gtassessment.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;

public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(mapper.map(employeeDto, Employee.class));
        ApiResponse response = new ApiResponse();
        EmployeeDto employee1 = mapper.map(employee, EmployeeDto.class);
        response.setData(employee1);
        response.setMessage("Employee created successfully");
        response.setSuccess(true);
        return response;
    }
}
