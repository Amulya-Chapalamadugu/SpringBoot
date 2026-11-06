package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {

    private Coach myCoach;//defining a reference variable whose type is the interface-Coach
    @Autowired
    public demoController(@Qualifier("swimCoach") Coach coach){
       System.out.println("In Constructor: "+getClass().getSimpleName());
        myCoach= coach;
        //anotherCoach= theCoach;

    }
    @GetMapping("/dailyWorkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}
//what spring does internally is
//Coach coach = new cricketCoach(); creating an object for cricketCoach of type interface
//demoCollector demo = new demoCollector(coach);
//demo.setCoach(coach)This is called setter Injection