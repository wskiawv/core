package com.htrj.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;


import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 转换word,txt,ppt,excel成pdf类
 * @author he
 *
 */
public class PDFConverter {
	// public File outputFile;//输出文件
	// public File inputFile;//输入文件
	private  static Process process;	
	protected static Log log=LogFactory.getLog(PDFConverter.class);
	//openoffice安装路径
	private static  String openoffice;
	//启动openoffice服务命令
	private static String command;
	
	//系统平台默认window
	private static  String os;
	//PDF2SWF安装路径
	//private static String exepath;
	/**
	 * 转换pdf文件
	 * 
	 * @param openoffice
	 *       
	 * @param command
	 *  
	 * @param inputFile源文件
	 * @param outputFile输出文件pdf
	 * @return
	 */
	public static int converterToPDF(File inputFile, File outputFile) {
		try {
			// inputFile=new File(inputpath);
			// outputFile=new File(outputpath);
			if (!inputFile.exists()) {
				// 返回-1表示源文件不存在
				return -1;
			}
			if (outputFile.exists()) {
				// PDF文件已经存在
				return -2;
				// outputFile.getParentFile().mkdirs();
			}
			os=System.getProperty("os.name");
			System.out.println("system os:"+os);
			String configpath=PDFConverter.class.getClassLoader().getResource("OpenOfficeConfig.xml").getPath();
			//String configpath = "Play.applicationPath.getPath()" + "OpenOfficeConfig.xml";
			statOpenOfficeService(configpath);
			
			OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			connection.disconnect();
			stopOpenOfficeService();
		}  catch (IOException e) {
			log.info("文件打开失败"+ e.toString());
			e.printStackTrace();			
		}catch(OpenOfficeException e) {
			log.info("连接OpenOffice服务失败"+e.toString());
			e.printStackTrace();
			
		}finally {
			// connection.disconnect();

		}
		return 1;
	}

	/**
	 * 打开openoffice服务
	 * 
	 * @param openoffice 安装目录
	 * @param command启动服务命令
	 */
	public static void statOpenOfficeService(String configpath) {
		
		try {
			if (openoffice == null && command == null) {
				loadOpenOfficeConfig(new File(configpath));
			}
			// 启动服务
			process = Runtime.getRuntime().exec(openoffice + command);
			log.info("消息："+"openOffice服务启动成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("消息："+"openOffice服务启动失败");
			e.printStackTrace();
			
		}

	}

	/**
	 * 关闭openoffice服务
	 */
	public static void stopOpenOfficeService() {

		process.destroy();
	}

	/**
	 * 读取OpenOffice配置文件
	 * @param configpath
	 */
	public static void loadOpenOfficeConfig(File configpath) {
		Document document = null;
		
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configpath);
			List<Element> lists = document.getRootElement().elements();
			int i = 0;
			for (Element e : lists) {
				/*if(configpath.getName().toString().equals("OpenOfficeConfig.xml"))
				{*/
					if (i == 0) {
						openoffice = e.getText();
						log.info("Openoffice安装路径:"+ openoffice);
					} else if (i == 1) {
						command = e.getText();
						log.info("执行命令是:"+ command);
					}
				/*}else if(configpath.getName().toString().equals("PDFtoSWFConfig.xml")){
					if (i == 0) {
						exepath = e.getText();
						log.info("PDF2SWF安装路径:", exepath);
						
					} else if (i == 1) {
						os = e.getText();
						log.info("系统平台:", os);
					}*/
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
	/*public static int pdfToSwf(File input,File output){
		if (!input.exists()) {
			// 表示pdf源文件不存在
			return -1;
		}
		if (output.exists()) {
			// swf文件已经存在
			return -2;
			// outputFile.getParentFile().mkdirs();
		}
		String fileName=output.getName().substring(output.getName().lastIndexOf(".") + 1);
		Process pro=null;
		
		String configpath = Play.applicationPath.getPath() + "/conf/PDFtoSWFConfig.xml";
		if(os==null&&exepath==null)
		{
			//加载配置文件
			loadOpenOfficeConfig(new File(configpath));
		}
		if(os.equals("window")){
			try{
				String cmd=exepath+input.getPath()+" -o "+output.getPath()+" -T 9";
				pro=Runtime.getRuntime().exec(cmd);
			}catch(IOException e){
				e.printStackTrace();
			}
		}else if(os.equals("linux"))
		{
			try{
				String cmd =exepath+input.getPath()+" -o"+output.getPath()+" -T 9";
				pro=Runtime.getRuntime().exec(cmd);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		pro.destroy();
		return 1;
	}*/
}
