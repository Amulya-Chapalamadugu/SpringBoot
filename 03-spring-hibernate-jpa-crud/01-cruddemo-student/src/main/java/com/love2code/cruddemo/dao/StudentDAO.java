package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    //Finding Student by Last Name
    List<Student> findByLastName(String theLastName);
    //Updating the lastname
    void Update(Student theStudent);
    //Delete the Student by Id
    void delete(int id);
    //Deleting all Students which returns the number of rows deleted
    int deleteAll();

}
