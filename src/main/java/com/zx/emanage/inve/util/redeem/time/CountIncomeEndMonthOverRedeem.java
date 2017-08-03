package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zx.emanage.inve.util.IncomeGlobal;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeEndMonthOverRedeem
 * 模块名称：到期月份到期或超期赎回时客户收益处理类
 * @Description: 内容摘要：到期月份到期或超期赎回，预约赎回到到期月份到期或超期赎回时对客户收益处理
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public class CountIncomeEndMonthOverRedeem extends CountIncomeRedeemTimeAbstract
{

    /**
     * @Title: handleIncomeAfterFinancePaymentForOrderRedeemAbstract
     * @Description: 预约赎回到到期月份到期或超期赎回单据财务回款后的收益处理
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月28日 下午1:18:24
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeAfterFinancePaymentForOrderRedeemAbstract(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    protected void handleIncomeAfterFinancePaymentForOrderRedeemAbstract(CountIncomeRedeemTimeData data)
    {
        // 查询赎回收益信息（因为到期月份的正常收益设置成了赎回收益，所以需要查询）
        String payTypes = "'" + PAY_TYPE_PUBLIC + "','" + PAY_TYPE_REDEEM + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 未打款情况下的基本收益
        BigDecimal income = BigDecimal.ZERO;
        // 循环查询的赎回收益信息
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 收益的应付日期的月份大于等于赎回日期的月份时处理收益
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
            {
                // 如果赎回收益是未支付状态，进行处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是赎回收益
                    if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type()))
                    {
                        income = income.add(transaIncome.getProduct_interest_account());
                        // 设置成已支付
                        transaIncome.setPay_status(PAY_STATUS_ALREADY_PAY);
                        // 实际支付日期设置成赎回日期
                        transaIncome.setAct_return_date(data.getRedeem().getRedeem_date());
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 公益收益设置成已终止
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        // 生成赎回交易日志及理财结束交易日志
        generateRedeemLog(data);
        // 未打款时和赎回收益一起变成已支付状态，这时需要在赎回交易日志中加上未支付基本收益金额
        redeemTransaLog.setProduct_interest_account(redeemTransaLog.getProduct_interest_account().add(income));
        generateEndLog(data);
        // 设置赎回时机对象
        generateRedeemIncome(data);
    }

    /**
     * @Title: handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract
     * @Description: 年付产品，到期月份超期赎回的预约赎回在财务回款后客户收益处理方法
     * @param data 赎回时机对象
     * @author: jinzhm
     * @time:2018年1月13日 下午4:14:40
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
    */
    @Override
    protected void handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract(CountIncomeRedeemTimeData data)
    {
        // 查询赎回收益信息（因为到期月份的正常收益设置成了赎回收益，所以需要查询）
        String payTypes = "'" + PAY_TYPE_PUBLIC + "','" + PAY_TYPE_REDEEM + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 未打款情况下的基本收益
        BigDecimal income = BigDecimal.ZERO;
        // 年数
        int year = 0;
        // 循环查询的赎回收益信息
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 如果是正常收益year+1
            if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
            {
                year++;
            }

            /**
            *  是正常收益的时候
            *  是一年产品的时候
            *  只要收益应付时间的月份大于等于赎回日期的月份就需要处理
            *  是两年产品的时候
            *  第一年收益应付日期月份赎回时，需要判断赎回日期和第一年收益应付日期
            *      2016-07-01之前上单单据收益到期日赎回，不修改该收益信息
            *      2016-07-01之后上单单据收益到期日赎回，修改该收益信息
            *  第二年收益应付日期的月份大于等于赎回日期的月份就需要处理
            *  是公益收益的时候
            *  只要收益应付日期的月份大于等于赎回日期的月份就需要处理
             */
            if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem().getRedeem_date(),
                                                                       year, data.getCategory().getCategory_deadline(),
                                                                       data.getOldDateOfPayment()))
            {
                // 如果赎回收益是未支付状态，进行处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是公益收益
                    if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 公益收益设置成已终止
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                }
            }
            // 如果是当月的赎回收益且未支付
            else if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type())
                     && IncomeUtil.isIncomeNotPaid(transaIncome)
                     && DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                                .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) == 0)
            {
                income = income.add(transaIncome.getProduct_interest_account());
                // 设置成已支付
                transaIncome.setPay_status(PAY_STATUS_ALREADY_PAY);
                // 实际支付日期设置成赎回日期
                transaIncome.setAct_return_date(data.getRedeem().getRedeem_date());
                updRedeemTransaIncomeList.add(transaIncome);
            }
        }
        // 生成赎回交易日志及理财结束交易日志
        generateRedeemLog(data);
        // 未打款时和赎回收益一起变成已支付状态，这时需要在赎回交易日志中加上未支付基本收益金额
        redeemTransaLog.setProduct_interest_account(redeemTransaLog.getProduct_interest_account().add(income));
        generateEndLog(data);
        // 设置赎回时机对象
        generateRedeemIncome(data);
    }

    /**
     * @Title: handleIncomeCancelOrderRedeem
     * @Description: 预约赎回是到期日或到期月份超期赎回，取消时对客户收益影响处理
     * @param redeemTimeData 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午2:07:15
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeCancelOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeem(CountIncomeRedeemTimeData data)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询正常，公益，赎回收益信息
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_NORMAL + "','" + IncomeGlobal.PAY_TYPE_PUBLIC + "','"
                          + IncomeGlobal.PAY_TYPE_REDEEM + "'";
        // 查询的收益信息（包含正常收益，公益收益，赎回收益）
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环所有收益信息
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            // 如果是收益应付日期的月份大于等于赎回申请日期的月份进行收益处理
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
            {
                // 如果是不是已支付状态，需要处理
                if (!IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    // 如果是赎回收益，修改成正常收益（该赎回收益肯定是到期月份的原正常收益）
                    if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type()))
                    {
                        // 设置成正常收益
                        transaIncome.setPay_type(PAY_TYPE_NORMAL);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益设置成未支付
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 设置成未支付
                        transaIncome.setPay_status(PAY_STATUS_NOT_PAY);
                        // 如果原有公益收益的投资金额和要计算的公益收益的投资金额不相同的话，需要重新计算
                        if (transaIncome.getProduct_account().compareTo(data.getProductAccount()) != 0)
                        {
                            // 计算公益收益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                     data.getProductAccount(),
                                                                                     data.getPublicReturnRate());
                            // 设置公益收益，总收益，投资金额
                            transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_account(data.getProductAccount());
                        }
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: handleIncomeCancelOrderRedeemForFullYear
     * @Description: 取消到期月份到期日或超期赎回的预约赎回时客户收益影响处理
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午10:41:25
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeCancelOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询正常，公益，赎回收益信息
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_NORMAL + "','" + IncomeGlobal.PAY_TYPE_PUBLIC + "','"
                          + IncomeGlobal.PAY_TYPE_REDEEM + "'";
        // 查询的收益信息（包含正常收益，公益收益，赎回收益）
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 年数
        int year = 0;
        // 循环所有收益信息
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            // 如果是正常收益year+1
            if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
            {
                year++;
            }
            /**
             *  是正常收益的时候
             *  是一年产品的时候
             *  只要收益应付时间的月份大于等于赎回日期的月份就需要处理
             *  是两年产品的时候
             *  第一年收益应付日期月份赎回时，需要判断赎回日期和第一年收益应付日期
             *      2016-07-01之前上单单据收益到期日赎回，不修改该收益信息
             *      2016-07-01之后上单单据收益到期日赎回，修改该收益信息
             *  第二年收益应付日期的月份大于等于赎回日期的月份就需要处理
             *  是公益收益的时候
             *  只要收益应付日期的月份大于等于赎回日期的月份就需要处理
              */
            if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem().getRedeem_date(),
                                                                       year, data.getCategory().getCategory_deadline(),
                                                                       data.getOldDateOfPayment()))
            {
                // 如果是不是已支付状态，需要处理
                if (!IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    // 如果是公益收益设置成未支付
                    if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 设置成未支付
                        transaIncome.setPay_status(PAY_STATUS_NOT_PAY);
                        // 如果原有公益收益的投资金额和要计算的公益收益的投资金额不相同的话，需要重新计算
                        if (transaIncome.getProduct_account().compareTo(data.getProductAccount()) != 0)
                        {
                            // 计算公益收益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                     data.getProductAccount(),
                                                                                     data.getPublicReturnRate());
                            // 设置公益收益，总收益，投资金额
                            transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_account(data.getProductAccount());
                        }
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                }
            }
            // 赎回收益进不了上面的条件中，赎回收益单独判断
            // 如果是赎回收益，不是已支付，收益应付日期和赎回日期是同一个月份，将赎回收益改成正常收益
            else if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type())
                     && !IncomeUtil.isIncomeAlreadyPaid(transaIncome)
                     && DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                                .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) == 0)
            {
                // 设置成正常收益
                transaIncome.setPay_type(PAY_TYPE_NORMAL);
                IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                updIncomeList.add(transaIncome);
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: handleIncomeOrderRedeem
     * @Description: 预约赎回到到期月份到期或超期赎回时客户收益处理
     *      只处理大于等于赎回月份，未支付状态数据
     *      到期月份的正常收益设置成赎回收益（只有一条数据）
     *      到期月份的公益收益设置成已终止状态（应该也是只有一条数据）
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息
     * @author: jinzhm
     * @time:2016年12月28日 下午1:20:24
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeem(CountIncomeRedeemTimeData data)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(data.getTransa().getWms_inve_transa_id());
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环收益信息集合
        for (int i = 0; i < incomeList.size(); i++)
        {
            // 获得收益信息对象
            transaIncome = incomeList.get(i);
            // 如果是收益应付日期的月份大于等于赎回申请日期的月份进行收益处理
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
            {
                // 如果是未支付处理，如果是已支付状态不处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是正常收益设置成赎回收益
                    if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                    {
                        // 到期月份的正常收益设置成赎回收益
                        transaIncome.setPay_type(PAY_TYPE_REDEEM);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益设置成已终止
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 到期月份的公益收益设置成已终止状态
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: handleIncomeOrderRedeemForFullYear
     * @Description: 到期月份到期日或超期赎回时客户收益影响处理
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午10:05:22
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(data.getTransa().getWms_inve_transa_id());
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 年数
        int year = 0;
        // 循环收益信息集合
        for (int i = 0; i < incomeList.size(); i++)
        {
            // 获得收益信息对象
            transaIncome = incomeList.get(i);
            // 如果是正常收益year+1
            if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
            {
                year++;
            }
            /**
            *  是正常收益的时候
            *  是一年产品的时候
            *  只要收益应付时间的月份大于等于赎回日期的月份就需要处理
            *  是两年产品的时候
            *  第一年收益应付日期月份赎回时，需要判断赎回日期和第一年收益应付日期
            *      2016-07-01之前上单单据收益到期日赎回，不修改该收益信息
            *      2016-07-01之后上单单据收益到期日赎回，修改该收益信息
            *  第二年收益应付日期的月份大于等于赎回日期的月份就需要处理
            *  是公益收益的时候
            *  只要收益应付日期的月份大于等于赎回日期的月份就需要处理
             */
            if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem().getRedeem_date(),
                                                                       year, data.getCategory().getCategory_deadline(),
                                                                       data.getOldDateOfPayment()))
            {
                // 如果是未支付处理，如果是已支付状态不处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是正常收益设置成赎回收益
                    if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                    {
                        // 到期月份的正常收益设置成赎回收益
                        transaIncome.setPay_type(PAY_TYPE_REDEEM);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益设置成已终止
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 到期月份的公益收益设置成已终止状态
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: getRedeemIncome
     * @Description: 到期月份到期日或超期赎回获得赎回金额
     * @param data 赎回时机数据对象
     * @return 
     *      收益已打款 赎回收益=赎回基本收益+赎回奖励收益+赎回公益收益-已付收益
     *      收益未打款 赎回收益=赎回公益收益-已付收益
     * @author: jinzhm
     * @time:2016年12月28日 下午4:39:26
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#getRedeemIncome(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getRedeemIncome(CountIncomeRedeemTimeData data)
    {
        // 是否已打款标记
        boolean isAlreadyPaid = false;
        // 查询收益信息
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id());

        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) == 0)
            {
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    isAlreadyPaid = true;
                    break;
                }
            }
        }

        // 已付收益
        BigDecimal paidIncome = data.getRedeemDetail().getPaid_income() == null ? BigDecimal.ZERO
                                                                               : data.getRedeemDetail()
                                                                                     .getPaid_income();
        // 已打款收益
        if (isAlreadyPaid)
        {
            return data.getRedeemDetail().getPayable_basic_income()
                       .add(data.getRedeemDetail().getPayable_reward_income())
                       .add(data.getRedeemDetail().getExtend_income()).subtract(paidIncome);
        }
        // 未打款收益
        else
        {
            return data.getRedeemDetail().getExtend_income().subtract(paidIncome);
        }
    }
}
