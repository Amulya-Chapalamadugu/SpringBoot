package com.love2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class myCloudLogAspect {

    @Before("com.love2code.aopdemo.aspect.LoveAopExpressions.noGetterAndSetter()")
    public void logToCloudAsync(){
        System.out.println("\n========>>>> Logging to cloud in async mode");
    }
}
