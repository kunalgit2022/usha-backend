package com.sparc.usha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparc.usha.entity.UserEntity;
import com.sparc.usha.response.UserInfoResponse;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByMobileNo(String mobile);

	boolean existsByEmail(String email);

	Optional<UserEntity> findByMobileNo(String mobNo);
	
	Optional<UserEntity> findByEmail(String emailId);

	Optional<UserEntity> findByName(String username);
	@Query(value="select * from usha_schema.users_tbl where municipality_id=:municipalityId",nativeQuery = true)
	List<UserEntity> getByMunicipalityId(Integer municipalityId);
}
