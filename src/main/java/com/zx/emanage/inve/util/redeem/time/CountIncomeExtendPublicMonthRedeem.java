package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;


/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeExtendPublicMonthRedeem
 * 模块名称：超期预约赎回，超过1个月公益收益赎回对客户收益影响处理
 * @Description: 内容摘要：超1个月公益收益赎回或预约赎回对客户收益影响处理
 * @author jinzhm
 * @date 2017年7月7日
 * @version V1.0
 * history:
 * 1、2017年7月7日 jinzhm 创建文件
 */
public class CountIncomeExtendPublicMonthRedeem extends CountIncomeExtendMonthRedeem
{
    /**
     * @Title: getRedeemIncome
     * @Description: 跨月超期赎回（超过到期日期+1个月）时获得的赎回收益
     * @param data 赎回时机数据对象
     * @return 赎回收益
     * @author: jinzhm
     * @time:2017年7月7日 下午1:23:51
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeExtendMonthRedeem#getRedeemIncome(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2017年7月7日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getRedeemIncome(CountIncomeRedeemTimeData data)
    {
        // 已付收益
        BigDecimal paidIncome = data.getRedeemDetail().getPaid_income() == null ? BigDecimal.ZERO
                                                                               : data.getRedeemDetail()
                                                                                     .getPaid_income();
        // 活期利率
        BigDecimal currentIncome = data.getRedeemDetail().getCurrent_income() == null ? BigDecimal.ZERO
                                                                                     : data.getRedeemDetail()
                                                                                           .getCurrent_income();
        return currentIncome.subtract(paidIncome);
    }
}
