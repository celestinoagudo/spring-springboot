package com.apachekafka.multipartitions.service;

import static com.apachekafka.multipartitions.config.KafkaConfiguration.GROUP_ID;
import static com.apachekafka.multipartitions.config.KafkaConfiguration.TOPIC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.apachekafka.multipartitions.model.User;


@Service
public class UserService
{

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	private Logger logger = LogManager.getLogger(UserService.class);

	@KafkaListener(
			topicPartitions = @TopicPartition(topic = TOPIC, partitions = "2"),
			groupId = GROUP_ID,
			containerFactory = "kafkaUserListenerContainerFactory")
	public void consumeMessage(User user)
	{
		logger.info(String.format("Consumed message %s", user));

	}

	@KafkaListener(
			topicPartitions = @TopicPartition(topic = TOPIC, partitions = "0"),
			groupId = GROUP_ID,
			containerFactory = "kafkaUserListenerContainerFactory")
	public void consumeMessage1(User user)
	{
		logger.info(String.format("Consumed message %s", user));

	}

	@KafkaListener(
			topicPartitions = @TopicPartition(topic = TOPIC, partitions = "1"),
			groupId = GROUP_ID,
			containerFactory = "kafkaUserListenerContainerFactory")
	public void consumeMessage2(User user)
	{
		logger.info(String.format("Consumed message %s", user));

	}

	public String publishMessageToFirstPartition(User user)
	{

		kafkaTemplate.send(TOPIC, 0, "key-30000000", user);

		return "published successfully [0]";

	}

	public String publishMessageToSecondPartition(User user)
	{

		kafkaTemplate.send(TOPIC, 1, "key-30000010", user);

		return "published successfully [1]";

	}

	public String publishMessageToThirdPartition(User user)
	{

		kafkaTemplate.send(TOPIC, 2, "key-30000200", user);

		return "published successfully [2]";

	}

}
