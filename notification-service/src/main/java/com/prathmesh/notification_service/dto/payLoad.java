package com.prathmesh.notification_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class payLoad {

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String receiverId;
	private String subject;
	private String message;
}
