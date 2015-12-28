package com.htrj.web.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.sys.UserRole;
import com.htrj.core.controller.BaseController;


/**
 * 用户与角色 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/UserRoles")
public class UserRoles extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(UserRole model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(UserRole.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(UserRole model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(UserRole.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public UserRole show(Long id){
		return (UserRole)_Show(UserRole.class, id);
	}
	
	
	
}
