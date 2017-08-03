package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeNotOverRedeem
 * 模块名称：未到期赎回客户收益处理类
 *      赎回日期的月份小于协议到期日期的月份（未到期赎回）
 * @Description: 内容摘要：对于未到期赎回或预约赎回（预约到未到期赎回）时客户收益的处理
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public class CountIncomeNotOverRedeem extends CountIncomeRedeemTimeAbstract
{

    /**
     * @Title: getRedeemIncome
     * @Description: 未到期赎回时获得赎回金额
     * @param data 赎回时机对象
     * @return 赎回金额 = 赎回基本收益+赎回奖励收益-已付收益
     * @author: jinzhm
     * @time:2016年12月28日 下午4:31:13
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#getRedeemIncome(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getRedeemIncome(CountIncomeRedeemTimeData data)
    {
        // 已付收益
        BigDecimal paidIncome = data.getRedeemDetail().getPaid_income() == null ? BigDecimal.ZERO
                                                                               : data.getRedeemDetail()
                                                                                     .getPaid_income();
        return data.getRedeemDetail().getPayable_basic_income().add(data.getRedeemDetail().getPayable_reward_income())
                   .subtract(paidIncome);
    }

}
