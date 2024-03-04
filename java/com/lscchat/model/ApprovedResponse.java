package com.lscchat.model;

import java.sql.Timestamp;

public class ApprovedResponse {
	private Long id;
	private String campaign;
	private String template;
	private String fullname;
	private Timestamp createDate;
	
	public ApprovedResponse(Long id, String campaign, String template, String fullname, Timestamp createDate) {
		super();
		this.id = id;
		this.campaign = campaign;
		this.template = template;
		this.fullname = fullname;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
}
