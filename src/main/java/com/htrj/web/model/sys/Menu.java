package com.htrj.web.model.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.htrj.core.model.Model;

/**
 * 系统菜单 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_menu")
public class Menu extends Model{

	
	@Column(name = "text")
	private String text;
	@Column(name = "qtip")
	private String qtip;
	@Column(name = "xtype")
	private String xtype;
	@Column(name = "leaf")
	private Boolean leaf;
	@Column(name = "iconCls")
	private String iconCls;
	@Column(name = "modules")
	private String modules;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pid")
	private Menu pid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pid", fetch = FetchType.EAGER)
	private List<Menu> children = new ArrayList<Menu>();
	@Column(name = "orderNum")
	private Integer orderNum;
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public Menu getPid() {
		return pid;
	}

	public void setPid(Menu pid) {
		this.pid = pid;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		if (orderNum == null) {
			this.orderNum = 0;
		} else {
			this.orderNum = orderNum;
		}
	}

}
