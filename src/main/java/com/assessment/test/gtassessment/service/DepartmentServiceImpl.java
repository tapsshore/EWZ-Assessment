package com.assessment.test.gtassessment.service;

import com.assessment.test.gtassessment.domain.Department;
import com.assessment.test.gtassessment.dto.ApiResponse;
import com.assessment.test.gtassessment.dto.DepartmentDto;
import com.assessment.test.gtassessment.exceptions.ApiException;
import com.assessment.test.gtassessment.repository.DepartmentRepository;
import com.assessment.test.gtassessment.utils.i18.enums.Status;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    final private ModelMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse createDepartment(DepartmentDto request) {
        Optional<Department> searchedDepartment = departmentRepository.findByName(request.getName());
        if (searchedDepartment.isPresent()){
            throw new ApiException("Department already exists");
        }
        Department department = departmentRepository.save(mapper.map(request, Department.class));
        ApiResponse response = new ApiResponse();
        response.setData(mapper.map(department, DepartmentDto.class));
        response.setMessage("Department created successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public ApiResponse getAll() {
        List<Department> departments = departmentRepository.findAllByStatus(Status.ACTIVE);
        List<DepartmentDto> dtos = new ArrayList<>();
        ApiResponse response = new ApiResponse();

        for (Department department :
                departments) {
            DepartmentDto departmentDto = mapper.map(department, DepartmentDto.class);
            dtos.add(departmentDto);
        }
        response.setData(dtos);
        response.setMessage("Departments retrieved successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public ApiResponse getDepartmentById(Long departmentId) {
        Optional<Department> department = departmentRepository.findByIdAndStatus(departmentId, Status.ACTIVE);
        ApiResponse response = new ApiResponse();

        if (department.isPresent()) {

            response.setData(mapper.map(department.get(), DepartmentDto.class));
            response.setMessage("Department retrieved successfully");
            response.setSuccess(true);
            return response;
        } else {
            response.setMessage("Department not found");
            response.setSuccess(false);
            return response;
        }
    }

    @Override
    public ApiResponse update(DepartmentDto request) {
        ApiResponse response = new ApiResponse();
        Department savedDepartment;
        Optional<Department> department = departmentRepository.findByIdAndStatus(request.getId(), Status.ACTIVE);
        if (department.isPresent()) {
            Department retrievedDepartment = department.get();

            if (request.getName() != null) {
                retrievedDepartment.setName(request.getName());
            }
            savedDepartment = departmentRepository.save(retrievedDepartment);

            response.setData(mapper.map(savedDepartment, DepartmentDto.class));
            response.setMessage("Department updated successfully");
            response.setSuccess(true);
            return response;
        } else {
            response.setMessage("Department does not exist");
            response.setSuccess(false);
            return response;
        }
    }

    @Override
    public ApiResponse delete(Long id) {
        ApiResponse response = new ApiResponse();
        Department deleted;
        Optional<Department> department = departmentRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (department.isPresent()) {
            Department retrievedDepartment = department.get();
            retrievedDepartment.setStatus(Status.DELETED);
            departmentRepository.save(retrievedDepartment);

            response.setMessage("Department deleted successfully");
            response.setSuccess(true);
            return response;
        } else {
            response.setMessage("Could not delete Department because it does not exist");
            response.setSuccess(false);
            return response;
        }
    }

}
