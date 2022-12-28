package com.sparc.usha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.response.SlumResponse;
import com.sparc.usha.response.WardResponse;
import com.sparc.usha.service.SlumService;

@RestController
@RequestMapping("/api/slum")
@CrossOrigin(origins = "*",maxAge = 3600)
public class SlumController {
	@Autowired
	SlumService slumService;
	/**
	 * @author Mohsina
	 * 05-11-2022
	 * Get Ward No By SlumId
	 */
	@GetMapping("/getWardNoBySlumId/{slumId}")
	private ResponseEntity<?> getWardNoBySlumId(@PathVariable Integer slumId) {
		List<WardResponse> wardNoBySlumId = slumService.getWardNoBySlumId(slumId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, wardNoBySlumId);
		
	}
	/**
	 * @author Mohsina
	 * 05-11-2022
	 * Get Slum Info By MunicipalityId
	 */
	@GetMapping("/getSlumInfoByMunicipalityId/{municipalityId}")
	private ResponseEntity<?> getSlumInfoByMunicipalityId(@PathVariable Integer municipalityId) {
		List<SlumResponse> slumInfoByMunicipalityId = slumService.getSlumInfoByMunicipalityId(municipalityId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, slumInfoByMunicipalityId);
		
	}
	/**
	 * @author Mohsina
	 * 09-11-2022
	 * get wardInfo By MunicipalityId
	 */
	@GetMapping("/getWardInfoByMunicipalityId/{municipalityId}")
	private ResponseEntity<?> getWardInfoByMunicipalityId(@PathVariable Integer municipalityId) {
		List<WardResponse> wardInfoByMunicipalityId = slumService.getWardInfoByMunicipalityId(municipalityId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, wardInfoByMunicipalityId);
		
	}
	/**
	 * @author Mohsina
	 * 09-11-2022
	 * get SlumDetails By MunicipalityId And WardNo
	 */
	@GetMapping("/getSlumDetailsByMunicipalityIdAndWardNo")
	private ResponseEntity<?> getSlumIdAndSlumNameByMunicipalityIdAndWardNo(@RequestParam Integer wardNo,@RequestParam Integer municipalityId){
		List<SlumResponse> slumIdAndSlumNameByMunicipalityIdAndWardNo = slumService.getSlumIdAndSlumNameByMunicipalityIdAndWardNo(wardNo, municipalityId);
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, slumIdAndSlumNameByMunicipalityIdAndWardNo);
		
	}
	
}
