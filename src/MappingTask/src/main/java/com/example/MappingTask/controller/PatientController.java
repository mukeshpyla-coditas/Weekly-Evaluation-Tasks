package com.example.MappingTask.controller;

import com.example.MappingTask.entity.MedicalHistory;
import com.example.MappingTask.entity.Patient;
import com.example.MappingTask.response.PatientResponse;
import com.example.MappingTask.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // 1. Adding a patient
    @PostMapping("/add/patients")
    public Patient savePatient(@RequestBody Patient patient) {
        return service.savePatient(patient);
    }

    // 2. Retrieving all patients
    @GetMapping("/view/patients")
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }

    // 3. Getting a patient by ID
    @GetMapping("/get/patient/{id}")
    public Optional<Patient> getPatientById(@PathVariable("id") Integer id) {
        return service.getPatientById(id);
    }

    // 4. Getting patient and his respective medical history by ID
    @GetMapping("/get/patient/medical-history/{id}")
    public PatientResponse getMedicalHistory(@PathVariable Integer id) {
        return service.getMedicalHistory(id);
    }

    // 5. updating a patient
    @PutMapping("/{id}/patient")
    public Patient updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        return service.updatePatient(id, patient);
    }

    // 6. Updating Medical History
    @PutMapping("/{id}/medical-history")
    public MedicalHistory updateMedicalHistory(@PathVariable("id") Integer id, @RequestBody MedicalHistory medicalHistory) {
        return service.updateMedicalHistory(id, medicalHistory);
    }

    // 7. Delete a Patient
    @DeleteMapping("/{id}")
    public Patient deletePatient(@PathVariable("id") Integer id) {
        return service.deletePatient(id);
    }

    // 8. View all Medical Histories
    @GetMapping("/medical-histories")
    public List<MedicalHistory> getAllMedicalHistories() {
        return service.getAllMedicalHistories();
    }

    // 9. Add medical history to existing patient
    @PostMapping("/{id}/medical-history")
    public Patient addMedicalHistory(@PathVariable("id") Integer id, @RequestBody MedicalHistory medicalHistory) {
        return service.addMedicalHistory(id, medicalHistory);
    }

    // 10. Find patients by Blood Group
    @GetMapping("/search")
    public List<Patient> findByBloodGroup(@RequestParam String bloodGroup) {
        return service.findByBloodGroup(bloodGroup);
    }
}
