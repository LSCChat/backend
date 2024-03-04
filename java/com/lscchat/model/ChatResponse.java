package com.lscchat.model;

import java.sql.Timestamp;

public class ChatResponse {
	private Long messageId;
	private Long type;
	private String messageType;
	private String message;
	private Timestamp dateTime;
	public ChatResponse() {
		
	}
	public ChatResponse(Long messageId, Long type, String messageType, String message, Timestamp dateTime) {
		super();
		this.messageId = messageId;
		this.type = type;
		this.messageType = messageType;
		this.message = message;
		this.dateTime = dateTime;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
