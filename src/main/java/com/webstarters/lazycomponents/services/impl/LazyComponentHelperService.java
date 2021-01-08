package com.webstarters.lazycomponents.services.impl;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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
}
