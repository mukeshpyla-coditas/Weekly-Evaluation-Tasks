package com.example.aopTask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer applicantId;
    private String applicantName;
    private String applicantEmail;
    private List<String> skills;
    private Integer experience;
    private String description;
    private String fileType;
    private Double fileSize;
}
