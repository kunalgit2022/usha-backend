package com.sparc.usha.service;

import java.util.List;
import java.util.Optional;

import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.request.LrcRequest;
import com.sparc.usha.response.LrcResponse;

public interface LrcService {

	List<LrcResponse> getAllLrcDetailsByUshaSurveyId(Integer ushaSurveyId);

	String insertLrcDetails(LrcRequest request);

	String updateLrcDetails(LrcRequest request);

}
