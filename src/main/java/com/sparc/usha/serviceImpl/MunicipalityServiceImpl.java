package com.sparc.usha.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparc.usha.entity.MunicipalityCorpoEntity;
import com.sparc.usha.repository.MunicipalityRepository;
import com.sparc.usha.response.CreateKeyValueResponse;
import com.sparc.usha.service.MunicipalityService;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {
	@Autowired
	MunicipalityRepository municipalityRepo;

	@Override
	public List<MunicipalityCorpoEntity> getAllMunicipalityList() {
		return municipalityRepo.findAll();

	}
}
