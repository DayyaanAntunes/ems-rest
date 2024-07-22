package com.challenge.ems.employee.service;

import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findEmployeeById(Long id);
    Employee save(EmployeeCommand employeeCommand);
    Employee update(Long id, EmployeeCommand employeeCommand);
    void delete(Long id);


}
