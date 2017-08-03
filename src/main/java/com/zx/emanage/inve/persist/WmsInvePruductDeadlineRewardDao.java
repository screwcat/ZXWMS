package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;
import com.zx.sframe.util.mybatis.BaseDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductDeadlineReward;

@MyBatisRepository
public interface WmsInvePruductDeadlineRewardDao extends BaseDao<WmsInvePruductDeadlineReward>
{
    /**
     * 奖励利率保存
     * 
     * @param map
     * @return baisong
     */

    public Integer savemap(Map<String, String> map);

    /**
     * 奖励利率根据期限表id查询并对应赋值
     * 
     * @param map
     * @return baisong
     */

    public List<Map<String, Object>> getListVal(Integer Wms_inve_pruduct_deadline_id);

    /**
     * 奖励利率根据期限表id查询并对应赋值
     * 
     * @param map
     * @return baisong
     */
    public List<WmsInvePruductDeadlineReward> showValCheck(Integer Wms_inve_pruduct_deadline_id);

    /**
     * 奖励利率根据期限表id查询并对应赋值 new
     * 
     * @param map
     * @return baisong
     */
    public List<WmsInvePruductDeadlineReward> showValbycategory(Integer Wms_inve_pruduct_deadline_id);

}