package com.sparc.usha.request;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.sparc.usha.entity.UshaSurveyEntity;

import lombok.Data;

@Data
public class LrcRequest {
	private Integer lrcApplicantionId;

	private Integer ushaSurveyId;

	private Date plotOccupyingDate;

	private String eastDirection;

	private String westDirection;

	private String northDirection;

	private String southDirection;

	private MultipartFile applicantPhoto;

	private MultipartFile spousePhoto;

	private MultipartFile applicantFingerprint;

	private MultipartFile spouseFingerprint;

	private Boolean activeStatus;

	private Integer statusId;

	private String createdOn;

	private String updated_on;
}
