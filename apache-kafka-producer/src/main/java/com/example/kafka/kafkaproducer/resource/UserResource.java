package com.example.kafka.kafkaproducer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.kafkaproducer.model.User;
import com.example.kafka.kafkaproducer.service.UserService;


@RestController
@RequestMapping("kafka")
public class UserResource
{

	@Autowired
	private UserService userService;

	@PostMapping("/publish")
	public String postMessage(@RequestBody final User user)
	{
		return userService.publicMessage(user);

	}

}
