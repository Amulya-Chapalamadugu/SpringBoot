package com.love2code.aopdemo;

import com.love2code.aopdemo.dao.AccountDAO;
import com.love2code.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO){

        return runner->{
            demoTheBeforeAdvice(theAccountDAO,theMembershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount, true);

        //call the membership dao account
        theMembershipDAO.addAccount();
        theAccountDAO.doWork();
        theMembershipDAO.goToSleep();


    }

}
