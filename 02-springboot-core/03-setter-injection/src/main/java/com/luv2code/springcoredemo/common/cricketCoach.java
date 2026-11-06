package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;


@Component
public class cricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast running for at least 20 minutes!!!";
    }
}
