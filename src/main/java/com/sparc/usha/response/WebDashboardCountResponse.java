package com.sparc.usha.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class WebDashboardCountResponse {
	private Integer wardCount;
	private Integer slumCount;
	private Integer ushaSurveycount;
	private Integer ushaSurveyCompletedcountTodaysDate;
	private Integer ushaSurveyCompletedcountTillDate;
	private Integer lrcApplicationRegisterdTodaysDate;
	private Integer lrcApplicationRegisterdTillDate;
	private Integer applicationValidatedTodaysDate;
	private Integer applicationValidatedTillDate;
	private Integer noOfSurveyorsTodaysDate;
	private Integer noOfSurveyorsTillDate;
	private Integer wardCovered;
}
