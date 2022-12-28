package com.sparc.usha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.sparc.usha.entity.SlumEntity;
import com.sparc.usha.entity.WardEntity;
import com.sparc.usha.response.SlumResponse;
import com.sparc.usha.response.WardResponse;

public interface SlumService {

	List<WardResponse> getWardNoBySlumId(Integer slumId);

	List<SlumResponse> getSlumInfoByMunicipalityId(Integer municipalityId);

	List<WardResponse> getWardInfoByMunicipalityId(Integer municipalityId);
	
	List<SlumResponse> getSlumIdAndSlumNameByMunicipalityIdAndWardNo(Integer ward_no,Integer municipalityId);

}
