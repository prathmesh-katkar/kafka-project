package com.prathmesh.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.prathmesh.notification_service.dto.payLoad;
import com.prathmesh.notification_service.mailservice.MailNotificationService;
import com.prathmeshsales.baseorder_service.dto.OrderDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailConsumer {

	private MailNotificationService notificationService;

	public MailConsumer(MailNotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void mailconsumer(OrderDetails details) {

		log.info(String.format("Notification received : %s", details.toString()));

		payLoad mail = notificationService.creatMail(details);

		notificationService.sendMail(mail);
	}

}
