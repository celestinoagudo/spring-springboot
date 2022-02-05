package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Celestino Agudo
 *
 */
@Component
public class TennisCoach implements Coach {
  @Autowired
  @Qualifier("randomFortuneServiceFromPropFile")
  private FortuneService fortuneService;

  // Injection via constructor

  //  @Autowired
  //  public TennisCoach(FortuneService fortuneService) {
  //    this.fortuneService = fortuneService;
  //  }

  public TennisCoach() {}

  @Override
  public String getDailyWorkout() {
    return "Practice your backhand volley";
  }

  @Override
  public String getDailyFortune() {
    return fortuneService.getFortune();
  }
  
  @PostConstruct
  public void doMyStartupStuff() {
	  System.out.println(String.format(">> %s do my startup stuff", TennisCoach.class));
  }
  
  @PreDestroy
  public void doMyCleanUpStuff() {
	  System.out.println(String.format(">> %s do my cleanup stuff", TennisCoach.class));
  }
  // Injection via setter method

  //  @Autowired
  //  public void setFortuneService(FortuneService fortuneService) {
  //    this.fortuneService = fortuneService;
  //  }

  // Injection via method
  //  @Autowired
  //  public void setInstanceField(FortuneService fortuneService) {
  //    this.fortuneService = fortuneService;
  //  }
}
