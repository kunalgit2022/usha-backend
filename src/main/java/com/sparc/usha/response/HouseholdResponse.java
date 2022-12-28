package com.sparc.usha.response;

import lombok.Data;
@Data
public class HouseholdResponse {

	private Integer householdId;

	private Integer ushaSurveyId;

	private String name;

	private Integer age;

	private String gender;

	private String occupation;

	private String relationToApplicant;

	private Boolean dependantToApplicant;

	private String contactDetails;

	private Boolean activeStatuse;

}
