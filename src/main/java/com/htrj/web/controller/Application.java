package com.htrj.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.htrj.core.controller.BaseController;
import com.htrj.web.model.sys.User;
import com.htrj.web.model.sys.SystemResources;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
@Controller
public class Application extends BaseController {
	
	@RequestMapping(value="/login", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(User user,HttpServletRequest request){
		
		getSysAllUrl(request);
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
				//JSONObject node=new JSONObject();
				List<JSONObject> node=new ArrayList();
				node=getNode(menuList,node,String.valueOf(mL.get("id")));
				js.put("children",node);
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
	public List<JSONObject> getNode(List<Map<String,Object>> list,List<JSONObject>nodeList,String id){
		//List<JSONObject> nodeList =new ArrayList();
		for(Map<String,Object> mL:list){
			JSONObject node=new JSONObject();
			if(mL.get("pid")!=null && id.equals(String.valueOf(mL.get("pid")))){
				node.put("id", mL.get("id"));
				node.put("leaf", mL.get("leaf"));
				node.put("xtype", mL.get("xtype"));
				node.put("text", mL.get("text"));
				node.put("qtip",mL.get("qtip"));
				node.put("iconCls", mL.get("iconCls"));
				Boolean leaf=Boolean.parseBoolean(String.valueOf(mL.get("leaf")));
				if(!leaf){
					List<JSONObject> cList=new ArrayList();
					JSONObject js=new JSONObject();
					cList=getNode(list,cList,String.valueOf(mL.get("id")));
					node.put("children", cList);
					
				}else{
					
				}
				nodeList.add(node);
			}
		}
		return nodeList;
	}
	public void getSysAllUrl(HttpServletRequest request){
		ServletContext servletContext = request.getSession().getServletContext();
		if(servletContext == null){
//			return;
		}
		WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		List<SystemResources> list=new ArrayList<SystemResources>();
		Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(appContext,HandlerMapping.class, true, false);
		for(HandlerMapping handlerMapping:allRequestMappings.values()){
			if(handlerMapping instanceof RequestMappingHandlerMapping){
				RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet())
                {
                    RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();
                    HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();

                    RequestMethodsRequestCondition methodCondition = requestMappingInfo.getMethodsCondition();
                    String requestType = methodCondition.getMethods().toString();

                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    String requestUrl = patternsCondition.getPatterns().toString();

                    String controllerName = mappingInfoValue.getBeanType().toString();
                    String requestMethodName = mappingInfoValue.getMethod().getName();
                    Class<?>[] methodParamTypes = mappingInfoValue.getMethod().getParameterTypes();
                    SystemResources item = new SystemResources();
                    item.setControllerName(controllerName);
                    System.out.println(controllerName);
                    item.setMethod(requestMethodName);
                    System.out.println(requestMethodName);
                    item.setAction(requestType);
                    System.out.println(requestType);
                    item.setUrl(requestUrl);
                    System.out.println(requestUrl);
                    list.add(item);
                }

			}
		}	
	}

}
