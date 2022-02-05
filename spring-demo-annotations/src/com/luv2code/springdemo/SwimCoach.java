
package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Celestino Agudo
 *
 */
public class SwimCoach implements Coach {
	
	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;

	private FortuneService fortuneService;

	public SwimCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Swim 1000 meters as a warm up and email to: " + email;
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune() + String.format("for team: %s", team);
	}

}
