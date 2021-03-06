package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 满月存量奖励方式月付产品
 * 
 * @author 金志明
 * @date 2017年10月12日 下午2:42:17
 */
public class CountIncomeForFullMonth extends CountIncomeAbstract
{

	@Override
    protected Map<String, Object> handleIncomeAndLog()
	{
        List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<WmsInveTransaLog>();// 生成交易日志集合
        List<WmsInveTransaIncome> wmsInveTransaIncomes = new ArrayList<WmsInveTransaIncome>();// 计算收益信息集合

        int months = 0;// 相差月数
        int incomeDays = 0;// 每月收益天数
        int dayOfMonth = 0;// 当月实际天数
        // String payStatus = PAY_STATUS_NOT_PAY;// 支付状态

        BigDecimal categoryReturnRate = category.getBasic_monthly_rate() != null ? category.getBasic_monthly_rate()
                                                                                : category.getCategory_return_rate()
                                                                                          .divide(new BigDecimal("12"),
                                                                                                  8,
                                                                                                  RoundingMode.HALF_UP);// 月基础利率（%）
        BigDecimal bonusReturnRate = BigDecimal.ZERO;// 满月奖励利率（%）
        BigDecimal bonus = BigDecimal.ZERO;// 应收奖励金额
        BigDecimal income = BigDecimal.ZERO;// 应收收益金额
        BigDecimal productAccount = protocol.getProduct_account();// 计算时金额

        Date nowDate = DateUtil.getDate10(new Date());
        Date startDate = protocol.getDate_of_payment();// 支付日期作为理财开始日期
        Date endDate = protocol.getEnd_of_date();// 理财结束日期
        Date returnDate = null;// 收益日期

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);// cal设置理财支付日期，cal用作循环
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);

        // 保存理财上单日志信息
        // 理财上单时returnDate=null且奖励收益及应收收益都等于0
        wmsInveTransaLogs.add(setWmsInveTransaLog(startDate, TRANSA_START, bonus, income, protocol, user));

        // 支付日期作为开始日期按月循环到理财结束日期计算收益信息
        // cal小于calEnd(理财结束日期)一直循环
        // 因为有可能单据是2016-07-01之前的单据，这里判断两个日期大小的时候需要用<=,不能用<。2016-07-01之前上单的单据的结束日期和开始日期是相同的。
        while (cal.compareTo(calEnd) <= 0)
		{
            // 如果是循环日期和理财结束日期是同年同月，说明循环到了最后一个月
            if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH))
            {
                // 最后一个月收益天数直接获得月初到结束日期的天数
                incomeDays = calEnd.get(Calendar.DAY_OF_MONTH);
                // 最后一个月满月数和产品期限相同
                months = category.getCategory_deadline();
                // 最后一个月返利日期是结束日期
                returnDate = endDate;
            }
            else
            {
                // 如果循环没有到最后一个月，收益天数直接获得cal(每次循环都将cal移到下一个1号)当前时间到月末的天数
                // DateUtil.getDaysOfInterval方法获取当前日期到月末的天数时，不包含当天。
                // 判断循环是否在处理第一个月
                if (cal.getTime().compareTo(startDate) == 0)
                {
                    incomeDays = DateUtil.getDaysOfInterval(cal.getTime());
                    // 如果是第一月，需要判断是否是2016-07-01号或之后上单
                    if (IncomeUtil.isLaterThanMagicDate(this.oldDateOfPayment))
                    {
                        // 如果是2016-07-01或之后上单，第一月收益天数需要+1
                        incomeDays += 1;
                    }
                }
                else
                {
                    // 如果不是第一月或最后一个月，其他月份收益天数直接取当月天数
                    incomeDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    // 不是最后一个月，且也不是第一个月的话，月数+1。
                    months++;
                }
                // 如果循环没有到最后一个月，返利日期就是当月最后一天
                returnDate = DateUtil.getLastDayOfMonth(cal.getTime());
            }
            dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 获得当月实际天数

            // 收益 = 收益天数 / 当月实际天数 * 金额 * （年华收益利率 / 100 ）
            income = new BigDecimal(incomeDays).divide(new BigDecimal(dayOfMonth), 8, RoundingMode.HALF_UP)
                                               .multiply(productAccount)
                                               .multiply((categoryReturnRate.divide(new BigDecimal(100), 8,
                                                                                    RoundingMode.HALF_UP)));

            // 获得奖励利率
            bonusReturnRate = getFullMonthBonusReturnRate(months);
            // 计算奖励金额
            bonus = bonusReturnRate.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
                                                                   : bonusReturnRate.divide(new BigDecimal(100), 8,
                                                                                            RoundingMode.HALF_UP)
                                                                                    .multiply(productAccount);

            // 如果生成收益的时间（年月，不计较日）小于生成收益的当前时间的（年月），生成收益记录的支付状态为已支付
            // payStatus =
            // DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(nowDate))
            // < 0 ? PAY_STATUS_ALREADY_PAY
            // : PAY_STATUS_NOT_PAY;

            // 封装数据
            wmsInveTransaIncomes.add(setWmsInveTransaIncomeRemarks(setWmsInveTransaIncome(new java.sql.Date(
                                                                                                            returnDate.getTime()),
                                                                                          null, bonusReturnRate, bonus,
                                                                                          categoryReturnRate, income,
                                                                                          incomeDays,
                                                                                          PAY_STATUS_NOT_PAY,
                                                                                          protocol, category, user),
                                                                   bonus, returnDate, months));

            // 将日期移到下个月1号
            cal.setTime(DateUtil.getFirstDayOfNextMonth(cal.getTime()));
		}

        Map<String, Object> rMap = new HashMap<String, Object>();
        rMap.put("wmsInveTransaIncomes", wmsInveTransaIncomes);
        rMap.put("wmsInveTransaLogs", wmsInveTransaLogs);

        return rMap;
	}

	@SuppressWarnings("unchecked")
    @Override
    protected Map<String, Object> handleIncomeAndLogForRedeem()
	{
        List<WmsInveTransaIncome> wmsInveTransaIncomes = (List<WmsInveTransaIncome>) dataMap.get("wmsInveTransaIncomes");// 获得赎回前的收益信息集合
        WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");// 获得赎回信息

        Date redeemDate = (Date) dataMap.get("redeemDate");// 赎回时间
        Date startDate = protocol.getDate_of_payment();// 支付日期为理财开始日期
        Date endDate = protocol.getEnd_of_date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);// cal设置为理财开始日期，用作循环，每次循环设置成下个月1号
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);

        BigDecimal categoryReturnRate = category.getBasic_monthly_rate() != null ? category.getBasic_monthly_rate()
                                                                                : category.getCategory_return_rate()
                                                                                          .divide(new BigDecimal("12"),
                                                                                                  8,
                                                                                                  RoundingMode.HALF_UP);// 月基础利率（%）
        BigDecimal bonusReturnRate = BigDecimal.ZERO;// 奖励利率
        BigDecimal income = BigDecimal.ZERO;// 赢得收益
        BigDecimal bonus = BigDecimal.ZERO;// 奖励收益
        BigDecimal productAccount = prod.getProduct_account() == null ? protocol.getProduct_account()
                                                                     : prod.getProduct_account();// 获得当前投资金额。

        int months = 0;// 满月月数
        int incomeDays = 0;// 收益天数
        int dayOfMonth = 0;// 当月实际天数

        // 标记是否生成赎回收益，false表示未生成
        boolean flag = false;

        // 循环已有的收益信息，修改收到赎回影响的收益信息
        for (WmsInveTransaIncome wmsInveTransaIncome : wmsInveTransaIncomes)
		{
            // 判断循环时间cal是否循环到最后一个月，到最后一个月满月月数（months）设置成产品期限
            if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == calEnd.get(Calendar.MONTH))
            {
                incomeDays = calEnd.get(Calendar.DAY_OF_MONTH);
                months = protocol.getProduct_deadline();
            }
            else
            {
                // 如果循环没有到最后一个月，收益天数直接获得cal当前时间到月末的天数
                // DateUtil.getDaysOfInterval方法获取当前日期到月末的天数时，不包含今天。
                // 判断循环是否在处理第一个月
                if (cal.getTime().compareTo(startDate) == 0)
                {
                    incomeDays = DateUtil.getDaysOfInterval(cal.getTime());
                    if (IncomeUtil.isLaterThanMagicDate(this.oldDateOfPayment))
                    {
                        // 如果是2016-07-01或之后上单，第一月收益天数需要+1
                        incomeDays += 1;
                    }
                }
                else
                {
                    // 不是最后一个月，且也不是第一个月的话，收益天数=当月天数
                    incomeDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                    // 不是最后一个月，且也不是第一个月的话，月数+1。
                    months++;
                }
            }

            // 设置修改人和修改时间
            wmsInveTransaIncome.setLast_update_user_id(user.getUserId());
            wmsInveTransaIncome.setLast_update_timestamp(new Timestamp(new Date().getTime()));

            dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 获得当月实际天数

            // 收益 = 收益天数 / 当月实际天数 * 金额 * （年华收益利率 / 100）
            income = new BigDecimal(incomeDays).divide(new BigDecimal(dayOfMonth), 8, RoundingMode.HALF_UP)
                                               .multiply(productAccount)
                                               .multiply((categoryReturnRate.divide(new BigDecimal(100), 8,
                                                                                    RoundingMode.HALF_UP)));

            // 获得奖励利率
            bonusReturnRate = getFullMonthBonusReturnRate(months);
            // 计算奖励金额
            bonus = bonusReturnRate.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO
                                                                   : bonusReturnRate.divide(new BigDecimal(100), 8,
                                                                                            RoundingMode.HALF_UP)
                                                                                    .multiply(productAccount);

            /*
             * 理财超期赎回时，需要修改收益信息的支付状态，不修改收益金额等其他信息（包括交易日志）
             * 理财超期赎回时，原最后一条基本收益的收益金额等信息不修改，只修改状态。（11-08到期11-31有一条公益收益记录，11-22赎回时，11-08应该是已支付，且收益金额不变，干掉11-31记录）
             */
            if (PAY_TYPE_NORMAL.equals(wmsInveTransaIncome.getPay_type())
                && redeemDate.compareTo(protocol.getEnd_of_date()) < 0)
            {
                wmsInveTransaIncome.setProduct_account(productAccount);
                wmsInveTransaIncome.setProduct_interest_account(setScale(income.add(bonus)));
                wmsInveTransaIncome.setPayable_basic_income(setScale(income));
                wmsInveTransaIncome.setPayable_reward_income(setScale(bonus));
            }

            // 循环收益记录到应付收益日期的月份和赎回日期的月份相同的记录
            if (DateUtil.getLastDayOfMonth(wmsInveTransaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
            {
                // 赎回收益日期和理财结束日期是相同月份（到期月份赎回）。
                if (DateUtil.getLastDayOfMonth(redeemDate).compareTo(DateUtil.getLastDayOfMonth(endDate)) == 0)
                {
                    // 到期月份赎回时，可能存在两条收益记录（正常收益和公益收益），循环在正常收益时处理赎回，公益收益时不处理。
                    if (PAY_TYPE_NORMAL.equals(wmsInveTransaIncome.getPay_type()))
                    {
                        // 生成赎回收益及其他处理
                        handleAndGenerateRedeemIncome(wmsInveTransaIncome);
                        // 标记已经生成赎回收益
                        flag = true;
                        // 续期后赎回数据处理
                        handleExtendRedeemData(wmsInveTransaIncome);
                    }
                    // else和上面的if肯定是两次不一样的wmsInveTransaIncome循环对象，else中肯定是公益收益
                    else
                    {
                        // 如果不是已支付状态
                        if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                        {
                            // 如果是部分赎回,重新计算公益收益
                            if (!"1".equals(redeem.getIs_fully_redeemed()))
                            {

                                BigDecimal publicIncome = new BigDecimal(wmsInveTransaIncome.getDays_extend_income()).divide(new BigDecimal(
                                                                                                                                            365),
                                                                                                                             8,
                                                                                                                             BigDecimal.ROUND_HALF_UP)
                                                                                                                     .multiply(productAccount)
                                                                                                                     .multiply(this.publicReturnRate)
                                                                                                                     .setScale(2,
                                                                                                                               RoundingMode.UP);
                                wmsInveTransaIncome.setProduct_account(productAccount);
                                wmsInveTransaIncome.setProduct_interest_account(publicIncome);
                                wmsInveTransaIncome.setExtend_income(publicIncome);
                            }
                            // 如果是全部赎回
                            else
                            {
                                // 将收益支付状态修改成已终止
                                wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);// 设置成已终止
                            }
                        }
                    }
				}
                // 不是到期月份赎回
				else
                {
                    handleAndGenerateRedeemIncome(wmsInveTransaIncome);// 生成赎回收益及其他处理
                    // 标记已经生成赎回收益
                    flag = true;
                    // 续期后赎回数据处理
                    handleExtendRedeemData(wmsInveTransaIncome);
				}
			}
            // 如果应付日期大于等于赎回日期
            else if (wmsInveTransaIncome.getReturn_date().compareTo(redeemDate) >= 0)
            {
                // 如果赎回是全部赎回，且不是已支付收益
                if ("1".equals(redeem.getIs_fully_redeemed()) && !IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                {
                    // 将收益支付状态修改成已终止
                    wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);// 设置成已终止
                }
			}

            // 将循环日期cal移到下个月1号
            cal.setTime(DateUtil.getFirstDayOfNextMonth(cal.getTime()));
		}

        // 未生成赎回收益
        if (!flag)
        {
            // 生成赎回收益信息及一些其他处理
            dataMap.put("redeemIncome", generateRedeemIncome(6));
            // 生成赎回交易日志
            dataMap.put("redeemLog", generateRedeemLog(protocol.getWms_inve_transa_id(), 3));
            // 理财结束日志
            dataMap.put("transaEndLog", generateTransaLog(redeemDate, protocol, user, TRANSA_END));
        }

        return dataMap;
	}

    @Override
    protected void packageIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
	{
        super.packageIncomeAndLogData(protocol, user);
        getFullMonthOtherData();
	}

    @Override
    protected void packageIncomeAndLogForRedeemData(WmsInveTransaProtocol protocol, UserBean user)
	{
        super.packageIncomeAndLogForRedeemData(protocol, user);
        getFullMonthOtherData();
	}
	
    @Override
    protected void packageIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                                    Date extendDate, UserBean user)
	{
        super.packageIncomeAndLogForAutoExtend(protocol, oldProtocol, extendDate, user);
        getFullMonthOtherData();
	}

    /**
    * @Title: packageAndHandleIncomeAndLogData
    * @Description: 月付-满月奖励重新生成收益时，准备额外需要的数据
    * @param protocol 协议信息
    * @param user 登录用户信息
    * @author: jinzhm
    * @time:2017年1月6日 下午1:04:14
    * @see com.zx.emanage.inve.util.CountIncomeAbstract#packageAndHandleIncomeAndLogData(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.sframe.util.vo.UserBean)
    * history:
    * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    protected void packageAndHandleIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
    {
        super.packageAndHandleIncomeAndLogData(protocol, user);
        getFullMonthOtherData();
    }

    /**
     * 获得其他计算时需要的数据
     *
     * @author 金志明
     * @date 2017年10月12日 上午11:46:23
     */
    private void getFullMonthOtherData()
    {
        returnRate = IncomeUtil.getCategoryReturnRate(category);
        // 根据上单产品表主键查询产品利率设置信息集合
        List<WmsInvePruductRebateWay> pruductRebateWayList = IncomeUtil.getWmsInvePruductRebateWayList(category.getWms_inve_pruduct_category_id());
        dataMap.put("rebateWayList", pruductRebateWayList);
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 获得月付-满月奖励方式的奖励利率
     * @param month 满月数量
     * @return 奖励利率
     * @author: jinzhm
     * @time:2017年1月12日 下午3:58:11
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int)
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month)
    {
        List<WmsInvePruductRebateWay> rebateWays = (List<WmsInvePruductRebateWay>) dataMap.get("rebateWayList");
        return getFullMonthBonusReturnRate(month, rebateWays);
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 获得奖励利率
     * @param month 满月数量
     * @param rebateWayList 月付奖励设置信息集合
     * @return 奖励利率
     * @author: jinzhm
     * @time:2017年2月21日 下午1:20:30
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int, java.util.List)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month, List<WmsInvePruductRebateWay> rebateWayList)
    {
        BigDecimal bonusRate = BigDecimal.ZERO;
        for (WmsInvePruductRebateWay rebateWay : rebateWayList)
        {
            if (month == rebateWay.getFull_month())
            {
                bonusRate = rebateWay.getBonus_rate();
            }
        }
        return bonusRate;
    }

}
