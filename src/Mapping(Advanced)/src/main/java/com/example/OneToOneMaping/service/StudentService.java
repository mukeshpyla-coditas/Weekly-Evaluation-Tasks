package com.example.OneToOneMaping.service;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.Student;
import com.example.OneToOneMaping.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    private final StudentRepository repository;

    StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void enrollStudent(Integer studentId, Integer courseId) {
        Student student = repository.findStudent(studentId);
        Course course = repository.findCourse(courseId);

        student.addCourse(course);
    }

    public Student deleteStudent(Integer id) {
        return repository.deleteStudent(id);
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudentWithCourses(Integer id) {
        return repository.getStudentWithCourses(id);
    }

    public Course getCourseWihStudents(Integer id) {
        return repository.getCourseWithStudents(id);
    }
}
