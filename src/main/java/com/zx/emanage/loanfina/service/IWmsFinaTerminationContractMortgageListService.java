package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractMortgageListSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaTerminationContractMortgageListService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractMortgageListSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaTerminationContractMortgageListVO
	 * @author auto_generator
	 */	
	public WmsFinaTerminationContractMortgageList getInfoByPK(Integer wms_fina_termination_contract_mortgage_list_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaTerminationContractMortgageList bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaTerminationContractMortgageList bean, UserBean user);
}