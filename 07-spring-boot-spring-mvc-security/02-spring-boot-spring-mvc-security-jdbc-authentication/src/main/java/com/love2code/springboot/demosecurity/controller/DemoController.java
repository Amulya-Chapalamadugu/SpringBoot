package com.love2code.springboot.demosecurity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome(){

        return "home";
    }

    //Add request mapping for /leaders
    @GetMapping("/leaders")
    public String showLeaders(){

        return "leaders";
    }

    //Add request mapping for /systems
    @GetMapping("/systems")
    public String showSystems(){

        return "systems";
    }

}
