package com.love2code.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    //need controller method to show initial HTML Form
    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }
    //need a controller method to process html form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    //need a controller method to read form data and add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){
        //read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        //convert the data to the Upper case
        theName = theName.toUpperCase();
        //Create message
        String result = "Yo!"+theName;
        //Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

    @RequestMapping("/processFormWithRequestParam")
    public String processFromVersionThree(@RequestParam("studentName") String theName, Model model){
        //This is called Parameter binding
        //Now we can directly use theName and convert the data to the Upper case
        theName = theName.toUpperCase();
        //Create message
        String result = "Hey!"+theName;
        //Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
}
