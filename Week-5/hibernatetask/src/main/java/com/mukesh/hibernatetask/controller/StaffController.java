package com.mukesh.hibernatetask.controller;

import com.mukesh.hibernatetask.entity.Staff;
import com.mukesh.hibernatetask.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    private final StaffService service;

    public StaffController(StaffService service) {
        this.service = service;
    }

    // Gets all staff members
    @GetMapping("/")
    public List<Staff> getAll() {
        return service.getAllStaff();
    }

    // Gets staff member by id
    @GetMapping("/{id}")
    public Staff getAll(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    // Adds the staff member into the DB
//    @PostMapping()
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public Staff create(@RequestBody Staff staff) {
        return service.saveStaff(staff);
    }

    // Retrieves the staff members with salary greater than provided salary.
    @GetMapping("/salary/above/{amount}")
    public List<Staff> salaryAbove(@PathVariable("amount") Integer amount) {
        return service.getSalaryAbove(amount);
    }

    // Retrieves the staff members between min and max experience
    @GetMapping("/experience")
    public List<Staff> experienceBetween(@RequestParam("min") Integer min, @RequestParam("max") Integer max) {
        return service.getExperienceBetween(min, max);
    }

    // Updates the salary of the staff with provided staff id, with provided amound
    @PostMapping("{id}/salary")
    public Staff updateStaff(@PathVariable("id") Integer id, @RequestParam("amount") Double amount) {
        if(service.updateStaffSalary(id, amount) == 1) {
            return service.getById(id);
        }
        else return null;
    }

    // Retrieves the staff members whose profile is "Trainer"
    @GetMapping("/profile/trainer")
    public List<Staff> trainer() {
        return service.getTrainerProfile();
    }

    // Retrieves the staff members whose profile is NOT "Trainer"
    @GetMapping("/profile/not-trainer")
    public List<Staff> nonTrainer() {
        return service.getNonTrainerProfile();
    }
}
