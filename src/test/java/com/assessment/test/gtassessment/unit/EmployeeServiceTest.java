package com.assessment.test.gtassessment.unit;

import com.assessment.test.gtassessment.domain.Employee;
import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.EmployeeDto;
import com.assessment.test.gtassessment.repository.EmployeeRepository;
import com.assessment.test.gtassessment.service.EmployeeService;
import com.assessment.test.gtassessment.service.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @Mock
    ModelMapper mapper;

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    private Long id;
    private String firstName;
    private String lastName;
    private String employeeNumber;
    private String nationalId;
    private ApiResponse apiResponse;
    private EmployeeDto employeeDto;
    private Employee employee;



    @Before
    public void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository, mapper);
        id = 1L;
        firstName = "Tapiwanashe";
        lastName = "Shoshore";
        employeeNumber = "EWZ123";
        nationalId = "22-271198-Y-83";


        employeeDto =  new EmployeeDto();
        employeeDto.setId(id);
        employeeDto.setFirstName("Tapiwanashe");
        employeeDto.setLastName("Shoshore");
        employeeDto.setEmployeeNumber("EWZ123");
        employeeDto.setNationalId("22-271198-Y-83");

        employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("First Name");
        employee.setLastName("Last Name");
        employee.setEmployeeNumber("Employee Number");
        employee.setNationalId("National ID");


        apiResponse =  new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Employee created successfully");
        apiResponse.setData(employee);
    }
    @Test
    public void createEmployeeShouldPassForAValidEmployee(){
        final String expectedMessage = "Employee created successfully";

        when(employeeRepository.save(any())).thenReturn(employee);

        when(mapper.map(employee, EmployeeDto.class)).thenReturn(employeeDto);
        when(mapper.map(employeeDto, Employee.class)).thenReturn(employee);

        final ApiResponse apiResponse = employeeService.createEmployee(employeeDto);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(expectedMessage, apiResponse.getMessage());
    }
}
