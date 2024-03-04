package com.lscchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lscchat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	//Total count
		@Query(value = "SELECT COUNT(*) FROM message WHERE message.send_list_id = ?1", nativeQuery = true)
		Long countByTotalListId(Long sendListId);
		
	//Send count
	@Query(value = "SELECT COUNT(*) FROM message " +
	            "INNER JOIN status ON message.message_id = status.message_id " +
	            "WHERE message.send_list_id = ?1 AND status.send=1", nativeQuery = true)
	Long countBySendListId(Long sendListId);
	
	//delivered count
	@Query(value = "SELECT COUNT(*) FROM message " +
		            "INNER JOIN status ON message.message_id = status.message_id " +
		            "WHERE message.send_list_id = ?1 AND status.delivered=1", nativeQuery = true)
	Long countByDeliveredListId(Long sendListId);
		
	//read count
	@Query(value = "SELECT COUNT(*) FROM message " +
		            "INNER JOIN status ON message.message_id = status.message_id " +
		            "WHERE message.send_list_id = ?1 AND status.readed=1", nativeQuery = true)
		Long countByReadListId(Long sendListId);
	
	//read count
		@Query(value = "SELECT COUNT(*) FROM message " +
			            "INNER JOIN status ON message.message_id = status.message_id " +
			            "WHERE message.send_list_id = ?1 AND status.failed=1", nativeQuery = true)
			Long countByFailedListId(Long sendListId);
		
		//Find dep_id and unit_id
		
		@Query(value = "SELECT * FROM `message` WHERE ((`receive_id`=?2 AND `send_id`=?3) OR (`receive_id`=?3 AND `send_id`=?2)) AND `dep_id`!= 'NULL' AND `unit_id`!= 'NULL' AND `company_id`=?1 LIMIT 1;", nativeQuery = true)	
		Message findDepAndUnit(Long companyId, String cNumber, String uNumber);
		
		
	//Return contact chat
		 @Query(value = "SELECT phone_number, MAX(create_date) AS max_create_date "
		            + "FROM ( "
		            + "    SELECT send_id AS phone_number, create_date "
		            + "    FROM message "
		            + "    WHERE company_id = :companyId AND dep_id = :depId AND unit_id = :unitId AND send_id != :mobileNo "
		            + "    UNION "
		            + "    SELECT receive_id AS phone_number, create_date "
		            + "    FROM message "
		            + "    WHERE company_id = :companyId AND dep_id = :depId AND unit_id = :unitId AND receive_id != :mobileNo "
		            + ") AS subquery "
		            + "GROUP BY phone_number "
		            + "ORDER BY max_create_date DESC", nativeQuery = true)
		    List<List<String>> findChatContact(@Param("companyId") Long companyId, 
		                                       @Param("depId") Long depId, 
		                                       @Param("unitId") Long unitId, 
		                                       @Param("mobileNo") String mobileNo);
		 
	//Return chat
		 @Query(value = "SELECT * FROM `message` WHERE (`receive_id`= :userMobile OR `send_id`= :userMobile) AND `company_id`= :companyId AND `dep_id`= :depId AND `unit_id`= :unitId", nativeQuery = true)
		 List<Message> findChat(@Param("companyId") Long companyId, 
			              @Param("depId") Long depId, 
			              @Param("unitId") Long unitId,
			              @Param("userMobile") String userMobile);
		 
	//Not ready message for contact list
//		 @Query(value = "SELECT message.send_id, " +
//		            "COUNT(message.message_id) AS message_count, " +
//		            "MAX(message.create_date) AS last_message_timestamp, " +
//		            "(SELECT message.message " +
//		            " FROM message " +
//		            " INNER JOIN status ON message.message_id = status.message_id " +
//		            " WHERE message.send_id=:sendId " +
//		            "   AND status.readed IS NULL " +
//		            " ORDER BY message.create_date DESC " +
//		            " LIMIT 1) AS last_message_text " +
//		            "FROM message " +
//		            "INNER JOIN status ON message.message_id = status.message_id " +
//		            "WHERE message.send_id=:sendId " +
//		            "  AND status.readed IS NULL " +
//		            "GROUP BY message.send_id", nativeQuery = true)
		 @Query(value = "SELECT message.send_id, " +
		            "COUNT(message.message_id) AS message_count, " +
		            "MAX(message.create_date) AS last_message_timestamp, " +
		            "(SELECT message.message " +
		            " FROM message " +
		            " INNER JOIN status ON message.message_id = status.message_id " +
		            " WHERE message.send_id=:sendId " +
		            "   AND status.readed IS NULL " +
		            " ORDER BY message.create_date DESC " +
		            " LIMIT 1) AS last_message_text, " +
		            "(SELECT fullname FROM contacts WHERE mobile_no=:sendId AND company_id=:companyId AND dep_id=:depId AND unit_id=:unitId) AS name " +
		            "FROM message " +
		            "INNER JOIN status ON message.message_id = status.message_id " +
		            "WHERE message.send_id=:sendId " +
		            "  AND status.readed IS NULL " +
		            "GROUP BY message.send_id", nativeQuery = true)
		 List<Object[]> findByNotRead(@Param("sendId") String mobile,
										 @Param("companyId") Long companyId,
						                 @Param("depId") Long depId,
						                 @Param("unitId") Long unitId);
}
