package com.sparc.usha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sparc.usha.entity.IdentitytProofEntity;
import com.sparc.usha.response.IdentityProofResponse;

public interface IdentitytProofRepository extends JpaRepository<IdentitytProofEntity, Integer> {
	@Query(value="SELECT * FROM usha_schema.identity_proof where id_proof_type=:idProofType",nativeQuery = true)
	List<IdentitytProofEntity> getIdentityProofByIdType(Integer idProofType);

}
