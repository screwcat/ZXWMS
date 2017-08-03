package com.zx.emanage.util.web;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import jodd.servlet.URLDecoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zx.platform.syscontext.PlatformGlobalVar;

/**
 * @ClassName: FileDealController
 * @Description: 内容摘要：文件处理Controller
 * @author WangShuai
 * @date 2016年11月23日
 * @version V1.0
 * history:
 * 1、2016年11月23日 WangShuai 创建文件
 */
@Controller
public class FileDealController {

	/**
	 * @Title: fileDownload
	 * @Description: 处理文件下载
	 * @param filepath
	 * @param response 
	 * @author: WangShuai
	 * @time:2016年11月23日 上午10:49:35
	 * history:
	 * 1、2016年11月23日 WangShuai 创建方法
	*/
	@RequestMapping("/fileExport.dos")
	public void fileDownload(String out_file_name, HttpServletResponse response) {
		//根据配置文件获取文件的具体路径
		String out_file_name_de=URLDecoder.decode(out_file_name,"UTF-8");
		
		String filePath = PlatformGlobalVar.SYS_PROPERTIES.get("realUploadCatalog");
		filePath=filePath+"/"+out_file_name_de;
		System.out.println("===============+"+filePath);
		InputStream in=null;  
		OutputStream out = null;
		try {
			response.setContentType("octets/stream");  
			response.addHeader("Content-Type", "text/html; charset=utf-8");  
			String downLoadName = new String(out_file_name.getBytes("gbk"), "iso8859-1");  
			response.addHeader("Content-Disposition", "attachment;filename=" + downLoadName);  
			// 文件输入流
            in= new BufferedInputStream(new FileInputStream(filePath));
			// 输出流
			out = response.getOutputStream();

			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			
			out.write(buffer);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
            if (in!=null){try {in.close();} catch (IOException e) {}}  
            if (out!=null){try {out.close();} catch (IOException e) {}}  
        }  
	}
}
