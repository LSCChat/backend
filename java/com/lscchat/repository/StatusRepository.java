package com.lscchat.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lscchat.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{
//	@Transactional
//	@Query(value = "UPDATE `status` SET `delivered_date` = ?6, `delivered` = ?5 WHERE `status`.`company_id` = ?1 AND `status`.`dep_id` = ?2 AND `status`.`unit_id` = ?3 AND `status`.`message_id` = ?4", nativeQuery = true)
//	void updateDelivered(Long companyId, Long depId, Long unitId, String messageId, Long delivered, Timestamp deliveredDate);
	
//	Status findByMessageId(String message_id);
	Status findByMessageId(String message_id);

	@Transactional(propagation = Propagation.REQUIRED)
	@Modifying
	@Query(value = "UPDATE status s " +
	        "INNER JOIN message m ON m.message_id = s.message_id " +
	        "SET s.readed = 1, s.readed_date = ?2 " +
	        "WHERE m.send_id = ?1 " +
	        "AND m.company_id = ?3 " +
	        "AND m.dep_id = ?4 " +
	        "AND m.unit_id = ?5 " +
	        "AND s.readed IS NULL", nativeQuery = true)
	void updateStatusForMobile(String mobile, Timestamp create_date, Long companyId, Long depId, Long unitId);
	
}
