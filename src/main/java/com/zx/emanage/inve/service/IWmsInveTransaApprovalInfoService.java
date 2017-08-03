package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveDebtWorkFlowVO;
import com.zx.emanage.inve.vo.WmsInveTransaApprovalInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveTransaApprovalInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveTransaApprovalInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveTransaApprovalInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveTransaApprovalInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveTransaApprovalInfoVO
	 * @author auto_generator
	 */	
	public WmsInveTransaApprovalInfo getInfoByPK(Integer wms_inve_transa_approval_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveTransaApprovalInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveTransaApprovalInfo bean, UserBean user);
	
	
	/**
	 * 保存审核意见
	 * @param info
	 * @return
	 * history:
	 * 1. 2017年7月10日17:13:51 donghao修改方法,增加参数incomeCardParams收益卡的相关参数
	 */
	public String saveToSubmitNullify(WmsInveTransaApprovalInfo info,String taskId,String yjtype,UserBean user,String compe, String incomeCardParams);
	
	/**
	 * 保存单据作废原因
	 * 
	 * @param info
	 * @return
	 */
	public String saveToCancel(UserBean user,WmsInveTransaApprovalInfo info,WmsInveDebtWorkFlowVO wDebtWorkFlowVO);
	/**
	 * 保存草稿单据作废原因
	 * 
	 * @param info
	 * @return
	 */
	public String saveToCancelCG(UserBean user,WmsInveTransaApprovalInfo info);
	

}