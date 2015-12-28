package com.htrj.web.model.sys;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;

/**
 * 管理员 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_admin")
public class Admin extends Model {

	private String name;
	private String password;
	private String create_datetime;
	private String email;
	private String phone;
	private String qq;

}
