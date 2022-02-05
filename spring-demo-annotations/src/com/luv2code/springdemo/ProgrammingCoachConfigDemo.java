
package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class ProgrammingCoachConfigDemo {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(ProgrammingCoachConfig.class);
		
		Coach programmingCoach = context.getBean(
				"programmingCoach", ProgrammingCoach.class);
		System.out.println(programmingCoach.getDailyWorkout());
		System.out.println(programmingCoach.getDailyFortune());
		context.close();
		
	}

}
