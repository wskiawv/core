package com.htrj.core.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Page<T> {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private Integer start;
	private Integer limit;
	private Long totalCount;
	private String orderBy = null;
	private String order = null;

	private List<T> result = Collections.emptyList();
	
	
	public Page(Map<String,Object> params) {	
		if(params.containsKey("start") && params !=null && !params.get("start").toString().equals(""))
		{
			this.start =Integer.valueOf((String) params.get("start"));
		}
		if(this.start==null){
			this.start = Integer.valueOf(0);
		}
		if(params.containsKey("limit") && params !=null && !params.get("limit").toString().equals(""))
		{
			this.limit=Integer.valueOf((String) params.get("limit"));
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
	
	public Page(Integer start, Integer limit) {
		
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

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
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

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public static String getAsc() {
		return ASC;
	}

	public static String getDesc() {
		return DESC;
	}
	
}
