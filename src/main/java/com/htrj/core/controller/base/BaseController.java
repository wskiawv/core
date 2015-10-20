package com.htrj.core.controller.base;

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
import com.htrj.core.util.Page;

@Controller
public class BaseController<T>{
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
	protected BaseServiceI<T> baseService;
	
	/**
	 * 返回json字符串
	 * @param o
	 * @throws IOException
	 */
	public void renderJson(Object o) throws IOException{
		JSONSerializer js =new JSONSerializer();
		response.getWriter().write(js.toJSON(o).toString());
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
	public void _Save(T o) throws IOException{
		baseService.save(o);
		Msg msg=new Msg("success","true");
		renderJson(msg);
	}
	/**
	 * 删除
	 * @param o
	 * @param id
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Delete(T o){		
		baseService.delete(o);		
	}
	@ResponseBody 
	public void _Update(T o) throws IOException{
		baseService.update(o);
		Msg msg=new Msg("success","true");
		renderJson(msg);
	}
	
	/**
	 * 查询分页
	 * @param o
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Search(Class<T> o) throws IOException{
		Map<String,Object> params=getRequestParams();
		Page<T> page=baseService.find(o, params);
		renderJson(page);
	}
	
	/**
	 * 批量删除
	 * @param o
	 * @throws IOException 
	 */
	@ResponseBody 
	public void _Delete(Class<T> o,String ids) throws IOException{	
		String []values=ids.split(",");
		if(values !=null && values.length>0){
			for(String id:values){		
				T t= baseService.getById(o, Long.parseLong(id));
				_Delete(t);
			}	
		}
		Msg msg=new Msg("success","true");
		renderJson(msg);
		
	}
	
	/**
	 * 获取实体对象
	 * @param c
	 * @param id
	 * @return
	 */
	@ResponseBody 
	public T _Show(Class<T> c,Long id){
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
	public BaseServiceI<T> getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseServiceI<T> baseService) {
		this.baseService = baseService;
	}
}

