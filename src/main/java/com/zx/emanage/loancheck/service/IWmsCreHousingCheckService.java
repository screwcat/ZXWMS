package com.zx.emanage.loancheck.service;

import java.util.Map;

import com.zx.emanage.cremanage.vo.WmsCarLoanWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreHousingCheckSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAssessment;
import com.zx.emanage.util.gen.entity.WmsCreHousingCheck;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingCheckService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreHousingCheckSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreHousingCheckSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingCheckVO
     * @author auto_generator
     */
    public WmsCreHousingCheck getInfo(Integer wms_cre_housing_check_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreHousingCheck bean, WmsCreHousingAssessment wmsCreHousingAssessment, UserBean user,
                       WmsHouseCreditWorkFlowVO vo, String fileArr , String fileArryf);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreHousingCheck bean, UserBean user);

    /**
     * Description :房贷办件退件申请
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String cardToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
    /**
     * Description :房贷办件退件申请
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String cardToBackCar(WmsCarLoanWorkFlowVO wVo, UserBean user);
    
    public String WmsCreHousingCheckSaveForCompleted(WmsCreHousingCheck bean, WmsCreHousingAssessment wmsCreHousingAssessment, UserBean user,
            WmsHouseCreditWorkFlowVO vo, String fileArr, String fileArryf);
    
    /**
     * Description : 发送房产评估单基本信息详细信息
     */
    public WmsCreHousingCheckSearchBeanVO sendHouseAssessmentBasicInfoOne(WmsCreHousingCheckSearchBeanVO queryInfo);
    
    /**
     * Description : 发送房产评估单房产信息详细信息
     */
    public WmsCreHousingCheckSearchBeanVO sendHouseAssessmentBasicInfoTwo(WmsCreHousingCheckSearchBeanVO queryInfo);
    
    
    
}