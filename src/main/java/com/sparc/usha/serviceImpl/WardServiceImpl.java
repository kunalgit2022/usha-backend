package com.sparc.usha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparc.usha.entity.WardEntity;
import com.sparc.usha.repository.WardRepository;
import com.sparc.usha.response.WardResponse;
import com.sparc.usha.service.WardService;
@Service
public class WardServiceImpl implements WardService{
	@Autowired
	WardRepository wardRepo;
	public List<WardResponse> getWardNoBySlumId(Integer slumId) {
		List<WardEntity> wardNoBySlumId = wardRepo.getWardNoBySlumId(slumId);
		List<WardResponse> list = new ArrayList<>();
		wardNoBySlumId.stream().forEach(x->{
			WardResponse response =new WardResponse();
			response.setWardNumber(x.getWardNo());
				list.add(response);
		});
		return list;
	}
	@Override
	public List<WardResponse> getWardInfoByMunicipalityId(Integer municipalityId) {
		List<Integer> wardNoByMunicipalityId = wardRepo.getWardInfoByMunicipalityId(municipalityId);
		List<WardResponse> list = new ArrayList<>();
		wardNoByMunicipalityId.stream().forEach(x->{
			WardResponse response =new WardResponse();
			response.setWardNumber(x);
				list.add(response);
		});
		
		return list;
	}
	@Override
	public String getCountWardByMunicipalityId(Integer municipalityId) {
		// TODO Auto-generated method stub
		String count=wardRepo.getCountWardByMunicipalityId(municipalityId);
		return null;
	}
}
