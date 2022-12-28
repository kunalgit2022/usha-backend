package com.sparc.usha.service;

import java.util.List;

import com.sparc.usha.response.DashboardResponse;

public interface DashboardService {	

	List<DashboardResponse> getDashboardStatusCount(Integer surveyorId);

}
