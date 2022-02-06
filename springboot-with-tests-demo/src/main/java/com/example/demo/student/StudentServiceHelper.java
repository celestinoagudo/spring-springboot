package com.example.demo.student;

import java.util.function.BiPredicate;
import java.util.function.Predicate;


public class StudentServiceHelper
{

	protected static boolean isNullOrEmpty(String text)
	{
		return (text == null) || text.isEmpty();

	}

	protected static boolean isSatisfied(final BiPredicate<Student, Student> rule,
		final Student existing, final Student payload)
	{

		return rule.test(existing, payload);

	}

	protected static boolean isSatisfied(final Predicate<Student> rule, final Student student)
	{
		return rule.test(student);

	}

	private StudentServiceHelper( )
	{}
}
