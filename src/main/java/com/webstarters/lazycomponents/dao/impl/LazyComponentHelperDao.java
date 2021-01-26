package com.webstarters.lazycomponents.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LazyComponentHelperDao {
	
	private static final String QUERY_TO_GET_IN_PARAMETERES = "SELECT routinesParameter.PARAMETER_NAME FROM information_schema.ROUTINES as routines "
            + " INNER JOIN information_schema.PARAMETERS as routinesParameter "
            + " ON routines.SPECIFIC_NAME = routinesParameter.SPECIFIC_NAME "
            + " WHERE routinesParameter.PARAMETER_MODE = :parameterMode AND routines.SPECIFIC_NAME = :routineName ";

	@Autowired
	private DataSource dataSource = null;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;

	public List<Map<String, Object>> getTableInformationByName(String tableName) {
		String query = "select COLUMN_NAME as columnName, COLUMN_TYPE as columnType from information_schema.COLUMNS "
				+ " where TABLE_NAME = :tableName and TABLE_SCHEMA = :schemaName ORDER BY ORDINAL_POSITION ASC ";
		List<Map<String, Object>> resultSet = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();){
			String schemaName = connection.getCatalog();
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("tableName", tableName);
			parameterMap.put("schemaName", schemaName);
			resultSet = namedParameterJdbcTemplate.queryForList(query, parameterMap);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return resultSet;
	}
	
	public List<String> getInParametersForRoutine(String routineName) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("routineName", routineName);
        parameterMap.put("parameterMode", "IN");
        List<String> results = new ArrayList<>();
        try {
            results = namedParameterJdbcTemplate.queryForList(QUERY_TO_GET_IN_PARAMETERES, parameterMap, String.class);
        } catch (DataAccessException expection) {
        	expection.printStackTrace();
        }
        return results;
	}
	
	public List<String> getTableInformation() {
		String query = "select TABLE_NAME as tableName from information_schema.TABLES where TABLE_SCHEMA = :schemaName ORDER BY TABLE_SCHEMA ASC ";
		List<String> resultSet = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();){
			String schemaName = connection.getCatalog();
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("schemaName", schemaName);
			resultSet = namedParameterJdbcTemplate.queryForList(query, parameterMap, String.class);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return resultSet;
	}
}
