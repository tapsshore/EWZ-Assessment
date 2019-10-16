package com.assessment.test.gtassessment.controller;

import com.assessment.test.gtassessment.dto.EmployeeDto;
import com.assessment.test.gtassessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }
}
