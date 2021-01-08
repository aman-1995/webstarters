package com.webstarters.lazycomponents.vo;

public class ComponentDetailsVo {

	private String componentId = null;
	
	private String componentDescription = null;
	
	private String tableName = null;
	
	private String query = null;
	
	private Integer queryType = null;
	
	public ComponentDetailsVo(ComponentDetailsEntity componentDetailsEntity) {
		super();
		this.componentId = componentDetailsEntity.getComponentId();
		this.componentDescription = componentDetailsEntity.getComponentDescription();
		this.tableName = componentDetailsEntity.getTableName();
		this.query = componentDetailsEntity.getQuery();
		this.queryType = componentDetailsEntity.getQueryType();
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
