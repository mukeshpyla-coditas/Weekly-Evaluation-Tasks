package com.example.OneToOneMaping.repository;

import com.example.OneToOneMaping.entity.Course;
import com.example.OneToOneMaping.entity.Student;
import com.example.OneToOneMaping.exception.CourseNotFoundException;
import com.example.OneToOneMaping.exception.StudentNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {
    private final EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Student save(Student student) {
        if(student.getCourses() != null) {
            List<Course> courseList = student.getCourses();
            Iterator<Course> iterator = courseList.iterator();
            while(iterator.hasNext()) {
                Course course = iterator.next();
                if(entityManager.find(Course.class, course.getId()) == null) {
                    iterator.remove();
                    throw new CourseNotFoundException("There is no such course present. Please try again.");
                }
                else {
                    student.addCourse(course);
                }
            }
        }
        entityManager.persist(student);
        return student;
    }

    public Student findStudent(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if(student == null) {
            throw new StudentNotFoundException("Specified student is not found");
        }
        return student;
    }

    public Student getStudentWithCourses(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if(student == null) {
            throw new StudentNotFoundException("Specified student is not found");
        }
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s " +
                "JOIN FETCH s.courses WHERE s.id = :id", Student.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public Course getCourseWithStudents(Integer id) {
        Course course = entityManager.find(Course.class, id);
        if(course == null) {
            throw new CourseNotFoundException("Specified course is not found");
        }
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c " +
                "JOIN FETCH c.students WHERE c.id = :id", Course.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public Course deleteCourse(Integer id) {
        Course course = entityManager.find(Course.class, id);
        if(course == null) {
            throw new CourseNotFoundException("Specified course is not found");
        }
        for(Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }

        entityManager.remove(course);
        return course;
    }

    public Student deleteStudent(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if(student == null) {
            throw new StudentNotFoundException("Specified student is not found");
        }
        student.getCourses().clear();
        entityManager.remove(student);

        return student;
    }

    public Course findCourse(Integer id) {
        Course course = entityManager.find(Course.class, id);
        if(course == null) {
            throw new CourseNotFoundException("Specified course is not found");
        }
        return course;
    }
}
