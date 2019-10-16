package com.assessment.test.gtassessment.controller;

import com.assessment.test.gtassessment.dto.DepartmentDto;
import com.assessment.test.gtassessment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DepartmentDto request) {
        return new ResponseEntity(departmentService.createDepartment(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity getDepartmentById(@PathVariable final Long departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PutMapping(value = "/{departmentId}")
    public ResponseEntity update(@PathVariable final Long departmentId, @RequestBody DepartmentDto request) {
        request.setId(departmentId);
        return ResponseEntity.ok(departmentService.update(request));
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity delete(@PathVariable final Long departmentId) {
        return ResponseEntity.ok(departmentService.delete(departmentId));
    }
}
