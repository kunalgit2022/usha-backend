package com.sparc.usha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.response.DashboardResponse;
import com.sparc.usha.response.JsonMessageResponse;
import com.sparc.usha.response.WebDashboardCountResponse;
import com.sparc.usha.service.DashboardService;
import com.sparc.usha.service.WebDashboardCountService;

@RestController
@RequestMapping("/api/dashboardCount")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashboardCountController {
	
	@Autowired
	DashboardService dashBoardservice;
	
	@Autowired
	WebDashboardCountService webDashboardCountService;

	
	/*
	 * Prasanjit 14-11-2022 
	 * 
	 */
	@GetMapping("/getWebDashboardCount")
	public WebDashboardCountResponse getWebDashboardCount(@RequestParam String muncipalityId,@RequestParam String wardNo) {
		WebDashboardCountResponse webDashboardCount = webDashboardCountService.getWebDashboardCount(muncipalityId, wardNo);
		return webDashboardCount;

	}
	/*
	 * Mohsina Perween 12-11-2022 Get status count by status1,status2,status3 by
	 * survey id
	 */
	@GetMapping("/getDashboardStatusCountBySurveyorId/{surveyorId}")
	public ResponseEntity<?> getDashboardStatusCountBySurveyorId(@PathVariable Integer surveyorId) {
		List<DashboardResponse> allDashBoardCount = dashBoardservice.getDashboardStatusCount(surveyorId);
		return ResponseEntity.ok(allDashBoardCount);
	}
	/* 	
	 * Mohsina Perween 25-11-2022 Get all ward covered by municipality id
	 */
	@GetMapping("/getwardCoveredByMunicipalityId")
	public ResponseEntity<?> getwebCoveredByMunicipalityId(@RequestParam String muncipalityId) {
		WebDashboardCountResponse webDashboardCount = webDashboardCountService.getwebCoveredByMunicipalityId(muncipalityId);
		return ResponseEntity.ok(webDashboardCount);
	}
}
