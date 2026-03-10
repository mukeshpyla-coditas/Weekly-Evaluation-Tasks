package com.mukesh.spinghibernateproject.service;

import com.mukesh.spinghibernateproject.entity.User;
import com.mukesh.spinghibernateproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    // registers the user
    public String registerUser(User user) {
        if(repository.existsByUsername(user.getUsername())) return "Username already exists...";

        if(repository.existsByEmail(user.getEmail())) return "Email already exists...";

        // Validate all the fields in the request of user.
        validateFields(user);

        // Validate the password correctness - (minimum of 8 characters required)
        validatePassword(user.getPassword());

        // Validate the email with valid email regex.
        validateEmail(user.getEmail());

        System.out.println("User name: " + user.getUsername());
        System.out.println("User Password: " + user.getPassword());
        return repository.registerUser(user);
    }

    // validates the email entered by the user
    private void validateEmail(String email) {
        if(!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new RuntimeException("Email is not in the required format! Please check and re-enter.");
        }
    }

    // checks that each and every field in NOT null while registration
    private void validateFields(User user) {
        List<String> invalidFields = new ArrayList<>();
        if(user.getFirstName() == null) invalidFields.add("First Name");
        if(user.getLastName() == null) invalidFields.add("Last Name");
        if(user.getUsername() == null) invalidFields.add("Username");
        if(user.getEmail() == null) invalidFields.add("Email");
        if(user.getPassword() == null) invalidFields.add("Password");
        if(user.getPhoneNumber() == null) invalidFields.add("Phone Number");
        if(user.getDateOfBirth() == null) invalidFields.add("Date of Birth");

        if(!invalidFields.isEmpty()) throw new RuntimeException("There are fields which are missing in the request: " + invalidFields);

    }

    // validates the password and also gives the strength of the password.
    private void validatePassword(String password) {
        if(password == null || password.isEmpty()) throw new RuntimeException("Password is required");
        if(password.length() < 8) throw new RuntimeException("Password must contain at least 8 characters");

        int count = 0;

        // Checks if password contains at least 2 UpperCase letters
        if(password.matches(".*[A-Z].*[A-Z]")) count++;

        // Checks if password contains a special character
        if(password.matches(".*[!@#$&*]")) count++;

        // Checks if password contains at least 2 digits
        if(password.matches(".*[0-9].*[0-9]")) count++;

        // Checks if password contains at least 3 LowerCase letters
        if(password.matches(".*[a-z].*[a-z].*[a-z]")) count++;

        if(count < 3) System.out.println("Password is weak");
        else if(count >= 3) System.out.println("Password is strong");
    }

    // logins the user by email and password
    public String loginUserByEmail(User user) {
        return repository.loginUserByEmail(user);
    }

    // logins the user by username and password
    public String loginUserByUsername(User user) {
        return repository.loginUserByUsername(user);
    }
}
