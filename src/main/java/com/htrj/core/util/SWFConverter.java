package com.htrj.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 转换pdf成swf文件类
 * @author he
 *
 */
public class SWFConverter {
	//private static Process process;
	private static Log log = LogFactory.getLog(SWFConverter.class);
	//系统平台默认window
	private static String os;
	//PDF2SWF安装路径
	private static String exepath;
	private static String cmmd;
	/**
	 * 读取PDF2SWF配置文件
	 * @param configpath
	 */
	public static void loadPDF2SWFConfig(File configpath) {
		Document document = null;

		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configpath);
			List<Element> lists = document.getRootElement().elements();
			int i = 0;
			for (Element e : lists) {								
				if (i == 0) {
					exepath = e.getText();
					log.info("PDF2SWF安装路径:"+ exepath);
					
				} else if (i == 1) {
					os = e.getText();
					log.info("系统平台:"+ os);
				}else if(i == 2){
					cmmd=e.getText();
					log.info("cmmd命令:"+ os);
				}
				i++;
			}
				
			
		} catch (Exception ex) {
			log.error("读取xml文件出错,错误信息是：{}"+ex.getMessage());
		}
		// return document;

	}
	
	/**
	 * 转换pdf文件成swf文件
	 * @param input pdf源文件
	 * @param output  swf文件
	 * @return
	 */
	public static int pdfToSwf(File input,File output){
		if (!input.exists()) {
			// 表示pdf源文件不存在
			return -1;
		}
		if (output.exists()) {
			// swf文件已经存在
			return -2;
			// outputFile.getParentFile().mkdirs();
		}
		//String fileName=output.getName().substring(output.getName().lastIndexOf(".") + 1);
		Process pro;
		os=System.getProperty("os.name");
		System.out.println("system os:"+os);
		String configpath=SWFConverter.class.getClassLoader().getResource("PDFtoSWFConfig.xml").getPath();
		//String configpath = "Play.applicationPath.getPath()" + "/conf/PDFtoSWFConfig.xml";
		if(os==null&&exepath==null&&cmmd==null)
		{
			//加载配置文件
			loadPDF2SWFConfig(new File(configpath));
		}
		//if(os.equals("window")){
			try{
				String cmd=exepath+" "+input.getPath()+" "+cmmd+" "+output.getPath();
				//String cmd=exepath+" "+input.getPath()+" -o "+output.getPath();
				pro=Runtime.getRuntime().exec(cmd);
				BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream()));
				//System.out.println(pro.getInputStream());
				//System.out.print(pro.getOutputStream());
				while(br.readLine()!=null);
				//pro.destroy();
				pro.waitFor();
				pro.exitValue();
			}catch(IOException e){
				log.info("cmmd yichang:"+ e.getMessage());
				System.getProperty("os.name");
				e.printStackTrace();			
			}catch(InterruptedException e){
				e.printStackTrace();
				
			}finally{
				
			}
			
		/*}else if(os.equals("linux"))
		{
			try{
				String cmd =exepath+input.getPath()+cmmd+output.getPath();
				pro=Runtime.getRuntime().exec(cmd);
			}catch(IOException e){
				e.printStackTrace();
			}
		}*/
		//pro.destroy();
		return 1;
	}

}
