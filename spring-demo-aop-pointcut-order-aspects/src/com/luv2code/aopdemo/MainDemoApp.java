package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;


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
			accountDAO.setAccountName("Test Acct Name");
			accountDAO.setAccountNumber("Test Acct Number");

			//get the bean from the screen container
			MembershipDAO membershipDAO =
				applicationContext.getBean("membershipDAO", MembershipDAO.class);

			//call the business method
			accountDAO.addAccount(new Account());

			accountDAO.doWork();

			//call the business method
			membershipDAO.addAccount();

			membershipDAO.doWork();
		}

	}

}
