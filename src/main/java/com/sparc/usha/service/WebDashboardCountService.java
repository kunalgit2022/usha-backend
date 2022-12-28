package com.sparc.usha.service;

import java.util.List;

import com.sparc.usha.response.WebDashboardCountResponse;

public interface WebDashboardCountService {
	WebDashboardCountResponse getWebDashboardCount(String muncipalityId,String wardNo);

	WebDashboardCountResponse getwebCoveredByMunicipalityId(String muncipalityId);

}
