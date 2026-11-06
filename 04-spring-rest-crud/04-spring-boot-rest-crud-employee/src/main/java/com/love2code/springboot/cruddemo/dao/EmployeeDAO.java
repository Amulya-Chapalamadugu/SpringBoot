package com.love2code.springboot.cruddemo.dao;

import com.love2code.springboot.cruddemo.entity.Employee;
import com.love2code.springboot.cruddemo.service.EmployeeService;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int empId);
    Employee save(Employee emp);
    //Employee update(Employee emp);
    void deleteById(int empId );

}
