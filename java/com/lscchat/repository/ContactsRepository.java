package com.lscchat.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lscchat.model.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
	
	//List<Contacts> findByCompanyIdAndDepId(Long companyId, Long depId);
	@Query(value = "SELECT * FROM contacts WHERE company_id = ?1 AND dep_id = ?2 AND unit_id = ?3", nativeQuery = true)
	List<Contacts> findByIds(Long companyId, Long depId, Long unitId);
	
	@Query(value = "SELECT * FROM `contacts` WHERE `mobile_no`=?1 AND `company_id`=?2 AND `dep_id`=?3 AND `unit_id`=?4 ORDER BY id DESC LIMIT 1", nativeQuery = true)
	Contacts findName(String mobile, Long companyId, Long depId, Long unitId);

}
