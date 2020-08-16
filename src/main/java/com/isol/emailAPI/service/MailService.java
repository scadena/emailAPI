package com.isol.emailAPI.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.isol.emailAPI.model.AttachmentB64;
import com.isol.emailAPI.model.Message;

/**
 * 
 * @author Salvador Cadena
 *
 */
@Service
public class MailService {

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * This function is used to send mail without attachment.
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(Message message) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(message.getTo());
		mail.setSubject(message.getSubject());
		mail.setText(message.getBody());
		mail.setFrom(message.getFrom());

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	/**
	 * This fucntion is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 * @throws IOException 
	 */
	public void sendEmailWithAttachment(Message msg) throws MailException, MessagingException, IOException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom(msg.getFrom());
		helper.setTo(msg.getTo());
		helper.setSubject(msg.getSubject());
		helper.setText(msg.getBody());
				
		for (AttachmentB64 attachment : msg.getAttachments()) {
			
			byte[] decode = Base64.decodeBase64(attachment.getBase64Content());			
			InputStream is = new ByteArrayInputStream(decode);
			ByteArrayDataSource bsds = new ByteArrayDataSource(is, "application/octet-stream");
			helper.addAttachment(attachment.getFileName(), bsds);
		}
		javaMailSender.send(mimeMessage);
	}

}