package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfig
{
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository)
	{
		return args -> {

			List<Student> data = Arrays.asList(
				new Student(LocalDate.of(2000, Month.JANUARY, 5), "test1@gmail.com", "Test 1"),
				new Student(LocalDate.of(1996, Month.JANUARY, 5), "test2@gmail.com", "Test 2"));

			studentRepository.saveAll(data);
		};

	}

}
