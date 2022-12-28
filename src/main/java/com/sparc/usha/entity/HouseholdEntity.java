package com.sparc.usha.entity;

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
@Table(name = "household_member")
public class HouseholdEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "household_id")
	private Integer householdId;
	
	//@OneToMany
	@ManyToOne
	//@Column(name = "usha_survey_id")
	@JoinColumn(name = "usha_survey_id")
	private UshaSurveyEntity ushaSurveyId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Column(name = "relation_to_applicant")
	private String relationToApplicant;
	
	@Column(name = "dependant_to_applicant")
	private Boolean dependantToApplicant;
	
	@Column(name = "contact_details")
	private String contactDetails;
	
	@Column(name = "active_status")
	private Boolean activeStatus=true;
	
	
}
