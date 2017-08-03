package com.zx.emanage.reportmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.emanage.reportmanage.persist.WmsInveCustomerMonthlyIncomeDao;
import com.zx.emanage.reportmanage.persist.WmsLoanInveClaimsInquiryReportDao;
import com.zx.emanage.reportmanage.service.IWmsLoanInveClaimsInquiryReportService;
import com.zx.emanage.reportmanage.vo.WmsInveCustomerMonthlyIncomeBeanVo;
import com.zx.emanage.reportmanage.vo.WmsLoanInveClaimsInquiryReportBeanVo;
import com.zx.emanage.sysmanage.persist.SysSpecialUserDao;
import com.zx.emanage.util.gen.SysTools;
import com.zx.emanage.util.gen.entity.SysSpecialUser;
import com.zx.platform.syscontext.vo.GridDataBean;
import com.zx.sframe.util.vo.UserBean;
/**
 * 版权所有：版权所有(C) 2015，卓信金控
 * 文件名称: WmsLoanInveClaimsInquiryReportServiceImpl.java
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
@Service("wmsLoanInveClaimsInquiryReportService")
public class WmsLoanInveClaimsInquiryReportServiceImpl implements IWmsLoanInveClaimsInquiryReportService{
	  @Autowired
	    private WmsLoanInveClaimsInquiryReportDao wmsLoanInveClaimsInquiryReportDao;
	  @Autowired
	  private SysSpecialUserDao specialUserDao;
	/**
	 * 债权查询--报表
	 * @return List
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getLoanInveClaims(
			WmsLoanInveClaimsInquiryReportBeanVo queryInfo,UserBean user) {
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        if (queryInfo.getBill_code() != null && !"".equals(queryInfo.getBill_code()))
	        {
	        	paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	        }
	        if (queryInfo.getProt_code() != null && !"".equals(queryInfo.getProt_code()))
	        {
	        	paramMap.put("prot_code", SysTools.getSqlLikeParam(queryInfo.getProt_code()));
	        }
	        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
	        {
	        	paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
	        }
	        if (queryInfo.getCredit_name() != null && !"".equals(queryInfo.getCredit_name()))
	        {
	        	paramMap.put("credit_name", SysTools.getSqlLikeParam(queryInfo.getCredit_name()));
	        }
	        if (queryInfo.getDate_of_assign_begin() != null && !"".equals(queryInfo.getDate_of_assign_begin()))
	        {
	        	paramMap.put("date_of_assign_begin",queryInfo.getDate_of_assign_begin());
	        }
	        if (queryInfo.getDate_of_assign_end() != null && !"".equals(queryInfo.getDate_of_assign_end()))
	        {
	        	paramMap.put("date_of_assign_end", queryInfo.getDate_of_assign_end());
	        }
	        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
	        {
	        	paramMap.put("category_name",queryInfo.getCategory_name());
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        paramMap.put("pagesize", queryInfo.getPagesize());
	        paramMap.put("offset", queryInfo.getOffset());
	        //俩种显示方式
	        //List<Map<String, Object>> list = wmsLoanInveClaimsInquiryReportDao.getLoanInveClaims(paramMap);
	        List<Map<String, Object>> list = wmsLoanInveClaimsInquiryReportDao.getLoanInveClaimsExcel(paramMap);
	        SysSpecialUser specialUser= new SysSpecialUser();
	        specialUser.setEnable_flag("1");
	        List<SysSpecialUser> specilaUsers =specialUserDao.getListByEntity(specialUser);
	        list=SysTools.setListView(list, user, specilaUsers);
	        GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),wmsLoanInveClaimsInquiryReportDao.findCount(paramMap),list);
	        return bean.getGridData();
	}
	/**
	 *债权查询--报表--明细
	 * @return List
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getLoanInveClaimsMx(WmsLoanInveClaimsInquiryReportBeanVo queryInfo) {
		 Map<String,Object>   paramMap = new HashMap<String, Object>();
			 paramMap.put("wms_inve_transa_prod_id", queryInfo.getWms_inve_transa_prod_id());
			 paramMap.put("sortname", queryInfo.getSortname());
	         paramMap.put("sortorder", queryInfo.getSortorder());
	         paramMap.put("pagesize", queryInfo.getPagesize());
	         paramMap.put("offset", queryInfo.getOffset());
			 List<Map<String,Object>> list =wmsLoanInveClaimsInquiryReportDao.getLoanInveClaimsMx(paramMap);
	         GridDataBean<Map<String, Object>> bean = new GridDataBean<Map<String, Object>>(queryInfo.getPage(),wmsLoanInveClaimsInquiryReportDao.findCountMx(paramMap),list);
	        return bean.getGridData();
	}
	/**
	 * 债权查询--报表--Excel
	 * @return List
	 * @author baisong
	 */
	@Override
	public Map<String, Object> getLoanInveClaimsExcel(
			WmsLoanInveClaimsInquiryReportBeanVo queryInfo,UserBean user) {
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        if (queryInfo.getBill_code() != null && !"".equals(queryInfo.getBill_code()))
	        {
	        	paramMap.put("bill_code", SysTools.getSqlLikeParam(queryInfo.getBill_code()));
	        }
	        if (queryInfo.getProt_code() != null && !"".equals(queryInfo.getProt_code()))
	        {
	        	paramMap.put("prot_code", SysTools.getSqlLikeParam(queryInfo.getProt_code()));
	        }
	        if (queryInfo.getCus_name() != null && !"".equals(queryInfo.getCus_name()))
	        {
	        	paramMap.put("cus_name", SysTools.getSqlLikeParam(queryInfo.getCus_name()));
	        }
	        if (queryInfo.getCredit_name() != null && !"".equals(queryInfo.getCredit_name()))
	        {
	        	paramMap.put("credit_name", SysTools.getSqlLikeParam(queryInfo.getCredit_name()));
	        }
	        if (queryInfo.getDate_of_assign_begin() != null && !"".equals(queryInfo.getDate_of_assign_begin()))
	        {
	        	paramMap.put("date_of_assign_begin",queryInfo.getDate_of_assign_begin());
	        }
	        if (queryInfo.getDate_of_assign_end() != null && !"".equals(queryInfo.getDate_of_assign_end()))
	        {
	        	paramMap.put("date_of_assign_end", queryInfo.getDate_of_assign_end());
	        }
	        if (queryInfo.getCategory_name() != null && !"".equals(queryInfo.getCategory_name()))
	        {
	        	paramMap.put("category_name", queryInfo.getCategory_name());
	        }
	        paramMap.put("sortname", queryInfo.getSortname());
	        paramMap.put("sortorder", queryInfo.getSortorder());
	        List<Map<String, Object>> list = wmsLoanInveClaimsInquiryReportDao.getLoanInveClaimsExcel(paramMap);
	        SysSpecialUser specialUser= new SysSpecialUser();
	        specialUser.setEnable_flag("1");
	        List<SysSpecialUser> specilaUsers =specialUserDao.getListByEntity(specialUser);
	        list=SysTools.setListView(list, user, specilaUsers);
	        paramMap.clear();
	        paramMap.put("Rows", list);
	        return paramMap;
	}
	/**
	 *债权查询--报表--投资产品
	 * @return List
	 * @author baisong
	 */
	@Override
	public List<Map<String,Object>> getLoanInveClaimsCategory(WmsLoanInveClaimsInquiryReportBeanVo queryInfo) {
			Map<String,Object>   paramMap = new HashMap<String, Object>();
			List<Map<String,Object>> list =wmsLoanInveClaimsInquiryReportDao.getLoanInveClaimsCategory(paramMap);
			paramMap.put("wms_inve_pruduct_category_id","-1");
			paramMap.put("category_name", "请选择");
			list.add(0, paramMap);
	        return list;
	}
	/**
	 *债权查询--报表--投资产品
	 * @return List
	 * @author baisong
	 */
	@Override
	public List<Map<String,Object>> getLoanInveClaimsCategoryAll() {
			Map<String,Object>   paramMap = new HashMap<String, Object>();
			List<Map<String,Object>> list =wmsLoanInveClaimsInquiryReportDao.getLoanInveClaimsCategoryAll();
			paramMap.put("wms_inve_pruduct_category_id","-1");
			paramMap.put("category_name", "请选择");
			list.add(0, paramMap);
	        return list;
	}
}
