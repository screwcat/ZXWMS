package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaCreRepaymentDetails;
import com.zx.emanage.loanfina.vo.WmsFinaCreRepaymentDetailsSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreRepaymentDetailsService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreRepaymentDetailsVO
	 * @author auto_generator
	 */	
	public WmsFinaCreRepaymentDetails getInfoByPK(Integer wms_fina_cre_repayment_details_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaCreRepaymentDetails bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaCreRepaymentDetails bean, UserBean user);
	/**
	 * Description :通过贷款表主键获取期还款明细表数据
	 * @param wms_cre_credit_head_id
	 * @return list
	 * @author hancd
	 */
	public Map<String,Object> getListBySearchforDetails(
			WmsFinaCreRepaymentDetailsSearchBeanVO queryInfo);
}