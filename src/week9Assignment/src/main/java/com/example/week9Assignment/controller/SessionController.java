package com.example.week9Assignment.controller;

import com.example.week9Assignment.entity.Session;
import com.example.week9Assignment.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping("/create")
    public Session createSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }
}
