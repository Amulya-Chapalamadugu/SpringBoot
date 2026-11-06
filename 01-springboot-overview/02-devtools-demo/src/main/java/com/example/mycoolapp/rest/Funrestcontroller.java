package com.example.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Funrestcontroller {
    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }
    @GetMapping("/worksout")
    public String checkWorkout(){
        return "New endpoint is working";
    }

}
