package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Instructor;
import com.love2code.cruddemo.entity.InstructorDetail;
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

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        /*InstructorDetail tempDetail = entityManager.find(InstructorDetail.class, theId);

        entityManager.remove(tempDetail);//if we delete the instructor details object then
        // instructor will automatically be deleted coz of cascading
        */

        InstructorDetail tempDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object reference
        //break bi-directional link

        tempDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(tempDetail);

    }

}
