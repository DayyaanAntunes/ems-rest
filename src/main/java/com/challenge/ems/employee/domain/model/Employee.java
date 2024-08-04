package com.challenge.ems.employee.domain.model;

import com.challenge.ems.employee.domain.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String nationality;
    private String address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    @Enumerated(EnumType.STRING)
    private ModalityType modalityType;
    private String idNumber;
    private String nuit;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "role")
    private List<Role> roles;

    private Double salary;
    private String email;
    private String phoneNumber;
    private String socialSecurityNumber;
    private LocalDate startedAt;
    private LocalDate terminatedAt;
    private Byte contractYears;
}
