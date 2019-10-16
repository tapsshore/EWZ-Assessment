package com.assessment.test.gtassessment.repository;

import com.assessment.test.gtassessment.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
