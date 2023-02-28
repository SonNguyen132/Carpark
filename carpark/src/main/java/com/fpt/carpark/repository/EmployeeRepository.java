package com.fpt.carpark.repository;

import com.fpt.carpark.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    @Query(value = "select  * from employee where employee_name = ?", nativeQuery = true)
    Employee findByEmployeeName(String employeeName);
    @Query(value = "select  * from employee", nativeQuery = true)
    List<Employee> findAll();
    @Query(value = "select  * from employee where account = ?", nativeQuery = true)
    List<Employee> findByAccount(String account);
    @Query(value = "select  * from employee where employee_email = ?", nativeQuery = true)
    List<Employee> findByEmployeeEmail(String employeeEmail);
}
