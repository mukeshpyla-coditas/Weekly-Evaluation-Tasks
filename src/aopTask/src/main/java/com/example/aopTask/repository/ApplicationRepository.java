package com.example.aopTask.repository;

import com.example.aopTask.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Resume, Integer> {
}
