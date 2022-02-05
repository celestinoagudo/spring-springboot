package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class AnnotationDemoApp {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "applicationContext.xml"
    );
    TennisCoach tennisCoach = context.getBean("tennisCoach", TennisCoach.class);
    System.out.println(tennisCoach.getDailyWorkout());
    System.out.println(tennisCoach.getDailyFortune());
    //    BasketballCoach basketBallCoach = context.getBean(
    //      "basketballCoach",
    //      BasketballCoach.class
    //    );
    //    System.out.println(basketBallCoach.getDailyWorkout());
    //    System.out.println(basketBallCoach.getDailyFortune());
    context.close();
  }
}
