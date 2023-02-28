package com.fpt.carpark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "employee")
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long id;
    @Column(name = "account")
    @NotNull(message = "account shouldn't be null")
    @Pattern(regexp = "([a-zA-Z]){2,}(([0-9]{0,})){2,}", message = "Not valid")
    private String account;
    @Column(name = "department")
    @NotNull(message = "department shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid department")
    private String department;
    @Column(name = "employeeAddress")
    @NotNull(message = "employeeAddress shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid department")
    private String employeeAddress;
    @Column(name = "employeeBirthDate")
    @NotNull(message = "employeeBirthDate shouldn't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate employeeBirthDate;
    @Column(name = "employeeEmail")
    @Email(message = "Invalid email")
    private String employeeEmail;
    @Column(name = "employeeName")
    @NotNull(message = "Name shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{2,20}$", message = "Invalid Name")
    @Size(min = 2, message = "length is greater or equal 2")
    private String employeeName;
    @Column(name = "employeePhone")
    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$", message = "Invalid mobile phone")
    //0333578223, 05, 07,08,09
    private String employeePhone;
    @Column(name = "password")
    @NotNull(message = "password shouldn't be null")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$", message = "Phải chứa ít nhất 1 ký tự số từ 0 – 9 " +
            "+ ít nhất 1 ký tự chữ thường + ít nhất 1 ký tự chữ hoa")
    @Size(min = 6, message = "length is greater or equal 6")
    private String password;
    @Column(name = "sex")
    @NotEmpty
    @Pattern(regexp = "^Nam$|^Nu$", message = "allowed input: Nam or Nu")
    private String sex;
}
