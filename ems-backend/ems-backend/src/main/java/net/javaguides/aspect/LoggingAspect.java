package net.javaguides.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // pointcut：service.impl all methods
    @Pointcut("execution(* net.javaguides.service.impl.*.*(..))")
    public void serviceMethods() {}

    // around：time
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String method = joinPoint.getSignature().toShortString();

        log.info(">>> Entering: {}", method);
        Object result = joinPoint.proceed();  // method execution
        long elapsed = System.currentTimeMillis() - start;
        log.info("<<< Exiting: {} | {}ms", method, elapsed);

        return result;
    }

    // abnormal notices
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logException(Exception ex) {
        log.error("Exception in service: {}", ex.getMessage());
    }
}