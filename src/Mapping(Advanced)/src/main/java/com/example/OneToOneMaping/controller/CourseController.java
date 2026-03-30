package com.example.OneToOneMaping.controller;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // adding a course
    @PostMapping("/add/course")
    public Course addCourse(@RequestBody Course course) {
        return service.addCourse(course);
    }

    // get course details and their respective reviews using course ID
    @GetMapping("/get/course-with-id/{courseId}")
    public Course getCourseAndTheirInstructors(@PathVariable("courseId") Integer courseId) {
        return service.getCourseAndTheirInstructors(courseId);
    }

    // get courses using instructor ID
    @GetMapping("/get/courses/{instructorId}")
    public List<Course> getCoursesViaInstructorId(@PathVariable("instructorId") Integer instructorId) {
        return service.getCoursesViaInstructorId(instructorId);
    }

    // updates the details of a course
    @PutMapping("/update/course")
    public Course updateCourse(@RequestBody Course course)
    {
        return service.updateCourse(course);
    }

    // deletes a course with specified courseId
    // (but will not delete the respective instructor)
    // (but deletes all the course reviews present for that course)
    @DeleteMapping("/delete/course/{courseId}")
    public Course deleteCourse(@PathVariable("courseId") Integer courseId) {
        return service.deleteCourse(courseId);
    }
    // have maintained the cascade type in such a way that
    // even a course is deleted, the respective instructor is NOT
}
