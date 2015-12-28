package com.htrj.web.controller.sys;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.htrj.web.model.sys.Menu;
import com.htrj.core.controller.BaseController;


/**
 * 系统菜单 控制器
 * 
 * @author he
 */
@Controller
@RequestMapping("/sys/Menus")
public class Menus extends BaseController {	
	

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void save(Menu model) {
		_Save(model);
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(String id) {
		_Delete(Menu.class,id);
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(Menu model) {
		_Update(model);		
	}
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public void search() {
		_Search(Menu.class);
	}
	@RequestMapping(value="/show", method=RequestMethod.POST)
	public Menu show(Long id){
		return (Menu)_Show(Menu.class, id);
	}
	@RequestMapping(value="/initMenu", method=RequestMethod.GET)
	public void initMent() {
		String menuFile="menu.xml";
		String path=this.getClass().getClassLoader().getResource("").getPath();		
		SAXReader saxReader = new SAXReader();
		boolean isUpdateMenus=false;
		try{
			InputStream is = new FileInputStream(path+menuFile);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			Document document = saxReader.read(isr);

			Element root = document.getRootElement();
			isUpdateMenus = Boolean.parseBoolean(root.attribute("isUpdateMenus").getValue());
			String sql="truncate table t_menu";
			if(isUpdateMenus){
				super.getBaseService().executeSql(sql);
				toSaveMenus(root,null);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void toSaveMenus(Element element, Menu parentMenus) {
		int i = 0;
		for (Iterator iterInner = element.elementIterator(); iterInner.hasNext();) {
			Element elementInner = (Element) iterInner.next();
			Menu menus = new Menu();
			// 获取person节点的text属性的值
			menus.setPid(parentMenus);
			menus.setOrderNum(i);
			i++;
			Attribute textAttr = elementInner.attribute("text");
			if (textAttr != null) {
				String text = textAttr.getValue();
				if (text != null && !text.equals("")) {
					menus.setText(text);
					menus.setQtip(text);
				}
			}
			Attribute xtypeAttr = elementInner.attribute("xtype");
			if (xtypeAttr != null) {
				String xtype = xtypeAttr.getValue();
				if (xtype != null && !xtype.equals("")) {
					menus.setXtype(xtype);
					menus.setLeaf(true);
				}else{
					menus.setLeaf(false);
				}
			}else{
				menus.setLeaf(false);
			}
			getBaseService().save(menus);
			toSaveMenus(elementInner, menus);
		}
	}
	
	
}
