package com.love2code.springboot.cruddemo.service;

import com.love2code.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int empId);
    Employee save(Employee emp);
    //Employee update(Employee emp);
    void deleteById(int empId );

}
