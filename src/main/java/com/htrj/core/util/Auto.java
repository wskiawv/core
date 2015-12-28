package com.htrj.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import com.htrj.core.util.InputStreamTemplateLoader;
import com.htrj.core.util.SimpleStringUtils;




/**
 * 模型、控制器生成类
 * 
 * @author he
 * 
 */
public class Auto {
	private static InputStream modelTemplateContent = null;
	private static InputStream controllerTemplateContent = null;

	/**
	 * 初始化文件输入流
	 */
	public static void init() {
		try {
			modelTemplateContent = Auto.class.getResource("model.ftl").openStream();
			controllerTemplateContent = Auto.class.getResource("controller.ftl").openStream();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static InputStream getContent(InputStream file, InputStream def) throws Exception {
		if (file != null) {
			return file;
		}
		return def;
	}

	/**
	 * 根据模板生成文件
	 * @param context
	 * @param templateContent
	 * @param javaPath
	 * @throws Exception
	 */
	private static void process(Map<String, Object> context, InputStream templateContent, String javaPath) throws Exception {
		File f = new File(javaPath);
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new InputStreamTemplateLoader(templateContent));
		cfg.setDefaultEncoding("UTF-8");
		Template template = cfg.getTemplate("");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
		template.process(context, out);
		out.flush();
	}

	public static void generate(Map<String, String> maps) throws Exception {
		Map map = new HashMap(maps.size());
		for (Map.Entry m : maps.entrySet()) {
			map.put((String) m.getKey(), m.getValue());
		}
		generate1(map);
	}

	public static Map<String, Object> generate1(Map<String, Object> maps) throws Exception {
		String cls = (String) maps.get("cls");
		String model = StringUtils.substringAfterLast(cls, ".");
		String _package = StringUtils.substringBeforeLast(cls, ".");
		
		if (_package.indexOf(".") != -1)
			_package = "." + StringUtils.substringAfter(_package, ".");
		else {
			_package = "";
		}
		String moduleName="";
		if ((!StringUtils.isBlank(_package)) && (!".".equals(_package))) {
			moduleName="/"+StringUtils.join(StringUtils.split(_package, "."), "/");
		}		
		//String table = SimpleStringUtils.plural(SimpleStringUtils.SEOName(model, "_"));
		String table = model.toLowerCase();
		String controller = SimpleStringUtils.plural(model);
		String mextend = (String) maps.get("mextend");
		if (StringUtils.isBlank(mextend)) {
			mextend = "Model";
		}
		String cextend = (String) maps.get("cextend");
		String desc = (String) maps.get("desc");

		String ppath = "";
		if ((!StringUtils.isBlank(_package)) && (!".".equals(_package))) {
			ppath = StringUtils.join(StringUtils.split(_package, "."), "/") + "/";
		}
		String genPackage="";
		if ((!StringUtils.isBlank(maps.get("package").toString())) && (!".".equals(maps.get("package").toString()))) {
			genPackage = StringUtils.join(StringUtils.split(maps.get("package").toString(), "."), "/") + "/";
		}
		
		String cPath = maps.get("appPath").toString() + "/" + genPackage+"/controller/" + ppath + controller + ".java";
		String mPath = maps.get("appPath").toString() + "/" + genPackage+"/model/" + ppath + model + ".java";

		Map context = new HashMap();
		context.put("time", new Date(System.currentTimeMillis()));
		context.put("model", model);
		context.put("_package", _package);
		context.put("moduleName", moduleName);
		context.put("desc", desc);
		context.put("table", table);
		context.put("mextend", mextend);
		context.put("cextend", cextend);
		context.put("controller", controller);

		List list = (List) maps.get("properties");
		if (list == null) {
			list = new ArrayList(0);
		}
		context.put("properties", list);

		String modelBusCode = (String) maps.get("modelBusCode");
		if (modelBusCode == null) {
			modelBusCode = "";
		}
		context.put("modelBusCode", modelBusCode);

		String controllerBusCode = (String) maps.get("controllerBusCode");
		if (controllerBusCode == null) {
			controllerBusCode = "";
		}
		context.put("controllerBusCode", controllerBusCode);

		context.put("modelImports", maps.get("modelImports"));
		context.put("controllerImports", maps.get("controllerImports"));

		generateModel(context, null, mPath);
		generateController(context, null, cPath);

		return context;
	}

	/**
	 * 生成实体类
	 * @param context
	 * @param templateFile
	 * @param javaPath
	 * @throws Exception
	 */
	public static void generateModel(Map<String, Object> context, InputStream templateFile, String javaPath) throws Exception {
		InputStream templateContent = getContent(templateFile, modelTemplateContent);
		process(context, templateContent, javaPath);
	}
	/**
	 * 生成控制器类
	 * @param context
	 * @param templateFile
	 * @param javaPath
	 * @throws Exception
	 */
	public static void generateController(Map<String, Object> context, InputStream templateFile, String javaPath) throws Exception {
		InputStream templateContent = getContent(templateFile, controllerTemplateContent);
		process(context, templateContent, javaPath);
	}

}
