package com.feuji.security.aop;

import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.feuji.security.entity.Student;
@Aspect
@Component
public class studentaop {


	//generate logger object by using loggerfactory
	private static final Logger log = LoggerFactory.getLogger(studentaop.class);


	// Generate before advice for com.feuji.security.controller.Studentcontroller include method signature
	@Before("execution(* com.feuji.security.controller.Studentcontroller.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		// write log.warn message in the console
		log.info(joinpoint.getSignature().getName() + " method is called in before advice");
	}
	// Generate after advice for com.feuji.security.controller.Studentcontroller
	@After("execution(* com.feuji.security.controller.Studentcontroller.*(..))")
	public void afterAdvice(JoinPoint joinpoint) {
		//write log.warn message in the console
		log.info(joinpoint.getSignature().getName() + " method is called in after advice");
	}
	// Generate around advice
	@Around("execution(* com.feuji.security.controller.Studentcontroller.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable {	
		//write log.warn message in the console
		log.info(joinpoint.getSignature().getName() + " method is called in around advice");

		// Proceed with the original method execution
		Object result = joinpoint.proceed();

		log.info("Around method - After method execution");

		// Return the result of the original method
		return result;
	}
	
	// generate after returning advice for com.feuji.security.controller.Studentcontroller and returns studnt object
	@AfterReturning(pointcut = "execution(* com.feuji.security.controller.Studentcontroller.getStudent(..))", returning = "studentOptional")
	public void afterReturning(JoinPoint joinPoint, Optional<Student> studentOptional) {
	    // write log.warn message in the console	
	    log.info(joinPoint.getSignature().getName() + " method is called in after returning advice");
	    
	    // Check if the Optional contains a value before logging
	    if (studentOptional.isPresent()) {
	        log.info("Method returned value is : " + studentOptional.get());
	    } else {
	        log.info("Method returned an empty Optional.");
	    }
	}
	
	// generate after throwing advice for com.feuji.security.controller.Studentcontroller and returns exception
	@AfterThrowing(pointcut = "execution(* com.feuji.security.controller.Studentcontroller.getStudent(..))", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
		// write log.warn message in the console
		log.info(joinPoint.getSignature().getName() + " method is called in after throwing advice");
		log.info("An exception has been thrown: " + exception);
	}

}


