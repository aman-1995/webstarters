package com.webstarters.lazycomponents.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aman Prasad
 *
 */
@Entity
@Table(name = "lzc_component_details")
public class ComponentDetailsEntity {

	@Id
	@Column(name = "component_id")
	private String componentId = null;
	
	@Column(name = "component_description")
	private String componentDescription = null;
	
	@Column(name = "table_name")
	private String tableName = null;
	
	@Column(name = "query")
	private String query = null;
	
	@Column(name = "query_type")
	private Integer queryType = null;
	
	public ComponentDetailsEntity() {
		
	}
	
	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}
	
}
