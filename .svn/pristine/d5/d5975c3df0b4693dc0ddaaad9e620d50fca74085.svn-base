package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreRevNeglectHistory;
import com.zx.emanage.loanreview.vo.WmsCreRevNeglectHistorySearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevNeglectHistoryService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreRevNeglectHistorySearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreRevNeglectHistoryVO
	 * @author auto_generator
	 */	
	public WmsCreRevNeglectHistory getInfoByPK(Integer wms_cre_rev_neglect_history_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreRevNeglectHistory bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreRevNeglectHistory bean, UserBean user);
	
	 /**
     * @Title: creHousingIgonre 
     * @Description:房贷审核忽略 
     * @param taskId
     * @param wms_cre_credit_head_id
     * @param user
     * @return    
     * @return String    
     * @throws
     * @author lvtu 
     * @date 2015年7月7日 上午10:33:03
     */
	public String creHousingIgonre(Integer taskId, Integer wms_cre_credit_head_id, UserBean user, String flag);
}