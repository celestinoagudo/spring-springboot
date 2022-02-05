package com.luv2code.springdemo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer
{
	@Column(name = "email")
	private String emailAddress;

	@Column(name = "first_name")
	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "last_name")
	private String lastName;

	public Customer( )
	{}

	public Customer(String firstName, String lastName, String emailAddress)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;

	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass()))
		{
			return false;
		}
		Customer other = (Customer) obj;
		return Objects.equals(firstName, other.firstName) && (id == other.id)
			&& Objects.equals(lastName, other.lastName);

	}

	/**
	 * @return the emailAddress
	 */
	public synchronized String getEmailAddress()
	{
		return emailAddress;

	}

	/**
	 * @return the firstName
	 */
	public synchronized String getFirstName()
	{
		return firstName;

	}

	/**
	 * @return the id
	 */
	public synchronized int getId()
	{
		return id;

	}

	/**
	 * @return the lastName
	 */
	public synchronized String getLastName()
	{
		return lastName;

	}

	@Override
	public int hashCode()
	{
		return Objects.hash(firstName, id, lastName);

	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public synchronized void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;

	}

	/**
	 * @param firstName the firstName to set
	 */
	public synchronized void setFirstName(String firstName)
	{
		this.firstName = firstName;

	}

	/**
	 * @param id the id to set
	 */
	public synchronized void setId(int id)
	{
		this.id = id;

	}

	/**
	 * @param lastName the lastName to set
	 */
	public synchronized void setLastName(String lastName)
	{
		this.lastName = lastName;

	}

	@Override
	public String toString()
	{
		return "Customer [emailAddress=" + emailAddress + ", firstName=" + firstName + ", id=" + id
			+ ", lastName=" + lastName + "]";

	}

}
