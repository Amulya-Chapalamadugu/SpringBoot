package com.love2code.cruddemo.dao;

import com.love2code.cruddemo.entity.Course;
import com.love2code.cruddemo.entity.Instructor;
import com.love2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        //break associations of all courses for instructor
        List<Course> courses = tempInst.getCourses();

        for(Course course : courses){
            course.setInstructor(null);
        }

        //remove or delete the instructor
        //**we are not deleting the associated courses
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

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id= :data",Course.class);

        query.setParameter("data", theId);

        //execute query
        List<Course> courses = query.getResultList();
        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //the below code will retrieve instructors and courses
        //the join fetch is similar to eager loading
        TypedQuery<Instructor>  query = entityManager.createQuery("select i from Instructor i "
                                                                + "JOIN FETCH i.courses "
                                                                + "where i.id= :data", Instructor.class);

        query.setParameter("data",theId);
        Instructor tempInstr = query.getSingleResult();
        return tempInstr;

    }

    @Override
    @Transactional
    public void update(Instructor tempInstr) {
        entityManager.merge(tempInstr);

    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //retrieve the course
        Course tempCourse= entityManager.find(Course.class,theId);

        //delete the course
        entityManager.remove(tempCourse);
    }

}
