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

import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.response.SlumResponse;
import com.sparc.usha.response.WardResponse;
import com.sparc.usha.service.SlumService;
import com.sparc.usha.service.WardService;

@RestController
@RequestMapping("/api/ward")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WardController {	
	@Autowired
	WardService wardService;
	/**
	 * @author Mohsina
	 * 05-11-2022
	 * Get Ward No By SlumId
	 */
	@GetMapping("/getWardNoBySlumId/{slumId}")
	private ResponseEntity<?> getWardNoBySlumId(@PathVariable Integer slumId) {
		List<WardResponse> wardNoBySlumId = wardService.getWardNoBySlumId(slumId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, wardNoBySlumId);
		
	}
	/**
	 * @author Mohsina
	 * 09-11-2022
	 * get wardInfo By MunicipalityId
	 */
	@GetMapping("/getWardInfoByMunicipalityId/{municipalityId}")
	private ResponseEntity<?> getWardInfoByMunicipalityId(@PathVariable Integer municipalityId) {
		List<WardResponse> wardInfoByMunicipalityId = wardService.getWardInfoByMunicipalityId(municipalityId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, wardInfoByMunicipalityId);
		
	}
	/**
	 * @author Mohsina
	 * 10-11-2022
	 * count no. of ward By MunicipalityId
	 */
	@GetMapping("/getCountWardByMunicipalityId/{municipalityId}")
	private ResponseEntity<?> getCountWardByMunicipalityId(@PathVariable Integer municipalityId) {
		String countWardByMunicipalityId = wardService.getCountWardByMunicipalityId(municipalityId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, countWardByMunicipalityId);
		
	}
}
