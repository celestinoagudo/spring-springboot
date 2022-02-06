package com.example.kafka.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.kafka.kafkaproducer.model.User;


@EnableKafka
@Configuration
public class KafkaConfiguration
{
	//should be in application.properties
	public static final String GROUP_ID = "new_topic_grp_id";

	public static final String SERVER = "192.168.1.132:9092";

	public static final String TOPIC = "NEW_TOPIC";

	@Bean
	public ConsumerFactory<String, User> consumerFactory()
	{

		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
		configuration.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(configuration, new StringDeserializer(),
			new JsonDeserializer<>(User.class));

	}

	@Bean
	public KafkaTemplate<String, User> kafkaTemplate()
	{
		return new KafkaTemplate<>(producerFactory());

	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaUserListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory =
			new ConcurrentKafkaListenerContainerFactory<>();

		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

		return kafkaListenerContainerFactory;

	}

	@Bean
	public ProducerFactory<String, User> producerFactory()
	{

		Map<String, Object> configuration = new HashMap<>();
		configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
		configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(configuration);

	}

}
