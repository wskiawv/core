package com.htrj.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htrj.core.controller.base.BaseController;
import com.htrj.core.model.User;


@Controller
@RequestMapping("/user")
public class Users extends BaseController<User> {
	
	@RequestMapping(value="/save", method=RequestMethod.POST)	
	public void save(User user) throws IOException{
		_Save(user);		
	}
	@RequestMapping(value="/success", method=RequestMethod.POST)	
	public String success(){
		System.out.println("success");
		return "main";
	}
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public void index(){
		
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) throws IOException{		
		_Delete(User.class,id);	
		
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(User user) throws IOException{
		_Update(user);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() throws IOException{
		_Search(User.class);		
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public User show(Long id){
		return _Show(User.class,id);		
	}
	
	@ResponseBody
	@RequestMapping(value="/userList", method=RequestMethod.GET)	
	public List<User> userList(){
		String sql="from User";
		List<User> user=super.getBaseService().find(sql);
		return user;
	}
}
