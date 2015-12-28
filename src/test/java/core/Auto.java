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
		maps.put("cls", "model.sys.User");
		maps.put("desc", "用户");
		maps.put("cextend", "BaseController");
		maps.put("appPath", "E:/workspace/core/src/main/java/");
		maps.put("package", "com.htrj.web");
		
		com.htrj.core.util.Auto.init();
		com.htrj.core.util.Auto.generate(maps);
	}

}
