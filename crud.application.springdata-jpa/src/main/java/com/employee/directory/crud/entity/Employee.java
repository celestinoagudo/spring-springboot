package com.employee.directory.crud.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employee")
public class Employee
{

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "last_name")
	private String lastName;

	public Employee( )
	{

	}

	/**
	 * @param email
	 * @param firstName
	 * @param id
	 * @param lastName
	 */
	public Employee(String email, String firstName, String lastName)
	{
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
			&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName);

	}

	/**
	 * @return the email
	 */
	public synchronized String getEmail()
	{
		return email;

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
	public synchronized Long getId()
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
		return Objects.hash(email, firstName, id, lastName);

	}

	/**
	 * @param email the email to set
	 */
	public synchronized void setEmail(String email)
	{
		this.email = email;

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
	public synchronized void setId(Long id)
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
		return "Employee [email=" + email + ", firstName=" + firstName + ", id=" + id
			+ ", lastName=" + lastName + "]";

	}

}
