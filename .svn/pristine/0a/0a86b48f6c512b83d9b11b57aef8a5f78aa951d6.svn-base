package com.zx.emanage.cremanage.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsCreCreditHeadHouseSearchBeanVO;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreHousingAttSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreHousingAtt;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreHousingAttService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreHousingAttSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author wangyishun
     */
    public Map<String, Object> getListWithoutPagingForHt(WmsCreHousingAttSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreHousingAttSearchBeanVO queryInfo);

    /**
     * Description :信息完善提交
     * 
     * @param 上传文件、贷款ID
     * @return "success" or "error" or user defined
     * @author ry
     */
    public String doSQSave(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String fileArr, UserBean user);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreHousingAttVO
     * @author auto_generator
     */
    public WmsCreHousingAtt getInfoByPK(Integer wms_cre_housing_att_id);

    /**
     * Description :get single-table information by foreign key
     * 
     * @param wms_cre_credit_head_id
     * @return WmsCreHousingAttVO
     */
    public List<WmsCreHousingAtt> getInfoByFK(Integer wms_cre_credit_head_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreHousingAtt bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreHousingAtt bean, UserBean user);

    /**
     * 公证附件信息保存
     * 
     * @param bean
     * @param user
     * @return
     */
   //public String addNotarizationNewRecord(UserBean user, String taskId, int wms_cre_credit_head_id, String fileArr);
    public String addNotarizationNewRecord(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean);
    
    /**
     *合同补充附件信息保存
     * 
     * @param bean
     * @param user
     * @return
     */
   //public String addNotarizationNewRecord(UserBean user, String taskId, int wms_cre_credit_head_id, String fileArr);
    public String addSupplementAgreeInfo(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean);
    /**
     * 他项附件信息保存
     * 
     * @param bean
     * @param user
     * @return
     */
    //public String addOthersNewRecord(UserBean user, String taskId, int wms_cre_credit_head_id, String fileArr);
    public String addOthersNewRecord(UserBean user, WmsCreCreditHeadHouseSearchBeanVO bean);
    
    /**
     * Description :补录合同提交
     * 
     * @param 上传文件、贷款ID
     * @return "success" or "error" or user defined
     * @author zhubo
     */
    //public String doBLSave(UserBean user, String taskId, int wms_cre_credit_head_id, String fileArr);
    public String doBLSave(UserBean user,  WmsCreCreditHeadHouseSearchBeanVO bean);
}