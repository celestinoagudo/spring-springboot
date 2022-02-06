package com.example.demo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;


class DemoApplicationTests
{

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers()
	{
		//given
		int a = 20;
		int b = 30;

		//when
		int result = underTest.add(a, b);

		//then
		assertThat(result).isEqualTo(50);

	}

	class Calculator
	{
		int add(int a, int b)
		{
			return a + b;

		}
	}

}
