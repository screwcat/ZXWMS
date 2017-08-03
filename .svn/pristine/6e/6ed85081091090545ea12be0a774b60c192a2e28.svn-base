package com.zx.emanage.inve.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveActivityStatisticsDao
 * 模块名称：活动量统计
 * @Description: 内容摘要：
 * @author zhangmingjian
 * @date 2017年3月23日
 * @version V3.5
 * history:
 * 1、2017年3月23日 zhangmingjian 创建文件
 */
@MyBatisRepository
public interface WmsInveActivityStatisticsDao
{
    /**
     * 
     * @Title: selectActivityStatisticsList
     * @Description: 查询活动量统计列表信息(分页查询)
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月23日 下午3:16:14
     * history:
     * 1、2017年3月23日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectActivityStatisticsList(Map<String, Object> map);
    public Integer countActivityStatisticsList(Map<String, Object> map);

    /**
     * 
     * @Title: selectActivityStatisticsList
     * @Description: 查询团队活动量列表信息(分页查询)
     * @param bean
     * @return 
     * @author: zhangmingjian
     * @time:2017年3月30日 下午1:18:12
     * history:
     * 1、2017年3月30日 zhangmingjian 创建方法
     */
    public List<Map<String, Object>> selectActivityStatisticsListForTeam(Map<String, Object> map);
    public Integer countActivityStatisticsListForTeam(Map<String, Object> map);
}
