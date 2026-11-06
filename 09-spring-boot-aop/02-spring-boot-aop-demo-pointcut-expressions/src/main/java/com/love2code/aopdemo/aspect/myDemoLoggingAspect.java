package com.love2code.aopdemo.aspect;

import com.love2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect//It is a java class that has a collection of related advices like- @Before, @After
@Order(3)
public class myDemoLoggingAspect {


    @Around("execution(* com.love2code.aopdemo.service.*.getFortune(..)), ")
    public Object aroundGetFortune(ProceedingJoinPoint theProceed) throws Throwable{

        //print out method we are using on
        String method = theProceed.getSignature().toShortString();
        System.out.println("\n======>>>>Executing @Around on method: "+method);

        //get begin timestamp
        long start = System.currentTimeMillis();
        //we can also use----
        //long start = system.nanoTime();

        //now, lets execute the method
        //this method will throw an exception based on the value of tripwire
        Object result = null;

        try{
            result = theProceed.proceed();//theProceed will handle the target method and
        }
        catch (Exception exp){
            //log the exception
            System.out.println("The Exception is: "+exp.getMessage());
            result= "Major accident!! But No worries!!";
            //to rethrow the exception, delete the above result= line and
            //add "throw exp"
        }

        // proceed() will execute the target method
        long stop = System.currentTimeMillis();

        //compute duration and display it
        long duration = stop - start;
        System.out.println("==========>>>>Duration: "+duration/1000.0+" seconds");

        return result;

    }

    @After("execution(* com.love2code.aopdemo.dao.AccountDAO.findAccounts(..)), ")
    public void afterFinally(JoinPoint theJoinPoint){

        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>>>Executing @After(finally) on method: "+method);

    }


    @AfterThrowing(pointcut="execution(* com.love2code.aopdemo.dao.AccountDAO.findAccounts(..))",
                    throwing = "theExec")
    public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExec){
        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>>>Executing @AfterThrowing on method: "+method);

        //log the exception
        System.out.println("The Exception is: "+theExec);

    }


    //Add a new method for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.love2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result){

        //printout which method we're advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>>>Executing @AfterReturning on method: "+method);

        //print out the results of the method call
        System.out.println("\n=======>>>> Result is: "+ result);

        //let's post-process the data... lets modify it

        //convert the account names to Uppercase

        convertAccountNamesToUpperCase(result);
        System.out.println("\n=======>>>> Result is: "+ result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        //loop through the accounts
        for(Account tempAcc : result){

            //get the uppercase of the names

            String theUpper = tempAcc.getName().toUpperCase();

            //update the names on the account
            tempAcc.setName(theUpper);

        }


    }


    @Before("com.love2code.aopdemo.aspect.LoveAopExpressions.noGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        //Join point is to display the method signature

        System.out.println("\n========>>>> Executing @Before advice on method");

        //display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method Signature: " + methodSig);

        //display the method arguments

        Object[] arg = theJoinPoint.getArgs();
        for (Object tempArg : arg) {

            System.out.println("The arguments are: " + tempArg);

            if(tempArg instanceof Account){
                //downcast and print account specific stuff

                Account theAccount = (Account) tempArg;
                System.out.println("The Account Name: "+ theAccount.getName());
                System.out.println("The Account Level: "+ theAccount.getLevel());

            }
        }
    }

}
