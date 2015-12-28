package com.htrj.web.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.sys.Role;
import com.htrj.core.controller.BaseController;


/**
 * 角色 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/Roles")
public class Roles extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(Role model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(Role.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(Role model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(Role.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public Role show(Long id){
		return (Role)_Show(Role.class, id);
	}
	
	
	
}
