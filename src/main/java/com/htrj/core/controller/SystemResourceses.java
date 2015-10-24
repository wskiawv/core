package com.htrj.core.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.core.model.SystemResources;
import com.htrj.core.controller.base.BaseController;


/**
 * 系统资源 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/SystemResourceses")
public class SystemResourceses extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(SystemResources model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(SystemResources.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(SystemResources model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(SystemResources.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public SystemResources show(Long id){
		return (SystemResources)_Show(SystemResources.class, id);
	}
	
	
	
}
