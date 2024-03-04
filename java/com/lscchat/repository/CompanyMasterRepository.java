package com.lscchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lscchat.model.CompanyMaster;

public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Long>{
	
	@Query(value="SELECT * FROM `company_master` WHERE `mobile_no`= ?1", nativeQuery=true)
	CompanyMaster findByMobileNumber(String mobile);
	
	@Query(value="SELECT * FROM `company_master` WHERE `company_id`= ?1", nativeQuery=true)
	CompanyMaster findMobileNumber(Long companyId);
}
