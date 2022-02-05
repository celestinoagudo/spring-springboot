package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Order(2)
public class CloudLoggerAspect
{
	@Before("com.luv2code.aopdemo.aspect.PointcutExpressions.everyMethodThatIsNeitherSetterNorGetter()")
	public void logToCloud(JoinPoint joinPoint)
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
