package com.example.aopTask.controller;

import com.example.aopTask.entity.Resume;
import com.example.aopTask.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/upload")
    public String uploadResume(@RequestBody Resume resume) {
        return applicationService.saveResume(resume);
    }
}
