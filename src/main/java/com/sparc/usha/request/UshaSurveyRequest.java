package com.sparc.usha.request;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sparc.usha.entity.IdentitytProofEntity;
import com.sparc.usha.entity.MunicipalityCorpoEntity;
import com.sparc.usha.entity.SlumEntity;
import com.sparc.usha.entity.StatusEntity;
import com.sparc.usha.entity.UserEntity;

import lombok.Data;

@Data
public class UshaSurveyRequest {

	private Integer ushaSurveyId;
	private Integer municipalityId;// FK(municipality table(id))

	private Integer wardNo;

	private Integer slumType;// FK(mt_slum(slum_id))

	private String streetLane;

	private String houseNo;

	private String applicantName;

	private String applicantMobile;
	
	private String applicantGender;

	private String parentSpouseName;

	private String parentSpouseMobile;

	private Double familyAnualIncome;

	private Boolean anualIncomeProof;

	private Boolean ewsProof;

	private String ewsProofType;

	private String castCategory;

	private Boolean castCertificate;

	private Boolean pwd;

	private Boolean pwdCertificate;

	private MultipartFile ewsProofCertificate;
	
	private MultipartFile castProofCertificate;
	
	private MultipartFile pwdProofCertificate;

	private String religion;

	private Integer applicantIdProofType;

	private String applicantIdProofNo;

	private String applicantIdProofIssueDate;

	private MultipartFile applicantIdProofImage;

	private Integer applicantResdProofType;// FK(identity proof(id))

	private String applicantResdProofNo;

	private String applicantResdProofIssueDate;

	private MultipartFile applicantResdProofImage;

	private Integer spouseIdProofType;// FK(identity proof(id))

	private String spouseIdProofNo;

	private String spouseIdProofIssueDate;

	private MultipartFile spouseidProofImage;

	private String wallType;

	private String roofType;

	private Boolean houseHaveToilet;

	private Boolean dwellingAccessToPuccaPaver;

	private Boolean landOwnershipInAnyUlb;

	private Boolean landOwnershipInSameUlb;

	private Integer serveyorId;

	private Integer assigneeId;

	private String assignDate;

	private String reportedDate;

	private Integer status;

	private Boolean activeStatus;

	private List<addHouseHoldData> householdData;

	private String createdOn;

	// private String updated_on;

}
