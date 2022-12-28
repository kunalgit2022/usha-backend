package com.sparc.usha.request;

import javax.persistence.Column;

import com.sparc.usha.entity.UshaSurveyEntity;

import lombok.Data;

@Data
public class addHouseHoldData {
	private Integer houseHoldId;
	private Integer ushaSurveyId;
	private String name;
	private Integer age;
	private String Gender;
	private String occupation;
	private String relationToApplicant;
	private Boolean dependantToApplicant;
	private String contactDetails;
	private Boolean activeStatus=true;
	

}
