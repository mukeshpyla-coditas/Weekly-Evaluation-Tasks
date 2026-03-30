package com.example.OneToOneMaping.repository;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.CourseReview;
import com.example.OneToOneMaping.entity.Instructor;
import com.example.OneToOneMaping.exception.CourseNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseRepository {
    // define entity manager
    private final EntityManager entityManager;

    // perform constructor injection
    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // able to add more than one course to a single instructor.
    // maintaining many-to-one relation
    @Transactional
    public Course addCourse(Course course) {
        Integer id = course.getInstructor().getId();
        Instructor instructor = entityManager.find(Instructor.class, id);
        // exception handling required
        instructor.addCourse(course);
        if(course.getReviews() != null) {
            for(CourseReview review : course.getReviews()) {
                course.addReview(review);
            }
        }
        entityManager.persist(course);
        return course;
    }

    // updates the course details - (Which is already present)
    @Transactional
    public Course updateCourse(Course course) {
        Course managedCourse = entityManager.find(Course.class, course.getId());
        if(managedCourse == null) {
            throw new CourseNotFoundException("Course not found");
        }

        managedCourse.setTitle(course.getTitle());
        if(course.getInstructor() != null) {
            Integer instructorId = course.getInstructor().getId();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            managedCourse.setInstructor(instructor);
        }

        if(course.getReviews() != null) {
            for(CourseReview review : course.getReviews()) {
                managedCourse.getReviews().add(review);
            }
        }

        return managedCourse;
    }

    public Course getCourseAndTheirInstructors(Integer courseId) {
        Course course = entityManager.find(Course.class, courseId);
        if(course == null) {
            throw new CourseNotFoundException("Specified course is not present");
        }
        return course;
    }

    public List<Course> getCoursesViaInstructorId(Integer instructorId) {
        Query query = entityManager.createQuery("FROM Course c WHERE c.instructor.id = :instructor_id");
        query.setParameter("instructor_id", instructorId);
        return query.getResultList();
    }

    @Transactional
    public Course deleteCourse(Integer courseId) {
        Course courseToDelete = entityManager.find(Course.class, courseId);
        if(courseToDelete == null) {
            throw new CourseNotFoundException("Specified Course is not found");
        }
        entityManager.remove(courseToDelete);

        return courseToDelete;
    }
}
