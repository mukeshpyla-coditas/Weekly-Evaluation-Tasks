package com.example.MappingTask.service;

import com.example.MappingTask.entity.MedicalHistory;
import com.example.MappingTask.entity.Patient;
import com.example.MappingTask.exception.PatientNotFoundException;
import com.example.MappingTask.repository.MedicalHistoryRepository;
import com.example.MappingTask.repository.PatientRepository;
import com.example.MappingTask.response.PatientResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    private final MedicalHistoryRepository medicalHistoryRepository;

    public PatientService(PatientRepository patientRepository, MedicalHistoryRepository medicalHistoryRepository) {
        this.patientRepository = patientRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public PatientResponse getMedicalHistory(Integer id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found."));
        MedicalHistory medicalHistory = patient.getMedicalHistory();
        return new PatientResponse(patient.getId(), patient.getName(), patient.getAge(), patient.getMobileNumber(), medicalHistory);
    }

    public Patient updatePatient(Integer id, Patient patient) {
        Patient existing = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found."));
        existing.setAge(patient.getAge());
        existing.setName(patient.getName());
        existing.setGender(patient.getGender());
        existing.setMobileNumber(patient.getMobileNumber());

        patientRepository.save(existing);

        return existing;
    }

    public MedicalHistory updateMedicalHistory(Integer id, MedicalHistory medicalHistory) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        MedicalHistory existing = patient.getMedicalHistory();
        existing.setBloodGroup(medicalHistory.getBloodGroup());
        existing.setAllergies(medicalHistory.getAllergies());
        existing.setPastDiseases(medicalHistory.getPastDiseases());
        existing.setCurrentMedication(medicalHistory.getCurrentMedication());

        medicalHistoryRepository.save(existing);

        return existing;
    }

    public Patient deletePatient(Integer id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        patientRepository.delete(patient);

        return patient;
    }

    public List<MedicalHistory> getAllMedicalHistories() {
        return medicalHistoryRepository.findAll();
    }

    public Patient addMedicalHistory(Integer id, MedicalHistory medicalHistory) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        medicalHistory.setPatient(patient);
        patient.setMedicalHistory(medicalHistory);

        patientRepository.save(patient);

        return patient;
    }

    public List<Patient> findByBloodGroup(String bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }
}
