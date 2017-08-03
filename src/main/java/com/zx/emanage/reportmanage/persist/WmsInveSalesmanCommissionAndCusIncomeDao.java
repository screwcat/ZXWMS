package com.zx.emanage.reportmanage.persist;

import java.util.List;
import java.util.Map;

import com.zx.sframe.util.mybatis.MyBatisRepository;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsInveSalesmanCommissionAndCusIncomeDao
 * 模块名称：业务员佣金及客户收益统计dao接口
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2016年12月9日
 * @version V1.0
 * history:
 * 1、2016年12月9日 jinzhm 创建文件
 */
@MyBatisRepository
public interface WmsInveSalesmanCommissionAndCusIncomeDao
{
    /**
     * @Title: getWmsInveSalesmanCommissionAndCusIncomeCount
     * @Description: 根据查询条件查询业务员佣金及客户收益统计记录个数
     * @param paramMap 查询条件
     * @return 统计记录个数
     * @author: jinzhm
     * @time:2016年12月9日 上午8:47:25
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    public int queryWmsInveSalesmanCommissionAndCusIncomeCount(Map<String, Object> paramMap);

    /**
     * @Title: queryWmsInveSalesmanCommissionAndCusIncome
     * @Description: 根据查询条件查询业务员佣金及客户收益统计数据
     * @param paramMap 查询条件
     * @return 统计数据
     * @author: jinzhm
     * @time:2016年12月9日 上午8:48:47
     * history:
     * 1、2016年12月9日 jinzhm 创建方法
     */
    public List<Map<String, Object>> queryWmsInveSalesmanCommissionAndCusIncome(Map<String, Object> paramMap);

    /**
     * @Title: queryWmsInveSalesmanCommissionAndCusIncomeOtherMonthCount
     * @Description: 根据查询条件查询业务员佣金及客户收益统计记录个数(不是当月，是老数据查询)
     * @param paramMap 查询条件
     * @return 统计记录个数
     * @author: jinzhm
     * @time:2016年12月12日 上午8:33:57
     * history:
     * 1、2016年12月12日 jinzhm 创建方法
     */
    public int queryWmsInveSalesmanCommissionAndCusIncomeOtherMonthCount(Map<String, Object> paramMap);

    /**
     * @Title: queryWmsInveSalesmanCommissionAndCusIncomeOtherMonth
     * @Description: 根据查询条件查询业务员佣金及客户收益统计数据(不是当月，是老数据查询)
     * @param paramMap 查询条件
     * @return 统计数据
     * @author: jinzhm
     * @time:2016年12月12日 上午8:34:36
     * history:
     * 1、2016年12月12日 jinzhm 创建方法
     */
    public List<Map<String, Object>> queryWmsInveSalesmanCommissionAndCusIncomeOtherMonth(Map<String, Object> paramMap);

}
