package com.example.OneToOneMaping.controller;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.CourseReview;
import com.example.OneToOneMaping.service.CourseReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseReviewController {
    private final CourseReviewService service;

    public CourseReviewController(CourseReviewService service) {
        this.service = service;
    }

    // adds a course review
    @PostMapping("/add/course-review")
    public Course addCourseReview(@RequestBody Course course) {
        return service.addCourseReview(course);
    }

    @GetMapping("/get/course-review")
    public List<Course> getCourses() {
        return service.getAllCourses();
    }
}
