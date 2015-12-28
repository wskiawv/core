package com.htrj.web.model.sys;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;

/**
 * 用户与角色 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_userrole")
public class UserRole extends Model {

	// 用户Id
	private String userId;
	// 角色Id
	private String roleId;

}
