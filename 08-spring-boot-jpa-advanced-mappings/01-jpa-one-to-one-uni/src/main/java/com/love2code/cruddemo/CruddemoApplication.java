package com.love2code.cruddemo;

import com.love2code.cruddemo.dao.AppDAO;
import com.love2code.cruddemo.dao.AppDAOImpl;
import com.love2code.cruddemo.entity.Instructor;
import com.love2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


    /*@Bean
    public CommandLineRunner commandLineRunner(String[] args){
        //commandline runner is from spring framework and is executed after all
        //beans hae been loaded

        return runner->{
            System.out.println("Hello World");
        };
    }*/


    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        //commandline runner is from spring framework and is executed after all
        //beans hae been loaded

        return runner->{
            //createInstructor(appDAO);
            //findInstructorById(appDAO);
            deleteInstructorById(appDAO);
        };

    }

    private void deleteInstructorById(AppDAO appDAO) {
        int theId = 2;
        System.out.println("Deleting the instructor...");
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!!");

    }

    private void findInstructorById(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding Instructor of id: "+theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("The Instructor is: "+ tempInstructor);
        System.out.println("The Associated Details are: "+tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

        //create the Instructor

        Instructor tempInstructor = new Instructor("Chad", "Derby","love2code@gmail.com");

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.luv2code.com/youtube","teaching and love to code!!!");

        //Associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor
        System.out.println("Saving the Instructor: "+ tempInstructor);
        //Note: this will also save the details object coz we used cascadetype=All in Instructor entity
        appDAO.save(tempInstructor);

        System.out.println("Done!!");






    }
}
