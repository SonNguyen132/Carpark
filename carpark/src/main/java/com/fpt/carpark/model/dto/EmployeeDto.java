package com.fpt.carpark.model.dto;

import com.fpt.carpark.model.Employee;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeDto {
    private Long id;
    private String account;
    private String department;
    private String employeeAddress;
    private LocalDate employeeBirthDate;
    private String employeeEmail;
    private String employeeName;
    private String employeePhone;
    private String sex;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.account = employee.getAccount();
        this.department = employee.getDepartment();
        this.employeeAddress = employee.getEmployeeAddress();
        this.employeeBirthDate = employee.getEmployeeBirthDate();
        this.employeeEmail = employee.getEmployeeEmail();
        this.employeeName = employee.getEmployeeName();
        this.employeePhone = employee.getEmployeePhone();
        this.sex = employee.getSex();
    }
}
