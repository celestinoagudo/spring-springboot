package com.luv2code.springdemo;

import com.luv2code.springdemo.dependency.injection.FortuneService;

/**
 * @author Celestino Agudo
 *
 * dependency injection via setter injection
 *
 */
public class CricketCoach implements Coach {
  private FortuneService fortuneService;
  private String emailAddress;
  private String team;

  /**
   * @param emailAddress the emailAddress to set
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * @param team the team to set
   */
  public void setTeam(String team) {
    this.team = team;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getTeam() {
    return team;
  }

  public CricketCoach() {}

  /**
   * @param fortuneService the fortuneService to set
   */
  public void setFortuneService(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @Override
  public String getDailyWorkout() {
    return "Perform Cricket";
  }

  @Override
  public String getDailyFortune() {
    return fortuneService.getFortune();
  }
}
