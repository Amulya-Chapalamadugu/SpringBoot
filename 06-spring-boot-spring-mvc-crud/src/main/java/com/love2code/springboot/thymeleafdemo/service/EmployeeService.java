package com.love2code.springboot.thymeleafdemo.service;

import com.love2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int empId);
    Employee save(Employee emp);
    //Employee update(Employee emp);
    void deleteById(int empId );

}
