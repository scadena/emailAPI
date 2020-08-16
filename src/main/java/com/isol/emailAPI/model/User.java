package com.isol.emailAPI.model;

import org.springframework.stereotype.Component;
/**
 * 
 * @author Salvador Cadena
 * @version 1.0
 *
 */
@Component
public class User {

	private String emailAddress;

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}