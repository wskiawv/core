package com.htrj.web.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.sys.User;
import com.htrj.core.controller.BaseController;


/**
 * 用户 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/Users")
public class Users extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(User model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(User.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(User model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(User.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public User show(Long id){
		return (User)_Show(User.class, id);
	}
	
	
	
}
