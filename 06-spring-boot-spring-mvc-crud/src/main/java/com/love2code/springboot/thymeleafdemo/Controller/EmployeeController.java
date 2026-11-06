package com.love2code.springboot.thymeleafdemo.Controller;

import com.love2code.springboot.thymeleafdemo.entity.Employee;
import com.love2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String employeeList(Model theModel){
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        //create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("emplId") int theId, Model theModel){

        //get the employee from the service
        Employee theEmpl = employeeService.findById(theId);

        //set the employee in the model to prepopulate the form to update
        theModel.addAttribute("employee", theEmpl);

        //send over to our form
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the employee
        employeeService.save(theEmployee);

        //use a redirection to prevent duplicate submission
        return "redirect:/employees/list";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("emplId") int theId, Model theModel){
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}
