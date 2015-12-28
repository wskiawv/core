package com.htrj.web.model.sys;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;

/**
 * 角色 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_role")
public class Role extends Model {

	// 角色名称
	private String name;
	// 创建时间
	private String create_datetime;
	// 修改时间
	private String update_datetime;
	// 角色类型
	private String role_type;
	// 状态
	private String status;
	// 备注
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
