package com.example.demo.movies.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect  // Indicates that this class is an aspect
@Component  // Marks this class as a Spring component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.demo.movies.service.*.*(..))")  // Executes before any method in the service package
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing method: {} in class: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning(pointcut = "execution(* com.example.demo.movies.service.*.*(..))", returning = "result")  // Executes after any method in the service package returns
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed: {} in class: {}, Return value: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName(), result);
    }
}
