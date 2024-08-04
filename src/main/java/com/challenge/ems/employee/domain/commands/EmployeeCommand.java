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

    @Pattern(regexp = "^\\d{13}$", message = "{employee.invalid-id-number}")
    @NotBlank(message = "{employee.missing-args}")
    private String idNumber;

    @Pattern(regexp = "^\\d{9}$", message = "{employee.invalid-nuit}")
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
    @Pattern(regexp = "^\\d{9}$", message = "{employee.invalid-phone-number}")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{9}$", message = "{employee.invalid-social-security-number}")
    @NotBlank(message = "{employee.missing-args}")
    private String socialSecurityNumber;

    @NotNull(message = "{employee.missing-args}")
    private LocalDate startedAt;

    @Positive(message = "{employee.invalid-contract-years}")
    private Byte contractYears;
}
