package com.example.aopTask.service;

import com.example.aopTask.entity.Resume;
import com.example.aopTask.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public String saveResume(Resume resume) {
        applicationRepository.save(resume);
        String extension = resume.getFileType();
        Double fileSize = resume.getFileSize();
        if(!extension.equals("PDF")) {
            throw new RuntimeException("Extension of the file does not match the requirement.");
        }
        else if(fileSize > 250) {
            throw new RuntimeException("File Size is exceeding the required limit. Please verify before uploading");
        }
        else {
            applicationRepository.save(resume);
            return "Successfully registered the resume";
        }
    }
}
