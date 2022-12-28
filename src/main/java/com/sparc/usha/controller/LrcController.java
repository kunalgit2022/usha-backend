package com.sparc.usha.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.request.LrcRequest;
import com.sparc.usha.request.UshaSurveyRequest;
import com.sparc.usha.response.JsonMessageResponse;
import com.sparc.usha.response.LrcResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.service.LrcService;

@RestController
@RequestMapping("/api/lrc")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LrcController {
	@Autowired
	LrcService lrcService;
	/*
	 * Mohsina 07-11-22 insert Lrc Details By UshaSurveyId
	 */
	@PostMapping("/insertLrcDetails")
	private ResponseEntity<?> insertLrcDetails(
			@RequestParam String modelLrcApplicationData,
			@RequestParam MultipartFile applicantPhoto,
			@RequestParam MultipartFile spousePhoto,
			@RequestParam MultipartFile applicantFingerprint,
			@RequestParam MultipartFile spouseFingerprint) {
		ObjectMapper mapper = new ObjectMapper();
		LrcRequest request=null;
		try {
			request = mapper.readValue(modelLrcApplicationData, LrcRequest.class);
			if(applicantPhoto!=null) 
			{
				request.setApplicantPhoto(applicantPhoto);
			}
			if(spousePhoto!=null) 
			{
				request.setSpousePhoto(spousePhoto);
			}
			if(applicantFingerprint!=null) 
			{
				request.setApplicantFingerprint(applicantFingerprint);
			}
			if(spouseFingerprint!=null) 
			{
				request.setSpouseFingerprint(spouseFingerprint);
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String msg = lrcService.insertLrcDetails(request);
		if (msg == "success") {
			return ResponseEntity.ok(new JsonMessageResponse("data inserted successfully"));
		}
		return ResponseEntity.ok(new JsonMessageResponse("failed to store"));
	}
	
	/*
	 * Mohsina 07-11-22 Get All Lrc Details By UshaSurveyId
	 */
	@GetMapping("getAllLrcDetailsByUshaSurveyId/{ushaSurveyId}")
	private ResponseEntity<?> getAllLrcDetailsByUshaSurveyId(@PathVariable Integer ushaSurveyId) {
		List<LrcResponse> lrcList = lrcService.getAllLrcDetailsByUshaSurveyId(ushaSurveyId);
		if (lrcList.isEmpty())
			return ResponseHandler.generateResponse("Data Not Available", HttpStatus.OK, lrcList);
		else
			return ResponseHandler.generateResponse("Data retrieved Successfully", HttpStatus.OK, lrcList);
	}
	/*
	 * Mohsina 11-11-22 update Lrc Details By UshaSurveyId
	 */
	@PutMapping("/updateLrcDetails")
	private ResponseEntity<?> updateLrcDetails(
			@RequestParam String modelLrcApplicationData,
			@RequestParam MultipartFile applicantPhoto,
			@RequestParam MultipartFile spousePhoto,
			@RequestParam MultipartFile applicantFingerprint,
			@RequestParam MultipartFile spouseFingerprint) {
		ObjectMapper mapper = new ObjectMapper();
		LrcRequest request=null;
		try {
			request = mapper.readValue(modelLrcApplicationData, LrcRequest.class);
			if(applicantPhoto!=null) 
			{
				request.setApplicantPhoto(applicantPhoto);
			}
			if(spousePhoto!=null) 
			{
				request.setSpousePhoto(spousePhoto);
			}
			if(applicantFingerprint!=null) 
			{
				request.setApplicantFingerprint(applicantFingerprint);
			}
			if(spouseFingerprint!=null) 
			{
				request.setSpouseFingerprint(spouseFingerprint);
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String msg = lrcService.updateLrcDetails(request);
		if (msg == "success") {
			return ResponseEntity.ok(new JsonMessageResponse("data updated successfully"));
		}
		return ResponseEntity.ok(new JsonMessageResponse("failed to store"));
	}
}
