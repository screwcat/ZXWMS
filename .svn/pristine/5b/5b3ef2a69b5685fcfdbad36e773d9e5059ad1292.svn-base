package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveReplace;
import com.zx.emanage.inve.vo.WmsInveReplaceSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveReplaceService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveReplaceSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveReplaceSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveReplaceVO
	 * @author auto_generator
	 */	
	public WmsInveReplace getInfoByPK(Integer wms_inve_replace_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveReplace bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveReplace bean, UserBean user);

	public Map<String, Object> get4Appro(Integer wms_inve_replace_id);

	public String cwsp(WmsInveReplace bean, UserBean user, String pass,
			String advice);

	public String zjlsp(WmsInveReplace bean, UserBean user, String pass);

	public String cancel(WmsInveReplace bean, UserBean user);
}