package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCreditGroup;
import com.zx.emanage.loanappro.vo.WmsCreCreditGroupSearchBeanVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditGroupService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCreditGroupSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCreditGroupSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditGroupVO
	 * @author auto_generator
	 */	
	public WmsCreCreditGroup getInfoByPK(Integer wms_cre_credit_group_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCreditGroup bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCreditGroup bean, UserBean user);

    /**
     * @Title: combinedLoanInfoSave
     * @Description: TODO(组合贷保存)
     * @param queryInfo
     * @param user
     * @return String
     * @author: jiaodelong
     * @time:2016年12月28日 上午10:47:52
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
    */
    public String combinedLoanInfoSave(WmsCreCreditGroup queryInfo, UserBean user);

    /**
     * @Title: combinedLoanInfoSaveForCaiFen
     * @Description: TODO(组合贷拆分保存)
     * @param bean
     * @param user
     * @return String
     * @author: jiaodelong
     * @time:2016年12月28日 下午6:26:56
     * history:
     * 1、2016年12月28日 jiaodelong 创建方法
    */
    public String combinedLoanInfoSaveForCaiFen(WmsCreCreditHeadVO bean, UserBean user);
}