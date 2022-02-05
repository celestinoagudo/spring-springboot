package com.luv2code.springdemo;

import com.luv2code.springdemo.dependency.injection.FortuneService;

/**
 * @author Celestino Agudo
 *
 */
public class BasketballCoach implements Coach {
  private FortuneService fortuneService;

  public BasketballCoach(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @Override
  public String getDailyWorkout() {
    return "Shoot a 30-footer 10 times";
  }

  @Override
  public String getDailyFortune() { // TODO Auto-generated method stub
    return fortuneService.getFortune();
  }
}
