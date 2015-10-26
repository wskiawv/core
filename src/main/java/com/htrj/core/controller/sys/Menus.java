package com.htrj.core.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.core.model.sys.Menu;
import com.htrj.core.controller.base.BaseController;


/**
 * 系统菜单 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/Menus")
public class Menus extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(Menu model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(Menu.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(Menu model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(Menu.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public Menu show(Long id){
		return (Menu)_Show(Menu.class, id);
	}
	
	
	
}
