package com.sparc.usha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparc.usha.entity.LRCapplicationEntity;

public interface LrcRepository extends JpaRepository<LRCapplicationEntity, Integer>{
	@Query(value="select * from usha_schema.lrc_applicantion where usha_survey_id=:ushaSurveyId",nativeQuery = true)
	List<LRCapplicationEntity> findAllByUshaSurveyId(Integer ushaSurveyId);

	Optional<LRCapplicationEntity> getByUshaSurveyId(Integer ushaSurveyId);
	@Query(value="select * from usha_schema.lrc_applicantion where usha_survey_id=:ushaSurveyId",nativeQuery = true)
	Optional<LRCapplicationEntity> getAllByUshaSurveyId(Integer ushaSurveyId);

}
