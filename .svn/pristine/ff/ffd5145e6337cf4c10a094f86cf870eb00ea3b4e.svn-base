package com.zx.emanage.loanfina.service;

import java.util.List;
import java.util.Map;

import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentHistorySearchBeanVO;
import com.zx.emanage.loanfina.vo.WmsFinaDrawBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCreRealrepayInfo;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetailsAtt;
import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentHistory;
import com.zx.emanage.util.gen.entity.WmsPostDunningCommission;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreRepaymentHistoryService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRepaymentHistoryVO
	 * @author auto_generator
	 */	
	public WmsFinaCreRepaymentHistory getInfoByPK(Integer wms_fina_cre_repayment_history_id);	
	
	/**
	 * Description :当用户还款时，保存相关数据
	 * @param bean
	 * @param user
	 * @param queryInfo
	 * @return "success" or "error" or user defined
	 * @author hancd
	 */
	public String save(String detailIds, String beanJSON, WmsFinaCreRepaymentHistory bean, UserBean user,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);
	/**
	 * Description :当用户还款时，保存相关数据
	 * @param bean
	 * @param user
	 * @param queryInfo
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	public String doSaveOverdue(WmsFinaCreRepaymentHistory bean, UserBean user,WmsFinaCreRepaymentHistorySearchBeanVO queryInfo,WmsPostDunningCommission wCommission);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaCreRepaymentHistory bean, UserBean user);
	/**
	 * Description :根据 wms_cre_credit_head_id 查询历史信息
	 * @param primary key 
	 * @return WmsPostRemindHistoryVO
	 * @author baisong
	*/	
	public Map<String, Object> getInfoByHK(Integer wms_post_remind_history_id);

	public WmsFinaCreRealrepayInfo getFPPrive(
			WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);	
	
	/***
	 * 根据wms_cre_credit_head_id查询多条逾期历史
	 * @param wms_cre_credit_head_id
	 * @return list
	 * baisong
	 */
	public Map<String, Object> searchHistory(WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);
	/**
	 * Description :根据 wms_cre_credit_head_id以及期数  查询对应期还款历史
	 * @param primary key 
	 * @return Map
	 * @author hancd
	 */	
	public Map<String, Object> getInfoByRepaymentorRepayNo(
			WmsFinaCreRepaymentHistorySearchBeanVO queryInfo);
	/**
	 * Description :逾期还款计算提成
	 * @param primary key 
	 * @return Map
	 * @author baisong 
	 */	
	public Map<String, Object> getDraw(
			WmsFinaDrawBeanVO queryInfo);
	
}