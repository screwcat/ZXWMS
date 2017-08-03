package com.zx.emanage.loanfina.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanfina.vo.WmsFinaCrePeriodRepaySearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsFinaCrePeriodRepay;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsFinaCrePeriodRepayService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsFinaCrePeriodRepaySearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsFinaCrePeriodRepayVO
     * @author auto_generator
     */
    public WmsFinaCrePeriodRepay getInfoByPK(Integer wms_fina_cre_period_pay_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsFinaCrePeriodRepay bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsFinaCrePeriodRepay bean, UserBean user);

    /**
     * 用于逾期查询其中本金和利息 Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public WmsFinaCrePeriodRepay getInfoByFK(Integer wms_cre_credit_line_customer_info_id);
    
    /**
	 * 	<!-- 电话催缴还款情况中每期信息-->
	 * @param wms_cre_credit_head_id
	 * @return
	 * baisong
	 */
	public Map<String, Object> getinfoforphone(Integer wms_cre_credit_head_id);

}