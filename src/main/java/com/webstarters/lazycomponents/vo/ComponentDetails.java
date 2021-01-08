package com.webstarters.lazycomponents.vo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentDetails {

	private String componentId = null;

	private String sortIndex = null;
	
	private String sortOrder = null;
	
	private int pageIndex = Integer.MIN_VALUE;
	
	private int rowsPerPage = Integer.MIN_VALUE;
	
	private int startIndex = Integer.MIN_VALUE;
	
	private Map<String, Object>	criteriaParams	= new HashMap<>();
	
	private Map<String, Object>	filterParams	= new HashMap<>();

	public ComponentDetails() {

	}
	
	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public Map<String, Object> getCriteriaParams() {
		return criteriaParams;
	}

	public void setCriteriaParams(Map<String, Object> criteriaParams) {
		this.criteriaParams = criteriaParams;
	}

	public Map<String, Object> getFilterParams() {
		return filterParams;
	}

	public void setFilterParams(Map<String, Object> filterParams) {
		this.filterParams = filterParams;
	}
	
	public Map<String, Object> createSingleFilterParamMap(List<String> inParams) {
		List<String> fixedValueParams = Arrays.asList("forCount","limitFrom","limitTo");
		Map<String, Object> parameterMap = new HashMap<>();
		for (String paramName : inParams) {
			if(Boolean.FALSE.equals(fixedValueParams.contains(paramName))) {
				Object paramValue = this.filterParams.getOrDefault(paramName, this.criteriaParams.get(paramName));
				parameterMap.put(paramName, paramValue);
			}
		}
		return parameterMap;
	}

	public void setStartIndex() {
		this.startIndex = this.rowsPerPage * (pageIndex - 1);
	}
}
