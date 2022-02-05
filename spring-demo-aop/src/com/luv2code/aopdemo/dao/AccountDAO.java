package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Component
public class AccountDAO
{
	public void addAccount(Account account)
	{
		System.out.println(String.format("%s : DOING MY DB WORK: ADDING AN ACCOUNT", getClass()));

	}

	public boolean doWork()
	{
		System.out.println(String.format("%s : DOING MY DB WORK: DO WORK", getClass()));

		return true;

	}

}
