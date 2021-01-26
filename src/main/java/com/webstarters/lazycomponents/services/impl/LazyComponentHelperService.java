package com.webstarters.lazycomponents.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.text.CaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webstarters.lazycomponents.dao.impl.LazyComponentHelperDao;

@Service
@Transactional(readOnly = true)
public class LazyComponentHelperService {

	@Autowired
	private LazyComponentHelperDao lazyComponentHelperDao = null;
	
	public String getQueryTemplateForTable(String tableName) {
		List<Map<String, Object>> resultSet = lazyComponentHelperDao.getTableInformationByName(tableName);
		StringBuffer query = new StringBuffer("SELECT ");
		StringJoiner joiner = new StringJoiner(", ");
		for (Map<String, Object> columns : resultSet) {
			String columnName = columns.get("columnName").toString();
			joiner.add(columnName + " AS " + CaseUtils.toCamelCase(columnName, false, '_').replaceAll("_", ""));
		}
		query.append(joiner.toString());
		query.append(" FROM ").append(tableName);
		return query.toString();
	}
	
	public Map<String, Object> getQueryTemplateForStoredProd(String tableName, List<String> selectedColumns) {
		List<Map<String, Object>> resultSet = lazyComponentHelperDao.getTableInformationByName(tableName);
		List<Map<String, Object>> inParams = resultSet.stream()
				.filter(map -> selectedColumns.contains(map.get("columnName").toString()))
				.collect(Collectors.toList());
		StringBuffer selectQuery = new StringBuffer("SELECT ");
		StringJoiner joiner = new StringJoiner(", ");
		for (Map<String, Object> columns : resultSet) {
			String columnName = columns.get("columnName").toString();
			joiner.add(columnName + " AS " + CaseUtils.toCamelCase(columnName, false, '_').replaceAll("_", ""));
		}
		selectQuery.append(joiner.toString());
		StringBuffer fromQuery = new StringBuffer(" FROM ").append(tableName);

		StringJoiner inParamString = new StringJoiner(", ");
		for (Map<String, Object> columns : inParams) {
			String columnName = columns.get("columnName").toString();
			String columnType = columns.get("columnType").toString();
			columns.put("param", CaseUtils.toCamelCase(columnName, false, '_').replaceAll("_", ""));
			inParamString.add(CaseUtils.toCamelCase(columnName, false, '_').replaceAll("_", "") + " " + columnType);
		}
		Map<String, Object> templateParameters = new HashMap<>();
		templateParameters.put("inParams", inParamString);
		templateParameters.put("selectString", selectQuery.toString());
		templateParameters.put("fromString", fromQuery.toString());
		templateParameters.put("filterParams", inParams);
		return templateParameters;
	}
	
	public List<String> getTablesInformation() {
		return lazyComponentHelperDao.getTableInformation();
	}

	public List<Map<String, Object>> getTableColumnDetails(String tableName) {
		return lazyComponentHelperDao.getTableInformationByName(tableName);
	}
}
