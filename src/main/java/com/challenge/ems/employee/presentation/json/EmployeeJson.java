package com.challenge.ems.employee.presentation.json;

import com.challenge.ems.employee.domain.enums.*;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeJson {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String nationality;
    private String address;
    private Gender gender;
    private Availability availability;
    private EmploymentStatus employmentStatus;
    private ModalityType modalityType;
    private String idNumber;
    private String nuit;
    private List<Role> roles;
    private Double salary;
    private String email;
    private String phoneNumber;
    private String socialSecurityNumber;
    private LocalDate startedAt;
    private LocalDate terminatedAt;
    private Byte contractYears;
}
