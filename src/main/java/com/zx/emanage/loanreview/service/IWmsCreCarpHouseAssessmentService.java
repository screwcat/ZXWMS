package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsCreCarpHouseAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.loanreview.vo.WmsCreCarpHouseAssessmentSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCarpHouseAssessmentService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreCarpHouseAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreCarpHouseAssessmentVO
	 * @author auto_generator
	 */	
	public WmsCreCarpHouseAssessment getInfoByPK(Integer wms_cre_carp_house_assessment_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreCarpHouseAssessment bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreCarpHouseAssessment bean, UserBean user);
	

    /**
     * Description :get single-table information by wms_cre_credit_head_id
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreHousingAssessmentVO
     * @author baisong
     */
    public WmsCreCarpHouseAssessment getInfoByFK(Integer wms_cre_credit_head_id);
}