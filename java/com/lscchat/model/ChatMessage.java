package com.lscchat.model;

import java.sql.Timestamp;

public class ChatMessage {
	
	private String senderId;
	private String reciverId;
	private String messageId;
	private Long type;
	private String messageType;
	private String message;
	private Timestamp dateTime;
	
	public ChatMessage() {
		this.dateTime = new Timestamp(System.currentTimeMillis());
	}

	public ChatMessage(String senderId, String reciverId, String messageId, Long type, String messageType, String message,
			Timestamp dateTime) {
		super();
		this.senderId = senderId;
		this.reciverId = reciverId;
		this.messageId = messageId;
		this.type = type;
		this.messageType = messageType;
		this.message = message;
		this.dateTime = new Timestamp(System.currentTimeMillis());
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReciverId() {
		return reciverId;
	}

	public void setReciverId(String reciverId) {
		this.reciverId = reciverId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
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

	@Override
	public String toString() {
		return "ChatMessage [senderId=" + senderId + ", reciverId=" + reciverId + ", messageId=" + messageId + ", type="
				+ type + ", messageType=" + messageType + ", message=" + message + ", dateTime=" + dateTime + "]";
	}
	
}
