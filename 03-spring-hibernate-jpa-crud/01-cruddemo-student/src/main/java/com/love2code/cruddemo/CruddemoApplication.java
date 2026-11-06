package com.love2code.cruddemo;

import com.love2code.cruddemo.dao.StudentDAO;
import com.love2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.transform.Source;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//Step 3 Updating the main application
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){//Injecting StudentDAO
	//the type CommandLine Runner is from the Spring boot framework and will be executed after the spring beans have been loaded

	return runner->{

		//createStudent(studentDAO);
		createMultipleStudents(studentDAO);
		//findStudent(studentDAO);
		//getStudentByQuery(studentDAO);
		//getStudentByLastName(studentDAO);
		//updateLastName(studentDAO);
		//deleteStudentById(studentDAO);
		//deletingAll(studentDAO);
	};
	}

	private void deletingAll(StudentDAO studentDAO) {
		int rowsDeleted = studentDAO.deleteAll();
		System.out.println("No of rows deleted are: "+rowsDeleted);
	}

	private void deleteStudentById(StudentDAO studentDAO) {
		int stId= 3;
		System.out.println("Deleted Student id is: "+stId);
		studentDAO.delete(stId);

	}

	private void updateLastName(StudentDAO studentDAO) {
		//retrieve Student based on the primary key(id)
		int id = 1;
		System.out.println("Getting the Student of id "+ id);
		Student st = studentDAO.findById(id);
		//Change first name to Scooby
		//st.setFirstName("Scooby");
		//Again change the first name
		st.setFirstName("Abhishek");
		//update the Student
		studentDAO.Update(st);

		//Display updated Student
		System.out.println("Updated Student is: "+st);
	}
	private void getStudentByLastName(StudentDAO studentDAO) {
		List<Student> theStudent= studentDAO.findByLastName("Chapa");
		for(Student temStudent:theStudent){
			System.out.println(temStudent);
		}
	}

	private void getStudentByQuery(StudentDAO studentDAO) {
		List<Student> theStudent = studentDAO.findAll();
		for(Student tempStudents: theStudent){
			System.out.println(tempStudents);
		}

	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating Multiple Students: ");
		Student tempStudent1 = new Student("Abhishek", "Chapalamadugu","bobby@123");
		Student tempStudent2 = new Student("Upadesh", "Turaka","uppu@123");
		Student tempStudent3 = new Student("Abhi", "Chapa","bobby1@gmail.com");
		studentDAO.save(tempStudent1);
		System.out.println("Saved Student Id is: "+tempStudent1.getId());
		studentDAO.save(tempStudent2);
		System.out.println("Saved Student Id is: "+tempStudent2.getId());
		studentDAO.save(tempStudent3);
		System.out.println("Saved Student Id is: "+tempStudent3.getId());
	}

	public void createStudent(StudentDAO studentDAO) {
		//create the student object

		System.out.println("Creating a new Student....");
		Student tempStudent = new Student("Amulya","Chapalamadugu","amulya@gmail.com");


		//save the student object
		System.out.println("Saving the Student.....");
		studentDAO.save(tempStudent);

		//display the id the saved student object
		System.out.println("Saved Student Id is: "+tempStudent.getId());
		System.out.println("Saved Student Id is: "+tempStudent.getFirstName());

	}

	private void findStudent(StudentDAO studentDAO) {
		Student tempStudents = new Student("Ammu", "Chaps","ammu@567");
		studentDAO.save(tempStudents);
		System.out.println("Getting id of saved Student..."+tempStudents.getId());
		int theId = tempStudents.getId();
		//Now retrieving from Data base using primary key

		Student myStudent = studentDAO.findById(theId);
		System.out.println("Found the Student: "+myStudent);
	}

}

