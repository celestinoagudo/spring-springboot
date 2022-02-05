package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
	@AfterReturning(
			pointcut = "com.luv2code.aopdemo.aspect.PointcutExpressions.getAccountsMethod()",
			returning = "result")
	public void afterGetAccountsAdvice(JoinPoint joinPoint, List<Account> result)
	{
		System.out.println("After getting the accouts...");
		String shortMethodSignature = joinPoint.getSignature().toShortString();
		System.out.println(String.format("Method Signature: %s", shortMethodSignature));
		result.replaceAll(account -> {
			account.setName(account.getName().toUpperCase());
			return account;
		});
		result.forEach(System.out::println);

	}

	@Before("com.luv2code.aopdemo.aspect.PointcutExpressions.everyMethodThatIsNeitherSetterNorGetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint)
	{
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] methodArguments = joinPoint.getArgs();

		System.out.println(String.format("Method: %s", methodSignature));

		for (Object argument : methodArguments)
		{
			System.out.println(String.format("Argument: %s", argument));
		}

	}

}
