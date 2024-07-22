package com.challenge.ems.employee.domain.mapper;

import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapToModel(EmployeeCommand employeeCommand);
    void mapToModel(EmployeeCommand employeeCommand, @MappingTarget Employee employee);

}
