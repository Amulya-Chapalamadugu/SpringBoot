package com.love2code.springboot.cruddemo.dao;


import com.love2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    //define field for entity manager
   private EntityManager entityManager;

   @Autowired
   public EmployeeDAOImpl(EntityManager theEntityManager){
       entityManager = theEntityManager;

   }

    @Override
    public List<Employee> findAll(){
       //create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee",Employee.class);
        //Execute the query and get result List
        List<Employee> employees = theQuery.getResultList();
        //return the result list
        return employees;
    }

    @Override
    public Employee findById(int empId) {
       Employee emp = entityManager.find(Employee.class,empId);
       return emp;
    }

    @Override
    public Employee save(Employee emp) {
       Employee theEmp =  entityManager.merge(emp);
       //merge method will save or update depending on the id of the employee
        //if id == 0, it will save or insert else update
       return theEmp;
       //here we have to return theEmp coz it has the updated id from the database in case of insert
    }

    @Override
    public void deleteById(int empId) {
        Employee em = entityManager.find(Employee.class, empId);
        entityManager.remove(em);
    }
}
