
package com.luv2code.springdemo;

/**
 * @author Celestino Agudo
 *
 */
public class ProgrammingCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public ProgrammingCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Solve 100+ DP Problems";
	}

	@Override
	public String getDailyFortune() {
		
		return fortuneService.getFortune();
	}

}
