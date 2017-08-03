package com.zx.emanage.reportmanage.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.reportmanage.vo.WmsLoanInveClaimsInquiryReportBeanVo;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: IWmsLoanInveClaimsInquiryReportService.java
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
public interface IWmsLoanInveClaimsInquiryReportService {
  /**
	 * 债权查询--报表
	 * @return List
	 * @author baisong
	 */
	public Map<String, Object> getLoanInveClaims(WmsLoanInveClaimsInquiryReportBeanVo queryInfo,UserBean user);
 /**
	 * 债权查询--报表--明细
	 * @return List
	 * @author baisong
	 */
	public Map<String, Object> getLoanInveClaimsMx(WmsLoanInveClaimsInquiryReportBeanVo queryInfo);
 /**
	 * 债权查询--报表--Excel
	 * @return List
	 * @author baisong
	 */
	public Map<String, Object> getLoanInveClaimsExcel(WmsLoanInveClaimsInquiryReportBeanVo queryInfo,UserBean user);
 /**
	 * 债权查询--报表--Excel
	 * @return List
	 * @author baisong
	 */
	public List<Map<String,Object>> getLoanInveClaimsCategory(WmsLoanInveClaimsInquiryReportBeanVo queryInfo);
	/**
	 * 查询全部债权信息 
	 * @return List
	 * @author yangqiyu
	 */
	public List<Map<String,Object>> getLoanInveClaimsCategoryAll();
}
