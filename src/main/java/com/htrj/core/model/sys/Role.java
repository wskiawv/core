package com.htrj.core.model.sys;


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
public class Role extends Model{
	//角色名称
	private String name;
	//创建时间
	private String create_datetime;
	//修改时间
	private String update_datetime;	
	//角色类型
	private String role_type;
	//状态
	private String status;
	//备注
	private String remark;
	
	
	
	
	
	
}
