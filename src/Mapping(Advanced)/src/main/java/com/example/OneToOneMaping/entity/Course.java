package com.example.OneToOneMaping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Setter
@Getter
public class Course {
    // define the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    // owning-side ----> for (Course---Instructor) relation
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    @JsonIgnoreProperties("courses")
    private Instructor instructor;

    // owning-side -----> for (Course----CourseReview) relation
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties("course")
    private List<CourseReview> reviews;

    // for (Course----Student) relation
    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private List<Student> students;


    public Course(String title) {
        this.title = title;
    }

    public void addReview(CourseReview review) {
        if(reviews == null) {
            reviews = new ArrayList<>();
        }

        reviews.add(review);
    }
}
