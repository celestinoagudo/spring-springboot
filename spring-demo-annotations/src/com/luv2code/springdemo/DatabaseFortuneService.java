package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

/**
 * @author Celestino Agudo
 *
 */
@Component
public class DatabaseFortuneService implements FortuneService {

  @Override
  public String getFortune() {
    return String.format("%s >> Today is your lucky day!", DatabaseFortuneService.class);
  }
}
