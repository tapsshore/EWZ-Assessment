package com.assessment.test.gtassessment.repository;

import com.assessment.test.gtassessment.domain.Department;
import com.assessment.test.gtassessment.utils.i18.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAllByStatus(Status active);

    Optional<Department> findByIdAndStatus(Long departmentId, Status active);

    Optional<Department> findByName(String name);

}
