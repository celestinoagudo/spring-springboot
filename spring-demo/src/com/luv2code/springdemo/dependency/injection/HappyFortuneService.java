package com.luv2code.springdemo.dependency.injection;

/**
 * @author Celestino Agudo
 * 
 * DI
 *
 */
public class HappyFortuneService implements FortuneService {

  @Override
  public String getFortune() {
    return "You're fortunate";
  }
}
