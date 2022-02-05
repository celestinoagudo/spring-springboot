package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Aspect
@Component
@Order(1)
public class AppLoggerAspect
{
	private static Logger moduleLogger = Logger.getLogger(AppLoggerAspect.class.getName());

	@After(value = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()")
	public void afterGetAccounts(JoinPoint joinPoint)
	{

		moduleLogger.info("After getting accounts regardless of the result ...");
		String shortMethodSignature = joinPoint.getSignature().toShortString();
		moduleLogger.info(String.format("Method Signature: %s", shortMethodSignature));

	}

	@AfterReturning(
			pointcut = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()",
			returning = "result")
	public void afterGetAccountsReturned(JoinPoint joinPoint, List<Account> result)
	{
		moduleLogger.info("After getting the accounts...");
		String shortMethodSignature = joinPoint.getSignature().toShortString();
		moduleLogger.info(String.format("Method Signature: %s", shortMethodSignature));
		result.replaceAll(account -> {
			account.setName(account.getName().toUpperCase());
			return account;
		});
		result.forEach(System.out::println);

	}

	@AfterThrowing(
			pointcut = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()",
			throwing = "exception")
	public void afterGetAccountsThrownAnException(JoinPoint joinPoint, Throwable exception)
	{
		moduleLogger.info("After throwing an exception...");
		moduleLogger.info(String.format("Method: %s", joinPoint.getSignature().toShortString()));
		moduleLogger.info(String.format("Error: %s", exception.getMessage()));

	}

	@Around(value = "com.luv2code.aopdemo.aspect.PointcutExpressions.getFortuneMethod()")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{

		moduleLogger
			.info(String.format("Method: %s", proceedingJoinPoint.getSignature().toShortString()));

		long startsAt = System.currentTimeMillis();

		Object result = null;
		try
		{
			result = proceedingJoinPoint.proceed();
		}
		catch (Exception exception)
		{
			moduleLogger
				.warning(String.format("An exception is encountered: %s", exception.getMessage()));

			//			result = "An exception happened but we swallowed it for you!";
			throw exception;
		}

		long endsAt = System.currentTimeMillis();

		moduleLogger.info(String.format("Method execution time is: %.2f seconds",
			((endsAt - startsAt) / 1000.00)));

		return result;

	}

	@Before("com.luv2code.aopdemo.aspect.PointcutExpressions.everyMethodThatIsNeitherSetterNorGetter()")
	public void beforeAddingAccount(JoinPoint joinPoint)
	{
		moduleLogger.info("Before adding an account...");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] methodArguments = joinPoint.getArgs();

		moduleLogger.info(String.format("Method: %s", methodSignature));

		for (Object argument : methodArguments)
		{
			moduleLogger.info(String.format("Argument: %s", argument));
		}

	}

}
