package com.zx.emanage.remind.web;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.loanappro.vo.WmsCreApproSearchBeanVO;
import com.zx.emanage.remind.service.IWmsCreCreditNotaryWarnService;
import com.zx.emanage.remind.vo.CombinedLoanBeanVO;
import com.zx.emanage.remind.vo.CreditMessageToRepayWarnBeanVO;
import com.zx.emanage.remind.vo.WmsCreCreditNotaryWarnSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditNotaryWarn;
import com.zx.platform.syscontext.PlatformGlobalVar;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * @ClassName: WmsCreCreditNotaryWarnController
 * @Description: 内容摘要：公证到期提醒
 * @author baisong
 * @date 2016年10月24日
 * @version V1.0
 *  history: 1、2016年10月24日 baisong 创建文件
 *  2、2016年11月24日 baisong 修改方法注释
 */
@Controller
public class WmsCreCreditNotaryWarnController {
    private static Logger log = LoggerFactory.getLogger(WmsCreCreditNotaryWarnController.class);

    @Autowired
    private IWmsCreCreditNotaryWarnService wmscrecreditnotarywarnService;

    /**
     * Description : 查询列表(不带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/SearchWmsCreCreditNotaryWarnWithoutPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithoutPaging(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.getListWithoutPaging(queryInfo, user);
    }

    /**
     * Description : 查询列表(带分页)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/SearchWmsCreCreditNotaryWarnWithPagingList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> getListWithPaging(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.getListWithPaging(queryInfo, user);
    }

    /**
     * Description : 根据主键查询
     * 
     * @param primary key
     * @return WmsCreCreditNotaryWarnVO
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/WmsCreCreditNotaryWarnGetInfoByPK.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public WmsCreCreditNotaryWarn getInfoByPK(HttpServletRequest request, Integer wms_cre_credit_notary_warn_id) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.getInfoByPK(wms_cre_credit_notary_warn_id, user);
    }

    /**
     * Description : 新增
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/WmsCreCreditNotaryWarnSave.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doSave(HttpServletRequest request, WmsCreCreditNotaryWarn bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditnotarywarnService.save(bean, user);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }

    /**
     * Description : 修改
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/WmsCreCreditNotaryWarnUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String doUpdate(HttpServletRequest request, WmsCreCreditNotaryWarn bean) {
        String result = "";
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        try {
            result = wmscrecreditnotarywarnService.update(bean, user);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            result = "error";
        }
        return result;
    }
    
    /**
     * Description : 查询公证到期提醒列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/searchVNotaryWarnList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchVNotaryWarnList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchVNotaryWarnList(queryInfo, user);
    }
    
    /**
     * Description : 查询公证到期查询列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/searchVNotarySearchList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchVNotarySearchList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchVNotaryWarnList(queryInfo, user);
    }
    
    /**
     * Description : 导入数据
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/importWmsCreCreditNotaryWarnData.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> importWmsCreCreditNotaryWarnData(HttpServletRequest request, HttpServletResponse response, 
    		WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        queryInfo = wmscrecreditnotarywarnService.importWmsCreCreditNotaryWarnData(request, response, queryInfo, user);
        resMap.put("result", queryInfo.getResult());
        resMap.put("err_num", queryInfo.getErr_num());
        resMap.put("flag", queryInfo.getFlag());
        return resMap;
    }
    
    /**
     * Description : 导出模板
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     * @throws IOException 
     */
    @RequestMapping(value = "/creditManage/downloadForNotaryWarn.do", method = {RequestMethod.GET, RequestMethod.POST})
    public void downloadForNotaryWarn(HttpServletRequest request, HttpServletResponse response, 
    		WmsCreCreditNotaryWarnSearchBeanVO queryInfo) throws IOException {
    	String file_path = PlatformGlobalVar.SYS_PROPERTIES.get("realDownloadPath") + File.separator + "WmsCreCreditNotaryWarnTemplate.xlsx";
    	File downloadFile = new File(file_path);
    	
    	BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        if(!downloadFile.exists()) {
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
        if(null != agent && -1 != agent.indexOf("MSIE")) {//IE
            encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
        } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
            encodedfileName = new String("房贷数据导入模版.xlsx".getBytes("UTF-8"), "iso-8859-1");
        } else {
            encodedfileName = java.net.URLEncoder.encode(downloadFile.getName(), "UTF-8");
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
    }
    
    
    /**
     * Description : 获取登录人Code
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/loancheck/wmspersonnelinitdelbtn.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String wmspersonnelinitdelbtn(HttpServletRequest request, HttpServletResponse response) {
    	   HttpSession session = request.getSession();
           UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return user.getUserCode();
    }
    
    /**
     * Description : 查询公证到期删除
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/creditManage/deleteVNotaryWarnInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String deleteVNotaryWarnInfo(HttpServletRequest request, WmsCreCreditNotaryWarn queryInfo) {
    	 String result = "";
    	 try {
    		 result = wmscrecreditnotarywarnService.deleteInfo(queryInfo.getWms_cre_credit_notary_warn_id());
		} catch (Exception e) {
			result = "error";
		}
		return result;
    }
    
    /**
     * Description : 查询还款提醒列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/remind/searchRepaymentReminderList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchRepaymentReminderList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchRepaymentReminderList(queryInfo, user);
    }
    
    /**
     * Description : 查询还款确认列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author jiaodelong
     */
    @RequestMapping(value = "/remind/searchRepaymentConfirmationList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchRepaymentConfirmationList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchRepaymentConfirmationList(queryInfo, user);
    }
    
    /**
     * Description : 查询还款查询列表(使用视图)
     * 
     * @param queryInfo
     * @return record list
     * @author administrator
     */
    @RequestMapping(value = "/remind/searchRepaymentSearchList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> searchRepaymentSearchList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
    	HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchRepaymentSearchList(queryInfo, user);
    }

    /**
    * 
    * @Title: searchloansSearchList
    * @Description: TODO(贷款数据查询单据列表 视图)
    * @param request
    * @param queryInfo
    * @return Map<String, Object>
    * @author: jiaodelong
    * @time:2016年11月23日 上午9:48:04
    * history:
    * 1、2016年11月23日 jiaodelong 创建方法
    */
    @RequestMapping(value = "/remind/searchloansSearchList.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> searchloansSearchList(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo)
    {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
        return wmscrecreditnotarywarnService.searchloansSearchList(queryInfo, user);
    }
    
    /**
    * 
    * @Title: searchBirthdayReminderList
    * @Description: (查询生日提醒列表 视图)
    * @param request
    * @param queryInfo
    * @return
    * @author: baisong
    * @time:2016年11月15日 上午10:59:39 history: 1、2016年11月15日 baisong 创建方法
    */
	@RequestMapping(value = "/remind/searchBirthdayReminderList.do", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> searchBirthdayReminderList(
			HttpServletRequest request,
			WmsCreCreditNotaryWarnSearchBeanVO queryInfo) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
		return wmscrecreditnotarywarnService.searchBirthdayReminderList(
				queryInfo,
				user);
	}
	
    /**
     * @Title: sendMessageBirthday
     * @Description: (获取信息放款数据同步到还款提醒模块)
     * @param request
     * @param bean
     * @return String
     * @author: baisong
     * @time:2016年11月15日 下午1:30:40 
     * history:
     * 1、2016年11月15日 baisong 创建方法
     */
	@RequestMapping(value = "/remind/getCreditMessageToRepayWarn.do", method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String getCreditMessageToRepayWarn(HttpServletRequest request,CreditMessageToRepayWarnBeanVO queryInfo) {
	    String result = "";
    	try {
    		HttpSession session = request.getSession();
    		UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
            result = wmscrecreditnotarywarnService.getCreditMessageToRepayWarn(queryInfo, user);
		} catch (Exception e) {
			result = "error";
		}
		return result;
	} 

    /**
     * 
     * @Title: getTeamManagerName
     * @Description: TODO(获取当前公正提醒表内所有的团队经理姓名)
     * @param request
     * @param queryInfo
     * @return 
     * @author: baisong
     * @time:2016年12月7日 下午5:23:22
     * history:
     * 1、2016年12月7日 baisong 创建方法
     */
    @RequestMapping(value = "/remind/getTeamManagerName.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getTeamManagerName(HttpServletRequest request, WmsCreCreditNotaryWarnSearchBeanVO queryInfo)
    {
        Map<String, Object> result = new HashMap<>();
        try
        {
            HttpSession session = request.getSession();
            UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
            result = wmscrecreditnotarywarnService.getTeamManagerName(queryInfo, user);
        }
        catch (Exception e)
        {
            result.put("result", "error");
        }
        return result;
    }

    /**
    * 
    * @Title: searchloansSearchList
    * @Description: TODO(组合贷)
    * @param request
    * @param queryInfo
    * @return Map<String, Object>
    * @author: baisong
    * @time:2016年11月23日 上午9:48:04
    * history:
    * 1、2016年11月23日 baisong 创建方法
    */
    @RequestMapping(value = "/remind/combinedLoan.do", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> combinedLoan(HttpServletRequest request, CombinedLoanBeanVO queryInfo)
    {
        Map<String, Object> result = new HashMap<>();
        try
        {
            try
            {
                HttpSession session = request.getSession();
                UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
                result = wmscrecreditnotarywarnService.combinedLoan(queryInfo, user);
            }
            catch (RuntimeException e)
            {
                // 缺少组合贷 等额本息产品
                result.put("result", "creloantypeerror");
            }
        }
        catch (Exception e)
        {
            result.put("result", "error");
        }
        return result;
    }
    /**
     * 
     * @Title: getAgreeLoanApproApproveListWithPaging
     * @Description: TODO(展期状态查询列表)
     * @param queryInfo
     * @param request
     * @return 
     * @author: jiaodelong
     * @time:2017年2月20日 上午9:17:29
     * history:
     * 1、2017年2月20日 jiaodelong 创建方法
     */
    @RequestMapping(value = "/loanappro/getAgreeInfoListForZQ.do", method = { RequestMethod.GET,
                                                                                               RequestMethod.POST })
     @ResponseBody
     public Map<String, Object> getAgreeLoanApproApproveListWithPaging(WmsCreApproSearchBeanVO queryInfo,
                                                                       HttpServletRequest request)
     {
         // 获取当前登录人
         HttpSession session = request.getSession();
         UserBean user = (UserBean) session.getAttribute(GlobalVal.USER_SESSION);
         return wmscrecreditnotarywarnService.getAgreeInfoListForZQ(queryInfo, user);
     }
    
}
