package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInvePadPowerSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInvePadPower;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePadPowerService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInvePadPowerSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInvePadPowerSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInvePadPowerVO
	 * @author auto_generator
	 */	
	public WmsInvePadPower getInfoByPK(Integer wms_inve_pad_power_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInvePadPower bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInvePadPower bean, UserBean user);

    /**
     * @Title: getWmsInvePadPowerListByDeviceNum
     * @Description: 查询pad权限表集合 判断用户是否有使用pad的权限
     * @param device_num
     * @return 
     * @author: zhangyunfei
     * @time:2017年2月23日 下午1:36:30
     * history:
     * 1、2017年2月23日 Administrator 创建方法
    */
    public boolean getWmsInvePadPowerAuthorByDeviceNum(String device_num, UserBean userBean);

    /**
     * @Title: authorPad
     * @Description: pad解锁 或强退 
     * @param wmsInvePadPower
     * @param sys_status 1解锁  4强退
     * @author: zhangyunfei
     * @time:2017年2月23日 下午4:33:28
     * history:
     * 1、2017年2月23日 Administrator 创建方法
    */
    public void authorPad(WmsInvePadPower wmsInvePadPower, Integer sys_status);

}