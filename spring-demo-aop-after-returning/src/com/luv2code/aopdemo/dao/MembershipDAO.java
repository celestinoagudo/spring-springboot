package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;


@Component
public class MembershipDAO
{
	public void addAccount()
	{
		System.out.println(String.format("%s : DOING MY DB WORK: ADDING AN ACCOUNT", getClass()));

	}

	public void doWork()
	{
		System.out.println(String.format("%s : DOING MY DB WORK: DO WORK", getClass()));

	}

}
