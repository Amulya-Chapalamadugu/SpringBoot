package com.love2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class myApiAnalyticsAspect {

    @Before("com.love2code.aopdemo.aspect.LoveAopExpressions.noGetterAndSetter()")
    public void performApiAnalytics(){
        System.out.println("\n========>>>> Performing API Analytics");
    }

}
