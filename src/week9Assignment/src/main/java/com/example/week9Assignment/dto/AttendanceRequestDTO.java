package com.example.week9Assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequestDTO {
    private Integer userId;
    private Integer sessionId;
    private Integer conferenceId;
    private String date;
}
