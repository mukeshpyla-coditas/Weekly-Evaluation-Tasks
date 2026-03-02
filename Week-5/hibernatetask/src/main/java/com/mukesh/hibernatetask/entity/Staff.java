package com.mukesh.hibernatetask.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffid")
    private Integer staffId;

    @Column(name = "name")
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "experience")
    private Integer experience;

    public Staff() { }

    public Staff(String name, String profile, Double salary, Integer experience) {
        this.name = name;
        this.profile = profile;
        this.salary = salary;
        this.experience = experience;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
