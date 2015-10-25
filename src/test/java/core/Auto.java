package core;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 生成控制器类和实体类
 * @author he
 *
 */
public class Auto  {
	@Test
	public void create() throws Exception{
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("cls", "model.sys.Admin");
		maps.put("desc", "系统管理员");
		maps.put("cextend", "BaseController");
		maps.put("appPath", "E:/newworkspace/core/src/main/java/");
		
		com.htrj.core.util.Auto.init();
		com.htrj.core.util.Auto.generate(maps);
	}

}
