package com.love2code.cruddemo;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.love2code.cruddemo.dao.AppDAO;
import com.love2code.cruddemo.dao.AppDAOImpl;
import com.love2code.cruddemo.entity.Course;
import com.love2code.cruddemo.entity.Instructor;
import com.love2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
            //deleteInstructorById(appDAO);
            //findInstructorDetailById(appDAO);
            //deleteInstructorDetailById(appDAO);


            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);

            //findCoursesForInstructor(appDAO);

            //findInstructorWithCoursesJoinFetch(appDAO);

            //updateInstructor(appDAO);
            
            //updateCourse(appDAO);
            //findCourseById(appDAO);

            //deleteCourseById(appDAO);
            
        };

    }

    private void deleteCourseById(AppDAO appDAO) {
        int id= 21;
        System.out.println("Deleting the course of id: "+id);

        appDAO.deleteCourseById(id);
        System.out.println("Done!!");
    }


    private void updateCourse(AppDAO appDAO) {
        int theId = 11;
        Course tempCourse= appDAO.findCourseById(theId);
        System.out.println("Finding course id: "+theId);
        System.out.println("the current course is "+ tempCourse);
        tempCourse.setTitle("Advanced Java Concepts");
        appDAO.updateCourse(tempCourse);
        System.out.println("Course updated Successfully: "+ tempCourse);

    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 6;
        Instructor tempInstr = appDAO.findInstructorById(theId);
        tempInstr.setLastName("Kumar");
        tempInstr.setFirstName("Akshay");
        tempInstr.setEmail("akshayk@gmail.com");
        appDAO.update(tempInstr);
        System.out.println("Update Successful: "+tempInstr);
        System.out.println("Done!!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;
        Instructor tempInstr = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("Finding Instructor with id: "+theId);
        System.out.println(tempInstr);
        System.out.println("Associated courses are: "+ tempInstr.getCourses());
        System.out.println("Done!!");

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding Instructor with id:"+theId );

        Instructor tempInstr = appDAO.findInstructorById(theId);

        System.out.println("Instructor is: "+tempInstr);

        //find courses for instructor id
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        //Associate the objects
        tempInstr.setCourses(courses);


        System.out.println("The Associated courses are: "+ tempInstr.getCourses());

        System.out.println("Done!!");

    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding Instructor with id:"+theId );

        Instructor tempInstr = appDAO.findInstructorById(theId);

        System.out.println("Instructor is: "+tempInstr);
        System.out.println("The Associated courses are: "+tempInstr.getCourses());

        System.out.println("Done!!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Chad", "Derby","love2code@gmail.com");

        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.luv2code.com/youtube","teaching and love to code!!!");

        //Associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //create some courses
        Course tempCourse1 = new Course("Mappings");
        Course tempCourse2 = new Course("JPA and Hibernate");
        Course tempCourse3 = new Course("Foundations of AI");

        //add courses to instructors

        tempInstructor.addCourse(tempCourse1);
        tempInstructor.addCourse(tempCourse2);
        tempInstructor.addCourse(tempCourse3);

        //save the Instructor
        //NOTE: This will also save the courses because of
        //cascadeType.PERSIST
        System.out.println("Saving the instructor: "+tempInstructor);
        System.out.println("The Courses: "+tempInstructor.getCourses());
        appDAO.save(tempInstructor);


        System.out.println("Done!!");


    }

    private void deleteInstructorDetailById(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Deleting the instructor details of id ..."+theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!!");
    }

    private void findInstructorDetailById(AppDAO appDAO) {

        //get the instructor detail object

        int theId = 1;
        System.out.println("Finding Instructor of id: "+theId);

        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        //print the instructor detail
        System.out.println("The Instructor Details are: "+ tempInstructorDetail);

        //print the instructor
        System.out.println("The Associated Instructor is: "+tempInstructorDetail.getInstructor());
        System.out.println("Done!!");
    }

    private void deleteInstructorById(AppDAO appDAO) {
        int theId = 6;
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
