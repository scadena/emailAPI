package com.isol.emailAPI.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isol.emailAPI.model.Message;
import com.isol.emailAPI.service.MailService;

/**
 * This class contains a Mail API developed using Spring Boot
 * 
 * @author Salvador Cadena
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private MailService notificationService;

	//@Autowired
	//private Message msg;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="send-mail", method = RequestMethod.POST, consumes="application/json")
	ResponseEntity<?> send(@RequestBody Message msg) {

		/*
		/*
		 * Here we will call sendEmail() for Sending mail to the sender.
		 */
		try {
			notificationService.sendEmail(msg);
		} catch (MailException mailException) {
			return new ResponseEntity<>(mailException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Your mail has been send to the user.",HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 * @throws MessagingException
	 */
	@RequestMapping("send-mail-attachment")
	ResponseEntity<?> sendWithAttachment(@RequestBody Message msg) throws MessagingException {


		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {
			notificationService.sendEmailWithAttachment(msg);
		} catch (MailException mailException) {
			return new ResponseEntity<>(mailException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (IOException ioException)
		{
			return new ResponseEntity<>(ioException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<>("Your mail has been send to the user.",HttpStatus.OK);
	}
}