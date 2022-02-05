package com.luv2code.springdemo.bean.scope;

import com.luv2code.springdemo.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class BeanScopeDemoApp {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "beanScopeApplicationContext.xml"
    );

    Coach coach = context.getBean("myCoach", Coach.class);
    Coach coach1 = context.getBean("myCoach", Coach.class);

    System.out.println(coach == coach1);

    //    System.out.println(coach.getDailyFortune());
    //    System.out.println(coach.getDailyWorkout());
    context.close();
  }
}
