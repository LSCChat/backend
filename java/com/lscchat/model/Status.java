package com.lscchat.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long company_id;
	private Long dep_id;
	private Long unit_id;
	@Column(name = "message_id")
	private String messageId;
	private Long send;
	private Timestamp send_date;
	private Long delivered;
	private Timestamp delivered_date;
	private Long readed;
	private Timestamp readed_date;
	private Long failed;
	private Timestamp failed_date;
	private String failed_reason;
	private Long flag;
	
	public Status() {
		this.flag = (long)0;
	}
	public Long getId() {
		return id;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public Long getDep_id() {
		return dep_id;
	}
	public void setDep_id(Long dep_id) {
		this.dep_id = dep_id;
	}
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Long getSend() {
		return send;
	}
	public void setSend(Long send) {
		this.send = send;
	}
	public Long getDelivered() {
		return delivered;
	}
	public void setDelivered(Long delivered) {
		this.delivered = delivered;
	}
	public Long getReaded() {
		return readed;
	}
	public void setReaded(Long readed) {
		this.readed = readed;
	}
	public Long getFailed() {
		return failed;
	}
	public void setFailed(Long failed) {
		this.failed = failed;
	}
	public Timestamp getFailed_date() {
		return failed_date;
	}
	public void setFailed_date(Timestamp failed_date) {
		this.failed_date = failed_date;
	}
	public String getFailed_reason() {
		return failed_reason;
	}
	public void setFailed_reason(String failed_reason) {
		this.failed_reason = failed_reason;
	}
	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public Timestamp getSend_date() {
		return send_date;
	}
	public void setSend_date(Timestamp create_date) {
		this.send_date = create_date;
	}
	public Timestamp getDelivered_date() {
		return delivered_date;
	}
	public void setDelivered_date(Timestamp delivered_date) {
		this.delivered_date = delivered_date;
	}
	public Timestamp getReaded_date() {
		return readed_date;
	}
	public void setReaded_date(Timestamp readed_date) {
		this.readed_date = readed_date;
	}
	
}
