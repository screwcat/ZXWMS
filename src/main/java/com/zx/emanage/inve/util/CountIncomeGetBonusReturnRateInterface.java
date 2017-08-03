package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeGetBonusReturnRateInterface
 * 模块名称：不同奖励方式获取奖励利率接口
 * @Description: 内容摘要：只有一个获得奖励利率方法
 * @author jinzhm
 * @date 2016年12月28日
 * @version V1.0
 * history:
 * 1、2016年12月28日 jinzhm 创建文件
 */
public interface CountIncomeGetBonusReturnRateInterface
{

    /**
     * @Title: getBonusReturnRate
     * @Description: 获得奖励利率
     * @param dataMap 获得奖励利率时需要的数据存放map对象
     * @return 奖励利率
     * @author: jinzhm
     * @time:2016年12月28日 下午1:42:02
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public BigDecimal getBonusReturnRate(Map<String, Object> dataMap);
}
