package com.love2code.springboot.cruddemo.service;

import com.love2code.springboot.cruddemo.dao.EmployeeDAO;
import com.love2code.springboot.cruddemo.dao.EmployeeDAOImpl;
import com.love2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //define field for EmployeeDAO and inject it in the constructor
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theId){
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional
    public Employee save(Employee emp) {
        return employeeDAO.save(emp);
    }

    @Override
    @Transactional
    public void deleteById(int empId) {
        employeeDAO.deleteById(empId);
    }
}
