package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;


@Component
public class TrafficFortuneService
{
	private static Logger moduleLogger = Logger.getLogger(TrafficFortuneService.class.getName());

	public String getFortune(boolean tripWire)
	{
		if (tripWire)
		{
			throw new RuntimeException("Tripped the wire!");
		}

		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException interruptedException)
		{
			moduleLogger.warning(
				String.format("An exception is encountered %s", interruptedException.getMessage()));

		}

		String[] fortunes =
			{
				"Expect heavy traffic", "Expect light traffic", "Expect moderate traffic"
			};
		return fortunes[(int) Math.floor(Math.random() * fortunes.length)];

	}

}
