package com.prathmeshsales.WebStore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.prathmeshsales.baseorder_service.dto.Product;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductService {

	private static List<Product> getProduct = Arrays.asList(
			new Product(UUID.randomUUID().toString(), "Lenovo s142 Laptop", 75000, 50),
			new Product(UUID.randomUUID().toString(), "Dell H121T Laptop", 65000, 60),
			new Product(UUID.randomUUID().toString(), "MacBook AirBook Laptop", 125000, 30),
			new Product(UUID.randomUUID().toString(), "HP Pavillion123 Laptop", 72000, 70),
			new Product(UUID.randomUUID().toString(), "Accser LM234 Laptop", 69000, 40),
			new Product(UUID.randomUUID().toString(), "Samsung 39D34 Laptop", 55000, 25));

	public List<Product> getProductList() {

		return getProduct;
	}

	public Optional<Product> checkProduct(Product product) {


		Optional<Product> findFirst = getProduct.stream()
				.filter((obj) -> obj.getName()
				.equals(product.getName()))
				.findFirst();


		if (findFirst.isEmpty()|| findFirst.get().getQty() == 0)
			return findFirst;

		Product askedProduct = findFirst.get();

		return Optional.ofNullable(askedProduct);
	}

}
