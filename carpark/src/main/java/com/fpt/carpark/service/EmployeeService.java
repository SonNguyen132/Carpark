package com.fpt.carpark.service;

import com.fpt.carpark.exception.EmployeeNotFound;
import com.fpt.carpark.model.Employee;
import com.fpt.carpark.model.dto.EmployeeDto;
import com.fpt.carpark.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //add
    public Employee add(Employee employee) throws EmployeeNotFound {
        List<Employee> employeesAccount = employeeRepository.findByAccount(employee.getAccount());
        List<Employee> employeesEmail = employeeRepository.findByEmployeeEmail(employee.getEmployeeEmail());
        if(employeesAccount.size() > 0){
            throw new EmployeeNotFound("Duplicate EmployeeAccount ");
        }else if(employeesEmail.size() > 0){
            throw new EmployeeNotFound("Duplicate EmployeeEmail ");
        }
        return employeeRepository.save(employee);
    }
    //edit
    public Employee update(Employee employee, Long id) throws EmployeeNotFound {
        if(employee != null) {
            Employee employee1 = employeeRepository.findById(id).orElseThrow(() ->
                    new EmployeeNotFound("Not found employee with employeeId = " + id));
                employee1.setEmployeeAddress(employee.getEmployeeAddress());
                employee1.setEmployeeName(employee.getEmployeeName());
                employee1.setEmployeeEmail(employee.getEmployeeEmail());
                employee1.setEmployeePhone(employee.getEmployeePhone());
                employee1.setAccount(employee.getAccount());
                employee1.setEmployeeBirthDate(employee.getEmployeeBirthDate());
                employee1.setDepartment(employee.getDepartment());
                employee1.setSex(employee.getSex());
                employee1.setPassword(employee.getPassword());
            return employeeRepository.save(employee1);
        }
        throw new EmployeeNotFound("Not found Employee");
    }

    //delete
    public String delete(Long id) throws EmployeeNotFound {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFound("Not found employee with employeeId = " + id + "to delete"));
        employeeRepository.delete(employee);
        return "Delete Success";
    }
    //get list employee
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees){
            employeeDtos.add(new EmployeeDto(employee));
        }
        return employeeDtos;
    }

    public Employee getEmployee( Long id) throws EmployeeNotFound{
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFound("Not found employee with employeeId = " + id ));
        return employee;
    }

    public Employee findById(Long id) throws EmployeeNotFound {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFound("Not found employee with employeeId = " + id));
        return employee;
    }

    // dung pagition phan trang hien thi list
    public List<EmployeeDto> getAllPagition(int page, int offset){
        Pageable pageable = PageRequest.of(page, offset);
        Page<Employee> pagedResult = employeeRepository.findAll(pageable);
        List<Employee> employees = new ArrayList<>();
        List<EmployeeDto> listDto = new ArrayList<>();
        if(pagedResult != null && pagedResult.hasContent()) {
            employees = pagedResult.getContent();// tra ve 1 list employee
        }
        for (Employee employee : employees){
            listDto.add(new EmployeeDto(employee));
        }
        return listDto;
    }
    //Search By Name
    public Employee findByName(String employeeName) throws EmployeeNotFound {
        Employee employee = employeeRepository.findByEmployeeName(employeeName);
        if (employee != null){
            return employee;
        }else {
            throw new EmployeeNotFound("Not Found Employee with employeeName = " + employeeName);
        }
    }
}
