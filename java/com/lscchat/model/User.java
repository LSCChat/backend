package com.lscchat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private Long company_id;
	private Long dep_id;
	private Long unit_id;
	private String fullname;
	private String email;
	private String password;
	private Long UserRole;
	private Long campaignLimit;
	private Long flag;
	private Timestamp create_date;
    private LocalDateTime update_date;
    
    public User() {
        this.create_date = new Timestamp(System.currentTimeMillis()); // Sets the timestamp to the current time
        this.flag = (long) 0;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getUserRole() {
		return UserRole;
	}

	public void setUserRole(Long userRole) {
		UserRole = userRole;
	}
	
	public Long getCampaignLimit() {
		return campaignLimit;
	}

	public void setCampaignLimit(Long campaignLimit) {
		this.campaignLimit = campaignLimit;
	}

	public Long getFlag() {
		return flag;
	}
	public void setFlag(Long flag) {
		this.flag = flag;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public LocalDateTime getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}
}
