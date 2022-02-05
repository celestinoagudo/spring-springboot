package com.luv2code.aopdemo;

public class Account
{
	private String level;

	private String name;

	/**
	 * @return the level
	 */
	public synchronized String getLevel()
	{
		return level;

	}

	/**
	 * @return the name
	 */
	public synchronized String getName()
	{
		return name;

	}

	/**
	 * @param level the level to set
	 */
	public synchronized void setLevel(String level)
	{
		this.level = level;

	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name)
	{
		this.name = name;

	}

}
