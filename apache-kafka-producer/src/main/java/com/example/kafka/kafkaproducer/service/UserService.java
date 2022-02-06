package com.example.kafka.kafkaproducer.service;

import static com.example.kafka.kafkaproducer.config.KafkaConfiguration.GROUP_ID;
import static com.example.kafka.kafkaproducer.config.KafkaConfiguration.TOPIC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafka.kafkaproducer.model.User;


@Service
public class UserService
{

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	private Logger logger = LogManager.getLogger(UserService.class);

	@KafkaListener(
			topics = TOPIC,
			groupId = GROUP_ID,
			containerFactory = "kafkaUserListenerContainerFactory")
	public void consumeMessage(User user)
	{
		logger.info(String.format("Consumed message: %s", user));

	}

	public String publicMessage(User user)
	{

		kafkaTemplate.send(TOPIC, user);

		return "published successfully";

	}

}
