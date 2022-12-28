package com.sparc.usha.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sparc.usha.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usha_survey")
public class UshaSurveyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usha_survey_id")
	private Integer ushaSurveyId;
	
	@ManyToOne
	//@Column(name = "municipality_id")
	@JoinColumn(name="municipality_id")
	private MunicipalityCorpoEntity municipalityId;//FK(municipality table(id))
	
	@Column(name = "ward_no")
	private Integer wardNo;
	
	@ManyToOne
	@JoinColumn(name = "slum_type")
	private SlumEntity slumType;//FK(mt_slum(slum_id))
	
	@Column(name = "street_lane")
	private String streetLane;
	
	@Column(name = "house_no")
	private String houseNo;
	
	@Column(name = "applicant_name")
	private String applicantName;	
	
	@Column(name = "gender")
	private String applicantGender;
	
	@Column(name = "applicant_mobile")
	private String applicantMobile;
	
	@Column(name = "parent_spouse_name")
	private String parentSpouseName;
	
	@Column(name = "parent_spouse_mobile")
	private String parentSpouseMobile;
	
	@Column(name = "family_anual_income")
	private Double familyAnualIncome;
	
	@Column(name = "anual_income_proof")
	private Boolean anualIncomeProof;
	
	@Column(name = "ews_proof")
	private Boolean ewsProof;
	
	@Column(name = "ews_proof_type")
	private String ewsProofType;
	
	@Column(name = "cast_category")
	private String castCategory;
	
	@Column(name = "cast_certificate")
	private Boolean castCertificate;
	
	@Column(name = "pwd")
	private Boolean pwd;
	
	@Column(name = "pwd_certificate")
	private Boolean pwdCertificate;
	
	@Column(name = "religion")
	private String religion;
	
	@ManyToOne
	//@Column(name = "applicant_id_proof_type")
	@JoinColumn(name = "applicant_id_proof_type")
	private IdentitytProofEntity applicantIdProofType;//FK(identity proof(id))
	
	@Column(name = "applicant_id_proof_no")
	private String applicantIdProofNo;
	
	@Column(name = "applicant_id_proof_issue_date")
	private LocalDate applicantIdProofIssueDate;
	
	@Column(name = "applicant_id_proof_image")
	private String applicantIdProofImage;
	
	@ManyToOne
	//@Column(name = "applicant_resd_proof_type")
	@JoinColumn(name = "identity_proof_id")
	private IdentitytProofEntity applicantResdProofType;//FK(identity proof(id))
	
	@Column(name = "applicant_resd_proof_no")
	private String applicantResdProofNo;
	
	@Column(name = "applicant_resd_proof_issue_date")
	private LocalDate applicantResdProofIssueDate;
	
	@Column(name = "applicant_resd_proof_image")
	private String applicantResdProofImage;
	
	@ManyToOne
	//@Column(name = "spouse_id_proof_type")
	@JoinColumn(name = "spouse_id_proof_type")
	private IdentitytProofEntity spouseIdProofType;//FK(identity proof(id))
	
	@Column(name = "spouse_id_proof_no")
	private String spouseIdProofNo;
	
	@Column(name = "spouse_id_proof_issue_date")
	private LocalDate spouseIdProofIssueDate;
	
	@Column(name = "spouse_id_proof_image")
	private String spouseidProofImage;
	
	@Column(name = "wall_type")
	private String wallType;
	
	@Column(name = "roof_type")
	private String roofType;
	
	@Column(name = "house_have_toilet")
	private Boolean houseHaveToilet;
	
	@Column(name = "dwelling_access_to_pucca_paver")
	private Boolean dwellingAccessToPuccaPaver;
	
	@Column(name = "land_ownership_in_any_ulb")
	private Boolean landOwnershipInAnyUlb;
	
	@Column(name = "land_ownership_in_same_ulb")
	private Boolean landOwnershipInSameUlb;
	
	@Column(name = "ews_proofCertificate")
	private String ewsProofCertificate;
	
	@Column(name = "cast_proofCertificate")
	private String castProofCertificate;
	
	@Column(name = "pwd_proofCertificate")
	private String pwdProofCertificate;
	
	@ManyToOne
	//@Column(name = "serveyor_id")
	@JoinColumn(name = "serveyor_id")
	private UserEntity serveyorId;//FK(user (id))
	
	@ManyToOne
	//@Column(name = "assignee_id")
	@JoinColumn(name = "assignee_id")
	private UserEntity assigneeId;//FK(user (id))
	
	@Column(name = "assign_date")
	private LocalDate assignDate;
	
	@Column(name = "reported_date")
	private LocalDate reportedDate;
	
	@ManyToOne
	//@Column(name = "status")
	@JoinColumn(name = "status")
	private StatusEntity status;//FK(mt_status(id))
	
	@Column(name = "active_status")
	private Boolean activeStatus=true;//by default(true)

	@Column(name="note")
	private String note;
	
	@Column(name="created_on")
	private LocalDate createdOn;
	
	@Column(name="updatedOn")
	private LocalDate updated_on;
}
