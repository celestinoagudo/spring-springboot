package com.luv2code.springdemo.dependency.injection;

/**
 * @author Celestino Agudo
 *
 */
public class RandomFortuneService implements FortuneService {
  private String[] fortunes = {
    "Fortune 1",
    "Fortune 2",
    "Fortune 3",
    "Fortune 4",
    "Fortune 5",
    "Fortune 6"
  };

  @Override
  public String getFortune() {
	  return fortunes[(int) Math.floor(Math.random() * fortunes.length)];
  }
}
