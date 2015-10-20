package com.htrj.core.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.core.model.Task;
import com.htrj.core.controller.base.BaseController;


/**
 * 任务 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/Tasks")
public class Tasks extends BaseController {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(Task model){
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) throws Exception {
		_Delete(Task.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(Task model){
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search(){
		_Search(Task.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public Task show(Long id){
		return (Task)_Show(Task.class, id);
	}
	
	
	
}
