package com.htrj.web.model${_package};


import javax.persistence.Entity;
import javax.persistence.Table;
import com.htrj.core.model.Model;



<#if (modelImports?has_content)>
	${modelImports}
</#if>


/**
 * ${desc} 模型
 * 
 * @author he
 */
@Entity
@Table(name = "t_${table}")
public class ${model} extends ${mextend}{

<#list properties as property>
	${property}	
</#list>

	${modelBusCode}
	
	
	
}
