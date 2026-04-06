package com.example.aopTask.aspect;

import com.example.aopTask.entity.Audit;
import com.example.aopTask.entity.Customer;
import com.example.aopTask.entity.User;
import com.example.aopTask.repository.AuditRepository;
import com.example.aopTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditRepository auditRepository;
    private final UserRepository userRepository;

    @After("execution(* com.example.aopTask.service.CustomerService.place*(..))")
    public void logCreation(JoinPoint joinPoint) {
        Audit audit = new Audit();
        Object[] args = joinPoint.getArgs();
        Customer customer = (Customer) args[0];
        Integer customerId = customer.getId();

        audit.setUserId(customerId);
        audit.setUsername(customer.getName());
        audit.setAction("ORDER_CREATED");
        audit.setTimestamp(LocalDateTime.now());

        auditRepository.save(audit);
    }

    @After("execution(* com.example.aopTask.service.ProductService.update*(..))")
    public void updateQuantityLog(JoinPoint joinPoint) {
        Audit audit = new Audit();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();

        audit.setUserId(user.getId());
        audit.setUsername(username);
        audit.setTimestamp(LocalDateTime.now());
        audit.setAction("UPDATE_QUANTITY");

        auditRepository.save(audit);
    }

    @After("execution(* com.example.aopTask.service.OrderService.cancel*(..))")
    public void cancelOrderLog(JoinPoint joinPoint) {
        Audit audit = new Audit();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).get();

        audit.setUserId(user.getId());
        audit.setUsername(username);
        audit.setTimestamp(LocalDateTime.now());
        audit.setAction("CANCELLED_ORDER");

        auditRepository.save(audit);
    }
}
