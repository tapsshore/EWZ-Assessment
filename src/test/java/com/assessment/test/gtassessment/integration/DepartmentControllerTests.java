package com.assessment.test.gtassessment.integration;


import com.assessment.test.gtassessment.domain.Department;
import com.assessment.test.gtassessment.dto.DepartmentDto;
import com.assessment.test.gtassessment.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentControllerTests.class)
public class DepartmentControllerTests {

    @MockBean
    DepartmentService departmentService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldReturnCreatedDepartment() throws Exception {

        DepartmentDto request = new DepartmentDto();
        request.setName("Tapiwanashe");

        Department department = new Department();
        department.setName(request.getName());

//        when(departmentService.createDepartment(any(DepartmentDto.class))).thenReturn(department);

        mockMvc.perform(post("/departments")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()));

    }

}