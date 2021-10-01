package com.webApp.loginApp.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.webApp.loginApp.entity.User;
import com.webApp.loginApp.util.OtpUtility;

@Service
public class OtpService {
	
	public String sendOtp(User userBean) {
		Integer Otp = OtpUtility.genOtp();
		
		 final String username = "springboot.test100@gmail.com";
	     final String password = "Springboot_test100";

	     Properties prop = new Properties();
	     prop.setProperty("mail.smtp.ssl.enable", "true");
	     prop.setProperty("mail.smtp.host", "smtp.gmail.com");
	     prop.setProperty("mail.smtp.socketFactory.port", "465");
	     prop.setProperty("mail.smtp.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	     prop.setProperty("mail.smtp.port", "465");
	     prop.setProperty("mail.smtp.auth", "true");
	        //prop.put("mail.smtp.starttls.enable", "true"); //TLS
	        
	     Session session = Session.getInstance(prop);	               

	     try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("springboot.test100@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(userBean.getEmail())
	            );
	            message.setSubject("Testing from Spring Boot");
	            message.setText("Here's your One Time Password (OTP) : " + Otp);

	            Transport.send(message, username, password);

	            System.out.println("Done");

	     } catch (MessagingException e) {
	            e.printStackTrace();
	     }
		return Integer.toString(Otp);
	}
}
