package com.htrj.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;








import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.servlet.ModelAndView;

import com.htrj.core.controller.base.BaseController;
import com.htrj.core.model.User;
import com.htrj.core.model.sys.Menu;
@Controller
public class Application extends BaseController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(User user,HttpServletRequest request){
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
		ModelAndView modelAndView = new ModelAndView(); 
		System.out.println("login success");		
		List<Menu> menus=getBaseService().find("from Menu");
		//Map<String,Object> treeMap=new HashMap();
		//List<Menu> listTree=new ArrayList();
		List<Map<String,Object>> treeJson=new ArrayList();
		for(Menu m1:menus){
			JSONObject js=new JSONObject();
			js.put("id", m1.getId());
			js.put("text", m1.getText());
			js.put("qtip", m1.getQtip());
			js.put("leaf", m1.getLeaf());			
			if(m1.getLeaf()==false){
				//js.put("children", value)
				m1.setChildren(genTree(menus,m1));
				List<JSONObject> children=new ArrayList();
				for(Menu m2:menus){
					JSONObject node=new JSONObject();
					if(m1.getId()==m2.getPid().getId()){
						
					}
				}
			}else{
				js.put("xtype", m1.getXtype());
				js.put("pid", m1.getPid().getId());
			}
		}
		JSONSerializer JS=new JSONSerializer();
//		JS.toJava(json, jsonConfig)
//		System.out.println(JS.toJSON(menus));
		modelAndView.addObject("menus",menus);
		modelAndView.setViewName("main");
		return modelAndView;
	}
	public List<Menu> genTree(List<Menu> list,Menu menu){
		List<Menu> menuList=new ArrayList();
		for(Menu m:list){
			if(m.getPid()!=null && m.getPid().getId()==menu.getId()){
				menuList.add(m);
			}
		}
		return menuList;
	}

}
