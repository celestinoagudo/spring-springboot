package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;


public class StudentRequestModel
{

	private Integer age;

	private LocalDate dateOfBirth;

	private String email;

	private Long id;

	private String name;

	public StudentRequestModel( )
	{}

	public StudentRequestModel(LocalDate dateOfBirth, String email, Long id, String name)
	{
		super();
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.name = name;
		this.id = id;

	}

	/**
	 * @param dateOfBirth
	 * @param email
	 * @param name
	 */
	public StudentRequestModel(LocalDate dateOfBirth, String email, String name)
	{
		super();
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.name = name;

	}

	/**
	 * @return the age
	 */
	public synchronized Integer getAge()
	{
		return Period.between(dateOfBirth, LocalDate.now()).getYears();

	}

	/**
	 * @return the dateOfBirth
	 */
	public synchronized LocalDate getDateOfBirth()
	{
		return dateOfBirth;

	}

	/**
	 * @return the email
	 */
	public synchronized String getEmail()
	{
		return email;

	}

	/**
	 * @return the id
	 */
	public synchronized Long getId()
	{
		return id;

	}

	/**
	 * @return the name
	 */
	public synchronized String getName()
	{
		return name;

	}

	/**
	 * @param age the age to set
	 */
	public synchronized void setAge(Integer age)
	{
		this.age = age;

	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public synchronized void setDateOfBirth(LocalDate dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;

	}

	/**
	 * @param email the email to set
	 */
	public synchronized void setEmail(String email)
	{
		this.email = email;

	}

	/**
	 * @param id the id to set
	 */
	public synchronized void setId(Long id)
	{
		this.id = id;

	}

	/**
	 * @param name the name to set
	 */
	public synchronized void setName(String name)
	{
		this.name = name;

	}

}
