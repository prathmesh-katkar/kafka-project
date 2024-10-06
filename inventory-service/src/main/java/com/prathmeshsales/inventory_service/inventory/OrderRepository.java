package com.prathmeshsales.inventory_service.inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prathmeshsales.inventory_service.dto.SKUDetails;

@Repository
public interface OrderRepository extends JpaRepository<SKUDetails, Integer> {

	Optional<SKUDetails> findByProductName(String productName);

}
