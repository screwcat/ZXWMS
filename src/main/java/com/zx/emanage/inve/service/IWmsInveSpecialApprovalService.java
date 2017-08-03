package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveSpecialApproval;
import com.zx.emanage.inve.vo.WmsInveSpecialApprovalSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInveSpecialApprovalService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInveSpecialApprovalSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInveSpecialApprovalVO
     * @author auto_generator
     */
    public WmsInveSpecialApproval getInfoByPK(Integer special_approval_leader_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInveSpecialApproval bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInveSpecialApproval bean, UserBean user);

    /**
     * Description :为下拉框添加特批领导
     * 
     * @param null
     * @return record list
     * @author ry
     */
    public List<com.zx.emanage.util.gen.entity.WmsInveSpecialApproval> getAllInveSpecialapprova();
}