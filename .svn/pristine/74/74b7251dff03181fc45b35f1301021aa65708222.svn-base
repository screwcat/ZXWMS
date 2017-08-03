package com.zx.emanage.loanreview.service;

import java.util.Map;

import com.zx.emanage.loanreview.vo.WmsCreCreditHistoricalInfoSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditHistoricalInfo;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditHistoricalInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCreditHistoricalInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
    public Map<String, Object> getListWithPaging(WmsCreCreditHistoricalInfoSearchBeanVO queryInfo, UserBean user);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditHistoricalInfoVO
	 * @author auto_generator
	 */	
	public WmsCreCreditHistoricalInfo getInfoByPK(Integer wms_cre_credit_historical_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCreditHistoricalInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCreditHistoricalInfo bean, UserBean user);
}