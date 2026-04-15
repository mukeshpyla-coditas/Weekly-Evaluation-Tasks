package com.example.week9Assignment.controller;

import com.example.week9Assignment.dto.AttendanceRequestDTO;
import com.example.week9Assignment.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/enroll")
    public String enrollUserInSession(@RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        return attendanceService.registerAttendanceOfUser(attendanceRequestDTO);
    }
}
