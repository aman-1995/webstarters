package com.webstarters.lazycomponents.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.webstarters.lazycomponents.dao.ComponentDetailsDao;

/**
 * @author Aman Prasad
 *
 */
public class ComponentDetailsDaoImpl implements ComponentDetailsDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;

	@Override
	public List<Map<String, Object>> executeQuery(String query, Map<String, Object> inParams) {
		return namedParameterJdbcTemplate.queryForList(query, inParams);
	}
}
