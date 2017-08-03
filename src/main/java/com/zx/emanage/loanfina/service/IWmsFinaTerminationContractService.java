package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaTerminationContract;
import com.zx.emanage.loanfina.vo.WmsFinaTerminationContractSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaTerminationContractService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaTerminationContractSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaTerminationContractSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaTerminationContractVO
	 * @author auto_generator
	 */	
	public WmsFinaTerminationContract getInfoByPK(Integer wms_fina_termination_contract_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaTerminationContract bean, UserBean user,WmsFinaTerminationContractSearchBeanVO beanvo);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaTerminationContract bean, UserBean user);
}