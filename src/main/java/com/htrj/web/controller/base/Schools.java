package com.htrj.web.controller.base;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.base.School;
import com.htrj.core.controller.BaseController;


/**
 * 学校基本信息 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/base/Schools")
public class Schools extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(School model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(School.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(School model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(School.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public School show(Long id){
		return (School)_Show(School.class, id);
	}
	
	
	
}
