package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;


public class MainDemoApp
{
	private static Logger moduleLogger = Logger.getLogger(MainDemoApp.class.getName());

	public static void main(String[] args)
	{
		try ( AnnotationConfigApplicationContext applicationContext =
			new AnnotationConfigApplicationContext(DemoConfig.class);)
		{
			TrafficFortuneService service =
				applicationContext.getBean("trafficFortuneService", TrafficFortuneService.class);

			moduleLogger.info(String.format("Fortune: %s", service.getFortune()));

		}

	}

}
