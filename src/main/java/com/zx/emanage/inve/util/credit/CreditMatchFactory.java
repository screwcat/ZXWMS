package com.zx.emanage.inve.util.credit;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchFactory
 * 模块名称：债权匹配工厂
 * @Description: 内容摘要：获得具体实现类的方法
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatchFactory
{
    /**
     * @Title: getCreditMatch
     * @Description: 根据流程名称获得对应债权匹配实现类
     * @param flowName 流程名称
     * @return 债权匹配实现类
     * @author: jinzhm
     * @time:2017年2月10日 下午4:59:49
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
     */
    public static CreditMatchInterface getCreditMatch(String flowName)
    {
        // 是签单流程
        if (CreditMatch.FLOW_TRANSA.equals(flowName))
        {
            return new CreditMatchForTransa();
        }
        // 是续期流程
        else if (CreditMatch.FLOW_EXTEND.equals(flowName))
        {
            return new CreditMatchForExtend();
        }
        // 是赎回流程
        else if (CreditMatch.FLOW_REDEEM.equals(flowName))
        {
            return new CreditMatchForRedeem();
        }
        else if (CreditMatch.FLOW_CHANGE_CREDIT_PACKAGE.equals(flowName))
        {
            return new CreditMatchForChangeCreditPackage();
        }
        // 都不是
        else
        {
            throw new IllegalArgumentException("流程名称不正确！");
        }
    }
}
