package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Celestino Agudo
 *
 */
public class AnnotationBeanScopeDemoApp {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "applicationContext.xml"
    );
    TennisCoach tennisCoach = context.getBean("tennisCoach", TennisCoach.class);
    TennisCoach tennisCoach2 = context.getBean("tennisCoach", TennisCoach.class);
    System.out.println(tennisCoach == tennisCoach2);
    context.close();
  }
}
