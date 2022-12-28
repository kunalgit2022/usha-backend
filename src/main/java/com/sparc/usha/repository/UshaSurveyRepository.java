package com.sparc.usha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sparc.usha.entity.UshaSurveyEntity;
import com.sparc.usha.response.DashboardResponse;
import com.sparc.usha.response.UshaSurveyResponse;


public interface UshaSurveyRepository extends JpaRepository<UshaSurveyEntity, Integer> {
	@Query(value="select * from usha_schema.usha_survey \r\n"
			+ "where municipality_id=:municipalityId and status=:statusId",nativeQuery = true)
	List<UshaSurveyEntity> getApplicantInfoByMunicipalityAndStatusId(Integer municipalityId, Integer statusId);
	@Query(value="select * from usha_schema.usha_survey \r\n"
			+ "where serveyor_id=:surveyorId ",nativeQuery = true)
	List<UshaSurveyEntity> getApplicantInfoBySurveyorId(Integer surveyorId);
	@Query(value="select * from usha_schema.usha_survey \r\n"
			+ "where status=:statusId",nativeQuery = true)
	List<UshaSurveyEntity> getApplicantInfoBySurveyorIdentity(Integer statusId);
	//List<UshaSurveyEntity> findByI(Integer ushaSurveyId);
	@Query(value="select * from usha_schema.usha_survey \r\n"
			+ "where serveyor_id=:surveyorId and status=:statusId",nativeQuery = true)
	List<UshaSurveyEntity> getushaSurveyInfoBySurveyorAndStatusId(Integer surveyorId, Integer statusId);
	@Procedure(value = "usha_schema.fn_getalldashboard_status_count")
	List<DashboardResponse> getDashboardStatusCount(@Param("surveyorid") Integer surveyorid);
	@Query(value="select name from usha_schema.users_tbl where id=:id",nativeQuery = true)
	String getByServeyorId(Integer id);
	

}
