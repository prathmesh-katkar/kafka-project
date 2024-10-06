package com.prathmeshsales.WebStore.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.prathmeshsales.baseorder_service.dto.OrderDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducer {

	private NewTopic topic;
	private KafkaTemplate<String, OrderDetails> kafkaTemplate;

	public KafkaProducer(NewTopic topic, KafkaTemplate<String, OrderDetails> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(OrderDetails orderDeatils) {

		log.info(String.format("Order Event -> %s", orderDeatils.toString()));

		Message<OrderDetails> message = MessageBuilder.withPayload(orderDeatils)
				.setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();

		kafkaTemplate.send(message);
	}

}
