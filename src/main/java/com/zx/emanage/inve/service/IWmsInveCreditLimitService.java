package com.zx.emanage.inve.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCreditLimit;
import com.zx.emanage.inve.vo.WmsInveCreditLimitSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCreditLimitService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCreditLimitSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCreditLimitSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCreditLimitVO
	 * @author auto_generator
	 */	
	public WmsInveCreditLimit getInfoByPK(Integer wms_inve_credit_limit);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCreditLimit bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCreditLimit bean, UserBean user);

    /**
     * @Title: getLocationRegionCreditMinAccount
     * @Description: 根据当前登录人所在区域获取区域的债权限制金额
     * @param user 当前登录人信息对象
     * @return 返回债权限制金额
     * @author: DongHao
     * @time:2017年4月7日 上午11:03:54
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    public BigDecimal getLocationRegionCreditMinAccount(UserBean user);

    /**
     * @Title: verifyCreditLimitAccountAvailable
     * @Description: 判断当前业务员所在区域的债权限制金额与投资金额进行比较
     * @param user 当前登录业务员信息对象
     * @param product_account 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param wms_inve_transa_id 上单表主键
     * @param origCategoryId 老产品的产品id
     * @param newCategoryId 新产品的产品id
     * @return 如果投资金额小于债权限制金额返回true,否则返回false
     * @author: DongHao
     * @time:2017年4月7日 上午11:25:12
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    public boolean verifyCreditLimitAccountAvailable(UserBean user, BigDecimal product_account,Date actDateOfPayment,Integer wms_inve_transa_id, Integer origCategoryId, Integer newCategoryId);

    /**
     * @Title: saveCreditLimit
     * @Description: 金额支付时配置债权不足需要向债权限制表中添加记录
     * @param user 当前登录人信息对象
     * @param product_account 投资金额
     * @return 返回信息提示
     * @author: DongHao
     * @time:2017年4月7日 上午11:34:12
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    public Map<String, Object> saveCreditLimit(UserBean user, BigDecimal product_account);
    
    /**
     * 
     * @Title: verifyActDateOfPaymentToNowDateBefore
     * @Description: 验证实际支付日期是否在当前系统时间之前,(之前返回false,等于当前系统时间返回true)
     * @param date
     * @return 
     * @author: DongHao
     * @time:2017年5月1日 下午8:04:36
     * history:
     * 1、2017年5月1日 DongHao 创建方法
     */
    public boolean verifyActDateOfPaymentToNowDateBefore(Date date);
}