package com.htrj.core.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.htrj.core.model.Msg;



import com.htrj.core.service.BaseServiceI;
import com.htrj.core.util.JqGridPage;
import com.htrj.core.util.Page;

@Controller
public class BaseController{
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected Log log=LogFactory.getLog(this.getClass());
	/**
	 * 注入 http request response对象
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
		
	}
//	public HttpServletRequest getRequert(){
//		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//	}
//	public  HttpServletResponse getResponse() {
//		HttpServletResponse response=((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
//	        return response;
//	}
	//注入service
	@Autowired
	protected BaseServiceI baseService;
	
	/**
	 * 返回json字符串
	 * @param o
	 * @throws IOException
	 */
	public void renderJson(Object o){
		try{
			JSONSerializer js =new JSONSerializer();
			response.getWriter().write(js.toJSON(o).toString());
		}catch(IOException e){
			log.info(e.getMessage());
			e.printStackTrace();
		}		
	}
	/**
	 * 获取前台请求参数
	 * @return
	 */
	public Map<String,Object> getRequestParams(){
		HttpServletRequest request=this.request;
		Map map=request.getParameterMap();		
		Map <String,Object> params= new HashMap();
		Set<String> keySet=map.keySet();
		for(String key:keySet){
			String [] values=(String[])map.get(key);
			String sum="";
			for(String value:values){
				sum+=value;
				System.out.println(value);
			}			
			params.put(key, sum);
		}
		return params;
	}
	
	/**
	 * 新增保存
	 * @param o
	 * @throws IOException 
	 */
	@ResponseBody  
	public void _Save(Object o){
		
		baseService.save(o);
		Msg msg=new Msg("新增成功！",true);
		renderJson(msg);
	}
	/**
	 * 删除
	 * @param o
	 * @param id
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Delete(Object o){		
		baseService.delete(o);		
	}
	@ResponseBody 
	public void _Update(Object o){
		baseService.update(o);
		Msg msg=new Msg("更新成功！",true);
		renderJson(msg);
	}
	
	/**
	 * 查询分页extjs
	 * @param o
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Search(Class o){
		Map<String,Object> params=getRequestParams();
		Page page=baseService.find(o, params);
		renderJson(page);
	}
	/**
	 * jqGrid分页
	 * @param o
	 */
	@ResponseBody 
	public void _Find(Class o){
		Map<String,Object> params=getRequestParams();
		JqGridPage page=baseService.findJqGridPage(o, params);
		renderJson(page);
	}
	/**
	 * 批量删除
	 * @param o
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Delete(Class o,String ids){	
		String []values=ids.split(",");
		if(values !=null && values.length>0){
			for(String id:values){		
				Object t= baseService.getById(o, Long.parseLong(id));
				_Delete(t);
			}	
		}
		Msg msg=new Msg("删除成功！",true);
		renderJson(msg);
		
	}
	
	/**
	 * 获取实体对象
	 * @param c
	 * @param id
	 * @return
	 */
	@ResponseBody 
	public Object _Show(Class c,Long id){
		return baseService.getById(c, id);
	}
	
	
	//get set 方法
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public BaseServiceI getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseServiceI baseService) {
		this.baseService = baseService;
	}
}

