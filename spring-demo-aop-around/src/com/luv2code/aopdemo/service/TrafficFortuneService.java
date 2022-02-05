package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;


@Component
public class TrafficFortuneService
{

	public String getFortune()
	{

		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException interruptedException)
		{

		}

		String[] fortunes =
			{
				"Expect heavy traffic", "Expect light traffic", "Expect moderate traffic"
			};
		return fortunes[(int) Math.floor(Math.random() * fortunes.length)];

	}

}
