package com.sparc.usha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparc.usha.entity.HouseholdEntity;
import com.sparc.usha.request.addHouseHoldData;

public interface HouseHoldRepository extends JpaRepository<HouseholdEntity, Integer> {
	@Query(value="select * from usha_schema.household_member where usha_survey_id=:ushaSurveyId",nativeQuery = true)
	Optional<HouseholdEntity> getByUshaSurveyId(Integer ushaSurveyId);
@Query(value="select * from usha_schema.household_member where usha_survey_id=:ushaSurveyId",nativeQuery = true)
	List<HouseholdEntity> findAllByUshaSurveyId(Integer ushaSurveyId);
@Query(value="select * from usha_schema.household_member where household_id=:houseHoldId",nativeQuery = true)
Optional<HouseholdEntity> findById(Integer houseHoldId);


}
