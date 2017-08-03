package com.zx.emanage.reportmanage.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.emanage.reportmanage.service.IWmsInveCustomerMonthlyIncomeService;
import com.zx.emanage.reportmanage.service.IWmsLoanInveClaimsInquiryReportService;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.reportmanage.vo.WmsLoanInveClaimsInquiryReportBeanVo;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsLoanInveClaimsInquiryReportController.java
 * 系统名称：WMS
 * 模块名称：报表管理-财务口径-理财-债权查询报表
 * 完成日期：
 * 作    者：
 * 内容摘要：
 * 
 * 文件调用：
 * 修改记录：
 * 修改时间：
 * 修 改 人: 
 * 修改内容：
 * 关联BUG：
 * 修改方法：
 */
@Controller
public class WmsLoanInveClaimsInquiryReportController {
	  private static Logger log = LoggerFactory.getLogger(WmsLoanInveClaimsInquiryReportController.class);
	 	@Autowired
		private IWmsLoanInveClaimsInquiryReportService wmsLoanInveClaimsInquiryReportServiceImpl;

	 	/**
		 * 债权查询--报表
		 * @return List
		 * @author baisong
		 */
	    @RequestMapping(value="/reportmanage/getLoanInveClaims.do",method={RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
	    public Map<String,Object> getDataViewList(WmsLoanInveClaimsInquiryReportBeanVo queryInfo,HttpServletRequest request){
	    	HttpSession session =request.getSession();
	    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
	        return wmsLoanInveClaimsInquiryReportServiceImpl.getLoanInveClaims(queryInfo,user);
	    }
	    /**
  		 * 债权查询--报表--明细
  		 * @return List
  		 * @author baisong
  		 */
  	    @RequestMapping(value="/reportmanage/getLoanInveClaimsMx.do",method={RequestMethod.GET,RequestMethod.POST})
  	    @ResponseBody
  	    public Map<String,Object> getLoanInveClaimsMx(WmsLoanInveClaimsInquiryReportBeanVo queryInfo){
  	        return wmsLoanInveClaimsInquiryReportServiceImpl.getLoanInveClaimsMx(queryInfo);
  	    }
  	    /**
  		 * 债权查询--报表--Excel
  		 * @return List
  		 * @author baisong
  		 */
  	    @RequestMapping(value="/reportmanage/getLoanInveClaimsExcel.do",method={RequestMethod.GET,RequestMethod.POST})
  	    @ResponseBody
  	    public Map<String,Object> getLoanInveClaimsExcel(WmsLoanInveClaimsInquiryReportBeanVo queryInfo,HttpServletRequest request){
  	    	HttpSession session =request.getSession();
  	    	UserBean user =(UserBean)session.getAttribute(GlobalVal.USER_SESSION);
  	        return wmsLoanInveClaimsInquiryReportServiceImpl.getLoanInveClaimsExcel(queryInfo,user);
  	    }
  	    /**
  		 * 债权查询--报表--投资产品
  		 * @return List
  		 * @author baisong
  		 */
  	    @RequestMapping(value="/reportmanage/getLoanInveClaimsCategory.do",method={RequestMethod.GET,RequestMethod.POST})
  	    @ResponseBody
  	    public List<Map<String,Object>> getLoanInveClaimsCategory(WmsLoanInveClaimsInquiryReportBeanVo queryInfo){
  	        return wmsLoanInveClaimsInquiryReportServiceImpl.getLoanInveClaimsCategory(queryInfo);
  	    }
  	    /**
  	     * 理财产品查询(全部产品) 
  	     * @return List
  	     * @author yangqiyu
  	     */
  	    @RequestMapping(value="/reportmanage/getLoanInveClaimsCategoryall.do",method={RequestMethod.GET,RequestMethod.POST})
	    @ResponseBody
  	    public List<Map<String,Object>> getLoanInveClaimsCategoryAll(){
  	    	return wmsLoanInveClaimsInquiryReportServiceImpl.getLoanInveClaimsCategoryAll();
  	    }
}
