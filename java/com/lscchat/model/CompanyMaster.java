package com.lscchat.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompanyMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long companyId;
	private String mobileNo;
	private String companyName;
	private Timestamp createDate;
	private Long flag;
	
	public CompanyMaster() {
		this.createDate = new Timestamp(System.currentTimeMillis());
		this.flag = (long) 0;
	}
	public CompanyMaster(Long companyId, String mobileNo, String companyName, Timestamp createDate, Long flag) {
		super();
		this.companyId = companyId;
		this.mobileNo = mobileNo;
		this.companyName = companyName;
		this.createDate = createDate;
		this.flag = flag;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
	
}
