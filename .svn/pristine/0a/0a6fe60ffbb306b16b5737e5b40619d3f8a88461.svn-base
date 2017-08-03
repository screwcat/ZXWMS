package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveProtocolRenewal;
import com.zx.emanage.inve.vo.WmsInveProtocolRenewalSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveProtocolRenewalService {
	/**
     * 获取协议续期页面列表数据-导出Excel
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	public Map<String, Object> getListWithoutPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,UserBean user);
    /**
     * 获取协议续期页面列表数据
     * @param queryInfo
     * @return Map
     * @author baisong
     */
	public Map<String, Object> getListWithPaging(WmsInveProtocolRenewalSearchBeanVO queryInfo,UserBean user);
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveProtocolRenewalVO
	 * @author auto_generator
	 */	
	public WmsInveProtocolRenewal getInfoByPK(Integer wms_inve_protocol_renewal_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveProtocolRenewal bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveProtocolRenewal bean, UserBean user);
}