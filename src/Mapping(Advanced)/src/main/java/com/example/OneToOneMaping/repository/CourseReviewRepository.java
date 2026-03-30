package com.example.OneToOneMaping.repository;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.CourseReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseReviewRepository {
    private final EntityManager entityManager;

    public CourseReviewRepository(EntityManager entityManager) { this.entityManager = entityManager; }

    @Transactional
    public Course saveCourse(Course course) {
        entityManager.persist(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return entityManager.createQuery("select c from Course c", Course.class).getResultList();
    }
}
