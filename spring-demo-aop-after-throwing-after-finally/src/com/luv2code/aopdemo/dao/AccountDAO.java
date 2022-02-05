package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

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

	public List<Account> getAccounts(boolean tripWire)
	{
		if (tripWire)
		{
			throw new RuntimeException("Tripped the wire!");
		}
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account("Account Name 1", "Level 1"));
		accounts.add(new Account("Account Name 2", "Level 2"));
		accounts.add(new Account("Account Name 3", "Level 3"));
		accounts.add(new Account("Account Name 4", "Level 4"));
		accounts.add(new Account("Account Name 5", "Level 5"));

		return accounts;

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
