package com.zx.emanage.creditRightManager.web;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zx.emanage.creditRightManager.service.WmsInveMulticreInfoService;
import com.zx.emanage.creditRightManager.vo.WmsInveMulticreInfoVO;
import com.zx.emanage.inve.web.WmsInveCreditController;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;


/**
 * @ClassName: WmsInveMulticreInfoController
 * @Description: 内容摘要：债权采集Cotroller
 * @author WangShuai
 * @date 2017年2月8日
 * @version V1.0
 * history:
 * 1、2017年2月8日 WangShuai 创建文件
 */
@Controller
public class WmsInveMulticreInfoController {
private static Logger log = LoggerFactory.getLogger(WmsInveMulticreInfoController.class);
	
	@Autowired
	private WmsInveMulticreInfoService wmsInveMulticreInfoService;

	/**
	 * @Title: creditCollectionlistall
	 * @Description: 债权采集查询页面
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:35
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/creditCollectionlistall.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> creditCollectionlistall(WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		return wmsInveMulticreInfoService.creditCollectionlistall(wmsInveMulticreInfoVO);
	}
	
	
	
	/**
	 * @Title: wmsCredRightColData
	 * @Description: 获取新增初始话数据
	 * 1.采集单据编号
	 * 2.创建时间，创建人
	 * @param request
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 上午10:40:38
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/wmsCredRightColData.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsCredRightColData(HttpServletRequest request) {
		HttpSession session = request.getSession();
	    UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveMulticreInfoService.wmsCredRightColData(user);
	}
	
	/**
	 * @Title: wmsCredRightColDataEdit
	 * @Description: 编辑页面信息获取
	 * @param request
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月22日 下午5:03:15
	 * history:
	 * 1、2017年2月22日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/wmsCredRightColDataEdit.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> wmsCredRightColDataEdit(HttpServletRequest request,WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		HttpSession session = request.getSession();
	    UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveMulticreInfoService.wmsCredRightColDataEdit(user,wmsInveMulticreInfoVO);
	}
	
	/**
	 * @Title: saveWmsCreditRightCollAddInfo
	 * @Description: 保存债权采集新增页面
	 * @param request
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午3:07:26
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/saveWmsCreditRightCollAddInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> saveWmsCreditRightCollAddInfo(HttpServletRequest request,WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		HttpSession session = request.getSession();
	    UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveMulticreInfoService.saveWmsCreditRightCollAddInfo(user,wmsInveMulticreInfoVO);
	}
		
	
		
		
	/**
	 * @Title: saveWmsCreditRightCollAddInfo
	 * @Description: 债权采集详情查询页面
	 * @param request
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月21日 下午3:08:09
	 * history:
	 * 1、2017年2月21日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/getWmsCredRightColDataList.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getWmsCredRightColDataList(HttpServletRequest request,WmsInveMulticreInfoVO wmsInveMulticreInfoVO) {
		HttpSession session = request.getSession();
	    UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmsInveMulticreInfoService.getWmsCredRightColDataList(user,wmsInveMulticreInfoVO);
	}
	/**
	 * @Title: creditRightExcelImport
	 * @Description: 债权采集Excel导入
	 * @param wmsInveMulticreInfoVO
	 * @return 
	 * @author: WangShuai
	 * @time:2017年2月8日 上午10:44:35
	 * history:
	 * 1、2017年2月8日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/creditRightExcelImport.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> creditRightExcelImport(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) {
		//判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        
        //批量导入。参数：文件名，文件。
       Map<String, Object> resMap = wmsInveMulticreInfoService.doCreditRightExcelImport(name,file);
       return resMap;
		
	
	}
	
		
	/**
	 * @Title: downloadForColTemplate
	 * @Description: 下载报表模版
	 * @param file
	 * @param request
	 * @param response
	 * @return 
	 * @author: WangShuai
	 * @throws IOException 
	 * @time:2017年2月20日 下午6:16:43
	 * history:
	 * 1、2017年2月20日 WangShuai 创建方法
	*/
	@RequestMapping(value = "/creRigMan/downloadForColTemplate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void downloadForColTemplate(
            HttpServletRequest request,HttpServletResponse response) throws IOException {
		// String file_path =
        // PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadPath") +
        // File.separator + "WmsInveCredTemplate.xlsx";
        String file_path = WmsInveCreditController.class.getResource("/").getPath();
        file_path = file_path + "/reportTemplate/债权采集导入模版.xls";
        File downloadFile = new File(file_path);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        if (!downloadFile.exists())
        {
            log.error("路径未找到：" + file_path);
            return;
        }
        fis = new FileInputStream(downloadFile);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);

        // 中文文件名支持
        String encodedfileName = null;
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE"))
        {// IE
            encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
        }
        else if (null != agent && -1 != agent.indexOf("Mozilla"))
        {
            encodedfileName = new String("债权采集导入模版.xls".getBytes("UTF-8"), "iso-8859-1");
        }
        else
        {
            encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");

        int byteRead = 0;
        byte[] buffer = new byte[8192];
        while ((byteRead = bis.read(buffer, 0, 8192)) != -1)
        {
            bos.write(buffer, 0, byteRead);
        }

        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
	}
}
