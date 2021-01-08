package com.webstarters.lazycomponents.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.webstarters.lazycomponents.dao.ComponentDetailsDao;
import com.webstarters.lazycomponents.dao.ComponentDetailsRepository;
import com.webstarters.lazycomponents.dao.impl.LazyComponentHelperDao;
import com.webstarters.lazycomponents.services.ComponentDetailsService;
import com.webstarters.lazycomponents.vo.ComponentDetails;
import com.webstarters.lazycomponents.vo.ComponentDetailsEntity;
import com.webstarters.lazycomponents.vo.ComponentDetailsVo;
import com.webstarters.lazycomponents.vo.ComponentResponseData;

/**
 * @author Aman Prasad
 *
 */
public class ComponentDetailsServiceImpl implements ComponentDetailsService {

	@Autowired
	private ComponentDetailsDao componentDao = null;
	
	@Autowired
	private ComponentDetailsRepository componentDetailsRepository = null;
	
	@Autowired
	private LazyComponentHelperDao lazyComponentHelperDao = null;
	
	@PostConstruct
	public void afterConstruct() {
		
	}

	@Override
	public ComponentDetailsVo getComponentDetailsByComponentId(String componentId) {
		ComponentDetailsEntity componentDetailsEntity = componentDetailsRepository.findById(componentId).orElseThrow();
		return new ComponentDetailsVo(componentDetailsEntity);
	}

	@Override
	public ComponentResponseData getCompenentData(ComponentDetails componentDetails) {
		ComponentDetailsVo componentDetailsVo = getComponentDetailsByComponentId(componentDetails.getComponentId());
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("limitFrom", componentDetails.getStartIndex());
		inParams.put("limitTo", componentDetails.getRowsPerPage());
		String limitQuery = "";
		Integer totalrecords = Integer.MIN_VALUE;
		if(componentDetailsVo.getQueryType() == 1) {
			inParams.putAll(componentDetails.getCriteriaParams());
			inParams.putAll(componentDetails.getFilterParams());
			limitQuery = " LIMIT :limitFrom, :limitTo";
			totalrecords = componentDao.executeQuery(componentDetailsVo.getQuery(), inParams).size();
		} else if(componentDetailsVo.getQueryType() == 2) {
			inParams = getInParamForStoredProd(componentDetails, componentDetailsVo.getTableName());
			inParams.put("limitFrom", componentDetails.getStartIndex());
			inParams.put("limitTo", componentDetails.getRowsPerPage());
			inParams.put("forCount", 1);
			Long resultTotalrecords = (Long) componentDao.executeQuery(componentDetailsVo.getQuery(), inParams).get(0).get("cnt");
			totalrecords = resultTotalrecords == null ? 0 : resultTotalrecords.intValue();
			inParams.put("forCount", 0);
		}
		List<Map<String, Object>> resultSet = componentDao.executeQuery(componentDetailsVo.getQuery() + limitQuery, inParams);
		return new ComponentResponseData(componentDetails.getPageIndex(), totalrecords, resultSet);
	}
	
	private Map<String, Object> getInParamForStoredProd(ComponentDetails componentDetails, String routineName) {
		List<String> routineParams = lazyComponentHelperDao.getInParametersForRoutine(routineName);
		return componentDetails.createSingleFilterParamMap(routineParams);
	}
	
	
}
