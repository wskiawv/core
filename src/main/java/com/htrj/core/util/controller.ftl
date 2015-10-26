package com.htrj.core.controller${_package};


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
@RequestMapping("${moduleName}/${controller}")
public class ${controller} extends ${cextend} {
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(${model} model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(${model}.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(${model} model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(${model}.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public ${model} show(Long id){
		return (${model})_Show(${model}.class, id);
	}
	
	
	${controllerBusCode}
}
