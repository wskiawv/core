package com.htrj.web.model.sys;

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
public class SystemResources extends Model {

	// 资源代码
	private String code;
	// 资源名称
	private String name;
	// 资源控制器名称
	private String controllerName;
	// 资源请求action名称
	private String action;
	//
	private String method;
	// 资源状态
	private String status;
	// 资源创建时间
	private String create_datetime;
	// 资源修改时间
	private String update_datetime;
	// url
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(String update_datetime) {
		this.update_datetime = update_datetime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
