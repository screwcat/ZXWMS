package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCarpCarsCustomerInfo;
import com.zx.emanage.cremanage.vo.WmsCreCarpCarsCustomerInfoSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsCreCarpHousingCustomerInfoSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpCarsCustomerInfoService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpCarsCustomerInfoVO
	 * @author auto_generator
	 */	
	public WmsCreCarpCarsCustomerInfo getInfoByPK(Integer wms_cre_carp_cars_customer_info_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpCarsCustomerInfo bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpCarsCustomerInfo bean, UserBean user);
	/**
	 * Description :get 根据贷款主表id返回抵押车辆信息
	 * @param queryInfo
	 * @return record list
	 * @author baisong
	 */
	public Map<String, Object> getList(WmsCreCarpCarsCustomerInfoSearchBeanVO queryInfo);
	/**
	 * @Title: getCarHInfoListWithoutPagingByMccid 
	 * @Description: 得到抵押房产信息 为车贷查询
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年7月29日 下午6:28:57
	 */
	public Map<String, Object> getCarHInfoListWithoutPagingByMccid(WmsCreCarpHousingCustomerInfoSearchBeanVO queryInfo);
	/**
	 * @Title: searchCarInfoExistCount 
	 * @Description: 车贷抵押车产重复性判断
	 * @param wms_cus_customer_id
	 * @param vehicle_idn_num
	 * @return Integer    
	 * @throws
	 * @author lvtu 
	 * @date 2015年8月4日 下午3:40:25
	 */
	public Integer searchCarInfoExistCount(String wms_cus_customer_head_id, String vehicle_idn_num);
	
}