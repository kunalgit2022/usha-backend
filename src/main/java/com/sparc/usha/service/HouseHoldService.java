package com.sparc.usha.service;

import java.util.List;

import com.sparc.usha.entity.HouseholdEntity;
import com.sparc.usha.response.HouseholdResponse;

public interface HouseHoldService {

	List<HouseholdResponse> getHouseholdDetailsByUshaSurveyId(Integer ushaSurveyId);

}
