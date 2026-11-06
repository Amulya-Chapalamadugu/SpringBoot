package com.love2code.cruddemo;

import com.love2code.cruddemo.dao.AppDAO;
import com.love2code.cruddemo.entity.*;
import org.hibernate.boot.jaxb.mapping.GenerationTiming;
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

    
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){
        //commandline runner is from spring framework and is executed after all
        //beans hae been loaded

        return runner->{

        //createCourseAndStudents(appDAO);
        //findCoursesAndStudents(appDAO);
            // findStudentsAndCourses(appDAO);
        //addMoreCoursesForStud(appDAO);
        //deleteCourseById(appDAO);
         deleteStudent(appDAO);
        };

    }

    private void deleteStudent(AppDAO appDAO) {

        int id = 1;
        System.out.println("Deleting the Student of Id: "+id);

        appDAO.deleteStudentById(id);
        System.out.println("Student deleted successfully!");



    }

    private void addMoreCoursesForStud(AppDAO appDAO) {

        int id = 2;
        Student temp = appDAO.findStudentAndCoursesByStudentId(id);

        //create more courses
        Course co1 = new Course("foundations of C");
        Course co2 = new Course("foundations of C++");
        Course co3 = new Course("foundations of Python");

        temp.addCourse(co1);
        temp.addCourse(co2);
        temp.addCourse(co3);

        appDAO.update(temp);

        System.out.println("Updated Student is: "+temp);
        System.out.println("Associated courses are: "+temp.getCourses());
        System.out.println("Done!!!");
    }

    private void findStudentsAndCourses(AppDAO appDAO) {

        int id = 2;
        Student stud = appDAO.findStudentAndCoursesByStudentId(id);
        System.out.println("The student is: "+stud);
        System.out.println("Associated Courses are: "+stud.getCourses());
        System.out.println("Done!!");


    }

    private void findCoursesAndStudents(AppDAO appDAO) {

        int courseId = 10;

        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(courseId);

        System.out.println("The Course is: "+tempCourse);
        System.out.println("Associated Students are: "+tempCourse.getStudents());
        System.out.println("Done!!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        //create course
        Course theCourse = new Course("Basics of Janga");

        //create students
        Student tempStu1= new Student("John", "Doe","john@gmail.com");
        Student tempStu2= new Student("Julia", "Roberts","julia@gmail.com");
        Student tempStu3= new Student("Oliver", "Black","Oliver@gmail.com");

        //add students to the course
        theCourse.addStudent(tempStu1);
        theCourse.addStudent(tempStu2);
        theCourse.addStudent(tempStu3);


        //save the course and associated students
        System.out.println("saving the Course: "+theCourse);
        System.out.println("saving Associated Courses: "+theCourse.getStudents());

        appDAO.save(theCourse);//this will also
        // save the students because of cascadeType.persist

        System.out.println("DOne!!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 11;
        System.out.println("Deleting the Course Id: "+ theId);
        appDAO.deleteCourseById(theId);//this will delte the reviews too
        System.out.println("Done!!!");

    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        //get the course and reviews
        int theId = 11;
        Course course = appDAO.findCourseAndReviewsByCourseId(theId);

        //print the course and reviews
        System.out.println("The course is: "+ course);
        System.out.println("The reviews of this course are: "+ course.getReviews());

        System.out.println("Done!!!");


    }

    private void createCourseAndReviews(AppDAO appDAO) {
        //create a course
        Course tempCourse = new Course("How to play Guitar");

        //add reviews to it
        tempCourse.addReview(new Review("Excellent course for beginners"));
        tempCourse.addReview(new Review("Cool course!!!"));
        tempCourse.addReview(new Review("Super Course"));


        System.out.println("Saving the Course...");
        System.out.println(tempCourse);
        System.out.println("The Reviews are...");
        System.out.println(tempCourse.getReviews());
        //save the course
        appDAO.save(tempCourse);//this will automatically save the reviews because of cascadeType.All

        System.out.println("Done!!");

    }

    private void deleteCourseById(AppDAO appDAO) {
        int id= 11;
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
