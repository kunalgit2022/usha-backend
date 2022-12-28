package com.sparc.usha.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sparc.usha.entity.SlumEntity;
import com.sparc.usha.entity.WardEntity;
import com.sparc.usha.response.DashboardResponse;

@Repository
public interface SlumRepository extends JpaRepository<SlumEntity, Integer>{
	@Query(value="select * from usha_schema.mt_slum where municipality_id=:municipalityId",nativeQuery = true)	
	List<SlumEntity> getSlumInfoByMunicipalityId(@Param("municipalityId") Integer municipalityId);
	@Query(value="select * from usha_schema.slum_ward where municipality_id=:municipalityId",nativeQuery = true)
	List<WardEntity> getWardInfoByMunicipalityId(Integer municipalityId);
//	@Procedure(procedureName = "osdi_gis.FN_GETLAYERSINFOBYFILTER")
//	List<Object[]> getAllCatalogueDataByKeyword(@Param("keyword") String keywor
	
//	@Query(value = "usha_schema.fn_getalldashboard_status_count(:surveyorid);", nativeQuery = true)
//	List<DashboardResponse> getDashboardStatusCount(@Param("surveyorid") Integer surveyorid);
	
//	@Procedure(procedureName = "usha_schema.fn_getalldashboard_status_count")
//	List<DashboardResponse> getDashboardStatusCount(Integer surveyorid);
}
