package com.luv2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    //create class for injections
    //define a private field for the dependency
    private final Coach myCoach;//defining a reference variable whose type is the interface-Coach

    //create a constructor for dependency injection
    @Autowired
    public demoController(Coach coach){
        myCoach= coach;

    }
    @GetMapping("/dailyWorkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
//whta spring does internally is
//Coach coach = new cricketCoach(); creating an object for cricketCoach of type interface
//demoCollector demo = new demoCollector(coach); This is called constructor Injection