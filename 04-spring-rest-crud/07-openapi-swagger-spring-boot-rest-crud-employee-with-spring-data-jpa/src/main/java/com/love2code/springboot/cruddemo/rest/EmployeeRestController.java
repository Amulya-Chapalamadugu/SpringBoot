package com.love2code.springboot.cruddemo.rest;

import com.love2code.springboot.cruddemo.entity.Employee;
import com.love2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;


//We create rest controller to use DAO
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

   /* //inject Employee DAO
    private EmployeeDAO employeeDAO;

    THIS CODE IS USED WHEN WE ARE NOT USING SERVICE LAYER

    public EmployeeRestController(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }
    //expose /employees and return the list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }*/

    //WHEN USING SERVICE LAYER

    //CREATE FIELD FOR SERVICE LAYER
    private EmployeeService employeeService;
    private JsonMapper jsonMapper;//for PATCH mapping

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeservice, JsonMapper thejsonMapper){
        employeeService = theEmployeeservice;
        jsonMapper = thejsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //Code for Getting Single Employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee emp =  employeeService.findById(employeeId);
        if(emp == null){
            throw new RuntimeException("Employee ID not found" +employeeId);
        }
        return emp;
    }

    //Code for Adding a new Employee - Mapping for Post /employees

    @PostMapping("/employees")//You have to implement this in postman in jason format
    public Employee addEmployee(@RequestBody Employee theEmpl){


        //Also just in case if they pass an id in JSON... set employeeId to 0
        //this is to force a save of new item... instead of update

        theEmpl.setEmployeeId(0);
        //if you are using Integer instead of int then you have to set employeeId to NULL
        Employee dbEmpl = employeeService.save(theEmpl);
        return dbEmpl;//dbEmpl has updated id from the database
    }

    //For update,we use put method

    //Add mapping for PUT  /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee empl){

       Employee dbEmployee =  employeeService.save(empl);
       return dbEmployee;
    }

    //add mapping for PATCH
   @PatchMapping("/employees/{emplId}")
    public Employee patchEmployee(@PathVariable int emplId, @RequestBody Map<String, Object> patchPayload){
        //JSON data is passed in as a map of key, value pairs in the request body
        Employee tempEmp = employeeService.findById(emplId);
        if(tempEmp == null){
            throw new RuntimeException("EmployeeId NOT FOUND  "+emplId);
        }

        //Also throw exception for id.
       // The "id" field is not allowed in the payload,Since we cannot change a primary key
       if(patchPayload.containsKey("employeeId")){
           throw new RuntimeException("Employee Id is not allowed in the request body "+emplId);
       }
       //Applies patch on given employee
       Employee patchedEmployee = jsonMapper.updateValue(tempEmp, patchPayload);
       Employee dbEmployee = employeeService.save(patchedEmployee);
       return  dbEmployee;
   }

    //add mapping for deleting employee by id- DELETE/employee/employeeId
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee not found  "+ employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Employee Deleted Successfully";
    }


}
