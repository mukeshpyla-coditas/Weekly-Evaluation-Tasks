package com.example.OneToOneMaping.service;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.repository.CourseReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseReviewService {
    private final CourseReviewRepository repository;

    CourseReviewService(CourseReviewRepository repository) {
        this.repository = repository;
    }

    public Course addCourseReview(Course course) {
        return repository.saveCourse(course);
    }

    public List<Course> getAllCourses() {
        return repository.getAllCourses();
    }
}
