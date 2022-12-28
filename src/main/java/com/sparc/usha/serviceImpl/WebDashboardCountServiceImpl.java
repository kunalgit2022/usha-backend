package com.sparc.usha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.stereotype.Service;

import com.sparc.usha.response.WebDashboardCountResponse;
import com.sparc.usha.service.WebDashboardCountService;

@Service
public class WebDashboardCountServiceImpl implements WebDashboardCountService {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public WebDashboardCountResponse getWebDashboardCount(String muncipalityId, String wardNo) {
		StoredProcedureQuery storedProcedureQuery = entityManager
				.createStoredProcedureQuery("usha_schema.fn_get_all_web_dashboard_data");
		storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(1, muncipalityId);
		storedProcedureQuery.setParameter(2, wardNo);
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = storedProcedureQuery.getResultList();
		WebDashboardCountResponse dashboardCount = new WebDashboardCountResponse();
		for (Object[] x : resultList) {
			dashboardCount.setWardCount(Integer.parseInt((x[0].toString())));
			dashboardCount.setSlumCount(Integer.parseInt((x[1].toString())));
			dashboardCount.setUshaSurveycount(Integer.parseInt((x[2].toString())));
			dashboardCount.setUshaSurveyCompletedcountTodaysDate(Integer.parseInt((x[3].toString())));
			dashboardCount.setUshaSurveyCompletedcountTillDate(Integer.parseInt((x[4].toString())));
			dashboardCount.setLrcApplicationRegisterdTodaysDate(Integer.parseInt((x[5].toString())));
			dashboardCount.setLrcApplicationRegisterdTillDate(Integer.parseInt((x[6].toString())));
			dashboardCount.setApplicationValidatedTodaysDate(Integer.parseInt((x[7].toString())));
			dashboardCount.setApplicationValidatedTillDate(Integer.parseInt((x[8].toString())));
			dashboardCount.setNoOfSurveyorsTodaysDate(Integer.parseInt(x[9].toString()));
			dashboardCount.setNoOfSurveyorsTillDate(Integer.parseInt(x[10].toString()));			
		}
		return dashboardCount;
	}
@Override
public WebDashboardCountResponse getwebCoveredByMunicipalityId(String muncipalityId) {
	StoredProcedureQuery storedProcedureQuery = entityManager
			.createStoredProcedureQuery("usha_schema.fn_get_all_wards_covered_web_dashboard");
	storedProcedureQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
	storedProcedureQuery.setParameter(1, muncipalityId);
	String wardCovered=storedProcedureQuery.getSingleResult().toString();
	WebDashboardCountResponse dashboardCount = new WebDashboardCountResponse();		
	dashboardCount.setWardCovered(Integer.parseInt(String.valueOf(wardCovered)));
	return dashboardCount;
}
}
