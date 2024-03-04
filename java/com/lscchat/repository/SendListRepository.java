package com.lscchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lscchat.model.SendList;

public interface SendListRepository extends JpaRepository<SendList, Long>{
	
	@Query(value = "SELECT * FROM send_list WHERE company_id = ?1 AND dep_id = ?2 AND unit_id = ?3 ORDER BY id DESC", nativeQuery = true)
	List<SendList> findByIds(Long companyId, Long depId, Long unitId);
	
	
}
