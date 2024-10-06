package com.prathmeshsales.inventory_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.prathmeshsales.baseorder_service.dto.OrderDetails;
import com.prathmeshsales.inventory_service.dto.SKUDetails;
import com.prathmeshsales.inventory_service.inventory.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {

	private OrderRepository orderRepository;

	public KafkaConsumer(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void kafkaListner(OrderDetails orderDeatils) {

		log.info(String.format("Inventory reveived Order -> %s", orderDeatils.toString()));

		SKUDetails newOrder = new SKUDetails();
		newOrder.setProductName(orderDeatils.getProduct().getName());
		newOrder.setProductValue(orderDeatils.getProduct().getValue());
		newOrder.setQty(orderDeatils.getProduct().getQty());

		orderRepository.save(newOrder);
		log.info("Database update, New Order Inserted successfully");
	}

}
