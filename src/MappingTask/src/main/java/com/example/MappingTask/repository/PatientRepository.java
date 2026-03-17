package com.example.MappingTask.repository;

import com.example.MappingTask.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p JOIN FETCH p.medicalHistory WHERE p.id = :id")
    public Patient getMedicalHistory(Integer id);

    @Query("SELECT p FROM Patient p JOIN FETCH p.medicalHistory m WHERE m.bloodGroup = :bloodGroup")
    List<Patient> findByBloodGroup(String bloodGroup);

}
