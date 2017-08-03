package com.zx.emanage.inve.service;

import java.util.Map;

import com.zx.emanage.inve.vo.WmsInveCustomerWxSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsInveCustomerWx;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCustomerWxService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerWxSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCustomerWxSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCustomerWxVO
	 * @author auto_generator
	 */	
	public WmsInveCustomerWx getInfoByPK(Integer wms_inve_customer_wx_id);	


    /**
     * @Title: setPassword
     * @Description: 设置密码
     * @param mobile_phone 手机号
     * @param password 密码
     * @return 成功返回wx登录用户信息，不成功返回错误标志
     * @author: jinzhm
     * @time:2017年7月21日 上午8:23:55
     * history:
     * 1、2017年7月21日 jinzhm 创建方法
     */
    public Map<String, Object> setPassword(String mobile_phone, String password);
    
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCustomerWx bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCustomerWx bean, UserBean user);
	
	/**
     * 
     * @Title: verifyCustomerPhoneIsRegistered
     * @Description: 根据手机号验证当前客户是否已经注册过或者是否允许注册
     * @param phone_number 手机号
     * @return 返回map错误信息提示集合
     * @author: DongHao
     * @time:2017年7月20日 上午11:15:21
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    public Map<String, Object> verifyCustomerPhoneIsRegistered(String phone_number);

    /**
     * 
     * @Title: zshCustomerLogin
     * @Description: 验证客户手机号和密码登录
     * @param phone_number 手机号
     * @param password 登录密码
     * @return 返回map集合信息
     * @author: DongHao
     * @time:2017年7月20日 下午1:25:17
     * history:
     * 1、2017年7月20日 DongHao 创建方法
     */
    public Map<String, Object> zshCustomerLogin(String phone_number, String password) throws Exception;
}