package com.prathmeshsales.inventory_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prathmeshsales.inventory_service.dto.Bill;
import com.prathmeshsales.inventory_service.dto.SKUDetails;
import com.prathmeshsales.inventory_service.inventory.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class InventroyController {

	private OrderRepository orderRepository;

	public InventroyController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	// http://localhost:8081/api/v1/get-product
	@GetMapping("/get-product")
	public ResponseEntity<List<SKUDetails>> getAllProduct() {

		List<SKUDetails> productList = orderRepository.findAll();

		if (productList.isEmpty())
			return new ResponseEntity<List<SKUDetails>>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<List<SKUDetails>>(productList, HttpStatus.BAD_REQUEST);

	}

	// http://localhost:8081/api/v1/add-product
	@PostMapping("/add-product")
	public ResponseEntity<String> addProduct(@RequestBody SKUDetails order) {

		log.info(order.toString());

		orderRepository.save(order);

		return ResponseEntity.ok("Product Saved successfully");

	}

	// http://localhost:8081/api/v1/get-produt-sell
	@GetMapping("/get-produt-sell")
	public ResponseEntity<Bill> getProductSell(@RequestParam("productname") String productname) {

		log.info("Product Name :" + productname);
		Optional<SKUDetails> getProduct = orderRepository.findByProductName(productname);

		if (getProduct.isEmpty())
			return new ResponseEntity<Bill>(HttpStatus.BAD_REQUEST);

		SKUDetails orderProduct = getProduct.get();

		double amount = orderProduct.getProductValue() * orderProduct.getQty();

		Bill bill = new Bill(UUID.randomUUID().toString(), orderProduct.getProductName(),
				orderProduct.getProductValue(), orderProduct.getQty(), amount);

		return new ResponseEntity<Bill>(bill, HttpStatus.OK);

	}
}
