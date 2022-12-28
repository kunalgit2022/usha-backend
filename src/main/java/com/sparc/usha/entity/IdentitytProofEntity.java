package com.sparc.usha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "identity_proof")
public class IdentitytProofEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="identity_proof_id")
	private Integer identityProofId;
	
	@Column(name = "id_proof_name")
	private String idProofName;
	
	@Column(name = "id_proof_type")
	private Integer idProofType;
	
		
	
}
