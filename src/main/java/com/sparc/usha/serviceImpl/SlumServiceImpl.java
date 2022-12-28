package com.sparc.usha.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sparc.usha.entity.SlumEntity;
import com.sparc.usha.entity.WardEntity;
import com.sparc.usha.repository.SlumRepository;
import com.sparc.usha.repository.WardRepository;
import com.sparc.usha.response.SlumResponse;
import com.sparc.usha.response.WardResponse;
import com.sparc.usha.service.SlumService;
@Service
public class SlumServiceImpl implements SlumService {
	@Autowired
	WardRepository wardRepo;
	@Autowired
	SlumRepository slumRepo;
	@Autowired
	EntityManager em;
	@Override
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
	public List<SlumResponse> getSlumInfoByMunicipalityId(Integer municipalityId) {
		 List<SlumEntity> slumInfoByMunicipalityId = slumRepo.getSlumInfoByMunicipalityId(municipalityId);
		 List<SlumResponse> list = new ArrayList<>();
		slumInfoByMunicipalityId.stream().forEach(x->{
			SlumResponse response = new SlumResponse();
			response.setSlumId(x.getSlumId());
			response.setSlumName(x.getSlumName());
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
	public List<SlumResponse> getSlumIdAndSlumNameByMunicipalityIdAndWardNo(Integer wardNo,Integer municipalityId) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= em.createNativeQuery("select s.slum_id as slum_Id,s.slum_name from usha_schema.mt_slum s \r\n"
				+ "join usha_schema.slum_ward w on s.slum_id=w.slum_id where w.ward_no = "+wardNo+" and w.municipality_id= "+municipalityId).getResultList();
		List<SlumResponse> arrlist=new ArrayList<>();
		list.forEach(f->{
			SlumResponse slumRes=new SlumResponse();
			slumRes.setSlumId(Integer.parseInt(f[0].toString()));
			slumRes.setSlumName((f[1].toString()));
			arrlist.add(slumRes);
		});
		arrlist.sort((x,y)->x.getSlumName().compareTo(y.getSlumName()));
		return arrlist;
	}
}
