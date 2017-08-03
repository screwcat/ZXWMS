package com.zx.emanage.loanappro.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditHeadVO;
import com.zx.emanage.loanappro.vo.WmsCreCreditVisaApplSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditVisaAppl;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditVisaApplService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCreditVisaApplSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditVisaApplVO
	 * @author auto_generator
	 */	
	public WmsCreCreditVisaAppl getInfoByPK(Integer wms_cre_credit_visa_appl_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCreditVisaAppl bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCreditVisaAppl bean, UserBean user);

    /**
     * @Title: saveforhouse
     * @Description: TODO(终审面签保存-房贷)
     * @param bean
     * @param user
     * @param approveHouseWorkFlowVO
     * @param beanVo
     * @return 
     * @author: baisong
     * @time:2017年2月21日 下午2:24:20
     * history:
     * 1、2017年2月21日 baisong 创建方法
    */
    public String saveforhouse(WmsCreCreditVisaAppl bean, UserBean user, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, WmsCreCreditHeadVO beanVo);
}