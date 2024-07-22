package com.challenge.ems.employee.domain.commands;

import com.challenge.ems.employee.domain.enums.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeCommand {

    @NotBlank(message = "{employee.missing-args}")
    private String name;

    @NotBlank(message = "{employee.missing-args}")
    private String surname;

    @NotNull(message = "{employee.missing-args}")
    private LocalDate birthDate;

    @NotBlank(message = "{employee.missing-args}")
    private String nationality;

    @NotBlank(message = "{employee.missing-args}")
    private String address;

    private Gender gender;

    @NotNull(message = "{employee.missing-args}")
    private Availability availability;

    @NotNull(message = "{employee.missing-args}")
    private EmploymentStatus employmentStatus;

    @NotNull(message = "{employee.missing-args}")
    private ModalityType modalityType;

    @NotBlank(message = "{employee.missing-args}")
    private String idNumber;

    @NotBlank(message = "{employee.missing-args}")
    private String nuit;

    @NotEmpty(message = "{employee.missing-args}")
    private List<Role> roles;

    @NotNull(message = "{employee.missing-args}")
    @Positive(message = "{employee.invalid-salary}")
    private Double salary;

    @NotNull(message = "{employee.missing-args}")
    @Email(message = "{employee.invalid-email}")
    private String email;

    @NotBlank(message = "{employee.missing-args}")
    private String phoneNumber;

    @NotBlank(message = "{employee.missing-args}")
    private String socialSecurityNumber;

    @NotNull(message = "{employee.missing-args}")
    private LocalDate startedAt;

    @Positive(message = "Employee contract years must be positive")
    private Byte contractYears;
}
