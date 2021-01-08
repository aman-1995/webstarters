package com.webstarters.lazycomponents.vo;

import java.util.List;
import java.util.Map;

public class ComponentResponseData {

	private String page = null;
	
	private String total = null;
	
	private String records = null;
	
	private List<Map<String,Object>> rows = null;

	public ComponentResponseData(Integer page, Integer totalRows, List<Map<String,Object>> rows) {
		this.rows = rows;
		this.total = String.valueOf(totalRows);
		this.records = String.valueOf(rows.size());
		this.page = String.valueOf(page);
	}
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<Map<String,Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String,Object>> rows) {
		this.rows = rows;
	}
	
}
