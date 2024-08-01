package com.challenge.ems.employee.service;

import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.mapper.EmployeeMapper;
import com.challenge.ems.employee.domain.model.Employee;
import com.challenge.ems.employee.persistence.EmployeeRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        try{
            return employeeRepository.findAll();
        }catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
            return employeeRepository.findById(id).orElseThrow(
                    ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee.not-found"));
    }

    @Override
    public Employee save(EmployeeCommand employeeCommand) {
        try {
            Employee employee = employeeMapper.mapToModel(employeeCommand);
            return employeeRepository.save(employee);
        } catch (ConstraintViolationException e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "employee.email-already-exists");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
        }
    }

    @Override
    public Employee update(Long id, EmployeeCommand employeeCommand) {
        try {
            Employee employee = findEmployeeById(id);
            employeeMapper.mapToModel(employeeCommand, employee);
            return employeeRepository.save(employee);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "employee.email-already-exists");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
        }
    }

    @Override
    public void delete(Long id) {
        try{
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee.not-found");
            }
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee.not-found");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "employee.invalid-argument");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal");
        }
    }


}
