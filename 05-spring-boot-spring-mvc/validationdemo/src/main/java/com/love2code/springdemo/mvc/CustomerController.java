package com.love2code.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    //Add an @initBinder to convert trim input Strings
    //removes leading and trailing whitespaces
    //resolve issue for our validation

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        //true means trim empty string to null
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/")
    public String showForm(Model theModel){
        theModel.addAttribute("customer",new Customer());

        return "customer-form";
    }
    @PostMapping("/processForm")
    public String processForm(
            @Valid @ModelAttribute("customer") Customer theCustomer,
            BindingResult theBindingResult) {

        //@Valid tells spring mvc to perform validation on the given form data
        //@ModelAttribute says read the value from the form attribute name in the html form
        //BindingResults holds the results of the validation

        System.out.println("Last Name: |" + theCustomer.getLastName() +" |");
        System.out.println("Binding Results: " + theBindingResult.toString()) ;
        System.out.println("\n\n\n\n");

        if(theBindingResult.hasErrors())
            return "customer-form";
        else
            return "confirmation-form";

    }
}
