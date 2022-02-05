package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class PointcutExpressions
{
	@Pointcut("everyMethodInDao() && !(setterMethod() || getterMethod())")
	public void everyMethodThatIsNeitherSetterNorGetter()
	{}

	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.*(..))")
	private void everyMethodInDao()
	{}

	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.set*(..))")
	private void getterMethod()
	{}

	@Pointcut("execution(public * com.luv2code.aopdemo.dao.*.get*(..))")
	private void setterMethod()
	{}
}
