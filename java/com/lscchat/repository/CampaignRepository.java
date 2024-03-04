package com.lscchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lscchat.model.ApprovedResponse;
import com.lscchat.model.Campaign;
import com.lscchat.model.PendingResponse;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	
	@Query(value="SELECT ", nativeQuery=true)
	List<Campaign> findByIds(Long companyId, Long depId, Long unitId, Long userRole);

	//Pending Approval
	@Query(value = "SELECT c.id, c.campaign, c.template, u.fullname, c.create_date FROM campaign c INNER JOIN user u ON c.created_by = u.id WHERE c.company_id = ?1 AND c.dep_id = ?2 AND c.unit_id = ?3 AND c.status = 1", nativeQuery = true)
	List<List<String>> findByPending(Long companyId, Long depId, Long unitId);
	
	@Query(value = "SELECT c.id, c.campaign, c.template, u.fullname, c.create_date FROM campaign c INNER JOIN user u ON c.created_by = u.id WHERE c.company_id = ?1 AND c.dep_id = ?2 AND c.unit_id = ?3 AND u.id = ?4", nativeQuery = true)
	List<List<String>> findByCampaignsUserRole(Long companyId, Long depId, Long unitId, Long userId);
	
//	@Query(value = "SELECT c.campaign AS campaign, c.template AS template FROM campaign c WHERE c.company_id = ?1 AND c.dep_id = ?2 AND c.unit_id = ?3", nativeQuery = true)
//	List<String> findByPending(Long companyId, Long depId, Long unitId);
//	
//	@Query(value = "SELECT c.campaign, c.template FROM campaign c WHERE c.company_id = :companyId AND c.dep_id = :depId AND c.unit_id = :unitId")
//	List<PendingResponse> findByPending(@Param("companyId") Long companyId, @Param("depId") Long depId, @Param("unitId") Long unitId);

//	@Query(value="SELECT * FROM `campaign` INNER JOIN user ON campaign.created_by = user.id WHERE campaign.company_id = ?1 AND campaign.dep_id = ?2 AND campaign.unit_id = ?3", nativeQuery=true)
//	List<ApprovedResponse> findByPending(Long companyId, Long depId, Long unitId);

}
