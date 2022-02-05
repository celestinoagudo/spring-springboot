package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	@After(value = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()")
	public void afterGetAccounts(JoinPoint joinPoint)
	{

		System.out.println("After getting accounts regardless of the result ...");
		String shortMethodSignature = joinPoint.getSignature().toShortString();
		System.out.println(String.format("Method Signature: %s", shortMethodSignature));

	}

	@AfterReturning(
			pointcut = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()",
			returning = "result")
	public void afterGetAccountsReturned(JoinPoint joinPoint, List<Account> result)
	{
		System.out.println("After getting the accounts...");
		String shortMethodSignature = joinPoint.getSignature().toShortString();
		System.out.println(String.format("Method Signature: %s", shortMethodSignature));
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
		System.out.println("After throwing an exception...");
		System.out.println(String.format("Method: %s", joinPoint.getSignature().toShortString()));
		System.out.println(String.format("Error: %s", exception.getMessage()));

	}

	@Before("com.luv2code.aopdemo.aspect.PointcutExpressions.everyMethodThatIsNeitherSetterNorGetter()")
	public void beforeAddingAccount(JoinPoint joinPoint)
	{
		System.out.println("Before adding an account...");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] methodArguments = joinPoint.getArgs();

		System.out.println(String.format("Method: %s", methodSignature));

		for (Object argument : methodArguments)
		{
			System.out.println(String.format("Argument: %s", argument));
		}

	}

}
