package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field for Entity Manager
    private EntityManager entityManager;


    //inject entity manager using constructor
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
        //the above operation will also save the details object
        //because of cascade.type=All
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
        //this will retrieve the instructor details object also
        //coz the default behavior of @oneToOne fetch type is eager
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve
        Instructor tempInst = entityManager.find(Instructor.class,theId);
        //remove
        entityManager.remove(tempInst);
    }
    //
}
