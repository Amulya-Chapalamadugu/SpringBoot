package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    //SwimCoach did not have a @Component. Instead,we figured as a spring Bean using @Bean
    @Bean
    public Coach swimCoach()//swimCoach is a Bean id of SwimCoach as Bean id's starting letter should be small
    {
        return new SwimCoach();
    }
}
