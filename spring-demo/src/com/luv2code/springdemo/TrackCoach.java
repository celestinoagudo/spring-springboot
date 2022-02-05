package com.luv2code.springdemo;

import com.luv2code.springdemo.dependency.injection.FortuneService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Celestino Agudo
 *
 */
public class TrackCoach implements Coach {
  private FortuneService fortuneService;

  public TrackCoach(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @Override
  public String getDailyWorkout() {
    return "Run a hard 5k";
  }

  @Override
  public String getDailyFortune() {
    return fortuneService.getFortune();
  }

  public void doMyStartupStuff() {
    String startupMessage = "Doing some startup stuffs";

    List<String> messages = new ArrayList<>();
    messages.add(startupMessage);
    messages.forEach(System.out::println);
  }

  public void doMyCleanUpStuff() {
    String cleanUpMessage = "Doing some clean up stuffs";

    List<String> messages = new ArrayList<>();
    messages.add(cleanUpMessage);
    messages.forEach(System.out::println);
  }
}
