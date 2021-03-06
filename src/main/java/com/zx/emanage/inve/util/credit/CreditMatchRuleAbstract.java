package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchRuleAbstract
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月15日
 * @version V1.0
 * history:
 * 1、2017年2月15日 jinzhm 创建文件
 */
public abstract class CreditMatchRuleAbstract implements CreditMatchRuleInterface
{

    // 当前时间
    protected Timestamp nowTime = new Timestamp(System.currentTimeMillis());

    /**
     * 拆分抵押包集合限制时间
     */
    protected static final int SPLIT_CREDIT_LIST_HOURS = 72;

    /**
     * 拆分外集合（优先匹配）
     */
    protected List<CreditData> creditDataOutList = new ArrayList<CreditData>();

    /**
     * 拆分内集合（拆分外集合不够时使用拆分内匹配）
     */
    protected List<CreditData> creditDataInList = new ArrayList<CreditData>();

    /**
     * 当前匹配到的债权集合
     */
    protected List<CreditData> matchedCreditDataList = new ArrayList<CreditData>();
    
    /**
     * 上单地区编号,沈阳为0024
     */
    protected String transaAreaCode = "";

    /**
     * 当前使用的债权的地区编号,沈阳为0024;多个地区英文逗号隔开
     */
    protected String usedCreditAreaCode = "";
    
    /**
     * @Title: match
     * @Description: 匹配债权
     * @param matchData 债权匹配数据对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月11日 下午3:24:18
     * @see com.zx.emanage.inve.util.credit.CreditMatchRuleInterface#match(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
    */
    @Override
    public boolean match(CreditMatchData matchData)
    {
        // 设置上单地区编号
        this.transaAreaCode = matchData.getTransaAreaCode();

        // 根据匹配时间拆分抵押包集合同时计算每个抵押包本组剩余可匹配金额
        splitCreditDataByHours(matchData, matchData.getCoeff());
        // 执行匹配规则
        return executeMatchRule(matchData);
    }

    /**
     * @Title: executeMatchRule
     * @Description: 按规则执行债权匹配
     * @param matchData 债权匹配数据对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月13日 上午11:14:30
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected boolean executeMatchRule(CreditMatchData matchData)
    {
        // 按正常参数设置走一遍常规的所有规则来匹配债权
        BigDecimal productAccount = matchCreditByRule(matchData, matchData.getProductAccount(), matchData.getCoeff());

        // 如果没有完全匹配
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            if (matchData.isMustMatch())
            {
                // 调用扩大系数重跑规则
                boolean returnFlag = matchCreditByCoeffRule(matchData, matchData.getProductAccount(),
                                                            matchData.getCoeff());
                if (!returnFlag)
                {
                    CreditUtils.logCreditMatchError(matchData.getTransaId(), matchData.getProtocolId(),
                                                    matchData.getProductAccount(), productAccount,
                                                    matchData.getFlowName());
                    return false;
                }
            }
            else
            {
                CreditUtils.logCreditMatchError(matchData.getTransaId(), matchData.getProtocolId(),
                                                matchData.getProductAccount(), productAccount,
                                                matchData.getFlowName());
                return false;
            }
        }
        else
        {
            // 保存匹配到债权的集合
            matchData.getMatchedCreditDataList().addAll(this.matchedCreditDataList);
        }
        return true;
    }

    /**
     * @Title: getGroupMinKeepProductAccount
     * @Description: 获得本组剩余可匹配金额
     * 计算a：
     *      一个团队可匹配一个抵押包不能超过抵押包的原始金额
     * 计算b：
     *      一个租可匹配一个抵押包不能超过原始金额*系数的金额
     * 计算c：
     *      每个抵押包在每组至少要分成3个债权
     * @param creditData
     * @return 返回计算3个数中最小的数
     * @author: jinzhm
     * @time:2017年2月12日 下午1:13:57
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    protected BigDecimal getGroupMinKeepProductAccount(CreditData creditData)
    {
        // a = 原始金额 - 本组已匹配金额
        BigDecimal a = creditData.getOrigProductAccount().subtract(creditData.getGroupUseProductAccount());
        // b = 最大可匹配金额 - 当前总已匹配金额
        BigDecimal b = creditData.getMaxProductAccount().subtract(creditData.getTotalUseProductAccount());
        // c = 原始投资金额 / 3
        BigDecimal c = BigDecimal.ZERO;
        // 如果原始金额小于3万就按一万分
        if (creditData.getOrigProductAccount().compareTo(new BigDecimal("30000")) <= 0)
        {
            c = new BigDecimal("10000");
        }
        else
        {
            c = creditData.getOrigProductAccount().divide(new BigDecimal("30000"), 0, RoundingMode.DOWN)
                          .multiply(new BigDecimal("10000"));
        }
        return a.min(b).min(c);
    }

    /**
     * @Title: getGroupMinKeepProductAccountWithGroupKeep
     * @Description: 按本组可匹配排序匹配时，抛弃每个抵押包必须分成平均分成3个债权的规则
     * 计算a：
     *      一个团队可匹配一个抵押包不能超过抵押包的原始金额
     * 计算b：
     *      一个租可匹配一个抵押包不能超过原始金额*系数的金额
     * @param creditData
     * @return 返回计算2个数中最小的数
     * @author: jinzhm
     * @time:2017年2月23日 下午5:11:47
     * history:
     * 1、2017年2月23日 jinzhm 创建方法
     */
    protected BigDecimal getGroupMinKeepProductAccountWithGroupKeep(CreditData creditData, BigDecimal coeff)
    {
        // a = 原始金额 - 本组已匹配金额
        BigDecimal a = creditData.getOrigProductAccount().subtract(creditData.getGroupUseProductAccount());
        // b = 最大可匹配金额 - 当前总已匹配金额
        BigDecimal b = creditData.getOrigProductAccount().multiply(coeff)
                                 .divide(new BigDecimal("10000"), 0, RoundingMode.UP).multiply(new BigDecimal("10000"))
                                 .subtract(creditData.getTotalUseProductAccount());
        return a.min(b);
    }

    /**
     * @Title: splitCreditDataWithMatchGroupKeep
     * @Description: 按本组剩余可匹配金额优先匹配做拆分及本组剩余计算（抛弃每个抵押包至少分成3个债权规则）
     * @param matchData 债权匹配数据对象
     * @param coeff 参数设定中的系数
     * @author: jinzhm
     * @time:2017年2月23日 下午5:10:03
     * history:
     * 1、2017年2月23日 jinzhm 创建方法
     */
    protected void splitCreditDataWithMatchGroupKeep(CreditMatchData matchData, BigDecimal coeff)
    {
        // 循环抵押包信息，根据规则拆分
        for (CreditData creditData : matchData.getCreditDataList())
        {
            // 计算设置本组剩余可匹配金额
            creditData.setGroupKeepProductAccount(getGroupMinKeepProductAccountWithGroupKeep(creditData, coeff));
        }
    }

    /**
     * @Title: splitCreditDataByHoursInit
     * @Description: 初始化债权拆分信息。根据规则拆分集合，同时计算所有债权本组可匹配的金额
     * @param matchData 债权匹配数据对象
     * @param coeff 系数
     * @author: jinzhm
     * @time:2017年4月14日 下午5:01:40
     * history:
     * 1、2017年4月14日 jinzhm 创建方法
     */
    protected void splitCreditDataByHoursInit(CreditMatchData matchData, BigDecimal coeff)
    {
        splitCreditDataByHours(matchData, coeff);
    }

    /**
     * @Title: splitCreditDataByHours
     * @Description: 根据规则拆分集合，同时计算所有债权本组可匹配的金额
     * @param matchData 债权匹配数据对象
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
            // 初始化是否已匹配和已匹配金额
            creditData.setMatched(false);
            creditData.setMatchedProductAccount(BigDecimal.ZERO);

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
     * @Title: matchCreditByNormalRule
     * @Description: 按正常规则匹配债权
     *      按合同到期日期排序后匹配规则
     * @param matchData 债权匹配数据对象
     * @param productAccount 剩余要匹配债权的投资金额
     * @param coeff 系数
     * @return 剩余要匹配的债权的投资金额
     * @author: jinzhm
     * @time:2017年2月15日 下午3:51:06
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByNormalRule(CreditMatchData matchData, BigDecimal productAccount, BigDecimal coeff)
    {
        // 先排序抵押包外集合
        // sortCreditDataList(creditDataOutList,
        // matchData.getProtocolEndDate());
        // 先匹配拆分外集合
        productAccount = matchCreditByRule(matchData, creditDataOutList, productAccount, coeff);

        // 匹配拆分外集合后还有剩余要匹配的投资金额，匹配拆分内集合
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            // 先排序抵押包外集合
            // sortCreditDataList(creditDataInList,
            // matchData.getProtocolEndDate());
            // 匹配拆分内集合
            productAccount = matchCreditByRule(matchData, creditDataInList, productAccount, coeff);
        }
        return productAccount;
    }

    /**
     * @Title: matchCreditByGroupKeepProductAccount
     * @Description: 按本组剩余可匹配金额排序匹配
     *      有多少用多少
     * @param matchData 债权匹配数据对象
     * @param productAccount 剩余要匹配债权的投资金额
     * @return 剩余要匹配的债权的投资金额
     * @author: jinzhm
     * @time:2017年2月20日 上午10:35:51
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByGroupKeepProductAccount(CreditMatchData matchData, BigDecimal productAccount)
    {
        // 如果拆分外和内集合匹配之后，还是有剩余投资金额要匹配的话。
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            sortCreditDataList(creditDataOutList);
            productAccount = matchCreditKeepByRule(matchData, creditDataOutList, productAccount);
        }

        // 如果使用拆分外的本组剩余可匹配债权包金额去匹配之后还有要匹配的投资金额，使用拆分内集合进行匹配
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            sortCreditDataList(creditDataInList);
            productAccount = matchCreditKeepByRule(matchData, creditDataInList, productAccount);
        }

        return productAccount;
    }

    /**
     * @Title: matchCreditByGroupKeepProductAccountInAlreadyMatched
     * @Description: 到当前已匹配到的集合中匹配债权
     * @param matchData 债权匹配数据对象
     * @param productAccount 剩余要匹配债权的投资金额
     * @return 剩余要匹配的债权的投资金额
     * @author: jinzhm
     * @time:2017年2月20日 上午10:38:59
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByGroupKeepProductAccountInAlreadyMatched(CreditMatchData matchData,
                                                                              BigDecimal productAccount)
    {
        // 如果还是没有匹配全，到当前已匹配到的集合中继续匹配
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            productAccount = matchCreditByRuleInMatched(matchData, creditDataOutList, productAccount);
        }
        // 如果还是没有匹配全，到当前已匹配到的集合中继续匹配
        if (productAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            productAccount = matchCreditByRuleInMatched(matchData, creditDataInList, productAccount);
        }

        return productAccount;
    }

    /**
     * @Title: matchCreditByRule
     * @Description: 按常规规则流程匹配一次债权
     * @param matchData 债权匹配对象
     * @param productAccount 要匹配的投资金额
     * @param coeff 系数（参数设定，可能会传入大于设定的系数）
     * @return 剩余要匹配的投资金额
     * @author: jinzhm
     * @time:2017年2月15日 上午10:04:37
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByRule(CreditMatchData matchData, BigDecimal productAccount, BigDecimal coeff)
    {
        // 调用正常规则匹配债权
        productAccount = matchCreditByNormalRule(matchData, productAccount, coeff);

        // 按本组剩余可匹配金额规则匹配前重新拆分同时重新计算本组可匹配金额（抛弃每个抵押包至少平均拆分3个债权规则）
        splitCreditDataWithMatchGroupKeep(matchData, coeff);

        // 调用本组剩余可用金额匹配债权
        productAccount = matchCreditByGroupKeepProductAccount(matchData, productAccount);

        // 按本组剩余可匹配金额匹配还是没有匹配到的话到当前已匹配的债权中匹配
        productAccount = matchCreditByGroupKeepProductAccountInAlreadyMatched(matchData, productAccount);

        return productAccount;
    }

    /**
     * @Title: matchCreditByRuleInMatched
     * @Description: 在当前已匹配到的集合中继续匹配债权，按剩余可匹配规则匹配
     * @param matchData 债权匹配数据对象
     * @param creditDataList 债权集合（72外或72内）
     * @param productAccount 剩余要匹配的投资金额
     * @return 剩余要匹配的投资金额
     * @author: jinzhm
     * @time:2017年2月15日 下午3:44:37
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByRuleInMatched(CreditMatchData matchData, List<CreditData> creditDataList,
                                                    BigDecimal productAccount)
    {
        // 循环当前已经匹配到的债权集合
        for (CreditData creditData : this.matchedCreditDataList)
        {
            // 如果剩余要匹配的投资金额是0，直接跳出循环
            if (productAccount.compareTo(BigDecimal.ZERO) == 0)
            {
                break;
            }
            // 保证该债权信息是72小外集合还是72内集合
            if (creditDataList.contains(creditData))
            {
                BigDecimal keepProductAccount = matchCreditByRuleInMatched(matchData, creditData, productAccount);

                // 如果本组剩余可匹配债权包金额小于等于0直接跳出当前循环
                if (keepProductAccount.compareTo(BigDecimal.ZERO) <= 0)
                {
                    continue;
                }

                // 更新当前已匹配金额
                creditData.setMatchedProductAccount(creditData.getMatchedProductAccount().add(keepProductAccount));
                // 更新剩余要匹配投资金额
                productAccount = productAccount.subtract(keepProductAccount);
            }
        }
        return productAccount;
    }

    /**
     * @Title: matchCreditByRuleInMatched
     * @Description: 在当前已匹配到的债权内进行债权匹配
     *      正常规则没有匹配全，本组剩余可匹配中也没有匹配全的话在当前已匹配到的债权内继续匹配
     * @param matchData 债权匹配对象
     * @param creditData 债权信息
     * @param productAccount 剩余要匹配债权的投资金额
     * @return 当前匹配到的金额
     * @author: jinzhm
     * @time:2017年2月27日 上午10:40:55
     * history:
     * 1、2017年2月27日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByRuleInMatched(CreditMatchData matchData, CreditData creditData,
                                                    BigDecimal productAccount)
    {
        // 本组实际剩余可匹配债权包金额
        BigDecimal groupKeepProductAccount = creditData.getGroupKeepProductAccount()
                                                       .subtract(creditData.getMatchedProductAccount());
        // 如果剩余可匹配金额是0的话直接返回0
        if (groupKeepProductAccount.compareTo(BigDecimal.ZERO) == 0)
        {
            return BigDecimal.ZERO;
        }

        // 剩余可匹配债权包金额
        BigDecimal keepProductAccount = BigDecimal.ZERO;
        // 该抵押包在本组已经匹配的次数
        int groupSplitCount = creditData.getGroupSplitCount();
        switch (groupSplitCount)
        {
            case 0:
                // 当本组已匹配该抵押包次数是0的时候，需要给本组剩余可匹配债权包金额保留2万，但是剩余可匹配金额小于或等于两万时，只能用1万
                keepProductAccount = groupKeepProductAccount.subtract(new BigDecimal("20000"));
                break;
            case 1:
                // 当本组已匹配该抵押包次数是1的时候，需要给本组剩余可匹配债权包金额保留2万，但是剩余可匹配金额小于两万时（等于一万），直接用1万
                keepProductAccount = groupKeepProductAccount.subtract(new BigDecimal("10000"));
                break;
            default:
                // 当本组已匹配该抵押包次数是2或更大的时候，直接可以全部使用
                keepProductAccount = groupKeepProductAccount;
                break;
        }

        // 使用投资金额+本组已使用+正常匹配已使用不能超过原始金额
        // if
        // (keepProductAccount.add(creditData.getGroupUseProductAccount()).add(creditData.getMatchedProductAccount())
        // .compareTo(creditData.getOrigProductAccount()) > 0)
        // {
        // keepProductAccount =
        // keepProductAccount.subtract(keepProductAccount.add(creditData.getGroupUseProductAccount())
        // .add(creditData.getMatchedProductAccount())
        // .subtract(creditData.getOrigProductAccount()));
        // }

        // 如果当前已匹配的债权数小于3，那在当前已匹配的债权中如何匹配，匹配数都不会超过3
        if (this.matchedCreditDataList.size() < 3)
        {
            return BigDecimal.ZERO;
        }

        // 如果剩余可匹配债权包金额大于等于需要匹配的投资金额
        if (keepProductAccount.compareTo(productAccount) >= 0)
        {
            keepProductAccount = productAccount;
        }

        return keepProductAccount;
    }

    /**
     * @Title: matchCreditKeepByRule
     * @Description: 按本组剩余债权包可匹配金额匹配剩余投资金额
     *      可以匹配多少就匹配多少知道全部匹配
     * @param matchData 债权包匹配数据对象
     * @param creditDataList 用本组剩余可匹配债权包金额排序的债权包集合（本组剩余可匹配减去了当前已匹配金额）
     * @param productAccount 剩余要匹配的投资金额
     * @return 剩余要匹配的投资金额
     * @author: jinzhm
     * @time:2017年2月13日 上午10:41:26
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditKeepByRule(CreditMatchData matchData, List<CreditData> creditDataList,
                                               BigDecimal productAccount)
    {
        // 循环用本组剩余可匹配债权包金额排序的集合
        for (CreditData creditData : creditDataList)
        {
            // 如果需要匹配债权的投资金额等于0时，说明已经全部匹配，直接跳出循环
            if (productAccount.compareTo(BigDecimal.ZERO) == 0)
            {
                break;
            }

            // 剩余可匹配债权包金额
            BigDecimal keepProductAccount = matchCreditKeepByRule(matchData, creditData, productAccount);

            // 如果本组剩余可匹配债权包金额小于等于0直接跳出当前循环
            if (keepProductAccount.compareTo(BigDecimal.ZERO) <= 0)
            {
                continue;
            }

            // 如果该抵押包之前已经匹配过
            if (creditData.isMatched())
            {
                // 跳过当前循环
                continue;
            }

            // 设置是否匹配过
            creditData.setMatched(true);
            // 更新当前已匹配金额
            creditData.setMatchedProductAccount(creditData.getMatchedProductAccount().add(keepProductAccount));
            // 保存到当前已匹配债权集合中
            this.matchedCreditDataList.add(creditData);

            // 更新剩余需要匹配的投资金额
            productAccount = productAccount.subtract(keepProductAccount);
        }
        return productAccount;
    }

    /**
     * @Title: matchCreditKeepByRule
     * @Description: 按本组剩余债权包可匹配金额匹配剩余投资金额
     *      可以匹配多少就匹配多少直到全部匹配
     * @param matchData 债权匹配数据对象
     * @param creditData 债权信息
     * @param productAccount 要匹配剩余投资金额
     * @return 可匹配的金额
     * @author: jinzhm
     * @time:2017年2月15日 下午3:31:00
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditKeepByRule(CreditMatchData matchData, CreditData creditData,
                                               BigDecimal productAccount)
    {
        // 本组实际剩余可匹配债权包金额
        BigDecimal groupKeepProductAccount = creditData.getGroupKeepProductAccount()
                                                       .subtract(creditData.getMatchedProductAccount());
        // 如果剩余可匹配金额是0的话直接返回0
        if (groupKeepProductAccount.compareTo(BigDecimal.ZERO) == 0)
        {
            return BigDecimal.ZERO;
        }

        // 剩余可匹配债权包金额
        BigDecimal keepProductAccount = BigDecimal.ZERO;
        // 该抵押包在本组已经匹配的次数
        int groupSplitCount = creditData.getGroupSplitCount();
        switch (groupSplitCount)
        {
            case 0:
                // 当本组已匹配该抵押包次数是0的时候，需要给本组剩余可匹配债权包金额保留2万，但是剩余可匹配金额小于或等于两万时，只能用1万
                keepProductAccount = groupKeepProductAccount.compareTo(new BigDecimal("20000")) > 0 ? groupKeepProductAccount.subtract(new BigDecimal(
                                                                                                                                                      "20000"))
                                                                                                   : new BigDecimal(
                                                                                                                    "10000");
                break;
            case 1:
                // 当本组已匹配该抵押包次数是1的时候，需要给本组剩余可匹配债权包金额保留2万，但是剩余可匹配金额小于两万时（等于一万），直接用1万
                keepProductAccount = groupKeepProductAccount.compareTo(new BigDecimal("20000")) >= 0 ? groupKeepProductAccount.subtract(new BigDecimal(
                                                                                                                                                       "10000"))
                                                                                                    : new BigDecimal(
                                                                                                                     "10000");
                break;
            default:
                // 当本组已匹配该抵押包次数是2或更大的时候，直接可以全部使用
                keepProductAccount = groupKeepProductAccount;
                break;
        }

        // 债权包至少要匹配三个债权
        switch (this.matchedCreditDataList.size())
        {
            case 1:
                // 当前已匹配到1个时需要再留一万给下一个匹配
                keepProductAccount.subtract(new BigDecimal(10000));
                break;
            case 0:
                // 当前已匹配到1个时需要再留一万给下一个匹配
                keepProductAccount.subtract(new BigDecimal(20000));
                break;
            default:
                break;
        }

        // 使用投资金额+本组已使用+正常匹配已使用不能超过原始金额
        // if
        // (keepProductAccount.add(creditData.getGroupUseProductAccount()).add(creditData.getMatchedProductAccount())
        // .compareTo(creditData.getOrigProductAccount()) > 0)
        // {
        // keepProductAccount =
        // keepProductAccount.subtract(keepProductAccount.add(creditData.getGroupUseProductAccount())
        // .add(creditData.getMatchedProductAccount())
        // .subtract(creditData.getOrigProductAccount()));
        // }

        // 如果剩余可匹配债权包金额大于等于需要匹配的投资金额
        if (keepProductAccount.compareTo(productAccount) >= 0)
        {
            keepProductAccount = productAccount;
        }

        return keepProductAccount;
    }

    /**
     * @Title: matchCreditByCoeffRule
     * @Description: 扩大系数匹配债权规则
     * @param matchData 债权匹配对象
     * @param productAccount 要匹配的投资金额
     * @param coeff 系数
     * @return 债权匹配成功标志
     * @author: jinzhm
     * @time:2017年2月15日 上午10:06:39
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected boolean matchCreditByCoeffRule(CreditMatchData matchData, BigDecimal productAccount, BigDecimal coeff)
    {
        coeff = coeff.add(new BigDecimal("0.5"));
        // 如果扩大的系数超过了最大可扩大债权匹配系数
        if (coeff.compareTo(matchData.getMaxCoeff()) > 0)
        {
            // 如果扩大的系数正好等于可扩大系数0.5的话，说明已经超过，直接返回没有匹配到的标志
            if (coeff.compareTo(matchData.getMaxCoeff().add(new BigDecimal("0.5"))) == 0)
            {
                return false;
            }
            // 如果扩大的系数不是正好等于可扩大系数0.5的话，用最大可扩大系数再匹配一次债权
            else
            {
                coeff = matchData.getMaxCoeff();
            }
        }
        // 初始化所有匹配数据
        initData(matchData, coeff);
        // 剩余要匹配的投资金额
        BigDecimal keepProductAccount = productAccount;
        // 按到期时间排序正常匹配
        keepProductAccount = matchCreditByNormalRule(matchData, keepProductAccount, coeff);

        // 按本组剩余可匹配金额规则匹配前重新拆分同时重新计算本组可匹配金额（抛弃每个抵押包至少平均拆分3个债权规则）
        splitCreditDataWithMatchGroupKeep(matchData, coeff);
        // 按本组剩余可匹配金额匹配
        keepProductAccount = matchCreditByGroupKeepProductAccount(matchData, keepProductAccount);
        // 在已匹配的集合中匹配
        keepProductAccount = matchCreditByGroupKeepProductAccountInAlreadyMatched(matchData, keepProductAccount);

        // 如果扩大系数后还是没有完全匹配，继续扩大系数重跑规则
        if (keepProductAccount.compareTo(BigDecimal.ZERO) > 0)
        {
            return matchCreditByCoeffRule(matchData, productAccount, coeff);
        }
        // 保存匹配到债权的集合
        matchData.getMatchedCreditDataList().addAll(this.matchedCreditDataList);
        return true;
    }

    /**
     * @Title: getMaxMatchProductAccount
     * @Description: 获得最大拆分后可匹配的投资金额
     * @param productAccount
     * @return 
     * @author: jinzhm
     * @time:2017年2月12日 下午2:06:45
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    protected abstract BigDecimal getMaxMatchProductAccount(BigDecimal productAccount);

    /**
     * @Title: findCreditByRule
     * @Description: 金额重大变小，倍数重小增大来匹配债权包
     * @param matchData 债权匹配数据对象
     * @param creditDataList 抵押包集合
     * @param productAccount 要匹配的投资金额
     * @param coeff 系数
     * @return 匹配后剩余要匹配的投资金额
     * @author: jinzhm
     * @time:2017年2月12日 下午4:37:17
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    protected BigDecimal matchCreditByRule(CreditMatchData matchData, List<CreditData> creditDataList,
                                           BigDecimal productAccount, BigDecimal coeff)
    {

        // 最大匹配剩余金额 = 最小可匹配投资金额（5万）
        for (BigDecimal matchMinProductAccount = getMaxMatchProductAccount(productAccount); matchMinProductAccount.compareTo(BigDecimal.ZERO) > 0; matchMinProductAccount = matchMinProductAccount.subtract(new BigDecimal(
                                                                                                                                                                                                                           10000)))
        {
            // 可匹配原始金额 = 最大可匹配金额*3（每个抵押包至少分成3个债权包）（15万）
            BigDecimal matchOrigProductAccount = matchMinProductAccount.multiply(new BigDecimal("3"));

            // 如果要匹配的剩余投资金额小于最小可匹配投资金额的话，本次循环可以结束，直接下次循环
            if (productAccount.compareTo(matchMinProductAccount) < 0)
            {
                continue;
            }

            // 系数从1开始0.5,0.5的往上加到参数设定中的系数为止
            for (BigDecimal ratio = new BigDecimal("1"); ratio.compareTo(coeff) <= 0; ratio = ratio.compareTo(coeff) < 0
                                                                                              && ratio.add(new BigDecimal(
                                                                                                                          "0.5"))
                                                                                                      .compareTo(coeff) > 0 ? ratio = coeff
                                                                                                                           : ratio.add(new BigDecimal(
                                                                                                                                                      "0.5")))
            {
                // 匹配债权，获得匹配到的抵押包信息
                List<CreditData> matchedCreditDataList = findCreditByRule(creditDataList, matchMinProductAccount,
                                                                          matchOrigProductAccount, ratio,
                                                                          productAccount);

                // 给当前条件匹配到的债权按照到期时间差值进行排序，如果差值相同按照债权地区进行排序
                sortCreditDataList(matchedCreditDataList, matchData.getProtocolEndDate());

                // 循环符合金额及倍数条件的抵押包依次取匹配
                for (int i = 0; i < matchedCreditDataList.size(); i++)
                {
                    CreditData creditData = matchedCreditDataList.get(i);
                    // 在规则1中加入抵押包在每组必须拆分成3个债权
                    BigDecimal matchedProductAccount = BigDecimal.ZERO;

                    // 如果当前匹配的金额大于1万的话需要判断抵押包在本组的匹配次数，如果等于1万，直接使用
                    if (matchMinProductAccount.compareTo(new BigDecimal("10000")) > 0)
                    {
                        // 实际本组可用金额
                        BigDecimal actGroupKeepProductAccount = BigDecimal.ZERO;
                        switch (creditData.getGroupSplitCount())
                        {
                            case 0:
                                // 抵押包在本组匹配次数为0的时候，本次本组实际可使用金额=本组剩余可使用金额大于2W的时候，需要给下次匹配留2w，等于2w留1w，不可能小于2w
                                actGroupKeepProductAccount = creditData.getGroupKeepProductAccount()
                                                                       .compareTo(new BigDecimal("20000")) > 0 ? creditData.getGroupKeepProductAccount()
                                                                                                                           .subtract(new BigDecimal(
                                                                                                                                                    "20000"))
                                                                                                              : creditData.getGroupKeepProductAccount()
                                                                                                                          .subtract(new BigDecimal(
                                                                                                                                                   "10000"));
                                // 在本次本组实际可使用金额和本次匹配金额中更小的值为本次实际匹配金额
                                matchedProductAccount = matchMinProductAccount.min(actGroupKeepProductAccount);
                                break;
                            case 1:
                                // 抵押包在本组匹配次数为1的时候，本次本组实际可使用金额=本组剩余可使用金额大于等于2w的时候，需要给下次匹配留1w，小于2w都是用
                                actGroupKeepProductAccount = creditData.getGroupKeepProductAccount()
                                                                       .compareTo(new BigDecimal("20000")) >= 0 ? creditData.getGroupKeepProductAccount()
                                                                                                                            .subtract(new BigDecimal(
                                                                                                                                                     "10000"))
                                                                                                               : creditData.getGroupKeepProductAccount();
                                // 在本次本组实际可使用金额和本次匹配金额中更小的值为本次实际匹配金额
                                matchedProductAccount = matchMinProductAccount.min(actGroupKeepProductAccount);
                                break;

                            default:
                                // 匹配次数大于1次的时候，直接使用匹配到的金额
                                matchedProductAccount = matchMinProductAccount;
                                break;
                        }
                    }
                    // 如果不大于0就直接使用匹配金额
                    else
                    {
                        matchedProductAccount = matchMinProductAccount;
                    }

                    // 更新债权包匹配情况
                    creditData.setMatched(true);
                    // 设置匹配到的金额
                    creditData.setMatchedProductAccount(matchedProductAccount);


                    // 判断此抵押包地区之前是否已经匹配过，如果没有匹配过
                    if (!isMatchedAreaCode(creditData.getLocalNum()))
                    {
                        usedCreditAreaCode = usedCreditAreaCode.length() == 0 ? creditData.getLocalNum()
                                                                             : usedCreditAreaCode + ","
                                                                               + creditData.getLocalNum();
                    }

                    // 保存当前已匹配的抵押包信息集合
                    this.matchedCreditDataList.add(creditData);

                    // 更新要匹配的投资金额
                    productAccount = productAccount.subtract(matchedProductAccount);
                    // 如果要匹配的投资金额小于最小可匹配金额，说明使用最小可匹配金额已经全部匹配，直接跳出循环
                    if (productAccount.compareTo(matchMinProductAccount) < 0)
                    {
                        break;
                    }
                }

                // 如果剩余投资金额小于最小可匹配金额
                if (productAccount.compareTo(matchMinProductAccount) < 0)
                {
                    break;
                }

            }

            // 如果剩余投资金额小于最小可匹配金额，跳出循环
            if (productAccount.compareTo(matchMinProductAccount) < 0)
            {
                break;
            }

        }
        return productAccount;
    }

    /**
     * @Title: findCreditByRule
     * @Description: 根据条件匹配抵押包信息
     * @param creditDataList 抵押包信息集合
     * @param matchMinProductAccount 最小可匹配投资金额（本组剩余可匹配投资金额要大于等于此数）
     * @param matchOrigProductAccount 最小可匹配原始金额（抵押包原始金额要大于等于此值）
     * @param ratio 系数（本组总已匹配金额/抵押包原始金额）
     * @param productAccount 要匹配的总投资金额
     * @return 匹配到的抵押包集合
     * @author: jinzhm
     * @time:2017年2月12日 下午3:21:45
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    protected List<CreditData> findCreditByRule(List<CreditData> creditDataList, BigDecimal matchMinProductAccount,
                                                BigDecimal matchOrigProductAccount, BigDecimal ratio,
                                                BigDecimal productAccount)
    {
        List<CreditData> matchedCreditDataList = new ArrayList<CreditData>();
        // 循环外集合
        for (CreditData creditData : creditDataList)
        {
            // 如果当前循环的抵押包已经匹配过，不能继续匹配
            if (creditData.isMatched())
            {
                continue;
            }
            // 找原始金额大于或等于要匹配的原始金额且本组剩余可匹配金额大于等于最大可匹配金额的抵押包
            if (creditData.getOrigProductAccount().compareTo(matchOrigProductAccount) >= 0
                && creditData.getGroupKeepProductAccount().compareTo(matchMinProductAccount) >= 0)
            {
                // 找抵押包原始金额*系数大于总共已匹配的金额的抵押包
                if (creditData.getOrigProductAccount().multiply(ratio)
                              .divide(new BigDecimal("10000"), 0, RoundingMode.UP).multiply(new BigDecimal("10000"))
                              .compareTo(creditData.getTotalUseProductAccount()) > 0)
                {
                    // 更新债权包匹配情况
                    // creditData.setMatched(true);
                    // 设置匹配到的金额
                    // creditData.setMatchedProductAccount(matchMinProductAccount);
                    matchedCreditDataList.add(creditData);
                    // 更新要匹配的投资金额
                    // productAccount =
                    // productAccount.subtract(matchMinProductAccount);
                    // 如果要匹配的投资金额小于最小可匹配金额，说明使用最小可匹配金额已经全部匹配，直接跳出循环
                    // if (productAccount.compareTo(matchMinProductAccount) < 0)
                    // {
                    // break;
                    // }
                }
            }
        }
        return matchedCreditDataList;
    }

    /**
     * @Title: sortCreditDataList
     * @Description: 根据本组剩余可匹配债权包金额进行排序（如果当前已匹配了的话需要减去当前已匹配金额）
     * @param creditDataList 要排序的抵押包集合
     * @author: jinzhm
     * @time:2017年2月13日 上午10:13:14
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected void sortCreditDataList(List<CreditData> creditDataList)
    {
        Collections.sort(creditDataList, new Comparator<CreditData>()
        {
            @Override
            public int compare(CreditData o1, CreditData o2)
            {
                // 减去当前已匹配之后进行排序
                return o1.getGroupKeepProductAccount().subtract(o1.getMatchedProductAccount())
                         .compareTo(o2.getGroupKeepProductAccount().subtract(o2.getMatchedProductAccount()));
            }
        });
    }

    /**
     * @Title: sortCreditDataList
     * @Description: 根据协议到期日期和抵押包结束日期差值排序抵押包集合
     *      差值小的在上
     *      差值相同，抵押结束日期更大的在上
     *      差值形同，抵押结束日期相同，默认排序
     * @param protocolEndDate 
     * @author: jinzhm
     * @time:2017年2月12日 下午1:52:37
     * history:
     * 1、2017年2月12日 jinzhm 创建方法
     */
    protected void sortCreditDataList(List<CreditData> creditDataList, final Date protocolEndDate)
    {
        // 进行排序
        Collections.sort(creditDataList, new Comparator<CreditData>()
        {
            // return -1; o1向上
            // return 1; o1向下
            @Override
            public int compare(CreditData o1, CreditData o2)
            {
                // 计算抵押包结束日期和协议到期时间的差值
                int o1DiffValue = DateUtil.getBetweenDays(protocolEndDate, o1.getCrepgEndDate());
                // 计算抵押包结束日期和协议到期时间的差值
                int o2DiffValue = DateUtil.getBetweenDays(protocolEndDate, o2.getCrepgEndDate());

                // 如果第一个差值大于第二个差值，第二个排序在更上面
                if (o1DiffValue > o2DiffValue)
                {
                    return 1;
                }
                // 如果第一个差值小于第二个差值，第一个排序在更上面
                else if (o1DiffValue < o2DiffValue)
                {
                    return -1;
                }
                // 如果两个差值相同，按照日期排序
                else
                {
                    // 如果o1的抵押包到期时间大于o2的，o1向上移
                    if (o1.getCrepgEndDate().compareTo(o2.getCrepgEndDate()) > 0)
                    {
                        return -1;
                    }
                    // 如果o1的抵押包到期时间小于o2的，o1向下移
                    else if (o1.getCrepgEndDate().compareTo(o2.getCrepgEndDate()) < 0)
                    {
                        return 1;
                    }
                    // 如果两个抵押包到期时间相同
                    else
                    {
                        // 如果o1抵押包地区和上单地区相同o1，且o2抵押包地区和上单地区不相同向上移
                        if (transaAreaCode.equals(o1.getLocalNum()) && !transaAreaCode.equals(o2.getLocalNum()))
                        {
                            return -1;
                        }
                        // 如果o2抵押包地区和上大地区相同o2，且o1抵押包地区和上单地区不相同向上移
                        else if (transaAreaCode.equals(o2.getLocalNum()) && !transaAreaCode.equals(o1.getLocalNum()))
                        {
                            return 1;
                        }
                        // 如果o1和o2的抵押包都是上单地区的
                        else if (transaAreaCode.equals(o1.getLocalNum()) && transaAreaCode.equals(o2.getLocalNum()))
                        {
                            return 0;
                        }
                        // 如果o1和o2的抵押包都不是上单地区的
                        else
                        {
                            // 如果o1抵押包地区是当前已匹配债权的地区的话o1向上移
                            if (isMatchedAreaCode(o1.getLocalNum()) && !isMatchedAreaCode(o2.getLocalNum()))
                            {
                                return -1;
                            }
                            // 如果o2抵押包地区是当前已匹配债权的地区的话o2向上移
                            else if (isMatchedAreaCode(o2.getLocalNum()) && !isMatchedAreaCode(o1.getLocalNum()))
                            {
                                return 1;
                            }
                            // 如果o1和o2两个抵押包地区都没有匹配过
                            else if (isMatchedAreaCode(o1.getLocalNum()) && isMatchedAreaCode(o2.getLocalNum()))
                            {
                                return 0;
                            }
                            // 抵押包地区不是上单地区也不是当前已匹配债权地区的话
                            else
                            {
                                // o1的地区id大于o2的地区id的话，o1向下移
                                if (o1.getLocalId() > o2.getLocalId())
                                {
                                    return 1;
                                }
                                // o1的地区id小于o2的地区id的话，o1向上移
                                else if (o1.getLocalId() < o2.getLocalId())
                                {
                                    return -1;
                                }
                                // 其他时候不动
                                return 0;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * @Title: initData
     * @Description: 初始化所有债权匹配数据
     * @param matchData 债权匹配数据对象
     * @param coeff 参数设定中的系数
     * @author: jinzhm
     * @time:2017年2月13日 上午11:16:06
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected void initData(CreditMatchData matchData, BigDecimal coeff)
    {
        // 清空已匹配到的债权集合
        this.matchedCreditDataList.clear();

        creditDataInList = new ArrayList<CreditData>();
        creditDataOutList = new ArrayList<CreditData>();

        // 根据匹配时间拆分抵押包集合同时计算每个抵押包本组剩余可匹配金额
        splitCreditDataByHoursInit(matchData, coeff);
    }

    /**
     * @Title: isMatchedAreaCode
     * @Description: 判断是否已经匹配该地区编号的债权
     * @param areaCode 地区编号
     * @return 已使用返回true，未使用返回false
     * @author: jinzhm
     * @time:2017年3月16日 下午5:22:04
     * history:
     * 1、2017年3月16日 jinzhm 创建方法
     */
    private boolean isMatchedAreaCode(String areaCode)
    {
        if (("," + usedCreditAreaCode + ",").indexOf("," + areaCode + ",") > -1)
        {
            return true;
        }
        return false;
    }
}
