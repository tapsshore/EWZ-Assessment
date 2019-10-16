package com.assessment.test.gtassessment.unit;

import com.assessment.test.gtassessment.domain.Department;
import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.DepartmentDto;
import com.assessment.test.gtassessment.exceptions.ApiException;
import com.assessment.test.gtassessment.repository.DepartmentRepository;
import com.assessment.test.gtassessment.service.DepartmentService;
import com.assessment.test.gtassessment.service.DepartmentServiceImpl;
import com.assessment.test.gtassessment.utils.i18.api.MessageService;
import com.assessment.test.gtassessment.utils.i18.enums.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DepartmentServiceTest {

    @Mock
    ModelMapper mapper;


    private DepartmentService departmentService;
    @Mock
    private DepartmentRepository departmentRepository;
    private String name;
    private Long id;
    private DepartmentDto departmentDto;
    private Department department;
    ApiResponse response;


    @Before
    public void setUp() {
        departmentService = new DepartmentServiceImpl(departmentRepository, mapper);
        id = 1L;
        name = "Audit";

        departmentDto = new DepartmentDto();
        departmentDto.setId(id);
        departmentDto.setName("Audit");

        department = new Department();
        department.setId(1L);
        department.setName("Department name");
        department.setStatus(Status.ACTIVE);

        response = new ApiResponse();
        response.setSuccess(true);
        response.setMessage("Department created successfully");
        response.setData(department);

    }

    @Test
    public void createDepartmentShouldPassForAValidDepartment() {
        final String expectedMessage = "Department created successfully";

        when(departmentRepository.save(any())).thenReturn(department);

        when(mapper.map(department, DepartmentDto.class)).thenReturn(departmentDto);
        when(mapper.map(departmentDto, Department.class)).thenReturn(department);

        final ApiResponse apiResponse = departmentService.createDepartment(departmentDto);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(expectedMessage, apiResponse.getMessage());

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test(expected = ApiException.class)
    public void createDepartmentShouldFailForAnExistingDepartment() {
        when(departmentRepository.findByName(anyString())).thenReturn(Optional.of(department));
        departmentService.createDepartment(departmentDto);
    }
    @Test
    public void findDepartmentByIdShouldFailForAnInvalidDepartment(){
        when(departmentRepository.findByIdAndStatus(anyLong(), any(Status.class))).thenReturn(Optional.empty());
        departmentService.getDepartmentById(id);
    }
//
//    @Test
//    public void findAllDepartmentsShouldFailIfTheRequestIsNull(){
//        when(departmentRepository.findAllByStatus(Status.ACTIVE)).thenReturn(any());
//        departmentService.getAll();
//    }


}