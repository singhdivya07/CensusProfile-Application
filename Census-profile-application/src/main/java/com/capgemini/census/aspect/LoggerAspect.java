package com.capgemini.census.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * This class records unusual circumstances or errors that may be happening in the program.
 * It gets the information about what is going on in the application.
 * 
 * @author HP
 *
 */
@Aspect
@Configuration
@Slf4j
public class LoggerAspect {


	@Pointcut("execution(* com.capgemini.census.controller.UserController.*(..))")
	public void applicationPackagePointcut() {

	}
	
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering method : {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				Object result = joinPoint.proceed();
				if (log.isDebugEnabled()) {
					if(joinPoint.getSignature().getName().equals("getCustomerById")) {
						log.info("Returning details of 1 customer");
					}else if(joinPoint.getSignature().getName().equals("getAllCustomers")) {
						log.info("Returning details of all customers");
					}
					log.debug("Exiting method: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
							joinPoint.getSignature().getName(), result);
				}
				return result;
			} catch (Exception e) {
				log.error("Error in {}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
				log.error(e.getMessage());
				throw e;
			}
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
