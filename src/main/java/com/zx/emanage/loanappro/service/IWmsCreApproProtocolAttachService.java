package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.cremanage.vo.WmsHouseCreditWorkFlowVO;
import com.zx.emanage.loanappro.vo.WmsCreApproProtocolAttachSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproProtocolAttach;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreApproProtocolAttachService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreApproProtocolAttachSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreApproProtocolAttachSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproProtocolAttachVO
     * @author auto_generator
     */
    public WmsCreApproProtocolAttach getInfoByPK(Integer wms_cre_appro_protocol_attach_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreApproProtocolAttach bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreApproProtocolAttach bean, UserBean user);
    
    public String updateAttach(WmsCreApproProtocolAttach bean, WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO, UserBean user);
    /**
     * 房贷合同结束流程 新版合同 ireport
     * @param approveHouseWorkFlowVO
     * @param user
     * @return
     * @author baisong 
     * @date 20160902
     */
    public String updateHTQDworkflow(WmsHouseCreditWorkFlowVO approveHouseWorkFlowVO,UserBean user);
}