package com.mukesh.spinghibernateproject.repository;

import com.mukesh.spinghibernateproject.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public String registerUser(User user) {
        if(existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists...");
        }
        if(existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists...");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        getSession().persist(user);

        return "User registration successful!";
    }

    public String loginUserByEmail(User user) {
        if(!existsByEmail(user.getEmail())) return "User does not exist. Please do register before login.";

        String presentPassword = getSession()
                .createQuery("SELECT u.password FROM User u WHERE u.email = :email", String.class)
                .setParameter("email", user.getEmail())
                .uniqueResult();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (presentPassword != null && encoder.matches(user.getPassword(), presentPassword)) {
            return "Login successful!";
        }
        return "Invalid credentials.";
    }

    public String loginUserByUsername(User user) {
        if(!existsByUsername(user.getUsername())) return "User does not exist. Please do register before login.";

        String presentPassword = getSession()
                .createQuery("SELECT u.password FROM User u WHERE u.username = :username", String.class)
                .setParameter("username", user.getUsername())
                .uniqueResult();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (presentPassword != null && encoder.matches(user.getPassword(), presentPassword)) {
            return "Login successful!";
        }
        return "Invalid credentials.";
    }

    public boolean existsByUsername(String username) {
        return getSession()
                .createQuery("SELECT 1 FROM User u WHERE u.username = :username", Integer.class)
                .setParameter("username", username)
                .uniqueResult() != null;
    }

    public boolean existsByEmail(String email) {
        return getSession()
                .createQuery("SELECT 1 FROM User u WHERE u.email = :email", Integer.class)
                .setParameter("email", email)
                .uniqueResult() != null;
    }
}
