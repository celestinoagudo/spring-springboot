package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyDemoLoggingAspect
{

	// this is where we add all of our related advises for logging

	/*matches any public method under the package com.luv2code.aopdemo.dao 
	that is accepting any number/type of parameters*/
	@Before("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice()
	{
		System.out.println("=========>>>>> Executing @Before advice on addAccount()");

	}

}
