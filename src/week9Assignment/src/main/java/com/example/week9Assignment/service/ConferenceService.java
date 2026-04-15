package com.example.week9Assignment.service;

import com.example.week9Assignment.entity.Conference;
import com.example.week9Assignment.entity.Session;
import com.example.week9Assignment.repository.ConferenceRepository;
import com.example.week9Assignment.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final SessionRepository sessionRepository;

    public Conference createConference(Conference conference) {
        Conference savedConference = conferenceRepository.save(conference);
        List<Session> sessionList = conference.getSession();
        List<Session> newSessionList = new ArrayList<>();
        for(Session session : sessionList) {
            Session existingSession = sessionRepository.findById(session.getId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
            existingSession.getConference().add(savedConference);
            sessionRepository.save(existingSession);
            newSessionList.add(existingSession);
        }

        savedConference.setSession(newSessionList);
        return savedConference;
    }
}
