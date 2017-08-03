package com.zx.emanage.loanreview.service;

import java.util.Map;

import com.zx.emanage.loanreview.vo.WmsCreHousingTrialAssessmentSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingTrialAssessment;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingTrialAssessmentService {
	/**
	 * Description :get record list records by vo queryInfo withnot paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithoutPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get record list records by vo queryInfo with paging
	 * @param queryInfo
	 * @return record list
	 * @author auto_generator
	 */
	public Map<String, Object> getListWithPaging(WmsCreHousingTrialAssessmentSearchBeanVO queryInfo);
	
	/**
	 * Description :get single-table information by primary key 
	 * @param primary key 
	 * @return WmsCreHousingTrialAssessmentVO
	 * @author auto_generator
	 */	
	public WmsCreHousingTrialAssessment getInfoByPK(Integer wms_cre_housing_trial_assessment_id);	
	
	/**
	 * Description :add method
	 * @param bean
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String save(WmsCreHousingTrialAssessmentSearchBeanVO bean, UserBean user);
	
	/**
	 * Description :update method
	 * @param bean contains pk at least
	 * @return "success" or "error" or user defined
	 * @author auto_generator
	 */
	public String update(WmsCreHousingTrialAssessment bean, UserBean user);
	
	/**
     * Description : 房产初审评估单初始化
     * @param bean user
     * @return resMap
     * @author wangyihan
     */
    public Map<String, Object> wmsCreHouseIngtrialassessmentDisp(WmsCreHousingTrialAssessmentSearchBeanVO bean, UserBean user);
	/**
     * Description : 房产初审评估单初始化--查看方法i
     * @param bean user
     * @return resMap
     * @author wangyihan
     */
    public Map<String, Object> getwmsCreHouseIngtrialassessmentDisp(WmsCreHousingTrialAssessmentSearchBeanVO bean);
	
}