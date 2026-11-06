package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define field for entity manager
   private EntityManager entityManager;

    //inject entity manager in the constructor
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //implement the save method
    //Add @Transactional since we are performing update on the database

    @Override
    @Transactional
    public void save(Student theStudent){
        entityManager.persist(theStudent);// Saves the Student to the database
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {

        //create query
        //TypedQuery<Student> theQuery = entityManager.createQuery("From Student", Student.class);
        //TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by lastName", Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student order by lastName desc", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //creating query
        TypedQuery<Student> theQuery = entityManager.createQuery("From Student where lastName= :theData", Student.class);
        //Set Parameter
        theQuery.setParameter("theData", theLastName);
        //return the result
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void Update(Student  theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student st = entityManager.find(Student.class,id);
        entityManager.remove(st);

    }

    @Override
    @Transactional
    public int deleteAll() {
        int noOfRowsDeleted = entityManager.createQuery("delete From Student").executeUpdate();
        return noOfRowsDeleted;
    }

}
