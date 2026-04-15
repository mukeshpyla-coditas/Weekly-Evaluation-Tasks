package com.example.week9Assignment.entity;

import com.example.week9Assignment.enums.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @ManyToMany
    @JoinTable(
            name = "conference_session",
            joinColumns = @JoinColumn(name = "conference_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "session_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("conference")
    private List<Session> session;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
