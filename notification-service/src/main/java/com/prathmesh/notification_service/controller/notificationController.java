package com.prathmesh.notification_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prathmesh.notification_service.dto.payLoad;
import com.prathmesh.notification_service.mailservice.MailNotificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notification")
public class notificationController {

	private MailNotificationService notificationService;

	public notificationController(MailNotificationService notificationService) {
		this.notificationService = notificationService;
	}

	// http://localhost:8082/api/notification/sent-email
	@PostMapping("/sent-email")
	public ResponseEntity<String> sendMailNotification(@Valid @RequestBody payLoad data) {

		notificationService.sendMail(data);

		return ResponseEntity.ok("Mail Sent successfully..!");

	}
}
