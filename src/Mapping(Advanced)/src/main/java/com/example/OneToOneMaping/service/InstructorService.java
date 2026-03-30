package com.example.OneToOneMaping.service;

import com.example.OneToOneMaping.entity.*;
import com.example.OneToOneMaping.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class InstructorService {
    private final InstructorRepository repository;

    InstructorService(InstructorRepository repository) {
        this.repository = repository;
    }

    public InstructorDetail addInstructorDetails(InstructorDetail instructorDetail) {
        return repository.saveInstructorDetail(instructorDetail);
    }

    public Instructor addInstructor(Instructor instructor) {
        return repository.saveInstructor(instructor);
    }

    public Instructor updateInstructor(Instructor instructor) {
        return repository.updateInstructor(instructor);
    }

    public Instructor findInstructorById(Integer id) {
        return repository.findInstructorById(id);
    }

    public InstructorDetail findInstructorDetailById(Integer id) {
        return repository.findInstructorDetailById(id);
    }

    public Instructor getInstructorWithCourses(Integer id) {
        return repository.getInstructorWithCourses(id);
    }

    public Instructor deleteInstructorById(Integer id) {
        return repository.deleteInstructorById(id);
    }

    public Instructor addInstructorWithCourses(Instructor instructor) {
        return repository.addInstructorWithCourses(instructor);
    }

    public InstructorDetail deleteInstructorDetail(Integer id) {
        return repository.deleteInstructorDetail(id);
    }
}
