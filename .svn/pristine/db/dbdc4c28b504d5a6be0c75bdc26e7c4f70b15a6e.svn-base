package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCreditReturnSearch;
import com.zx.emanage.cremanage.vo.WmsCreCreditReturnSearchSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditReturnSearchService {
	/**
	 * @Title: getListWithoutPaging 
	 * @Description: 退件列表Excel导出
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月24日 下午1:58:55
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo);
	
	/**
	 * @Title: getListWithPaging 
	 * @Description:  退件列表查询
	 * @param queryInfo
	 * @return    
	 * @return Map<String,Object>    
	 * @throws
	 * @author lvtu 
	 * @date 2015年6月24日 下午1:56:28
	 */
	public Map<String, Object> getListWithPaging(WmsCreCreditReturnSearchSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCreditReturnSearchVO
	 * @author auto_generator
	 */	
	public WmsCreCreditReturnSearch getInfoByPK(Integer wms_cre_credit_return_search_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCreditReturnSearch bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCreditReturnSearch bean, UserBean user);
}