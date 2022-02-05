package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

/**
 * @author Celestino Agudo
 *
 */
@Component
public class BasketballCoach implements Coach {

  @Override
  public String getDailyWorkout() {
    return "Shoot 30-footer 1000 times";
  }

  @Override
  public String getDailyFortune() {
    return null;
  }
}
