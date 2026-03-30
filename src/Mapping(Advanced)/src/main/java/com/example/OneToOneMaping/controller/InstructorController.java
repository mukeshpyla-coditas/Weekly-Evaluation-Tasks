package com.example.OneToOneMaping.controller;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.Instructor;
import com.example.OneToOneMaping.entity.InstructorDetail;
import com.example.OneToOneMaping.service.InstructorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class InstructorController {
    private final InstructorService service;

    InstructorController(InstructorService service) {
        this.service = service;
    }

    // add instructorDetails
    @PostMapping("/add/instructor-details")
    public InstructorDetail addInstructorDetails(@RequestBody InstructorDetail instructorDetail) {
        return service.addInstructorDetails(instructorDetail);
    }

    // add instructor
    @PostMapping("/add/instructor")
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        return service.addInstructor(instructor);
    }

    // fetch instructor by ID
    @GetMapping("/get/instructor/{id}")
    public Instructor findInstructorById(@PathVariable("id") Integer id) {
        return service.findInstructorById(id);
    }

    // getting instructors along with courses
    @GetMapping("/get/instructor-with-courses/{id}")
    public Instructor getInstructorWithCourses(@PathVariable("id") Integer id) {
        return service.getInstructorWithCourses(id);
    }

    // fetch instructor details by ID
    @GetMapping("/get/instructor-detail/{id}")
    public InstructorDetail findInstructorDetailById(@PathVariable("id") Integer id) {
        return service.findInstructorDetailById(id);
    }

    // delete instructor by ID
    @DeleteMapping("/delete/instructor/{id}")
    public Instructor deleteInstructorById(@PathVariable("id") Integer id) {
        return service.deleteInstructorById(id);
    }

    // fetch instructor along with the assigned courses
    @PostMapping("/add/instructor-with-courses")
    public Instructor addInstructorWithCourses(@RequestBody Instructor instructor) {
        for(Course tempCourse : instructor.getCourses()) {
            tempCourse.setInstructor(instructor);
        }
        return service.addInstructorWithCourses(instructor);
    }

    // Update instructor details
    @PutMapping("/update/instructor")
    public Instructor updateInstructor(@RequestBody Instructor instructor) {
        return service.updateInstructor(instructor);
    }

    @DeleteMapping("/delete/instructor-detail/{id}")
    public InstructorDetail deleteInstructorDetail(@PathVariable("id") Integer id) {
        return service.deleteInstructorDetail(id);
    }
}
