package com.challenge.ems.employee.presentation.controller;

import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.mapper.EmployeeMapper;
import com.challenge.ems.employee.presentation.json.EmployeeJson;
import com.challenge.ems.employee.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    
    private EmployeeServiceImpl employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeJson>> getAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.mapEmployeeListToJson(employeeService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeJson> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.mapToJson(employeeService.findEmployeeById(id)));
    }

    @PostMapping
    public ResponseEntity<EmployeeJson> createEmployee(@Valid @RequestBody EmployeeCommand employeeCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.mapToJson(employeeService.save(employeeCommand)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeJson> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeCommand employeeCommand) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.mapToJson(employeeService.update(id, employeeCommand)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
