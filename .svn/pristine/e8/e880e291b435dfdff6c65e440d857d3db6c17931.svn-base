package com.zx.sframe.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zx.platform.syscontext.util.StringUtil;

/**
 * 禁用IP和开通IP 公共访问权限方法
 * @author 
 *
 */
public class SysIPUtil {
	
	public static String  JudgeIP(HttpServletRequest request){
		String result="";
		//获取登陆IP
		String ip="";
		try {
			ip = getIpAddr(request);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	try 
    	{
    		//读取XML文件,获得document对象
    		SAXReader reader = new SAXReader();
			Document document = reader.read(new File(request.getServletContext().getRealPath("/")+"/WEB-INF/classes/IP.xml"));
			
			//获取根节点元素对象 
			Element root = document.getRootElement();
			
			// 获取LimIP节点 。
			Element e_have = root.element("Spe"); 
			// 获取Spe这个元素节点 中，所有子节点名称为value元素的节点 。
			List<Element> show_list = e_have.elements("value");
			
			Element e_hide = root.element("LimIP"); 
			// 获取Spe这个元素节点 中，所有子节点名称为value元素的节点 。
			List<Element> hide_list = e_hide.elements("value");
			
			result = operateIP(ip, show_list, hide_list);
			
		}
    	catch (DocumentException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}
	private static String operateIP(String ip, List<Element> show_list, List<Element> hide_list)
	{
		String result = "show";
		
		for(Element ele : show_list)
		{
			if(ele.getText().equals(ip))
			{
				return result;
			}
		}
		
		int index =  ip.lastIndexOf(".");
		String temp = ip.substring(0, index)+".*";
		
		for(Element ele : hide_list)
		{
			if(ele.getText().equals(temp))
			{
				result = "hide";
				return result;
			}
		}
		
		
		return result;
	}
	
	public static final String getIpAddr(final HttpServletRequest request)  
	        throws Exception {  
	    if (request == null) {  
	        throw (new Exception("getIpAddr method HttpServletRequest Object is null"));  
	    }  
	    String ipString = request.getHeader("x-forwarded-for");  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getRemoteAddr();  
	    }  
	  
	    // 多个路由时，取第一个非unknown的ip  
	    final String[] arr = ipString.split(",");  
	    for (final String str : arr) {  
	        if (!"unknown".equalsIgnoreCase(str)) {  
	            ipString = str;  
	            break;  
	        }  
	    } 
	    if("0:0:0:0:0:0:0:1".equals(ipString)){
	    	return "127.0.0.1";
	    }
	  
	    return ipString;  
	} 
	
}
