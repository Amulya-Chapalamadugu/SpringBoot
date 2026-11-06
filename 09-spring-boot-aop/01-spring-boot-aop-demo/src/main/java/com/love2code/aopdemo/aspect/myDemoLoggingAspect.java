package com.love2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect//It is a java class that has a collection of related advices like- @Before, @After
public class myDemoLoggingAspect {


    //this is where we add all of our related advices for logging

    //let's start with @Before advice

    //@Before("execution(public void addAccount())")//this means run the below code
    // before executing addAccount() and is called Pointcut expression


    //we only wanna match on AccountDAO
    //@Before("execution(public void com.love2code.aopdemo.dao.AccountDAO.addAccount())")
    //fully qualified class name

    //@Before("execution(* add*(com.love2code.aopdemo.Account))")


    //@Before("execution(* add*(com.love2code.aopdemo.Account, ..))")

    //@Before("execution(* add*(..))")


    @Before("execution(*  com.love2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice(){

        System.out.println("\n========>>>> Executing @Before advice on method");

    }

}
