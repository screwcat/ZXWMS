package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.domain.WmsCusCustomerLineHouseinfo;
import com.zx.emanage.util.gen.entity.WmsCreHousingFileInfo;
import com.zx.emanage.cremanage.vo.WmsCreHousingFileInfoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingFileInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging( UserBean user,WmsCreHousingFileInfo queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreHousingFileInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreHousingFileInfoVO
	 * @author auto_generator
	 */	
	public WmsCreHousingFileInfo getInfoByPK(Integer wms_cre_housing_file_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreHousingFileInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreHousingFileInfo bean, UserBean user);
/**
 * 
 * 贷款申请列表显示
 * @param bean
 * @return string
 * @author jiaodelong
 */
	public Map<String, Object> getHouseCreditList(WmsCreHousingFileInfo bean,UserBean user);
}