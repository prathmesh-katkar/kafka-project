package com.prathmeshsales.WebStore.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prathmeshsales.WebStore.kafka.KafkaProducer;
import com.prathmeshsales.WebStore.service.ProductService;
import com.prathmeshsales.baseorder_service.dto.OrderDetails;
import com.prathmeshsales.baseorder_service.dto.Product;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/store")
@Slf4j
public class StoreController {

	private ProductService productService;
	private KafkaProducer kafkaProducer;
	
	public StoreController(ProductService productService, KafkaProducer kafkaProducer) {
		this.productService = productService;
		this.kafkaProducer = kafkaProducer;
	}

	// http://localhost:8080/api/store/get-Product
	@GetMapping("/get-Product")
	public ResponseEntity<List<Product>> getProduct() {

		log.info(String.format("get-product called"));

		List<Product> productList = productService.getProductList();

		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}

	@PostMapping("/buy-product")
	public ResponseEntity<String> buyProduct(@RequestBody Product product) {

		log.info(String.format("buy-product called"));

		if (productService.checkProduct(product).isEmpty())
			return ResponseEntity.badRequest().body("Sorry Order is Not placed");

		product.setId(UUID.randomUUID().toString());

		OrderDetails orderDetails = new OrderDetails();

		orderDetails.setUserName("Prathmesh Katkar");
		orderDetails.setMobileNumber("7057257726");
		orderDetails.setUserId(UUID.randomUUID().toString());
		orderDetails.setTime(LocalDateTime.now());
		orderDetails.setProduct(product);
		orderDetails.setMailID("katkarprathm2@gmail.com");

		log.info("Order Details -> " + orderDetails.toString());
//		log.info("Qty -> " + product.getQty());

		kafkaProducer.sendMessage(orderDetails);
		return ResponseEntity.ok().body("Order Placed Scuessfully");

	}

}
