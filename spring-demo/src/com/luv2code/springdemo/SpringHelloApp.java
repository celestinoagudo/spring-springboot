package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class SpringHelloApp {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "applicationContext.xml"
    );
    //    Coach theCoach = context.getBean("myCoach", Coach.class);
    //    System.out.println(theCoach.getDailyWorkout());
    //    System.out.println(theCoach.getDailyFortune());
    //    context.close();

    CricketCoach myCricketCoach = context.getBean("myCricketCoach", CricketCoach.class);
    System.out.println(myCricketCoach.getDailyWorkout());
    System.out.println(myCricketCoach.getDailyFortune());
    System.out.println(myCricketCoach.getEmailAddress());
    System.out.println(myCricketCoach.getTeam());
    context.close();
  }
}
