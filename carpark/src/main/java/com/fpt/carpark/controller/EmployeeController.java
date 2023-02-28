package com.fpt.carpark.controller;

import com.fpt.carpark.exception.EmployeeNotFound;
import com.fpt.carpark.model.Employee;
import com.fpt.carpark.model.dto.EmployeeDto;
import com.fpt.carpark.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmPloyee(@RequestBody @Valid Employee employee) throws EmployeeNotFound {
        return new ResponseEntity<>(employeeService.add(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> update(@RequestBody @Valid Employee employee, @PathVariable Long id) throws EmployeeNotFound {
        return ResponseEntity.ok(employeeService.update(employee, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws EmployeeNotFound {
       return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> getEmployee(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOneEmplyee(@PathVariable Long id) throws EmployeeNotFound {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) throws EmployeeNotFound {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/list/{page}/{offset}")
    private ResponseEntity<List<EmployeeDto>> getAllPagition(@PathVariable int page, @PathVariable int offset){
        return ResponseEntity.ok(employeeService.getAllPagition(page, offset));
    }

    @GetMapping("/get/{employeeName}")
    public ResponseEntity<Employee> getByName(@PathVariable String employeeName) throws EmployeeNotFound {
        return ResponseEntity.ok(employeeService.findByName(employeeName));
    }
}
