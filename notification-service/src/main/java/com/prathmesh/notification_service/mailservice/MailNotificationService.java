package com.prathmesh.notification_service.mailservice;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.prathmesh.notification_service.dto.payLoad;
import com.prathmeshsales.baseorder_service.dto.OrderDetails;
import com.prathmeshsales.baseorder_service.dto.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailNotificationService implements EmailSerivce {

	@Value("${spring.mail.username")
	private String senderID;

	private JavaMailSender mailSender;

	public MailNotificationService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public void sendMail(String receiver, String subject, String body) {

		SimpleMailMessage mailNotification = new SimpleMailMessage();

		mailNotification.setTo(receiver);
		mailNotification.setFrom(senderID);
		mailNotification.setSubject(subject);
		mailNotification.setText(body);
		mailNotification.setSentDate(new Date());

		mailSender.send(mailNotification);
		log.info("Mail Sent successfully..!");
	}

	@Override
	public void sendMailWithFile(String receiver, String subject, String body, File file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMail(payLoad data) {

		SimpleMailMessage mailNotification = new SimpleMailMessage();

		mailNotification.setTo(data.getReceiverId());
		mailNotification.setFrom(senderID);
		mailNotification.setSubject(data.getSubject());
		mailNotification.setText(data.getMessage());
		mailNotification.setSentDate(new Date());

		mailSender.send(mailNotification);
		log.info("Mail Sent successfully..!");

	}
	
	public payLoad creatMail(OrderDetails details) {

		Product product = details.getProduct();

		payLoad payLoad = new payLoad();

		payLoad.setReceiverId(details.getMailID());
		payLoad.setSubject("Purchase order of " + product.getName());

		String mailBody =
				"Dear " + details.getUserName() + ",\n\n" +
				
				"Thank you for purchasing "+product.getQty()+" " + product.getName()+ ".\n" +
				"The total price is ₨" + product.getValue() * product.getQty() + ".\n\n"+
				"We hope you enjoy your product!\n\n" +
				
				"Best regards,\n" +
				"Prathemsh Sales Service\n" +
				details.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE);

		payLoad.setMessage(mailBody);

		return payLoad;

	}

}
