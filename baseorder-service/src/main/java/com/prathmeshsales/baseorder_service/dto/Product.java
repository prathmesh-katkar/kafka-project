package com.prathmeshsales.baseorder_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String id;
	private String name;
	private double value;
	private int qty;
}
