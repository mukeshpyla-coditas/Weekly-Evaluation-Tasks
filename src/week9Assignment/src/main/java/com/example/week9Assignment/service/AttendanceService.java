package com.example.week9Assignment.service;

import com.example.week9Assignment.dto.AttendanceRequestDTO;
import com.example.week9Assignment.entity.Attendance;
import com.example.week9Assignment.entity.Conference;
import com.example.week9Assignment.entity.Session;
import com.example.week9Assignment.entity.User;
import com.example.week9Assignment.exception.ConferenceNotFoundException;
import com.example.week9Assignment.exception.SessionNotFoundException;
import com.example.week9Assignment.exception.UserNotFoundException;
import com.example.week9Assignment.repository.AttendanceRepository;
import com.example.week9Assignment.repository.ConferenceRepository;
import com.example.week9Assignment.repository.SessionRepository;
import com.example.week9Assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final ConferenceRepository conferenceRepository;
    private final SessionRepository sessionRepository;

    public String registerAttendanceOfUser(AttendanceRequestDTO attendanceRequestDTO) {
        User user = userRepository.findById(attendanceRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Specified user is not found"));

        Session session = sessionRepository.findById(attendanceRequestDTO.getSessionId())
                .orElseThrow(() -> new SessionNotFoundException("Specified session is not found"));

        Conference conference = conferenceRepository.findById(attendanceRequestDTO.getConferenceId())
                .orElseThrow(() -> new ConferenceNotFoundException("Specified conference is not found"));


        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setSession(session);
        attendance.setConference(conference);
        attendance.setDateOfAttendance(new Date());

        attendanceRepository.save(attendance);

        return "User " + user.getUsername() + " has attended the session " + session.getSessionType();
    }
}
