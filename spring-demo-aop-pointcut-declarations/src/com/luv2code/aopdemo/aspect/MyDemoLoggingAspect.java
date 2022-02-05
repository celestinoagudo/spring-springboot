package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyDemoLoggingAspect
{
	@Before("everyMethodInDao() && !(setterMethod() || getterMethod())")
	public void beforeAddAccountAdvice()
	{
		System.out.println("=========>>>>> Executing beforeAddAccountAdvice advice");

	}

	@Before("everyMethodInDao() && !(setterMethod() || getterMethod())")
	public void performAnalytics()
	{
		System.out.println("=========>>>>> Executing performAnalytics advice");

	}

	/*
	 * matches any public method under the package com.luv2code.aopdemo.dao 
	 * that is accepting any number/type of parameters  
	 */
	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
	private void everyMethodInDao()
	{}

	/*
	 * matches getter methods under the package
	 */
	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.set*(..))")
	private void getterMethod()
	{}

	/*
	 * matches getter methods under the package
	 */
	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.get*(..))")
	private void setterMethod()
	{}

}
