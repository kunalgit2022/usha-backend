package com.sparc.usha.entity;

import java.time.LocalDate;
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
@Table(name = "lrc_applicantion")
public class LRCapplicationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lrc_applicantion_id")
	private Integer lrcApplicantionId;

	@ManyToOne
	// @Column(name = "usha_survey_id")
	@JoinColumn(name = "usha_survey_id")
	private UshaSurveyEntity ushaSurveyId;

	@Column(name = "plot_occupying_date")
	private Date plotOccupyingDate;

	@Column(name = "east_direction")
	private String eastDirection;

	@Column(name = "west_direction")
	private String westDirection;

	@Column(name = "north_direction")
	private String northDirection;

	@Column(name = "south_direction")
	private String southDirection;

	@Column(name = "applicant_photo")
	private String applicantPhoto;

	@Column(name = "spouse_photo")
	private String spousePhoto;

	@Column(name = "applicant_fingerprint")
	private String applicantFingerprint;

	@Column(name = "spouse_fingerprint")
	private String spouseFingerprint;

	@Column(name = "active_status", columnDefinition = "boolean default true")
	private Boolean activeStatus = true;

	@Column(name = "created_on")
	private LocalDate createdOn;

	@Column(name = "updatedOn")
	private LocalDate updated_on;

}
