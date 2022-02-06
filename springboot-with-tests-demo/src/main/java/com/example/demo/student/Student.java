package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class Student
{

	@Transient
	private Integer age;

	private LocalDate dateOfBirth;

	private String email;

	@Id
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long id;

	private String name;

	public Student( )
	{}

	public Student(LocalDate dateOfBirth, String email, Long id, String name)
	{
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.id = id;
		this.name = name;

	}

	public Student(LocalDate dateOfBirth, String email, String name)
	{
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.name = name;

	}

	public Student(StudentRequestModel requestModel)
	{
		this.dateOfBirth = requestModel.getDateOfBirth();
		this.email = requestModel.getEmail();
		this.name = requestModel.getName();

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
		Student other = (Student) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
			&& Objects.equals(name, other.name);

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

	@Override
	public int hashCode()
	{
		return Objects.hash(age, dateOfBirth, email, id, name);

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

	@Override
	public String toString()
	{
		return "Student [age=" + age + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", id="
			+ id + ", name=" + name + "]";

	}

}
