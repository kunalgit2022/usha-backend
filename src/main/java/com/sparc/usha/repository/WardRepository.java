package com.sparc.usha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparc.usha.entity.WardEntity;

public interface WardRepository extends JpaRepository<WardEntity, Integer> {
	@Query(value = "SELECT * FROM usha_schema.slum_ward where slum_id=:slumId", nativeQuery = true)
	List<WardEntity> getWardNoBySlumId(Integer slumId);

	@Query(value = "select distinct w.wardNo from WardEntity w where w.municipalityId.municipalityId=:municipalityId order by w.wardNo")
	List<Integer> getWardInfoByMunicipalityId(Integer municipalityId);
	@Query(value="")
	String getCountWardByMunicipalityId(Integer municipalityId);

}
