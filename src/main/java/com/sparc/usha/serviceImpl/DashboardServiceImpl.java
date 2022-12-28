package com.sparc.usha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparc.usha.repository.SlumRepository;
import com.sparc.usha.repository.UshaSurveyRepository;
import com.sparc.usha.response.DashboardResponse;
import com.sparc.usha.service.DashboardService;

@Service

public class DashboardServiceImpl implements DashboardService {
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	UshaSurveyRepository ushaRepo;
	@Autowired
	SlumRepository slumRepo;

	@Override
	public List<DashboardResponse> getDashboardStatusCount(Integer surveyorId) {
		// List<DashboardResponse>
		// list=ushaSurveyRepo.getDashboardStatusCount(surveyorId);
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("usha_schema.fn_getalldashboard_status_count");
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.setParameter(1, surveyorId);
		List<Object[]> resultList = query.getResultList();
		List<DashboardResponse> dashList=new ArrayList<DashboardResponse>();
		resultList.forEach(x->{
			DashboardResponse response = new DashboardResponse();
			response.setTotalUsha(x[0].toString());
			response.setTotalLrc(x[1].toString());
			response.setApprovedUsha(x[2].toString());
			response.setResurveyUsha(x[3].toString());
			response.setEditUsha(x[4].toString());
			response.setEditLrc(x[5].toString());
			dashList.add(response);
		});
		return dashList;
	}

}
