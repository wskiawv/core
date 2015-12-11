package core;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.htrj.core.model.sys.Menu;
import com.htrj.core.service.BaseServiceI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TestTree {
	@Autowired
	protected BaseServiceI baseService;
	@Test
	public void test(){
		/*List<Menu> menus=baseService.find("from Menu");
		List<JSONArray> treeArray=new ArrayList();
		for(Menu m1:menus){
			List<JSONObject> list=new ArrayList();
			if(m1.getPid()==null){
				JSONObject js=new JSONObject();
				js.put("id", m1.getId());
				js.put("leaf", m1.getLeaf());
				js.put("xtype", m1.getXtype());
				js.put("text", m1.getText());
				js.put("qtip",m1.getQtip());
				js.put("iconCls", m1.getIconCls());
				list=genTree(menus,m1);
				js.put("children", list);
			}
		}*/
	}
	@Test
	public void testSql(){
		String sql="select * from t_menu";
		List<Map<String,Object>> menuList=baseService.findBySql(sql);
		List<JSONObject> list=new ArrayList();
		for(Map<String,Object>  mL:menuList){
			System.out.println(mL.get("id"));
			System.out.println(mL.get("text"));
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
				System.out.println("---------");
			}
			
		}
		System.out.println(list.toString());
	}
	/*public List<JSONObject> genTree(List<Menu> list,Menu menu){
		List<JSONObject> menuList=new ArrayList();
		for(Menu m:list){
			if(m.getPid().equals(menu.getId())){
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
