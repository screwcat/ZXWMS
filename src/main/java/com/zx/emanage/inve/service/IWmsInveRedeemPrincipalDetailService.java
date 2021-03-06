package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveRedeemPrincipalDetail;
import com.zx.emanage.inve.vo.WmsInveRedeemPrincipalDetailSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveRedeemPrincipalDetailService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveRedeemPrincipalDetailSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveRedeemPrincipalDetailSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveRedeemPrincipalDetailVO
	 * @author auto_generator
	 */	
	public WmsInveRedeemPrincipalDetail getInfoByPK(Integer wms_inve_redeem_principal_detail_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveRedeemPrincipalDetail bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveRedeemPrincipalDetail bean, UserBean user);
}