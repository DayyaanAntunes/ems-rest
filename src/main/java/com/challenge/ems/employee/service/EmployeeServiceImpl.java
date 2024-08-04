package com.challenge.ems.employee.service;

import com.challenge.ems.configuration.exception.ResponseException;
import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.mapper.EmployeeMapper;
import com.challenge.ems.employee.domain.model.Employee;
import com.challenge.ems.employee.persistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
        }catch (Exception e){
            throw new ResponseException("internal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
            return employeeRepository.findById(id).orElseThrow(
                    ()-> new ResponseException("employee.not-found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Employee save(EmployeeCommand employeeCommand) {
            Employee employee = employeeMapper.mapToModel(employeeCommand);
            checkForUniqueConstraints(employee);
            return saveOrUpdateEmployee(employee);
    }

    @Override
    public Employee update(Long id, EmployeeCommand employeeCommand) {
            Employee employee = findEmployeeById(id);
            employeeMapper.mapToModel(employeeCommand, employee);
            return saveOrUpdateEmployee(employee);

    }

    private void checkForUniqueConstraints(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new ResponseException("employee.email-already-exists", HttpStatus.CONFLICT);
        }
        if (employeeRepository.existsByIdNumber(employee.getIdNumber())) {
            throw new ResponseException("employee.id_number-already-exists", HttpStatus.CONFLICT);
        }
        if (employeeRepository.existsByNuit(employee.getNuit())) {
            throw new ResponseException("employee.nuit-already-exists", HttpStatus.CONFLICT);
        }
        if (employeeRepository.existsBySocialSecurityNumber(employee.getSocialSecurityNumber())) {
            throw new ResponseException("employee.social_security_number-already-exists", HttpStatus.CONFLICT);
        }
    }

    private Employee saveOrUpdateEmployee(Employee employee){
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            throw new ResponseException("internal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            if (!employeeRepository.existsById(id)) {
                throw new ResponseException("employee.not-found", HttpStatus.NOT_FOUND);
            }
            employeeRepository.deleteById(id);
        } catch  (ResponseException e){
            throw e;
        } catch (Exception e) {
            throw new ResponseException("internal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
