package com.example.mockServerTask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponseDTO {
    private String name;
    private String email;
    private String gender;
    private String dateOfBirth;
    private String role;
}
