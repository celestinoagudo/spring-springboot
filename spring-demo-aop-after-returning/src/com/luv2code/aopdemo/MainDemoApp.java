package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;


public class MainDemoApp
{

	public static void main(String[] args)
	{
		//read spring config java class
		try ( AnnotationConfigApplicationContext applicationContext =
			new AnnotationConfigApplicationContext(DemoConfig.class);)
		{

			//get the bean from the screen container
			AccountDAO accountDAO = applicationContext.getBean("accountDAO", AccountDAO.class);

			List<Account> accounts = accountDAO.getAccounts();
			System.out.println("At Main Program");
			accounts.forEach(System.out::println);

		}

	}

}
