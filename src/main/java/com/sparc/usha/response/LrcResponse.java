package com.sparc.usha.response;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sparc.usha.entity.UshaSurveyEntity;

import lombok.Data;
@Data
public class LrcResponse {
	private Integer lrcApplicantionId;
	
	private Integer ushaSurveyId;
	
	private Date plotOccupyingDate;
	
	
	private String eastDirection;
	
	private String westDirection;
	
	
	private String northDirection;
	
	private String southDirection;
	

	private String applicantPhoto;
	
	
	private String spousePhoto;
	
	
	private String applicantFingerprint;
	
	
	private String spouseFingerprint;
	
	
	private Boolean activeStatus;	
}
