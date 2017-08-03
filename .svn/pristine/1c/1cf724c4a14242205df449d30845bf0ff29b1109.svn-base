package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveCreditSplitSpecSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCreditSplitSpec;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCreditSplitSpecService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCreditSplitSpecSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCreditSplitSpecSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCreditSplitSpecVO
	 * @author auto_generator
	 */	
	public WmsInveCreditSplitSpec getInfoByPK(Integer wms_inve_credit_split_spec_id);	
	
	    /**
     * Description :add method
     * @param beanJSON
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(String beanJSON, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCreditSplitSpec bean, UserBean user);
}