package com.lscchat.model;

public class PendingResponse {
	private String campaign;
	private String template;

	public PendingResponse(String campaign, String template) {
		this.campaign = campaign;
		this.template = template;
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
	
}
