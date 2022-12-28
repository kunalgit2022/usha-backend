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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparc.usha.entity.UshaSurveyEntity;
import com.sparc.usha.repository.HouseHoldRepository;
import com.sparc.usha.request.UshaSurveyRequest;
import com.sparc.usha.response.IdentityProofResponse;
import com.sparc.usha.response.JsonMessageResponse;
import com.sparc.usha.response.ResponseHandler;
import com.sparc.usha.response.UshaSurveyResponse;
import com.sparc.usha.service.UshaSurveyService;

/**
 * @author prasanjit
 *
 */
@RestController
@RequestMapping("/api/ushaSurvey")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UshaSurveyController {
	@Autowired
	HouseHoldRepository houseHoldRepo;
	@Autowired
	UshaSurveyService ushaSurveyService;

	/*
	 * Prasenjeet 01-11-22 Insert survey data of usha survey
	 */
	@PostMapping("/insertUshaSurveyData")
	private ResponseEntity<Object> insertUshaSurveyData(
			@RequestParam String modelUshaSurveyData,
			@RequestParam MultipartFile applicantIdProofImage, 
			@RequestParam MultipartFile applicantResdProofImage,
			@RequestParam MultipartFile spouseidProofImage,
			@RequestParam MultipartFile ewsProofCertificate,
			@RequestParam MultipartFile castProofCertificate,
			@RequestParam MultipartFile pwdProofCertificate
			) {
		ObjectMapper mapper = new ObjectMapper();
		UshaSurveyRequest request = null;
		try {
			request = mapper.readValue(modelUshaSurveyData, UshaSurveyRequest.class);
			if (!applicantIdProofImage.isEmpty()) {
				request.setApplicantIdProofImage(applicantIdProofImage);
			}
			if (!applicantResdProofImage.isEmpty()) {
				request.setApplicantResdProofImage(applicantResdProofImage);
			}
			if (!spouseidProofImage.isEmpty()) {
				request.setSpouseidProofImage(spouseidProofImage);
			}
			if (!ewsProofCertificate.isEmpty()) {
				request.setEwsProofCertificate(ewsProofCertificate);
			}
			if (!castProofCertificate.isEmpty()) {
				request.setCastProofCertificate(castProofCertificate);
			}
			if (!pwdProofCertificate.isEmpty()) {
				request.setPwdProofCertificate(pwdProofCertificate);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String msg = ushaSurveyService.insertUshaSurveyData(request);
		if (msg == "success") {
			return ResponseEntity.ok(new JsonMessageResponse("data inserted successfully"));
		}
		return ResponseEntity.ok(new JsonMessageResponse("failed to store"));

	}

	/*
	 * Mohsina 03-11-22 Get All survey data of people
	 */
	@GetMapping("/getAllUshaSurveyData")
	private ResponseEntity<?> getAllUshaSurveyData() {
		List<UshaSurveyResponse> surveyDataList = ushaSurveyService.getAllUshaSurveyData();
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, surveyDataList);

	}

	/*
	 * Mohsina 03-11-22 Get All survey data of people by Id
	 */
	@GetMapping("/getUshaSurveyDataById/{id}")
	private ResponseEntity<?> getUshaSurveyDataById(@PathVariable Integer id) {
		Optional<UshaSurveyEntity> surveyDataById = ushaSurveyService.getUshaSurveyDataById(id);
		if (surveyDataById.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, surveyDataById);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, surveyDataById);

	}

	/*
	 * Prasenjeet 05-11-22 get IdentityProof By IdType
	 */
	@GetMapping("/getIdentityProofByIdType/{idProofType}")
	private ResponseEntity<?> getIdentityProofByIdType(@PathVariable Integer idProofType) {
		List<IdentityProofResponse> identityProofById = ushaSurveyService.getIdentityProofByIdType(idProofType);
		if (identityProofById.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, identityProofById);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, identityProofById);

	}

	/**
	 * @author Mohsina 04-11-2022 Get ApplicantInfo By SurveyorId And StatusId
	 */
	@GetMapping("/getApplicantInfoByMunicipalityIdAndStatusId")
	private ResponseEntity<?> getApplicantInfoByMunicipalityAndStatusId(@RequestParam Integer municipalityId,
			@RequestParam Integer statusId) {
		List<UshaSurveyResponse> applicantInfoByMunicipalityAndStatusId = ushaSurveyService
				.getApplicantInfoByMunicipalityAndStatusId(municipalityId, statusId);
		if (applicantInfoByMunicipalityAndStatusId.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK,
					applicantInfoByMunicipalityAndStatusId);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK,
					applicantInfoByMunicipalityAndStatusId);
	}

	/*
	 * Mohsina 07-11-22 Get ApplicantInfo By SurveyorId
	 */
	@GetMapping("/getushaSurveyInfoBySurveyorAndStatusId")
	private ResponseEntity<?> getushaSurveyInfoBySurveyorAndStatusId(@RequestParam Integer surveyorId,
			@RequestParam Integer statusId) {
		List<UshaSurveyResponse> ushaSurveyInfoBySurveyorId = ushaSurveyService
				.getushaSurveyInfoBySurveyorAndStatusId(surveyorId, statusId);
		if (ushaSurveyInfoBySurveyorId.isEmpty())
			return ResponseHandler.generateResponse("data not avaiable", HttpStatus.OK, ushaSurveyInfoBySurveyorId);
		else
			return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK,
					ushaSurveyInfoBySurveyorId);
	}

	/*
	 * Mohsina 10-11-22 update Status By StatusId and UshaId
	 */
	@PutMapping("/updateStatusByStatusIdUshaId")
	private ResponseEntity<?> updateStatusByStatusIdUshaId(@RequestParam Integer statusId,
			@RequestParam Integer ushaSurveyId) {
		String message = ushaSurveyService.updateStatusByStatusIdUshaId(statusId, ushaSurveyId);
		if (message == "success")
			return ResponseEntity.ok(new JsonMessageResponse("success"));
		else
			return ResponseEntity.ok(new JsonMessageResponse("error"));

	}

	/*
	 * Mohsina 03-11-22 Get All Information of all applicant
	 */
	@GetMapping("/getAllInformationofAllSurveyData")
	private ResponseEntity<?> getAllInformationofAllSurveyData() {
		List<UshaSurveyResponse> surveyDataList = ushaSurveyService.getAllInformationofAllSurveyData();
		return ResponseHandler.generateResponse("data retrived successfully!", HttpStatus.OK, surveyDataList);

	}

	/*
	 * Mohsina 10-11-22 update Survey Data By UshaSurveyId
	 */
	@PutMapping("/updateSurveyDataByUshaSurveyId")
	private ResponseEntity<?> updateSurveyDataByUshaSurveyId(
			@RequestParam String modelUshaSurveyData,
			@RequestParam MultipartFile applicantIdProofImage, 
			@RequestParam MultipartFile applicantResdProofImage,
			@RequestParam MultipartFile spouseidProofImage,
			@RequestParam MultipartFile ewsProofCertificate,
			@RequestParam MultipartFile castProofCertificate,
			@RequestParam MultipartFile pwdProofCertificate) {
		ObjectMapper mapper = new ObjectMapper();
		UshaSurveyRequest request = null;
		try {
			request = mapper.readValue(modelUshaSurveyData, UshaSurveyRequest.class);
			if (!applicantIdProofImage.isEmpty()) {
				request.setApplicantIdProofImage(applicantIdProofImage);
			}
			if (!applicantResdProofImage.isEmpty()) {
				request.setApplicantResdProofImage(applicantResdProofImage);
			}
			if (!spouseidProofImage.isEmpty()) {
				request.setSpouseidProofImage(spouseidProofImage);
			}
			if (!ewsProofCertificate.isEmpty()) {
				request.setEwsProofCertificate(ewsProofCertificate);
			}
			if (!castProofCertificate.isEmpty()) {
				request.setCastProofCertificate(castProofCertificate);
			}
			if (!pwdProofCertificate.isEmpty()) {
				request.setPwdProofCertificate(pwdProofCertificate);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String msg = ushaSurveyService.updateSurveyDataByUshaSurveyId(request);
		if (msg == "success") {
			return ResponseEntity.ok(new JsonMessageResponse("data updated successfully"));
		}
		return ResponseEntity.ok(new JsonMessageResponse("failed to store"));

	}

	/*
	 * Mohsina 11-11-22 update note By UshaSurveyId
	 */
	@PutMapping("/updateNoteByUshaSurveyId")
	private ResponseEntity<?> updateNoteByUshaSurveyId(@RequestParam Integer ushaSurveyId, @RequestParam String note) {
		String message = ushaSurveyService.updateNoteByUshaSurveyId(ushaSurveyId, note);
		if (message.equals("success"))
			return ResponseEntity.ok(new JsonMessageResponse("success"));
		else
			return ResponseEntity.ok(new JsonMessageResponse("error"));

	}
}
