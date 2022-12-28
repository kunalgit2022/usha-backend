package com.sparc.usha.service;

import java.util.List;
import java.util.Optional;

import com.sparc.usha.entity.UshaSurveyEntity;
import com.sparc.usha.request.UshaSurveyRequest;
import com.sparc.usha.response.IdentityProofResponse;
import com.sparc.usha.response.UshaSurveyResponse;
import com.sparc.usha.response.HouseholdResponse;

public interface UshaSurveyService {

	String insertUshaSurveyData(UshaSurveyRequest request);

	List<UshaSurveyResponse> getAllUshaSurveyData();

	Optional<UshaSurveyEntity> getUshaSurveyDataById(Integer id);
	
	List<IdentityProofResponse> getIdentityProofByIdType(Integer idProofType);

	//List<UshaSurveyResponse> getApplicantInfoBySurveyorAndStatusId(Integer municipalityId, Integer statusId);

	List<UshaSurveyResponse> getApplicantInfoBySurveyorId(Integer surveyorId);

	List<UshaSurveyResponse> getAllInformationofAllSurveyData();

	List<HouseholdResponse> getAllInformationByushaSurveyId(Integer ushaSurveyId);

	String updateStatusByStatusIdUshaId(Integer statusId, Integer ushaSurveyId);



	List<UshaSurveyResponse> getushaSurveyInfoBySurveyorAndStatusId(Integer surveyorId, Integer statusId);

	List<UshaSurveyResponse> getApplicantInfoByMunicipalityAndStatusId(Integer municipalityId, Integer statusId);

	String updateStatusByStatusIdUshaId(UshaSurveyRequest request);

	String updateNoteByUshaSurveyId(Integer ushaSurveyId,String note);

	String updateSurveyDataByUshaSurveyId(UshaSurveyRequest request);

	//List<UshaSurveyEntity> getAllInformationByushaSurveyId(Integer ushaSurveyId);

}
