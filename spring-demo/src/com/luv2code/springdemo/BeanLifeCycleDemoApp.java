package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class BeanLifeCycleDemoApp {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "beanLifeCycleApplicationContext.xml"
    );

    TrackCoach coach = context.getBean("myCoach", TrackCoach.class);
    TrackCoach coach1 = context.getBean("myCoach", TrackCoach.class);

    //    System.out.println(coach.getDailyFortune());
    //    System.out.println(coach.getDailyWorkout());
    context.close();
  }
}
