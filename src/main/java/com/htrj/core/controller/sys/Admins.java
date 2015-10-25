package com.htrj.core.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.core.model.sys.Admin;
import com.htrj.core.controller.base.BaseController;


/**
 * 系统管理员 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/Admins")
public class Admins extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(Admin model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(Admin.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(Admin model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(Admin.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public Admin show(Long id){
		return (Admin)_Show(Admin.class, id);
	}
	
	
	
}
