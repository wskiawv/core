package com.htrj.core.model.sys;


import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;





/**
 * 角色与系统资源中间 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_rolesystemresources")
public class RoleSystemResources extends Model{
	//角色Id
	private String roleId;
	//资源Id
	private String systemResourcesId;

	
	
	
	
}
