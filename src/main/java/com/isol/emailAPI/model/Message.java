package com.isol.emailAPI.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	private String from;

	private String replyTo;

	private String[] to;

	private String[] cc;

	private String[] bcc;

	private String subject;

	private String body;
		
	private List<AttachmentB64> attachments;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<AttachmentB64> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentB64> attachments) {
		this.attachments = attachments;
	}


}
