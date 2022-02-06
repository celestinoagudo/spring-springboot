package com.example.demo.student;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@DisplayName("Student Repository Unit Tests")
class StudentRepositoryTest
{

	@Autowired
	private StudentRepository unitUnderTest;

	@AfterEach
	void cleanUp()
	{
		unitUnderTest.deleteAll();

	}

	@Test
	@DisplayName("It should determine if the student associated to the email doesn't exist")
	void itShouldCheckIfStudentEmailDoesntExist()
	{
		//given
		String email = "test2@gmail.com";

		//when
		boolean exists = unitUnderTest.findStudentByEmail(email).isPresent();

		//then
		assertThat(exists).isFalse();

	}

	@Test
	@DisplayName("It should determine if the student associated to the email exists")
	void itShouldFindStudentByEmail()
	{
		//given
		Student student = new Student(LocalDate.of(1998, JANUARY, 5), "test1@gmail.com", "Test 1");
		unitUnderTest.save(student);

		//when
		boolean exists = unitUnderTest.findStudentByEmail("test1@gmail.com").isPresent();

		//then
		assertThat(exists).isTrue();

	}

}
