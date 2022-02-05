package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Celestino Agudo
 *
 */
@Component
public class RandomFortuneServiceFromPropFile implements FortuneService {
  @Value("${prop.fortuneMessages}")
  private String fortuneMessageRaw;

  @Override
  public String getFortune() {
    String[] randomMessages = fortuneMessageRaw.trim().split(",");
    return randomMessages[(int) (Math.random() * randomMessages.length)];
  }
}
