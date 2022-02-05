package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Component
public class AccountDAO
{
	private String accountName;

	private String accountNumber;

	public void addAccount(Account account)
	{
		System.out.println(String.format("%s : DOING MY DB WORK: ADDING AN ACCOUNT", getClass()));

	}

	public boolean doWork()
	{
		System.out.println(String.format("%s : DOING MY DB WORK: DO WORK", getClass()));

		return true;

	}

	/**
	 * @return the accountName
	 */
	public synchronized String getAccountName()
	{
		return accountName;

	}

	/**
	 * @return the accountNumber
	 */
	public synchronized String getAccountNumber()
	{
		return accountNumber;

	}

	/**
	 * @param accountName the accountName to set
	 */
	public synchronized void setAccountName(String accountName)
	{
		System.out.println(String.format("Setting account name %s", accountName));
		this.accountName = accountName;

	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public synchronized void setAccountNumber(String accountNumber)
	{
		System.out.println(String.format("Setting account number %s", accountNumber));
		this.accountNumber = accountNumber;

	}

}
