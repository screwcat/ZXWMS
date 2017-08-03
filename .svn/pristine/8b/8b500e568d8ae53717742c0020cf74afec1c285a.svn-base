package com.zx.sframe.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

 
public class PoiExportForWord {
	
	/**
	 * 根据传入map替换word文件中形如"$P{key}"的值
	 * @throws IOException 
	 */
	public static void replaceWordValueByMap(Map<String, Object> map, String filePath) throws IOException {
		FileInputStream in = null;  
        HWPFDocument hdt = null;  
        try {  
            in = new FileInputStream(new File(filePath));  
            hdt = new HWPFDocument(in);  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
          
        //替换读取到的word模板内容的指定字段  
        Range range = hdt.getRange();  
        
        for (Map.Entry<String, Object> entry: map.entrySet()) {  
        	StringBuffer replaceValue = new StringBuffer();
        	if(entry.getValue() == null || entry.getValue().toString().equals("")) {
        		replaceValue = new StringBuffer();
        		for(int i = 0; i < entry.getKey().length() + 4; i++) {//占位符4位：$P{value}
        			replaceValue.append(" ");
        		}
        		map.put(entry.getKey(), replaceValue);
        	}
    		range.replaceText("$P{" + entry.getKey() + "}", entry.getValue().toString());  
        }
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();  
        FileOutputStream out = null;  

		try {  
            hdt.write(ostream);  
            out = new FileOutputStream(filePath);
            out.write(ostream.toByteArray());
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		
        ostream.close();
        in.close();   
        out.close();
	}
	
	/**
	 * 下载word
	 * @throws IOException 
	 */
	public static void downLoadWord(File downFile, String filePath, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
     
        if(!downFile.exists()) {
            return;
        }
        fis = new FileInputStream(filePath);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);
       
        File copyFileTemplpate = new File(filePath);
        //中文文件名支持
        String encodedfileName = null;
        String agent = request.getHeader("USER-AGENT");
        if(null != agent && -1 != agent.indexOf("MSIE")) {//IE
            encodedfileName = java.net.URLEncoder.encode(copyFileTemplpate.getName(), "UTF-8");
        } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
            encodedfileName = new String(copyFileTemplpate.getName().getBytes("UTF-8"), "iso-8859-1");
        } else {
            encodedfileName = java.net.URLEncoder.encode(copyFileTemplpate.getName(), "UTF-8");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        
        int byteRead = 0;
        byte[] buffer = new byte[8192];
        while((byteRead = bis.read(buffer,0,8192)) != -1) {
            bos.write(buffer, 0, byteRead);
        }
        
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
        
        //下载后删除复制后的文件
        copyFileTemplpate.delete();
	}
	
}
