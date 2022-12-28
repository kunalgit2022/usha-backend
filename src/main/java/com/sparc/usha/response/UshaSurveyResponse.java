package com.sparc.usha.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sparc.usha.entity.StatusEntity;
import com.sparc.usha.entity.UserEntity;

import lombok.Data;
//@JsonInclude(value = Include.NON_NULL)
@Data
public class UshaSurveyResponse {
	private String houseHoldNo;
	private String applicantName;
	private Integer ushaSurveyId;
	private String parentSpouseName;
	private Integer surveyorId;
	private Integer statusId;
	private String idProofNo;
	private Integer idProofType;
	private String idProofName;	
	private Integer slumId;
	private String slumName;
	private String municipalityName;
	private String note;
	private String surveyorName;
	private String createdOn; 
	//private long surveyCount;
	//private SurveyCountResponse surveyCount;
}
