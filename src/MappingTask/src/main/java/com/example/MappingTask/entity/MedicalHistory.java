package com.example.MappingTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "blood_group")
    private String bloodGroup;

    private List<String> allergies;

    private List<String> pastDiseases;

    private List<String> currentMedication;

    @OneToOne(mappedBy = "medicalHistory")
    @JsonIgnoreProperties("medicalHistory")
    private Patient patient;
}
