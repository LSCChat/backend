package com.lscchat.model;
import org.springframework.stereotype.Component;

@Component
public class SessionValues {
	private Long userId;
	private String fullname;
	private String email;
	private Long company_id;
	private Long dep_id;
	private Long unit_id;
	private Long userRole;
	private Long campaignLimit;
	
	public SessionValues(Long userId, String fullname, String email, Long company_id, Long dep_id, Long unit_id, Long userRole, Long campaignLimit) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.company_id = company_id;
		this.dep_id = dep_id;
		this.unit_id = unit_id;
		this.userRole = userRole;
		this.campaignLimit = campaignLimit;
	}
	public SessionValues() {
		super();
		this.userId = null;
		this.fullname = null;;
		this.email = null;
		this.company_id = null;
		this.dep_id = null;
		this.unit_id = null;
		this.userRole = null;
		this.campaignLimit = null;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public Long getUserRole() {
		return userRole;
	}
	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}
	public Long getCampaignLimit() {
		return campaignLimit;
	}
	public void setCampaignLimit(Long campaignLimit) {
		this.campaignLimit = campaignLimit;
	}
	
	
}
