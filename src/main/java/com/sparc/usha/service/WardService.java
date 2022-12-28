package com.sparc.usha.service;

import java.util.List;

import com.sparc.usha.response.WardResponse;

public interface WardService {

	List<WardResponse> getWardNoBySlumId(Integer slumId);

	List<WardResponse> getWardInfoByMunicipalityId(Integer municipalityId);

	String getCountWardByMunicipalityId(Integer municipalityId);

}
