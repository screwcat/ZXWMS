package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCustomerCard;
import com.zx.emanage.inve.vo.WmsInveCustomerCardSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveCustomerCardService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsInveCustomerCardSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsInveCustomerCardSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsInveCustomerCardVO
	 * @author auto_generator
	 */	
	public WmsInveCustomerCard getInfoByPK(Integer wms_inve_customer_card_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsInveCustomerCard bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsInveCustomerCard bean, UserBean user);

    /**
     * @Title: newAddIncomeCard
     * @Description: 添加收益卡信息
     * @param user 当前登录人员信息对象
     * @param wmsInveCustomerCard 收益卡信息对象
     * @return 返回map集合提示信息
     * @author: DongHao
     * @time:2017年4月7日 下午2:28:33
     * history:
     * 1、2017年4月7日 DongHao 创建方法
    */
    public Map<String, Object> newAddIncomeCard(UserBean user, WmsInveCustomerCard wmsInveCustomerCard);
}