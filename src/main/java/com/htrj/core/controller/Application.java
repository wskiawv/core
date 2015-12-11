package com.htrj.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
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
		String sql="select * from t_menu";
		List<Map<String,Object>> menuList=baseService.findBySql(sql);
		List<JSONObject> list=new ArrayList();
		for(Map<String,Object>  mL:menuList){			
			if(mL.containsKey("pid")&&mL.get("pid")==null){
				JSONObject js=new JSONObject();
				js.put("id", mL.get("id"));
				js.put("leaf", mL.get("leaf"));
				js.put("xtype", mL.get("xtype"));
				js.put("text", mL.get("text"));
				js.put("qtip",mL.get("qtip"));
				js.put("iconCls", mL.get("iconCls"));
				JSONObject node=new JSONObject();
				node=getNode(menuList,node,String.valueOf(mL.get("id")));
				js.put("children", node);
				list.add(js);				
			}
			
		}
		System.out.println(list.toString());
		JSONSerializer JS=new JSONSerializer();
//		JS.toJava(json, jsonConfig)
//		System.out.println(JS.toJSON(menus));
		modelAndView.addObject("menus",list.toString());
		modelAndView.setViewName("main");
		return modelAndView;
	}
	/*public List<JSONObject> genTree(List<Menu> list,Menu menu){
		List<JSONObject> menuList=new ArrayList();
		for(Menu m:list){
			if(m.getPid().equals(menu)){
				System.out.println("te------------");
			}
		}
		return menuList;
	}*/
	public JSONObject getNode(List<Map<String,Object>> list,JSONObject json,String id){
		for(Map<String,Object> mL:list){
			if(mL.get("pid")!=null && id.equals(String.valueOf(mL.get("pid")))){
				json.put("id", mL.get("id"));
				json.put("leaf", mL.get("leaf"));
				json.put("xtype", mL.get("xtype"));
				json.put("text", mL.get("text"));
				json.put("qtip",mL.get("qtip"));
				json.put("iconCls", mL.get("iconCls"));
				Boolean leaf=Boolean.parseBoolean(String.valueOf(mL.get("leaf")));
				if(!leaf){
					JSONObject js=new JSONObject();
					js=getNode(list,js,String.valueOf(mL.get("id")));
					json.put("children", js);
				}
			}
		}
		return json;
	}

}
