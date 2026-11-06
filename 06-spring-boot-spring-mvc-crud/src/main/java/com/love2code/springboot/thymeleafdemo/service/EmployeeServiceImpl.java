package com.love2code.springboot.thymeleafdemo.service;

import com.love2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.love2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //define field for EmployeeDAO and inject it in the constructor
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId){

        Optional<Employee> result = employeeRepository.findById(theId);
        Employee emp = null;
        if(result.isPresent())
            emp = result.get();
        else//we didn't find the employee of given id
            throw new RuntimeException("Employee NOT FOUND with id - "+theId);
        return emp;
    }

    @Override
    @Transactional
    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    @Transactional
    public void deleteById(int empId) {
        employeeRepository.deleteById(empId);
    }
}
