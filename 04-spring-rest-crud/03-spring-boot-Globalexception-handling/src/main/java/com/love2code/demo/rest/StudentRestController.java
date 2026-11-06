package com.love2code.demo.rest;

import com.love2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudent;
    /* @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> allStudents = new ArrayList<>();
        allStudents.add(new Student("Amulya","Chapalamadugu"));
        allStudents.add(new Student("Abhishek","Chapalamadugu"));
        allStudents.add(new Student("Paul Upadesh","Turaka"));
        allStudents.add(new Student("Hannah","Samuel"));
        return allStudents;
    }
     */

    //define PostConstruct to load the Student data....Only Once!
    @PostConstruct
    public void loadData() {
        theStudent = new ArrayList<>();
        theStudent.add(new Student("Amulya", "Chapalamadugu"));
        theStudent.add(new Student("Abhishek", "Chapalamadugu"));
        theStudent.add(new Student("Paul Upadesh", "Turaka"));
        theStudent.add(new Student("Hannah", "Samuel"));
        //return theStudent;

    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudent;
    }

    //defining an endpoint or /api/students/{studentId} - this will return student at index

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        //This is Step 3
        //Checking StudentId against Student List Size
        if (studentId >= theStudent.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id NOT FOUND- " + studentId);
        }

        return theStudent.get(studentId);
    }


    //Step 4: Add exception Handler using @ExceptionHandler

}