package com.sparc.usha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparc.usha.entity.HouseholdEntity;
import com.sparc.usha.entity.LRCapplicationEntity;
import com.sparc.usha.repository.HouseHoldRepository;
import com.sparc.usha.response.HouseholdResponse;
import com.sparc.usha.service.HouseHoldService;
@Service
public class HouseHoldServiceImpl implements HouseHoldService{
	@Autowired
	HouseHoldRepository houseHoldRepo;	
@Override
public List<HouseholdResponse> getHouseholdDetailsByUshaSurveyId(Integer ushaSurveyId) {
	try {
		List<HouseholdEntity> houseList = (List<HouseholdEntity>) houseHoldRepo.findAllByUshaSurveyId(ushaSurveyId);
		List<HouseholdResponse> list = new ArrayList();
		houseList.forEach(x->{
			HouseholdResponse response=new HouseholdResponse();
			response.setUshaSurveyId(ushaSurveyId);
			response.setHouseholdId(x.getHouseholdId());
			response.setName(x.getName());
			response.setAge(x.getAge());
			response.setGender(x.getGender());
			response.setContactDetails(x.getContactDetails());
			response.setOccupation(x.getOccupation());			
			response.setRelationToApplicant(x.getRelationToApplicant());
			response.setDependantToApplicant(x.getDependantToApplicant());			
			response.setActiveStatuse(x.getActiveStatus());
			list.add(response);
		});
		return list;
	}catch (Exception e) {
		e.printStackTrace();
		throw e;
	}	
}
}
