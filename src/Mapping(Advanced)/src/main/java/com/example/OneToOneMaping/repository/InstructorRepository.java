package com.example.OneToOneMaping.repository;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.Instructor;
import com.example.OneToOneMaping.entity.InstructorDetail;
import com.example.OneToOneMaping.exception.InstructorDetailNotFoundException;
import com.example.OneToOneMaping.exception.InstructorNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InstructorRepository {
    // define entity manager
    private final EntityManager entityManager;

    // perform constructor injection
    public InstructorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // save InstructorDetail
    @Transactional
    public InstructorDetail saveInstructorDetail(InstructorDetail instructorDetail) {
        entityManager.persist(instructorDetail);
        return instructorDetail;
    }

    // save Instructor
    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        if(instructor.getCourses() != null) {
            for(Course course : instructor.getCourses()) {
                course.setInstructor(instructor);
            }
        }
        entityManager.persist(instructor);
        return instructor;
    }

    // fetch instructor by ID
    public Instructor findInstructorById(Integer id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        if(instructor == null) {
            throw new InstructorNotFoundException("Not found specified instructor");
        }
        return instructor;
    }

    // find instructor detail by ID
    public InstructorDetail findInstructorDetailById(Integer id) {
        InstructorDetail instructorDetail =  entityManager.find(InstructorDetail.class, id);
        if(instructorDetail == null) {
            throw new InstructorDetailNotFoundException("Specified instructor detail is not present.");
        }
        return instructorDetail;
    }

    // find instructor along with courses ----> using JOIN FETCH to collect the data
    public Instructor getInstructorWithCourses(Integer id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        if(instructor == null) {
            throw new InstructorNotFoundException("Specified instructor is not found.");
        }
        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i FROM Instructor i " +
                "JOIN FETCH i.courses WHERE i.id = :id", Instructor.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    // delete Instructor by ID
// even if the instructor gets deleted, the associated courses will not be ----> as per the cascadeType mentioned
    @Transactional
    public Instructor deleteInstructorById(Integer id) {
        Instructor instructor = findInstructorById(id);
        if(instructor == null) {
            throw new InstructorNotFoundException("Specified instructor is not found");
        }
        entityManager.remove(instructor);
        return instructor;
    }

    // Update the instructor details
    @Transactional
    public Instructor updateInstructor(Instructor instructor) {
        // updating the instructorDetails if they are present in the body
        if(instructor.getInstructorDetail() != null) {
            instructor.setInstructorDetail(instructor.getInstructorDetail());
        }

        // updating the courses details if they are present in the body
        if(instructor.getCourses() != null) {
            for(Course course : instructor.getCourses()) {
                course.setInstructor(instructor);
            }
        }

        return entityManager.merge(instructor);
    }

    @Transactional
    public Instructor addInstructorWithCourses(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor;
    }

    @Transactional
    public InstructorDetail deleteInstructorDetail(Integer id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        if(instructorDetail == null) {
            throw new InstructorDetailNotFoundException("Specified instructor detail is not found");
        }

        entityManager.remove(instructorDetail);
        return instructorDetail;
    }
}

