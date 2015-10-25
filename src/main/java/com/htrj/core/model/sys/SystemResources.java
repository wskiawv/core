package com.htrj.core.model.sys;


import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;





/**
 * 系统资源 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_systemresources")
public class SystemResources extends Model{
	//资源代码
	private String code;
	//资源名称
	private String name;
	//资源控制器名称
	private String controllerName;
	//资源请求action名称
	private String action;
	//资源状态
	private String status;
	//资源创建时间
	private String create_datetime;
	//资源修改时间
	private String update_datetime;
	
	
	
	
}
