package com.example.aopTask.aspect;

import com.example.aopTask.entity.Audit;
import com.example.aopTask.entity.User;
import com.example.aopTask.repository.AuditRepository;
import com.example.aopTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LoginAuthenticationChecker {
    private final AuditRepository auditRepository;
    private final UserRepository userRepository;

    @AfterReturning(
            pointcut = "execution(* com.example.aopTask.service.UserService.login*(..))",
            returning = "user"
    )
    public void afterLogin(JoinPoint joinPoint, Object user) {
        User registeredUser = (User) user;
        log.info("User is successfully logged in...");
        log.info("Username of the user logged in: " + registeredUser.getUsername());
        Audit audit = new Audit();
        audit.setUserId(registeredUser.getId());
        audit.setAction("LOGGED IN");
        audit.setUsername(registeredUser.getUsername());
        audit.setTimestamp(LocalDateTime.now());

        auditRepository.save(audit);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopTask.service.UserService.login*(..))",
            throwing = "exception"
    )
    public void afterExceptionInLogin(JoinPoint joinPoint, Exception exception) {
        log.info("There was an exception while user login.");
        log.info("Exception occurred is: " + exception.getMessage());
    }

    @Before("execution(* com.example.aopTask.controller.CustomerController.*(..))")
    public void validateUser(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username).get();
        if(user == null) {
            log.info("Specified user is not present in the DB");
        }
        else {
            Audit audit = new Audit();
            audit.setUserId(user.getId());
            audit.setUsername(user.getUsername());
            audit.setAction("LOGGED IN");
            audit.setTimestamp(LocalDateTime.now());

            auditRepository.save(audit);
        }
    }
}
