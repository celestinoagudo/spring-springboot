package com.apachekafka.multipartitions.model;

public class User
{
	private String department;

	private String name;

	private Long salary;

	public User( )
	{}

	public User(String department, String name, Long salary)
	{
		super();
		this.department = department;
		this.name = name;
		this.salary = salary;

	}

	/**
	 * @return the department
	 */
	public synchronized String getDepartment()
	{
		return department;

	}

	/**
	 * @return the name
	 */
	public synchronized String getName()
	{
		return name;

	}

	/**
	 * @return the salary
	 */
	public synchronized Long getSalary()
	{
		return salary;

	}

	/**
	 * @param department the department to set
	 */
	public synchronized void setDepartment(String department)
	{
		this.department = department;

	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name)
	{
		this.name = name;

	}

	/**
	 * @param salary the salary to set
	 */
	public synchronized void setSalary(Long salary)
	{
		this.salary = salary;

	}

	@Override
	public String toString()
	{
		return "User [department=" + department + ", name=" + name + ", salary=" + salary + "]";

	}

}
