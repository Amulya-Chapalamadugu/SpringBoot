package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    //create class for injections
    //define a private field for the dependency
    private Coach myCoach;//defining a reference variable whose type is the interface-Coach
    private Coach anotherCoach;
    //create a setter for dependency injection
    @Autowired
    public demoController(@Qualifier("cricketCoach") Coach coach,
                          @Qualifier("cricketCoach") Coach theCoach){ //we can also use a constructor here i.e, a constructor injection
       System.out.println("In Constructor: "+getClass().getSimpleName());
        myCoach= coach;
        anotherCoach= theCoach;

    }
    @GetMapping("/dailyWorkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing Beans Checking whether myCoach == anotherCoach , "+(myCoach==anotherCoach);
        //if this comparison returns true, then it is Singleton otherwise false
    }
}
//what spring does internally is
//Coach coach = new cricketCoach(); creating an object for cricketCoach of type interface
//demoCollector demo = new demoCollector(coach);
//demo.setCoach(coach)This is called setter Injection