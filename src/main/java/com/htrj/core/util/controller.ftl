package com.htrj.core.controller${_package};

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.core.model${_package}.${model};
import com.htrj.core.controller.base.${cextend};

<#if (controllerImports?has_content)>
	${controllerImports}
</#if>

/**
 * ${desc} 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/${controller}")
public class ${controller} extends ${cextend}<${model}> {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(${model} model) throws Exception {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) throws Exception {
		_Delete(${model}.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(${model} model) throws IOException{
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() throws Exception{
		_Search(${model}.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public ${model} show(Long id){
		return _Show(${model}.class, id);
	}
	
	
	${controllerBusCode}
}
