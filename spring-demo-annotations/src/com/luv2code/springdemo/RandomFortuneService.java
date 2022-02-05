package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

/**
 * @author Celestino Agudo
 *
 */
@Component
public class RandomFortuneService implements FortuneService {
  private String[] data = {
    "Hi today is your lucky day!",
    "Hello today is your lucky day!",
    "Hello Hi, today is your lucky day!"
  };

  @Override
  public String getFortune() {
    return data[(int) (Math.random() * data.length)];
  }
}
