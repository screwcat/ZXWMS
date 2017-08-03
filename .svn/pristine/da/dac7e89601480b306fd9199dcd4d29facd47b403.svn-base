package com.zx.emanage.inve.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.inve.service.IWmsInveCreditService;
import com.zx.emanage.inve.vo.WmsInveCreditSearchBeanVO;
import com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCredit;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveCreditController
 * 模块名称：
 * @Description: 内容摘要：债权包controller
 * @author Guanxu
 * @date 2016年12月13日
 * @version V1.0
 * history:
 * 1、2016年12月13日 Guanxu 创建文件
 */
@Controller
public class WmsInveCreditController {
	private static Logger log = LoggerFactory.getLogger(WmsInveCreditController.class);
	
	@Autowired
	private IWmsInveCreditService wmsinvecreditService;

	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecreditwithoutpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSearchBeanVO queryInfo) {
		return wmsinvecreditService.getListWithoutPaging(queryInfo);
	}

	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	@RequestMapping(value = "/inve/wmsinvecreditwithpaginglist.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getListWithPaging(WmsInveCreditSearchBeanVO queryInfo) {
		return wmsinvecreditService.getListWithPaging(queryInfo);
	}

    /**
     * 
     * @Title: searchSpecList
     * @Description: 获取最新的拆分规则表
     * @param queryInfo
     * @return 
     * @author: Guanxu
     * @time:2016年12月20日 下午1:42:24
     * history:
     * 1、2016年12月20日 Guanxu 创建方法
     */
    @RequestMapping(value = "/inve/searchSpecList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public List<Map<String, Object>> searchSpecList(WmsInveCreditSearchBeanVO queryInfo)
    {
        return wmsinvecreditService.searchSpecList(queryInfo);
    }
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCreditVO
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecreditinfobypk.do", method = {RequestMethod.GET})
	@ResponseBody
	public WmsInveCredit getInfoByPK(Integer wms_inve_credit_id) {
		return wmsinvecreditService.getInfoByPK(wms_inve_credit_id);
	}	

	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
	@RequestMapping(value = "/inve/wmsinvecreditsave.do", method = {RequestMethod.POST})
	@ResponseBody
	public String doSave(HttpServletRequest request, WmsInveCredit bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecreditService.save(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}

	/**
	 * Description :update method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */	
    @RequestMapping(value = "/inve/wmsinvecreditupdate.do", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String doUpdate(HttpServletRequest request, WmsInveCredit bean) {
		String result = "";
		HttpSession session = request.getSession();
		UserBean user = (UserBean)session.getAttribute(GlobalVal.USER_SESSION);
		try {
			result = wmsinvecreditService.update(bean, user);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = "error";
		}
		/*			
		// record log	
		if("success".equals(result)){
			String msg = "log content";
			SysTools.saveLog(request, msg); // record log method
		}
		*/
		return result;
	}	

    @RequestMapping(value = "/inve/importWmsInveCreditInfo.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> importWmsInveCreditInfo(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        resMap = wmsinvecreditService.importWmsInveCreditInfo(request, response, user);
        return resMap;
    }

    /**
     * 
     * @Title: exportWmsInveCreditInfo
     * @Description: 导出拆分后的债权
     * @param request
     * @param response
     * @return
     * @throws ParseException 
     * @author: Guanxu
     * @time:2016年12月15日 下午3:12:54
     * history:
     * 1、2016年12月15日 Guanxu 创建方法
     */
    @RequestMapping(value = { "/export/exportWmsInveCreditInfo.dos" }, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> exportWmsInveCreditInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException
    {

        return wmsinvecreditService.exportWmsInveCreditInfo();
    }

    /**
     * 
     * @Title: downloadForNotaryWarn
     * @Description: 债权包模板导出
     * @param request
     * @param response
     * @param queryInfo
     * @throws IOException 
     * @author: Guanxu
     * @time:2016年12月13日 下午5:33:12
     * history:
     * 1、2016年12月13日 Guanxu 创建方法
     */
    @RequestMapping(value = "/inve/downloadForWmsInveCredTemplate.do", method = { RequestMethod.GET, RequestMethod.POST })
    public void downloadForWmsInveCredTemplate(HttpServletRequest request, HttpServletResponse response, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) throws IOException
    {
        // String file_path =
        // PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadPath") +
        // File.separator + "WmsInveCredTemplate.xlsx";
        String file_path = WmsInveCreditController.class.getResource("/").getPath();
        file_path = file_path + "/reportTemplate/WmsInveCredTemplate.xlsx";
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
            encodedfileName = new String("债权拆分_导入模板.xlsx".getBytes("UTF-8"), "iso-8859-1");
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