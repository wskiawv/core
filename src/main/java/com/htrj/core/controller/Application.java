package com.htrj.core.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.htrj.core.controller.base.BaseController;
import com.htrj.core.model.User;
@Controller
public class Application extends BaseController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(User user,HttpServletRequest request){
		//HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Map map=request.getParameterMap();
		//String[] username=(String[])map.get("username");
		Map <String,Object> params= new HashMap();
		Set<String> keySet=map.keySet();
		for(String key:keySet){
			String [] values=(String[])map.get(key);
			String sum="";
			for(String value:values){
				sum+=value;
				System.out.println(value);
			}
			//System.out.println(sum);
			params.put(key, sum);
		}
		for(String name:params.keySet()){
			System.out.println(name);
			System.out.println(params.get(name));
		}
		
		System.out.println("login success");
		return "main";
	}

}
