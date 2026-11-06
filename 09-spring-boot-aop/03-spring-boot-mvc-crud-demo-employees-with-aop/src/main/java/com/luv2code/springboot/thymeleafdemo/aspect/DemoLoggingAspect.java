package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.classfile.MethodSignature;
import java.util.logging.Logger;

@Component
@Aspect
public class DemoLoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..) )")
    private void forControllerPackage(){

    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..) )")
    private void forServicePackage(){

    }


    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..) )")
    private void forDaoPackage(){

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
    private void forAppFlow(){}


    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        //display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("======>>>> in @Before: calling Method: "+theMethod);

        //display the arguments
        Object[] args = theJoinPoint.getArgs();
        for(Object tempArgs : args){
            myLogger.info("=====>Argument: "+ tempArgs);
        }
    }

    //add @AfterReturning Advice
    @AfterReturning(pointcut= "forAppFlow()", returning ="result")
    public void afterReturning(JoinPoint theJoinPoint, Object result){

        //display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("======>>>> in @AfterReturning from Method: "+theMethod);

        //display data returned
        myLogger.info("======>>>>> The result is: "+result);
    }


}
