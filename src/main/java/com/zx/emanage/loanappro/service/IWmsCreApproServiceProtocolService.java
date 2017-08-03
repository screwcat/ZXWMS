package com.zx.emanage.loanappro.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.loanappro.vo.WmsCreApproServiceProtocolSearchBeanVO;
import com.zx.emanage.util.gen.entity.WmsCreApproServiceProtocol;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsCreApproServiceProtocolService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsCreApproServiceProtocolSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsCreApproServiceProtocolVO
     * @author auto_generator
     */
    public WmsCreApproServiceProtocol getInfoByPK(Integer wms_cre_appro_service_protocol_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsCreApproServiceProtocol bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsCreApproServiceProtocol bean, UserBean user);

    /**
     * 居间服务协议信息查询
     * 
     * @param
     * @return
     * @author 张风山
     */
    public Map<String, Object> selectAllByWmsCreCreditHeadId(String wms_cre_credit_head_id, UserBean user);

    /**
     * 居间服务协议信息查询
     * 
     * @param
     * @return
     * @author 张风山
     */
    public Map<String, Object> selectAllByProtocolId(String judgeString, UserBean user);

}