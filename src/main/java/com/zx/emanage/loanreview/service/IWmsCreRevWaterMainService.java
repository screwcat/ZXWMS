package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.google.gson.JsonArray;
import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreRevWaterMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevWaterMain;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevWaterMainService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevWaterMainSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevWaterMainSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevWaterMainVO
     * @author auto_generator
     */
    public WmsCreRevWaterMain getInfoByPK(Integer wms_cre_rev_water_main_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String save(String dsls, String dgls, String zysj, String water_appro_eval, UserBean user,
                       WmsCreditWorkFlowVO approveWorkFlowVO, String saveStatus);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String saveForFd(String dsls, String dgls, String water_appro_eval, UserBean user,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String saveStatus);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevWaterMain bean, UserBean user);

    /**
     * Description :信贷对私获取表单数据
     * 
     * @param primary key
     * @return WmsCreRevWaterMainVO
     * @author wangyishun
     */
    public Map<String, Object> getInfoByFK(Integer wms_cre_credit_head_id,
                                           Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :信贷对公获取表单数据
     * 
     * @param primary key
     * @return WmsCreRevWaterMainVO
     * @author wangyishun
     */
    public Map<String, Object> getInfoByFKForAdd(Integer wms_cre_credit_head_id,
                                                 Integer wms_cre_credit_line_customer_change_head_id);

    /**
     * Description :信贷流水退件申请
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String waterToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    /**
     * Description :房贷流水退件申请
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author wangyishun
     */
    public String waterToBackForFd(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
   
}