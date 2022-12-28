package com.sparc.usha.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sparc.usha.entity.MunicipalityCorpoEntity;
import com.sparc.usha.response.CreateKeyValueResponse;


public interface MunicipalityService {

	List<MunicipalityCorpoEntity> getAllMunicipalityList();

}
