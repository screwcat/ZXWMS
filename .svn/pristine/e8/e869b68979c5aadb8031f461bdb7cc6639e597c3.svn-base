package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.zx.emanage.inve.util.IncomeGlobal;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeExtendMonthRedeem
 * 模块名称：超期赎回对客户收益影响处理类
 *      赎回日期的月份大于协议到期日期的月份（超期赎回）
 * @Description: 内容摘要：超期赎回或预约赎回（预约到超期赎回）处理客户收益
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public class CountIncomeExtendMonthRedeem extends CountIncomeRedeemTimeAbstract
{
    
    /**
     * @Title: handleIncomeAfterFinancePaymentForOrderRedeemAbstract
     * @Description: 预约赎回时，超期跨越赎回时，财务打款完成后客户收益处理方法
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2017年4月27日 下午4:05:05
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeAfterFinancePaymentForOrderRedeemAbstract(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2017年4月27日 jinzhm 创建方法
    */
    @Override
    protected void handleIncomeAfterFinancePaymentForOrderRedeemAbstract(CountIncomeRedeemTimeData data)
    {
        // 查出赎回收益及公益收益集合
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
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 公益收益设置成已终止
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
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
     * @Description: 年付产品，预约赎回时，超期跨越赎回时，财务打款完成后客户收益处理方法
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2017年4月27日 下午4:43:50
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2017年4月27日 jinzhm 创建方法
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
     * @Title: handleIncomeOrderRedeem
     * @Description: 预约赎回是跨月超期时，客户收益处理方法
     *      跨月赎回只处理公益收益
     *      查询单据的公益收益进行处理
     * @param redeemTimeData 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午2:43:03
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     * 2、2017年04月20日 jinzhm 加入针对单据超期后只给一个月公益收益，之后赎回给活期利率需求修改
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeem(CountIncomeRedeemTimeData data)
    {
        List<WmsInveTransaIncome> updTransaIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询公益收益集合，有可能没有数据。当跨月预约赎回时，公益还没有生成
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_PUBLIC + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);

        // 获得理财到期时间
        Date endOfDate = data.getProtocol().getEnd_of_date();
        // 获得赎回时间
        Date redeemDate = data.getRedeem().getRedeem_date();

        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环公益收益集合，有可能没有公益收益以为有可能跨月预约
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            // 如果是收益应付日期的月份大于等于赎回申请日期的月份进行收益处理
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
            {
                // 如果是未支付处理，如果是已支付状态不处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    /*
                     * 公益收益只给到期后一个月。
                     * 如4月5日到期，公益收益给到5月5日。
                     * 2016-07-01之前上单单据，赎回当天有收益，5月5日或之后赎回，5天公益收益设置成赎回收益已支付，且给几天的央行活期利率；5月赎回，日期小于5号，公益收益设置成已终止，给几天赎回公益收益
                     * 2016-07-01之后上单单据，赎回当天无收益，5月5日之后赎回，5天公益收益设置成赎回收益已支付，且给几天央行活期利率；5月赎回，日期小于等于5号，公益收益设置成已终止，给几天赎回公益收益
                     * 
                     * 如果公益收益正好是到期日期+1个月，且和赎回日期月份相同；
                     * 如果单据是2016-07-01日之后（含）单据，赎回日期大于到期日期+1个月的话，设置成赎回收益
                     * 如果单据是2016-07-01之前单据，赎回日期大于等于到日日期+1个月的话，设置成赎回收益
                     * 其他时候都重新计算公益收益
                     */
                    if ((DateUtil.getLastDayOfMonth(DateUtils.addMonths(endOfDate, 1))
                                 .compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
                        && ((IncomeUtil.isLaterThanMagicDate(data.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(endOfDate,
                                                                                                                                     1)) > 0) || (!IncomeUtil.isLaterThanMagicDate(data.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(endOfDate,
                                                                                                                                                                                                                                                           1)) >= 0)))
                    {
                        transaIncome.setPay_type(PAY_TYPE_REDEEM);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                    else
                    {
                        // 计算要使用的投资金额
                        BigDecimal productAccount = data.getProductAccount();
                        // 计算公益收益，肯定是0
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                 productAccount,
                                                                                 data.getPublicReturnRate());
                        // 设置投资金额，公益收益，总收益
                        transaIncome.setProduct_account(productAccount);
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updTransaIncomeList;
    }

    /**
     * @Title: handleIncomeOrderRedeemForFullYear
     * @Description: 年付产品取消超期预约赎回
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息对象
     * @author: jinzhm
     * @time:2016年12月29日 上午11:05:36
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        List<WmsInveTransaIncome> updTransaIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询公益收益集合，有可能没有数据。当跨月预约赎回时，公益还没有生成
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_PUBLIC + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);

        // 获得理财到期时间
        Date endOfDate = data.getProtocol().getEnd_of_date();
        // 获得赎回时间
        Date redeemDate = data.getRedeem().getRedeem_date();

        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环公益收益集合，有可能没有公益收益以为有可能跨月预约
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            /**
             *  是公益收益的时候
             *  只要收益应付日期的月份大于等于赎回日期的月份就需要处理
             */
            if (IncomeUtil.isPublicIncomeNeedHandleTransaIncomeForRedeem(transaIncome.getPay_type(),
                                                                         transaIncome.getReturn_date(),
                                                                         data.getRedeem().getRedeem_date()))
            {
                // 如果是未支付处理，如果是已支付状态不处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    /*
                     * 公益收益只给到期后一个月。
                     * 如4月5日到期，公益收益给到5月5日。
                     * 2016-07-01之前上单单据，赎回当天有收益，5月5日或之后赎回，5天公益收益设置成赎回收益已支付，且给几天的央行活期利率；5月赎回，日期小于5号，公益收益设置成已终止，给几天赎回公益收益
                     * 2016-07-01之后上单单据，赎回当天无收益，5月5日之后赎回，5天公益收益设置成赎回收益已支付，且给几天央行活期利率；5月赎回，日期小于等于5号，公益收益设置成已终止，给几天赎回公益收益
                     * 
                     * 如果公益收益正好是到期日期+1个月，且和赎回日期月份相同；
                     * 如果单据是2016-07-01日之后（含）单据，赎回日期大于到期日期+1个月的话，设置成赎回收益
                     * 如果单据是2016-07-01之前单据，赎回日期大于等于到日日期+1个月的话，设置成赎回收益
                     * 其他时候都重新计算公益收益
                     */
                    if ((DateUtil.getLastDayOfMonth(DateUtils.addMonths(endOfDate, 1))
                                 .compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
                        && ((IncomeUtil.isLaterThanMagicDate(data.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(endOfDate,
                                                                                                                                     1)) > 0) || (!IncomeUtil.isLaterThanMagicDate(data.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(endOfDate,
                                                                                                                                                                                                                                                           1)) >= 0)))
                    {
                        transaIncome.setPay_type(PAY_TYPE_REDEEM);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                    else
                    {
                        // 计算要使用的投资金额
                        BigDecimal productAccount = data.getProductAccount();
                        // 计算公益收益，肯定是0
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                 productAccount,
                                                                                 data.getPublicReturnRate());
                        // 设置投资金额，公益收益，总收益
                        transaIncome.setProduct_account(productAccount);
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updTransaIncomeList;
    }

    /**
     * @Title: handleIncomeCancelOrderRedeem
     * @Description: 预约赎回是跨月超期时，客户收益处理方法
     *      有可能取消时还没有到预约赎回的月份，这时还没有生成公益收益，不需要处理
     *      有可能取消时已经支付了公益收益，这时需要新生成一个公益收益
     * @param redeemTimeData 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午2:58:38
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeCancelOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeem(CountIncomeRedeemTimeData data)
    {
        List<WmsInveTransaIncome> updTransaIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询公益收益集合，有可能没有数据。当跨月预约赎回时，公益还没有生成
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_PUBLIC + "','" + IncomeGlobal.PAY_TYPE_REDEEM + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环公益收益集合，有可能没有公益收益以为有可能跨月预约
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            // 如果是收益应付日期的月份大于等于赎回申请日期的月份进行收益处理
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
            {
                // 如果收益已经支付
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    // 如果收益已经支付，之后做取消预约赎回，需要新生成一个公益收益
                    BigDecimal income = BigDecimal.ZERO;
                    // 奖励收益
                    BigDecimal bonus = BigDecimal.ZERO;
                    // 公益收益
                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                             data.getProductAccount(),
                                                                             data.getPublicReturnRate());
                    // 生成一个新的公益收益
                    WmsInveTransaIncome publicTransaIncome = generateTransaIncome(data.getProductAccount(), income,
                                                                                  bonus, publicIncome,
                                                                                  transaIncome.getPay_type(),
                                                                                  transaIncome, data);
                    publicTransaIncome.setRemarks(DateUtil.date2String(transaIncome.getReturn_date(), "yyyy年MM月")
                                                  + "客户公益收益");
                    updTransaIncomeList.add(publicTransaIncome);
                }
                // 如果收益还没有支付
                else if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是未支付的赎回收益，将赎回收益设置成公益收益
                    if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type()))
                    {
                        // 设置成正常收益
                        transaIncome.setPay_type(PAY_TYPE_PUBLIC);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                    else
                    {
                        // 计算要使用的投资金额
                        BigDecimal productAccount = data.getProductAccount();
                        // 计算公益收益，肯定是0
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                 productAccount,
                                                                                 data.getPublicReturnRate());
                        // 设置投资金额，公益收益，总收益
                        transaIncome.setProduct_account(productAccount);
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updTransaIncomeList;
    }

    /**
     * @Title: handleIncomeCancelOrderRedeemForFullYear
     * @Description: 年付取消超期预约赎回处理客户收益
     * @param data 赎回时机对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午11:09:08
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeAbstract#handleIncomeCancelOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        List<WmsInveTransaIncome> updTransaIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 查询公益收益集合，有可能没有数据。当跨月预约赎回时，公益还没有生成
        String payTypes = "'" + IncomeGlobal.PAY_TYPE_PUBLIC + "','" + IncomeGlobal.PAY_TYPE_REDEEM + "'";
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                        .getWms_inve_transa_id(),
                                                                                    payTypes);
        // 收益信息
        WmsInveTransaIncome transaIncome = null;
        // 循环公益收益集合，有可能没有公益收益以为有可能跨月预约
        for (int i = 0; i < transaIncomeList.size(); i++)
        {
            transaIncome = transaIncomeList.get(i);
            /**
            *  是公益收益的时候
            *  只要收益应付日期的月份大于等于赎回日期的月份就需要处理
            */
            if (IncomeUtil.isPublicIncomeNeedHandleTransaIncomeForRedeem(transaIncome.getPay_type(),
                                                                         transaIncome.getReturn_date(),
                                                                         data.getRedeem().getRedeem_date()))
            {
                // 如果收益已经支付
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    // 如果收益已经支付，之后做取消预约赎回，需要新生成一个公益收益
                    BigDecimal income = BigDecimal.ZERO;
                    // 奖励收益
                    BigDecimal bonus = BigDecimal.ZERO;
                    // 公益收益
                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                             data.getProductAccount(),
                                                                             data.getPublicReturnRate());
                    // 生成一个新的公益收益
                    WmsInveTransaIncome publicTransaIncome = generateTransaIncome(data.getProductAccount(), income,
                                                                                  bonus, publicIncome,
                                                                                  transaIncome.getPay_type(),
                                                                                  transaIncome, data);
                    publicTransaIncome.setRemarks(DateUtil.date2String(transaIncome.getReturn_date(), "yyyy年MM月")
                                                  + data.getCategory().getCategory_name() + "客户公益收益");
                    updTransaIncomeList.add(publicTransaIncome);
                }
                // 如果收益还没有支付
                else if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 计算要使用的投资金额
                    BigDecimal productAccount = data.getProductAccount();
                    // 计算公益收益，肯定是0
                    BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                             productAccount, data.getPublicReturnRate());
                    // 设置投资金额，公益收益，总收益
                    transaIncome.setProduct_account(productAccount);
                    transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                    transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                    IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                    updTransaIncomeList.add(transaIncome);
                }
            }
            // 是赎回收益且是未支付的赎回日期月份的应付收益
            else if (PAY_TYPE_REDEEM.equals(transaIncome.getPay_type())
                     && !IncomeUtil.isIncomeAlreadyPaid(transaIncome)
                     && DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                                .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) == 0)
            {
                // 设置成正常收益
                transaIncome.setPay_type(PAY_TYPE_PUBLIC);
                IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                updTransaIncomeList.add(transaIncome);
            }
        }
        return updTransaIncomeList;
    }

    /**
     * @Title: getRedeemIncome
     * @Description: 跨月超期赎回时获得赎回收益
     * @param data 赎回时机对象
     * @return 赎回金额 = 公益收益-已付收益
     * @author: jinzhm
     * @time:2016年12月28日 下午4:35:00
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
        return data.getRedeemDetail().getExtend_income().subtract(paidIncome);
    }

}
