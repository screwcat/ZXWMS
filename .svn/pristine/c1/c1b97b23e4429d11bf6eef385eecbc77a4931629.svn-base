package com.zx.emanage.loancheck.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loancheck.vo.WmsCreCreditLineApplicantSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreCreditLineApplicant;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreCreditLineApplicantService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreCreditLineApplicantSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreCreditLineApplicantSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    public WmsCreCreditLineApplicant getInfoByPK(Integer wms_cre_credit_line_applicant_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String save(WmsCreCreditLineApplicant bean, String houseStr, String carStr,
                       WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String saveForAdd(String fileArr, WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreCreditLineApplicant bean, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreCreditLineApplicantVO
     * @author auto_generator
     */
    public WmsCreCreditLineApplicant getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public List<Map<String, Object>> getFileList(Integer wms_cre_credit_head_id, String data_type);
}