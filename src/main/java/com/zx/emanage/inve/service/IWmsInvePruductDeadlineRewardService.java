package com.zx.emanage.inve.service;

import java.util.Map;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductDeadlineReward;
import com.zx.emanage.inve.vo.WmsInvePruductDeadlineRewardSearchBeanVO;
import com.zx.sframe.util.vo.UserBean;

/*
 * @version 2.0
 */

public interface IWmsInvePruductDeadlineRewardService
{
    /**
     * Description :get record list records by vo queryInfo withnot paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithoutPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo);

    /**
     * Description :get record list records by vo queryInfo with paging
     * 
     * @param queryInfo
     * @return record list
     * @author auto_generator
     */
    public Map<String, Object> getListWithPaging(WmsInvePruductDeadlineRewardSearchBeanVO queryInfo);

    /**
     * Description :get single-table information by primary key
     * 
     * @param primary key
     * @return WmsInvePruductDeadlineRewardVO
     * @author auto_generator
     */
    public WmsInvePruductDeadlineReward getInfoByPK(Integer wms_inve_pruduct_deadline_reward_id);

    /**
     * Description :add method
     * 
     * @param bean
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String save(WmsInvePruductDeadlineReward bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author auto_generator
     */
    public String update(WmsInvePruductDeadlineReward bean, UserBean user);

    /**
     * Description :update method
     * 
     * @param bean contains pk at least
     * @return "success" or "error" or user defined
     * @author baisong
     */
    public List<List<Map<String, Object>>> getListVal(Integer wms_inve_pruduct_category_id);

    /**
     * Description :奖励利率查询
     * 
     * @param primary key
     * @return WmsInvePruductDeadlineRewardVO
     * @author baisong
     */
    public Map<String, Object> showValCheck(Integer wms_inve_pruduct_deadline_reward_id);

    public Map<String, Object> showValbycategory(Integer wms_inve_pruduct_deadline_id);
}