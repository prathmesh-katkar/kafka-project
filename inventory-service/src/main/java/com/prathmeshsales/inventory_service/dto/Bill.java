package com.prathmeshsales.inventory_service.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Data
@AllArgsConstructor
public class Bill {

	private String id;
	private String productName;
	private double productPrice;
	private int qty;
	private double totalAmount;
	
}
