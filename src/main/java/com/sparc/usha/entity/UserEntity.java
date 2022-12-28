package com.sparc.usha.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_tbl")

public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@Size(max = 50)
	
	private String name;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	private String mobileNo;
	
	@Column(name = "alternate_phone")
	private String alternatePhoneNo;
	
	@JsonIgnore
	//@NotBlank
	@Size(max = 120)
	private String password;
	
	@Column(columnDefinition = "boolean default true")
	private boolean activeSts=true;

	@Column(name = "role_id")
	private Integer roleId;
	@ManyToOne
	// @Column(name = "municipality_corp_id")
	@JoinColumn(name = "municipality_id")
	private MunicipalityCorpoEntity municipalityCorpId;
	
	private Integer createdBy;
	
	private LocalDate createdOn;
	
	private LocalDate updatedOn;
	
	private Integer updatedBy;
	// @ManyToMany(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Role roles;

}
