package com.sparc.usha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mt_slum")
public class SlumEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slumId;
	
	@ManyToOne
	//@Column(name = "municipality_id")
	@JoinColumn(name = "municipality_id")
	private MunicipalityCorpoEntity municipalityId;

//	@Column(name = "ward_no")
//	private Integer wardNo;
	
	@Column(name = "cluster")
	private Integer cluster;
	
	@Column(name = "slum_name")
	private String slumName;
	
	@Column(name = "slum_area")
	private Double slumArea;



}
