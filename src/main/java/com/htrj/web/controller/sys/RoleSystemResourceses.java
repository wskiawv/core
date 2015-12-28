package com.htrj.web.controller.sys;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.sys.RoleSystemResources;
import com.htrj.core.controller.BaseController;


/**
 * 角色与资源 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/RoleSystemResourceses")
public class RoleSystemResourceses extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(RoleSystemResources model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(RoleSystemResources.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(RoleSystemResources model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(RoleSystemResources.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public RoleSystemResources show(Long id){
		return (RoleSystemResources)_Show(RoleSystemResources.class, id);
	}
	
	
	
}
