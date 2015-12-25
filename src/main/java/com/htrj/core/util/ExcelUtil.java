package com.htrj.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	
	/**
	 * 根据实体类，生成实体字段 excel文件
	 * @param clazz
	 * @param path excel文件存放路径
	 */
	public static void ExportExcel(Class<?> clazz,String path){
		try{
			
			Field[] fields=clazz.getDeclaredFields();	
			//获取类名
			String modelname=clazz.getName().toString().substring(clazz.getName().toString().lastIndexOf(".")+1);
			
			File file =new File(path+modelname+".xls");
			if(!file.exists()){
				//file.mkdir();
				file.createNewFile();
			}
			WritableWorkbook book= Workbook.createWorkbook(file);
			WritableSheet sheet=book.createSheet("数据", 0);
			for (int i=0,j=1;i<fields.length;i++){
				String xtype=fields[i].getType().getName().toString().substring(fields[i].getType().getName().toString().lastIndexOf(".")+1);
				Label labelname=new Label(0,j,fields[i].getName().toString());
				Label labelxtype=new Label(1,j,xtype);
				sheet.addCell(labelname);
				sheet.addCell(labelxtype);
				j++;
			}
			book.write();
			book.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static List poireadExcel(Class clazz,File file){
		List list =new ArrayList();
		if(!file.exists()){
			System.out.println("file in not exists!");
			return null;
		}
		try{
			FileInputStream fs=new FileInputStream(file);
			HSSFWorkbook book=new HSSFWorkbook(fs);
			HSSFSheet sheet=book.getSheetAt(0);
			if(sheet !=null){
				List<String> field=new ArrayList();
				for(int r=0;r<sheet.getLastRowNum();r++){
					HSSFRow hr=sheet.getRow(r);
					if(r==0){
						for(int c=0;c<hr.getLastCellNum();c++){
							String value=hr.getCell(c).getStringCellValue();
							if(value != null && !value.equals("")){
								field.add(value);
							}
						}
					}else if(r>=1){
						Object t=clazz.newInstance();
						for(int c=0;c<hr.getLastCellNum();c++){
							String value=hr.getCell(c).getStringCellValue();
							if(value != null && !"".equals(value.trim())){		
								Class xtype=PropertyUtils.getPropertyType(t, field.get(c));
								String objectXtype=xtype.getName().toString();										
								if(objectXtype.equals("java.lang.Integer")){
									PropertyUtils.setProperty(t, field.get(c), Integer.parseInt(value));
								}else if (xtype != null){
									PropertyUtils.setProperty(t, field.get(c), value);
								}									
							}
						}
						if(t!=null){
							list.add(t);
						}
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据实体类和excel文件生成实体List集合,读excel文件
	 * @param clazz
	 * @param file
	 */
	public static  List readExcel(Class clazz,File file){
		//File file =new File("sf");
		List list=new ArrayList();
		if(!file.exists()){
			System.out.println("file in not exists!");
			return null;
		}		
		try{
			Workbook book = Workbook.getWorkbook(file);
			Sheet[] sheet=book.getSheets();
			if(sheet !=null&& sheet.length>0){
				for(int s=0;s<sheet.length;s++){	
					List<String> field=new ArrayList();	
					for(int r=0;r<sheet[s].getRows();r++){
						Cell[] cell=sheet[s].getRow(r);						
						if(r==0){									
							for(int c=0;c<cell.length;c++){														
								String value=cell[c].getContents();
								if(value != null && !value.equals("")){
									field.add(value);
								}							
							}	
						}else if(r>=1){
							Object t=clazz.newInstance();
							for(int c=0;c<cell.length;c++){								
								String value=cell[c].getContents();
								if(value != null && !value.trim().equals("")){		
									Class xtype=PropertyUtils.getPropertyType(t, field.get(c));
									String objectXtype=xtype.getName().toString();										
									if(objectXtype.equals("java.lang.Integer")){
										PropertyUtils.setProperty(t, field.get(c), Integer.parseInt(value));
										continue;
									}else if (xtype != null){
										PropertyUtils.setProperty(t, field.get(c), value);
										continue;
									}									
								}
							}
							if(t!=null){
								list.add(t);
							}							
							
						}
						
					}					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 通过实体类和实体数据生成excel文件 导出数据到excel
	 * @param clazz User.class
	 * @param list List<User> list
	 * @param outputPath输出文件目录
	 */
	public static void genExcel(Class clazz, List<Object> list,String outputPath) {
		try {
			File file = new File(outputPath+clazz.getName()+".xls");
			if (!file.exists()) {
				file.createNewFile();
			}
			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("数据", 0);
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Label labelname = new Label(i, 0, fields[i].getName()
						.toString());
				sheet.addCell(labelname);
			}
			int r = 1;
			for (Object p : list) {
				int c = 0;
				for (Field f : fields) {
					Label label = new Label(c, r,(String) PropertyUtils.getProperty(p, f.getName()));
					sheet.addCell(label);
					c++;
				}
				r++;
			}
			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 数据导出
	 * @param clazz实体名称
	 * @param list实体集合
	 * @param file导入文件名
	 */
	public static void ExcelOutput(Class clazz, List list,File file){
		try {			
			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("数据", 0);
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Label labelname = new Label(i, 0, fields[i].getName()
						.toString());
				sheet.addCell(labelname);
			}
			int r = 1;
			for (Object p : list) {
				int c = 0;
				for (Field f : fields) {
					Label label = new Label(c, r,String.valueOf(PropertyUtils.getProperty(p, f.getName())));
					sheet.addCell(label);
					c++;
				}
				r++;
			}
			book.write();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
