package com.apachekafka.multipartitions.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apachekafka.multipartitions.model.User;
import com.apachekafka.multipartitions.service.UserService;


@RestController
@RequestMapping("kafka")
public class UserResource
{

	@Autowired
	private UserService userService;

	@PostMapping("/publish")
	public String postMessage(@RequestBody final User user)
	{
		return userService.publishMessageToFirstPartition(user);

	}

	@PostMapping("/publish1")
	public String postMessage1(@RequestBody final User user)
	{
		return userService.publishMessageToSecondPartition(user);

	}

	@PostMapping("/publish2")
	public String postMessage2(@RequestBody final User user)
	{
		return userService.publishMessageToThirdPartition(user);

	}

}
