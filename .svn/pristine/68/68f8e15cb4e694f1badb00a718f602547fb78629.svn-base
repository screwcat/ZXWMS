package com.zx.emanage.loanreview.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.cremanage.vo.WmsCreditWorkFlowVO;
import com.zx.emanage.loanreview.vo.WmsCreRevPhoneMainSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneMain;
import com.zx.emanage.util.gen.entity.WmsCreRevPhoneModel;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreRevPhoneMainService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreRevPhoneMainSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreRevPhoneMainVO
     * @author auto_generator
     */
    public WmsCreRevPhoneMain getInfoByPK(Integer wms_cre_rev_phone_main_id);

    /**
     * 保存电信审核信息
     * 
     * @param
     * @author 张风山
     */
    public String save(String wms_cre_credit_head_id, String dsInfo, String lxrInfo,
                       WmsCreditWorkFlowVO approveWorkFlowVO, String phone_appro_eval, String documentary_opinion, String saveFlag, UserBean user,
                       WmsCreRevPhoneModel wmsCreRevPhoneModel);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreRevPhoneMain bean, UserBean user);

    public List<WmsCreRevPhoneMain> getListByEntity(WmsCreRevPhoneMain queryInfo);

    public String saveHouse(String wms_cre_credit_head_id, String dsInfo, String lxrInfo,
                            WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, String phone_appro_eval, String saveFlag,
                            UserBean user);

    public String phoneToBack(WmsCreditWorkFlowVO approveWorkFlowVO, UserBean user);

    public String housePhoneToBack(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
}