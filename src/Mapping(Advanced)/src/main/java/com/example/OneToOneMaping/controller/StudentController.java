package com.example.OneToOneMaping.controller;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.Student;
import com.example.OneToOneMaping.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // adds a student
    @PostMapping("/add/student")
    public Student addStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    // enrolls a student into a course
    @PostMapping("/{studentId}/courses/{courseId}")
    public String enrollStudent(@PathVariable("studentId") Integer studentId, @PathVariable("courseId") Integer courseId) {
        service.enrollStudent(studentId, courseId);
        return "Student enrolled successfully";
    }

    // get student with enrolled courses
    @GetMapping("/get/student/{id}")
    public Student getStudentWithCourses(@PathVariable("id") Integer id) {
        return service.getStudentWithCourses(id);
    }

    // get course with enrolled students
    @GetMapping("/get/course/{id}")
    public Course getCourseWithStudents(@PathVariable("id") Integer id) {
        return service.getCourseWihStudents(id);
    }

    // deletes the student with id
    @DeleteMapping("/delete/student/{id}")
    public Student deleteStudent(@PathVariable("id") Integer id) {
        return service.deleteStudent(id);
    }
}
