package com.prathmesh.notification_service.mailservice;

import java.io.File;

import com.prathmesh.notification_service.dto.payLoad;

public interface EmailSerivce {

	public void sendMail(String receiver, String subject, String body);

	public void sendMail(payLoad data);

	public void sendMailWithFile(String receiver, String subject, String body, File file);

}
