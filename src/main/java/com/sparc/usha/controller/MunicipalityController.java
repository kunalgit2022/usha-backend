package com.sparc.usha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.entity.MunicipalityCorpoEntity;
import com.sparc.usha.response.CreateKeyValueResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.service.MunicipalityService;

@RestController
@RequestMapping("/api/municipality")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MunicipalityController {
	@Autowired
	MunicipalityService municipalityService;
	/**
	 * @author Mohsina
	 * 05-11-2022
	 * Get  All Municipality Corporation List
	 */
	@GetMapping("/getAllMunicipalityList")
	private ResponseEntity<?> getAllMunicipalityList() {
		List<MunicipalityCorpoEntity> municipalityList = municipalityService.getAllMunicipalityList();
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, municipalityList);

	}
}
