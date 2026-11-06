package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//Prototype scope creates new object instances for each injection and hence it should return false when compare in demoController
public class cricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice fast running for at least 20 minutes";
    }
}
