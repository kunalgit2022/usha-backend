package com.sparc.usha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.entity.HouseholdEntity;
import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.repository.HouseHoldRepository;
import com.sparc.usha.response.HouseholdResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.service.HouseHoldService;

@RestController
@RequestMapping("/api/houseHold")
@CrossOrigin(origins = "*",maxAge = 3600)
public class HouseHoldController {
	@Autowired
	HouseHoldRepository houseHoldRepo;	
	@Autowired
	HouseHoldService houseService;
	@GetMapping("getHouseholdDetailsByUshaSurveyId/{ushaSurveyId}")
	private ResponseEntity<?> getHouseholdDetailsByUshaSurveyId(@PathVariable Integer ushaSurveyId) {
		List<HouseholdResponse> houseList = houseService.getHouseholdDetailsByUshaSurveyId(ushaSurveyId);
		if (houseList.isEmpty())
			return ResponseHandler.generateResponse("Data Not Available", HttpStatus.OK, houseList);
		else
			return ResponseHandler.generateResponse("Data retrieved Successfully", HttpStatus.OK, houseList);
	}
}
