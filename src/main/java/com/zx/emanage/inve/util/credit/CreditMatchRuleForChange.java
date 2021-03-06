package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchRuleForChange
 * 模块名称：替换债权包规则处理类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月15日
 * @version V1.0
 * history:
 * 1、2017年2月15日 jinzhm 创建文件
 */
public class CreditMatchRuleForChange extends CreditMatchRuleAbstract
{

    /**
     * @Title: executeMatchRule
     * @Description: 重写执行规则匹配债权方法
     *      替换债权时可能要匹配多个债权包，因此需要循环匹配
     * @param matchData
     * @return 
     * @author: jinzhm
     * @time:2017年2月15日 上午10:46:45
     * @see com.zx.emanage.inve.util.credit.CreditMatchRuleAbstract#executeMatchRule(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
    */
    @Override
    protected boolean executeMatchRule(final CreditMatchData matchData)
    {
        // 要更换债权的金额
        final BigDecimal productAccount = matchData.getProductAccount();
        // 剩余要匹配的投资金额
        BigDecimal keepProductAccount = productAccount;
        // 按正常参数设置走一遍常规的所有规则来匹配债权
        keepProductAccount = matchCreditByRule(matchData, productAccount, matchData.getCoeff());

        // 如果没有完全匹配
        if (keepProductAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            if (matchData.isMustMatch())
            {
                // 调用扩大系数重跑规则
                boolean returnFlag = matchCreditByCoeffRule(matchData, productAccount, matchData.getCoeff());
                if (!returnFlag)
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            CreditUtils.logCreditMatchError(matchData.getTransaId(), matchData.getProtocolId(),
                                                            matchData.getProductAccount(), productAccount,
                                                            CreditMatch.FLOW_CHANGE_CREDIT_PACKAGE);
                        }
                    }).start();
                    return false;
                }
                // 匹配成功，直接返回
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        // 清空后重新保存匹配到债权的集合
        matchData.getMatchedCreditDataList().addAll(this.matchedCreditDataList);
        return true;
    }

    /**
     * @Title: splitCreditDataByHoursInit
     * @Description: 初始化债权拆分信息。根据规则拆分集合，同时计算所有债权本组可匹配的金额
     * @param matchData 债权匹配数据对象
     * @param coeff 系数
     * @author: jinzhm
     * @time:2017年4月14日 下午5:15:27
     * @see com.zx.emanage.inve.util.credit.CreditMatchRuleAbstract#splitCreditDataByHoursInit(com.zx.emanage.inve.util.credit.CreditMatchData, java.math.BigDecimal)
     * history:
     * 1、2017年4月14日 jinzhm 创建方法
    */
    @Override
    protected void splitCreditDataByHoursInit(CreditMatchData matchData, BigDecimal coeff)
    {
        // 循环抵押包信息，根据规则拆分
        for (CreditData creditData : matchData.getCreditDataList())
        {
            // 判断是否已经匹配过（在更换债权包时时需要）
            WmsInveTransaCrepkg transaCrepkg = CreditUtils.isMatchedCredit(creditData.getId(),
                                                                           matchData.getMatchedTransaCrepkg());

            // 初始化是否已匹配和已匹配金额
            creditData.setMatched(false);
            creditData.setMatchedProductAccount(BigDecimal.ZERO);

            // 不等于null说明匹配过
            if (transaCrepkg != null)
            {
                creditData.setMatched(true);
                creditData.setTransaCrepkgId(transaCrepkg.getWms_inve_transa_crepkg_id());
                this.matchedCreditDataList.add(creditData);
            }

            // 原始投资金额乘系数获得最大可匹配金额
            BigDecimal maxProductAccount = creditData.getOrigProductAccount().multiply(coeff)
                                                     .divide(new BigDecimal("10000"), 0, RoundingMode.UP)
                                                     .multiply(new BigDecimal("10000"));

            creditData.setMaxProductAccount(maxProductAccount);

            // 计算设置本组剩余可匹配金额
            creditData.setGroupKeepProductAccount(getGroupMinKeepProductAccount(creditData));

            // 获得匹配时间
            Timestamp lastMatchTime = creditData.getAclMatchDate();

            // 根据上次匹配时间大于或小于72小时来拆分集合
            if (lastMatchTime == null
                || (nowTime.getTime() - lastMatchTime.getTime()) / (60 * 60 * 1000) > SPLIT_CREDIT_LIST_HOURS)
            {
                creditDataOutList.add(creditData);
            }
            else
            {
                creditDataInList.add(creditData);
            }
        }
    }

    /**
     * @Title: splitCreditDataByHours
     * @Description: 根据规则拆分集合，同时计算所有债权本组可匹配的金额
     * @param creditDataList 所有债权信息集合
     * @param coeff 参数设定中的系数
     * @author: jinzhm
     * @time:2017年2月11日 下午3:42:50
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    protected void splitCreditDataByHours(CreditMatchData matchData, BigDecimal coeff)
    {
        // 循环抵押包信息，根据规则拆分
        for (CreditData creditData : matchData.getCreditDataList())
        {
            // 判断是否已经匹配过（在更换债权包时时需要）
            WmsInveTransaCrepkg transaCrepkg = CreditUtils.isMatchedCredit(creditData.getId(),
                                                                           matchData.getMatchedTransaCrepkg());

            // 初始化是否已匹配和已匹配金额
            creditData.setMatched(false);
            creditData.setMatchedProductAccount(BigDecimal.ZERO);

            // 不等于null说明匹配过
            if (transaCrepkg != null)
            {
                creditData.setMatched(true);
                // 抵押包在本组已使用次数需要减1，因为之前查出的次数包含了此单据匹配的次数。
                creditData.setGroupSplitCount(creditData.getGroupSplitCount() - 1);
                creditData.setTransaCrepkgId(transaCrepkg.getWms_inve_transa_crepkg_id());
                this.matchedCreditDataList.add(creditData);
            }

            // 原始投资金额乘系数获得最大可匹配金额
            BigDecimal maxProductAccount = creditData.getOrigProductAccount().multiply(coeff)
                                                     .divide(new BigDecimal("10000"), 0, RoundingMode.UP)
                                                     .multiply(new BigDecimal("10000"));

            creditData.setMaxProductAccount(maxProductAccount);

            // 计算设置本组剩余可匹配金额
            creditData.setGroupKeepProductAccount(getGroupMinKeepProductAccount(creditData));

            // 获得匹配时间
            Timestamp lastMatchTime = creditData.getAclMatchDate();

            // 根据上次匹配时间大于或小于72小时来拆分集合
            if (lastMatchTime == null
                || (nowTime.getTime() - lastMatchTime.getTime()) / (60 * 60 * 1000) > SPLIT_CREDIT_LIST_HOURS)
            {
                creditDataOutList.add(creditData);
            }
            else
            {
                creditDataInList.add(creditData);
            }
        }
    }

    /**
     * @Title: getMaxMatchProductAccount
     * @Description: 获得最大拆分后可匹配的投资金额
     * @param productAccount
     * @return 
     * @author: jinzhm
     * @time:2017年2月15日 上午10:43:21
     * @see com.zx.emanage.inve.util.credit.CreditMatchRuleAbstract#getMaxMatchProductAccount(java.math.BigDecimal)
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getMaxMatchProductAccount(BigDecimal productAccount)
    {
        return productAccount;
    }

}
