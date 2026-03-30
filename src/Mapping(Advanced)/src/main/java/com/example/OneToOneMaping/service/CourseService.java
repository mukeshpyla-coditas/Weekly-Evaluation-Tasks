package com.example.OneToOneMaping.service;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.CourseReview;
import com.example.OneToOneMaping.entity.Instructor;
import com.example.OneToOneMaping.entity.Student;
import com.example.OneToOneMaping.repository.CourseRepository;
import com.example.OneToOneMaping.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;

    CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course getCourseAndTheirInstructors(Integer courseId) {
        return repository.getCourseAndTheirInstructors(courseId);
    }

    public List<Course> getCoursesViaInstructorId(Integer instructorId) {
        return repository.getCoursesViaInstructorId(instructorId);
    }

    public Course updateCourse(Course course) {
        return repository.updateCourse(course);
    }

    public Course deleteCourse(Integer courseId) {
        return repository.deleteCourse(courseId);
    }

    public Course addCourse(Course course) {
        return repository.addCourse(course);
    }
}
