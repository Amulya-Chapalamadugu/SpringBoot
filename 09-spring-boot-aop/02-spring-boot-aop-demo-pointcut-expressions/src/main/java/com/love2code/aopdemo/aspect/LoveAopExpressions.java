package com.love2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoveAopExpressions {

    //Pointcut exp declaration and using it for multiple times
    @Pointcut("execution(*  com.love2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    //create a pointcut for getters and setters
    @Pointcut("execution(* com.love2code.aopdemo.dao.*.get*(..))")
    public void getter(){}

    @Pointcut("execution(* com.love2code.aopdemo.dao.*.set*(..))")
    public void setter(){}

    //Combining pointcut declarations
    //pointcut to include package and exclude getters and setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void noGetterAndSetter(){}

}
