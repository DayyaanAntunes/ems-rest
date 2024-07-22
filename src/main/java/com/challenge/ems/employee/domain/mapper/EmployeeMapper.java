package com.challenge.ems.employee.domain.mapper;

import com.challenge.ems.employee.domain.commands.EmployeeCommand;
import com.challenge.ems.employee.domain.model.Employee;
import com.challenge.ems.employee.presentation.json.EmployeeJson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    Employee mapToModel(EmployeeCommand employeeCommand);
    void mapToModel(EmployeeCommand employeeCommand, @MappingTarget Employee employee);
    List<EmployeeJson> mapEmployeeListToJson(List<Employee> employees);
    EmployeeJson mapToJson(Employee employee);

}
