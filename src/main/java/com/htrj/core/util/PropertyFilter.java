package com.htrj.core.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Restrictions;


public class PropertyFilter {
	
	public void buildPropertyFilters(Class clazz,Map<String,Object> params){
		
		for(String name:params.keySet()){
			//判断是否包含有过虑属性
			if (!StringUtils.startsWithIgnoreCase(name, "filter_")) {
				continue;
			}
			String value = (String)params.get(name);
			if (StringUtils.isBlank(value)) {
				continue;
			}
			//截取下划线右边的属性名称
			String nameSuffix = StringUtils.substringAfterLast(name, "_");
			String matchType=StringUtils.substringBetween(name, "_");
			if(matchType.equals("LIKE")){
				Restrictions.eq(nameSuffix, value);
			}else if(matchType.equals("GT")){
			
			}
		}
	}
	
}
