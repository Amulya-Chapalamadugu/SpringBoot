package com.love2code.aopdemo;

import com.love2code.aopdemo.dao.AccountDAO;
import com.love2code.aopdemo.dao.MembershipDAO;
import com.love2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
                                               MembershipDAO theMembershipDAO,
                                               TrafficFortuneService theTraffic){

        return runner->{
            //demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
            //demoTheAfterReturningAdvice(theAccountDAO);
           // demoTheAfterThrowingAdvice(theAccountDAO);
            //demoTheAfterAdvice(theAccountDAO);
            //demoTheAroundAdvice(theTraffic);
            demoTheAroundAdviceHandleException(theTraffic);


        };
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService theTraffic) {


        System.out.println("\nMain Program: Demo the Around Advice Handle Exception");
        System.out.println("calling the getFortune() ");

        //the exception will never thrown to the main app
        //coz the exception is being handled in @Around advice
        boolean tripWire = true;
        String data = theTraffic.getFortune(tripWire);

        System.out.println("\nMy fortune is: "+data);
        System.out.println("Finished");

    }

    private void demoTheAroundAdvice(TrafficFortuneService theTraffic) {
        System.out.println("\nMain Program: Demo the Around Advice");
        System.out.println("calling the getFortune() ");

        String data = theTraffic.getFortune();
        System.out.println("\nMy fortune is: "+data);

        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {

        List<Account> theAccounts = null;
        try{
            //Add a boolean flag to simulate exceptions
            boolean tripWire = false;

            theAccounts = theAccountDAO.findAccounts(tripWire);
        }
        catch (Exception e) {
            System.out.println("\nMain Program....Caught Exception: "+e);
        }

        //display the accounts
        System.out.println("\nIn Main class\n-------------\ndemoTheAfterThrowingAdvice");
        System.out.println("The accounts are: "+theAccounts);


    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
        //call the methods
        List<Account> theAccounts = null;
        try{
            //Add a boolean flag to simulate exceptions
            boolean tripWire = true;//true will throw an exception

            theAccounts = theAccountDAO.findAccounts(tripWire);
        }
        catch (Exception e) {
            System.out.println("\nMain Program....Caught Exception: "+e);
        }

        //display the accounts
        System.out.println("\nIn Main class\n-------------\ndemoTheAfterThrowingAdvice");
        System.out.println("The accounts are: "+theAccounts);
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        //call the methods
        List<Account> theAccounts = theAccountDAO.findAccounts();//this method will watch for our @AfterReturning advice

        //display the accounts
        System.out.println("\nIn Main class\n-------------");
        System.out.println("The accounts are: "+theAccounts);
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

        Account myAccount = new Account();
        myAccount.setName("Ammu");
        myAccount.setLevel("Platinum");
        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        //calling getters and setters
        theAccountDAO.setName("Foo");
        theAccountDAO.setServiceCode("Silver");

        theAccountDAO.getName();
        theAccountDAO.getServiceCode();

        //call the membership dao account
        theMembershipDAO.addAccount();

        theMembershipDAO.goToSleep();


    }

}
