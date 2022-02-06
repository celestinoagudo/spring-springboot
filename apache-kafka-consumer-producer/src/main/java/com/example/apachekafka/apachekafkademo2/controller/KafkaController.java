package com.example.apachekafka.apachekafkademo2.controller;

import static com.example.apachekafka.apachekafkademo2.constants.KafkaConstants.TOPIC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaController
{

	private KafkaTemplate<String, String> kafkaTemplate;

	private Logger logger = LogManager.getLogger(KafkaController.class);

	@Autowired
	public KafkaController(KafkaTemplate<String, String> kafkaTemplate)
	{
		this.kafkaTemplate = kafkaTemplate;

	}

	@KafkaListener(topics = TOPIC)
	public void listenKafka(String message)
	{
		logger.info("Messaged coming from Kafka {}", message);

	}

	@GetMapping("/sendMessage")
	public void sendMessage(@RequestParam String message)
	{
		kafkaTemplate.send(TOPIC, message);
		logger.info(message);

	}

}
