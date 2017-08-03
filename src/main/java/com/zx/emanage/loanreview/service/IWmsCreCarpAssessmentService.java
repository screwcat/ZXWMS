package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCarpAssessment;
import com.zx.emanage.loanreview.vo.WmsCreCarpAssessmentSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpAssessmentService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpAssessmentVO
	 * @author auto_generator
	 */	
	public WmsCreCarpAssessment getInfoByPK(Integer wms_cre_carp_assessment_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpAssessment bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpAssessment bean, UserBean user);
	
	/**
	 * Description :add method--评估单保存更新
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author baisong
	 */	
	public String saveUpdate(WmsCreCarpAssessment bean, UserBean user,WmsCreCarpAssessmentSearchBeanVO beanvo);

	/**
	 * Description :get single-table information by primary key --获取数据评估单
	 * @param primary key 
	 * @return WmsCreCarpAssessmentVO
	 * @author baisong
	 */		
	public Map<String, Object> getInfoByHK(Integer wms_cre_credit_head_id);	
	
}