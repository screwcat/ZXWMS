package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsFinaCreMortgageList;
import com.zx.emanage.util.gen.entity.WmsFinaTerminationContractMortgageList;
import com.zx.emanage.loanfina.vo.WmsFinaCreMortgageListSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCreMortgageListService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsFinaCreMortgageListSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsFinaCreMortgageListVO
	 * @author auto_generator
	 */	
	public WmsFinaCreMortgageList getInfoByPK(Integer wms_fina_cre_mortgage_list_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsFinaCreMortgageList bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsFinaCreMortgageList bean, UserBean user);
	/**
	 * Description :根据提供的查询条件和必要条件查询符合条件的抵押物信息
	 * @param queryInfo
	 * @return record list
	 * @author hancd
	 */
	public Map<String, Object> getwmsfinacremortgagelistbySelect(
			WmsFinaCreMortgageListSearchBeanVO queryInfo);
	/**
	 * Description :根据对应条件来获取抵押物清单
	 * @param bean 
	 * @return Map<String,Object>
	 * @author baisong
	 */
	public Map<String,Object> getListByEntity(WmsFinaCreMortgageListSearchBeanVO bean, UserBean user);
	
	/**
	 * Description :正常还款确认 逾期还款确认中抵押物记录保存或更改
	 * @param bean 
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */
	public String saveUpdateMortgage(WmsFinaCreMortgageList bean, UserBean user);
}