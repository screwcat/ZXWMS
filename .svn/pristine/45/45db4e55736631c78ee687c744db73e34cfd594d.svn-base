package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanreview.vo.WmsCreHousingAssessmentSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingAssessmentService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreHousingAssessmentSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAssessmentVO
     * @author auto_generator
     */
    public WmsCreHousingAssessment getInfoByPK(Integer wms_cre_housing_assessment_id);

    /**
     * Description :get single-table information by wms_cre_credit_head_id
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreHousingAssessmentVO
     * @author zhubo
     */
    public WmsCreHousingAssessment getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreHousingAssessment bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreHousingAssessment bean, UserBean user);
}