package com.lscchat.model;

import java.sql.Timestamp;

public class CustomSendListResponse {
    private String templateName;
    private Timestamp date;
    private int status;
    private String total;
    private String send;
    private String delivered;
    private String read;
    private String reply;
    private String notSend;

    // Constructors, getters, and setters

    public CustomSendListResponse() {
        // Default constructor
    }

    public CustomSendListResponse(String templateName, Timestamp date, int status,
                                  String total, String send, String delivered, String read, String reply,
                                  String notSend) {
        this.templateName = templateName;
        this.date = date;
        this.status = status;
        this.total = total;
        this.send = send;
        this.delivered = delivered;
        this.read = read;
        this.reply = reply;
        this.notSend = notSend;
    }

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}



	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getNotSend() {
		return notSend;
	}

	public void setNotSend(String notSend) {
		this.notSend = notSend;
	}



}

