package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchRuleHandler
 * 模块名称：债权匹配规则实现类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatchRule1 extends CreditMatchRuleAbstract
{

    /**
     * 至少匹配抵押包个数分割值
     */
    private static final int PRODUCT_ACCOUNT_PARTITION_VALUE = 200000;

    /**
     * 大于等于分割值至少匹配抵押包数
     */
    private static final int PRODUCT_ACCOUNT_PARTITION_SPLIT_BIG_VALUE = 10;

    /**
     * 小于分割值至少匹配抵押包数
     */
    private static final int PRODUCT_ACCOUNT_PARTITION_SPLIT_SMALL_VALUE = 5;

    /**
     * @Title: getMaxMatchProductAccount
     * @Description: 获得最大拆分后可匹配的投资金额
     * 大于等于20万必须匹配至少10个抵押包；
     * 小于20万必须匹配至少5个抵押包；
     * 小于5万最大拆分后可匹配金额 = 投资金额-1万
     * 等于1万最大拆分后可匹配金额 = 1万
     * @param productAccount
     * @return 
     * @author: jinzhm
     * @time:2017年2月12日 下午2:06:45
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    @Override
    protected BigDecimal getMaxMatchProductAccount(BigDecimal productAccount)
    {
        // 判断投资金额和匹配抵押包个数分割值大小
        // 投资金额大于等于分割值，按照大于时拆分个数计算最大可匹配金额
        if (productAccount.compareTo(new BigDecimal(PRODUCT_ACCOUNT_PARTITION_VALUE)) >= 0)
        {
            return productAccount.divide(new BigDecimal(PRODUCT_ACCOUNT_PARTITION_SPLIT_BIG_VALUE).multiply(new BigDecimal(
                                                                                                                           "10000")),
                                         0, RoundingMode.DOWN).multiply(new BigDecimal("10000"));
        }
        // 投资金额小于分割值，按照小于时拆分个数计算最大可匹配金额
        else
        {
            // 如果投资金额小于最小可拆分个数*1万的金额时，按1万匹配（小于5万直接按1万匹配）
            if (productAccount.compareTo(new BigDecimal(PRODUCT_ACCOUNT_PARTITION_SPLIT_SMALL_VALUE).multiply(new BigDecimal(
                                                                                                                             "10000"))) < 0)
            {
                // 最大可匹配金额 = 1万
                return new BigDecimal("10000");
            }
            // 投资金额大于或等于可拆分个数*1万时
            else
            {
                return productAccount.divide(new BigDecimal(PRODUCT_ACCOUNT_PARTITION_SPLIT_SMALL_VALUE).multiply(new BigDecimal(
                                                                                                                                 "10000")),
                                             0, RoundingMode.DOWN).multiply(new BigDecimal("10000"));
            }
        }
    }
}
