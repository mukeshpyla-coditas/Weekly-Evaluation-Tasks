package com.example.aopTask.aspect;

import com.example.aopTask.entity.Customer;
import com.example.aopTask.entity.Payment;
import com.example.aopTask.entity.Product;
import com.example.aopTask.repository.CustomerRepository;
import com.example.aopTask.repository.OrderRepository;
import com.example.aopTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AdviceEncapsulation {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Before("execution(* com.example.aopTask.service.CustomerService.place*(..))")
    public void checkQuantityBeforePlacingOrder(JoinPoint joinPoint) throws InsufficientResourcesException {
        Object[] args = joinPoint.getArgs();
        Customer customer = (Customer) args[0];
        List<Product> productList = customer.getProductList();
        for(Product product : productList) {
            Integer requestedQuantity = product.getQuantity();
            Integer availableQuantity = productRepository.findById(product.getId()).get().getQuantity();
            if(availableQuantity < requestedQuantity) {
                throw new InsufficientResourcesException("Specified quantity is not available");
            }
            product.setQuantity(availableQuantity - requestedQuantity);
            log.info("Remaining quantity of the product available: " + (availableQuantity - requestedQuantity));
            productRepository.save(product);
        }
    }

    @Around("execution(* com.example.aopTask.controller.CustomerController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("Total Execution time taken to execute " + proceedingJoinPoint.getSignature().toShortString() + " is: " + (end - begin) + "ms");
        return result;
    }

    // User Story - 4
    @Around("execution(* com.example.aopTask.service.*.*(..))")
    public Object logExecutionTimeForService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("Total Execution time taken to execute " + proceedingJoinPoint.getSignature().toShortString() + " is: " + (end - begin) + "ms");
        return result;
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aopTask.service.CustomerService.performPayment(..))",
            returning = "payment"
    )
    public void logPaymentStatus(JoinPoint joinPoint, Object payment) {
        Payment paymentObject = (Payment) payment;
        log.info("Payment is successful. The transaction Id is: " + paymentObject.getId());
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopTask.service.CustomerService.performPayment(..))",
            throwing = "exception"
    )
    public void paymentFailureLog(JoinPoint joinPoint, Exception exception) {
        log.info("There wsa a failure in performing the transaction.");
        log.info("The reason for exception is: " + exception.getMessage());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aopTask.service.ApplicationService.save*(..))",
            returning = "status"
    )
    public void resumeUpload(JoinPoint joinPoint, Object status) {
        log.info("Successfully uploaded the resume.");
        log.info("Status of upload is: " + status);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopTask.service.ApplicationService.save*(..))",
            throwing = "exception"
    )
    public void resumeUpload(JoinPoint joinPoint, Exception exception) {
        log.info("There is an exception while uploading the resume. Please check the reason of exception in the logs:");
        log.info("Exception: " + exception.getMessage());
    }
}
