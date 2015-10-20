package com.htrj.core.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JqGridPage<T> implements Serializable{
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private Integer start;
	private Integer limit;
	private Long total;
	
	private String orderBy = null;
	private String order = null;

	private List<T> rows = Collections.emptyList();
	
	public JqGridPage(Map<String,Object> params) {	
		
		if(this.start==null){
			this.start = Integer.valueOf(0);
		}
		if(params.containsKey("rows") && params !=null && !params.get("rows").toString().equals(""))
		{
			this.limit=Integer.valueOf((String) params.get("rows"));
		}
		if(this.limit==null){
			this.limit=Integer.valueOf(20);
		}
		if(this.start.intValue()==0){
			Integer page = Integer.valueOf((String) params.get("page"));
			if (page != null) {
				this.start = Integer.valueOf((page.intValue() - 1) * this.limit.intValue());
			}
		}
		
	}
	
	public JqGridPage(Integer start, Integer limit) {
		
		this.start = start;
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public static String getAsc() {
		return ASC;
	}

	public static String getDesc() {
		return DESC;
	}

}
