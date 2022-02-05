
package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Celestino Agudo
 *
 */
@Configuration
public class ProgrammingCoachConfig {

	@Bean
	public Coach programmingCoach() {

		return new ProgrammingCoach(programmingFortuneService());

	}

	@Bean
	public FortuneService programmingFortuneService() {
		return new ProgrammingFortuneService();
	}

}
