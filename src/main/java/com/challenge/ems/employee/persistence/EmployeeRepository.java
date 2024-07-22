package com.challenge.ems.employee.persistence;

import com.challenge.ems.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
