package com.example.week9Assignment.controller;

import com.example.week9Assignment.entity.Conference;
import com.example.week9Assignment.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference")
@RequiredArgsConstructor
public class ConferenceController {
    private final ConferenceService conferenceService;

    @PostMapping("/create")
    public Conference createConference(@RequestBody Conference conference) {
        return conferenceService.createConference(conference);
    }
}
