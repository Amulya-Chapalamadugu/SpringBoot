package com.love2code.springboot.thymeleafdemo.dao;

import com.love2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   //Adding a method to sort by lastname
    public List<Employee> findAllByOrderByLastNameAsc();
    //Spring data jpa will parse the method name and looks for a specific format and pattern
    //creates appropriate query BTS
}
