package com.prathmeshsales.baseorder_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

	private String userId;
	private String userName;
	private String mobileNumber;
	private String mailID;
	private LocalDateTime time;
	private Product product;
}
