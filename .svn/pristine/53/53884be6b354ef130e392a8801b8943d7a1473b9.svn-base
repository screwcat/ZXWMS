package com.zx.sframe.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook; 

import com.zx.platform.syscontext.PlatformGlobalVar;



 /**
  * 版权所有：版权所有(C) 2016，卓信财富
  * 文件名称：ExcelDealUtil.java
  * 系统名称：PTP
  * 模块名称：报表多sheet导出功能 
  * auth ： zhangyunfei
  */
 
 
 
public class PoiExportUtil {
	
	/**
	 * doexport
	 * 导出报表到文件
	 * @param filename，报表模版名称
	 * Map<String,String>
	 */
	@SuppressWarnings("unchecked")
	public static void doexport(String filename,Map<String,Object> listMap,String out_file_name,HttpServletResponse response){
		
		String filepath = PoiExportUtil.class.getResource("/").getPath();
		filepath = filepath+"reportTemplate/";
		
		//模板路径		
		String in_file_path=filepath+filename;
		
        XLSTransformer transformer = new XLSTransformer();  
        
        InputStream in=null;  

        OutputStream out=null;  

        try {  
        	out_file_name = new String(out_file_name.getBytes("GBK"), "ISO8859_1");

        	response.setHeader("content-disposition", "attachment; filename="+out_file_name);  
            response.setContentType("application/msexcel");  
                     
            /**获取文件输入流*/
            in=new BufferedInputStream(new FileInputStream(in_file_path));  
            
            Workbook workbook=transformer.transformXLS(in, listMap);  
            out=response.getOutputStream();  
            
            workbook.write(out);  
            out.flush(); 
            
       } catch (Exception e) {  
           e.printStackTrace();  
       } finally {  
           if (in!=null){try {in.close();} catch (IOException e) {}}  
           if (out!=null){try {out.close();} catch (IOException e) {}}  
       }  

	}
	
	/**
	 * @Title: doExportToFile
	 * @Description: 将Excel导出到指定文件
	 * @param filename 文件名称
	 * @param listMap 值列表
	 * @param out_file_name 输出文件名
	 * @return 返回文件的路径，用于进行外面接收
	 * @author: WangShuai
	 * @time:2016年11月23日 上午9:50:23
	 * history:
	 * 1、2016年11月23日 WangShuai 创建方法
	*/
	public static String doExportToFile(String filename,Map<String,Object> listMap,String out_file_name){
		//输出文件起始路径
		String outFilePath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog");
		
		
		String filepath = PoiExportUtil.class.getResource("/").getPath();
		filepath = filepath+"reportTemplate/";
		
		//模板路径		
		String in_file_path=filepath+filename;
		
        XLSTransformer transformer = new XLSTransformer();  
        
        InputStream in=null;  

        OutputStream out=null;  

        try {  
        	outFilePath=outFilePath+"/"+out_file_name;

                     
            /**获取文件输入流*/
            in=new BufferedInputStream(new FileInputStream(in_file_path));  
            
            Workbook workbook=transformer.transformXLS(in, listMap);  
            out=new FileOutputStream(outFilePath);
            
            workbook.write(out);  
            out.flush(); 
            
       } catch (Exception e) {  
           e.printStackTrace();  
       } finally {  
           if (in!=null){try {in.close();} catch (IOException e) {}}  
           if (out!=null){try {out.close();} catch (IOException e) {}}  
       }  
		return outFilePath;
	}
}
