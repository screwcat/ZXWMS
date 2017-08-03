package com.zx.emanage.loanpost.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsPostRemindHistory;
import com.zx.emanage.loanpost.vo.WmsPostRemindHistorySearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsPostRemindHistoryService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsPostRemindHistorySearchBeanVO queryInfo, UserBean user);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsPostRemindHistorySearchBeanVO queryInfo, UserBean user);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String,Object> getInfoListWithPaging(WmsPostRemindHistorySearchBeanVO queryInfo, long Id);
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author auto_generator
	 */	
	public WmsPostRemindHistory getInfoByPK(Integer wms_post_remind_history_id);	
	/**
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author baisong
	*/	
	public Map<String, Object> getInfoByHK(Integer wms_post_remind_history_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsPostRemindHistory bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsPostRemindHistory bean, UserBean user);
	
	/**
	 * @Description: (更新电话是否提醒) 
	 * @param wms_cre_credit_head_id
	 * @param reminder_result
	 * @return    
	 * @return String    返回类型
	 * @throws
	 * @author lvtu
	 */
	public String updatePhoneRemindHistory(UserBean user, int repay_no, int wms_cre_credit_head_id, String reminder_result);
}