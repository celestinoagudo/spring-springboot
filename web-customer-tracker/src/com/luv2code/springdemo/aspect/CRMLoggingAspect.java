package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CRMLoggingAspect
{
	private Logger moduleLogger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void everyMethodInControllerPackage()
	{}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void everyMethodInDaoPackage()
	{}

	@Pointcut("everyMethodInControllerPackage() || everyMethodInDaoPackage() || everyMethodInServicePackage()")
	private void everyMethodInEligiblePackages()
	{}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void everyMethodInServicePackage()
	{}

	@Before("everyMethodInEligiblePackages()")
	private void logCalledMethodAndArgumentsPassed(JoinPoint joinPoint)
	{
		moduleLogger
			.info(String.format("Calling the method: %s", joinPoint.getSignature().toLongString()));

		Object[] argumentsPassed = joinPoint.getArgs();

		for (Object argument : argumentsPassed)
		{
			moduleLogger.info(String.format("Argument: %s", argument));
		}

	}

	@AfterReturning(pointcut = "everyMethodInEligiblePackages()", returning = "result")
	private void logCalledMethodAndItsResult(JoinPoint joinPoint, Object result)
	{
		moduleLogger
			.info(String.format("Called the method: %s", joinPoint.getSignature().toLongString()));
		moduleLogger.info(String.format("Result: %s", result));

	}

}
