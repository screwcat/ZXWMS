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

import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 年付产品
 * 
 * @author 金志明
 * @date 2017年10月12日 下午2:42:58
 */
public class CountIncomeForFullYear extends CountIncomeAbstract
{

    @Override
	protected Map<String, Object> handleIncomeAndLogForAutoExtend()
	{
        Map<String, Object> resMap = new HashMap<String, Object>();

        // 续期生成新单据的收益信息和交易日志集合
        List<WmsInveTransaLog> transaLogList = new ArrayList<WmsInveTransaLog>();
        List<WmsInveTransaIncome> transaIncomeList = new ArrayList<WmsInveTransaIncome>();

        // 年付产品利率
        WmsInvePruductYearPaySpecial paySpecial = (WmsInvePruductYearPaySpecial) dataMap.get("paySpecial");

        // 支付日期作为理财开始日期
        Date startDate = protocol.getDate_of_payment();
        // 理财结束日期
        Date endDate = protocol.getEnd_of_date();
        // 收益应付日期
        Date returnDate = null;

        // 年付利率
        BigDecimal returnRate = BigDecimal.ZERO;
        // 收益金额
        BigDecimal income = BigDecimal.ZERO;
        // 投资金额
        BigDecimal productAccount = protocol.getProduct_account();

        // 收益天数
        int incomeDays = 0;
        // 支付状态
        String payStatus = PAY_STATUS_NOT_PAY;

        Calendar cal = Calendar.getInstance();
        // cal作为循环用时间，初始值设置开理财开始日期
        cal.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        // calEnd为理财结束日期
        calEnd.setTime(endDate);

        // 第一条，理财上单的日志
        transaLogList.add(this.setWmsInveTransaLog(startDate, TRANSA_START, new BigDecimal(0), new BigDecimal(0),
                                                       protocol, user));

        int year = 1;
        while (cal.compareTo(calEnd) < 0)
        {
            // 获得年付利率
            returnRate = IncomeUtil.getCategoryReturnRate(category, paySpecial, year);
            // 计算基本收益
            income = IncomeUtil.computeIncome(productAccount, returnRate);
            // 收益天数
            incomeDays = DateUtil.getBetweenDays(cal.getTime(), DateUtil.setDatebyCalendar(cal.getTime(), 12));
            // 将循环的cal向后移12个月
            cal.setTime(DateUtil.setDatebyCalendar(cal.getTime(), 12));

            // 大于是因为07-01或之后单据，等于是因为07-01之前的单据
            if (cal.compareTo(calEnd) >= 0)
            {
                // 最后一年收益日期是理财结束日期
                returnDate = endDate;
            }
            else
            {
                // 如果是2016-07-01之后单据，结束日期是支付日期的前一天
                if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), -1));
                    // 不是最后一年收益日期是月末
                    // 修改成不是月末
                    returnDate = cal.getTime();
                    // 为了下次循环还原cal
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), 1));
                }
                else
                {
                    // 不是2016-07-01之后的单据，收益日期是支付日期的月末
                    // 修改成不是月末
                    returnDate = cal.getTime();
                }
            }

            payStatus = PAY_STATUS_NOT_PAY;

            // 如果有老单据收益信息
            if (!oldTransaIncomeList.isEmpty())
            {
                // 已付的或已生成的公益收益金额
                BigDecimal publicIncome = BigDecimal.ZERO;
                // 循环老单据收益信息
                for (int i = 0; i < oldTransaIncomeList.size(); i++)
                {
                    // 老单据收益
                    WmsInveTransaIncome transaIncome = oldTransaIncomeList.get(i);
                    // 如果是公益收益
                    if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 如果新单据年收益的收益应付日期当月的老单据公益收益已经支付，新单据收益设置成已支付
                        if (DateUtil.getLastDayOfMonth(returnDate)
                                    .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) == 0)
                        {
                            // 如果是已支付
                            if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                            {
                                payStatus = PAY_STATUS_EXTEND_ALREADY_PAY;
                            }
                        }
                    }
                }
                // 新单据年付收益金额-老单据公益收益
                income = income.subtract(publicIncome);
            }

            transaIncomeList.add(generateTransaIncome(returnDate, null, income, BigDecimal.ZERO, returnRate,
                                                      BigDecimal.ZERO,
                                                      incomeDays, payStatus, 12 * year));

            year++;
        }

        resMap.put("wmsInveTransaLogs", transaLogList);
        resMap.put("wmsInveTransaIncomes", transaIncomeList);
        return resMap;
	}

	@Override
	public Map<String, Object> handleIncomeAndLog()
	{
		Map<String, Object> resMap = new HashMap<String, Object>();

		List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<>();
		List<WmsInveTransaIncome> wmsInveTransaIncomes = new ArrayList<>();

		WmsInvePruductYearPaySpecial paySpecial = (WmsInvePruductYearPaySpecial) dataMap.get("paySpecial");// 年付特产品利率

        // String payStatus = PAY_STATUS_NOT_PAY;// 支付状态
		String operateItem = CountIncome.TRANSA_START;
		int daysOfCalculation = 0;// 收益天数

		Date nowDate = DateUtil.getDate10(new Date());// 当前时间
		Date startDate = protocol.getDate_of_payment();// 支付日期作为理财开始日期
		Date endDate = protocol.getEnd_of_date();// 理财结束日期
		Date oldPayOfDate = "".equals(dataMap.get("old_day_of_payment")) || dataMap.get("old_day_of_payment") == null ? startDate
				: DateUtil.strDate(dataMap.get("old_day_of_payment") + "", "yyyy-MM-dd");// 获得续期前支付日期，如果不是续期单据此处是本单据支付日期
		Date returnDate = null; // 收益日期

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);// cal作为循环用时间，初始值设置开理财开始日期
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(endDate);// calEnd为理财结束日期

		BigDecimal categoryReturnRate = BigDecimal.ZERO;// 产品年化利率
		BigDecimal income = BigDecimal.ZERO;// 应收收益
		BigDecimal productAccount = protocol.getProduct_account();// 计算时金额

		// 第一条，理财上单的日志
		wmsInveTransaLogs.add(this.setWmsInveTransaLog(startDate, operateItem, new BigDecimal(0),
				new BigDecimal(0), protocol, user));

		int year = 1;
		while (cal.compareTo(calEnd) < 0)
		{
			// 获取产品年化利率
			// 如果获得年付设置产品利率为0，则获取产品年化利率
            categoryReturnRate = IncomeUtil.getCategoryReturnRate(category, paySpecial, year);

			// 收益计算：收益 = 金额 * 年化利率 /100
			income = productAccount.multiply(categoryReturnRate)
					.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);

            daysOfCalculation = DateUtil.getBetweenDays(cal.getTime(), DateUtil.setDatebyCalendar(cal.getTime(), 12));// 收益天数
			cal.setTime(DateUtil.setDatebyCalendar(cal.getTime(), 12));// 将循环的cal向后移12个月

			/*
			 * 2016-10-20修改，如果是最后一年returnDate是endDate，其他时候returnDate和endDate相同但年份不同
			 */
			// 如果是最后一年returnDate是endDate，其他时候returnDate是payofdate的月末
			if (cal.compareTo(calEnd) >= 0)
			{// 大于是因为07-01或之后单据，等于是因为07-01之前的单据
				returnDate = endDate; // 最后一年收益日期是理财结束日期
				operateItem = CountIncome.TRANSA_INCOME;// 理财收益
			}
			else
			{
				// 如果是2016-07-01之后单据，结束日期是支付日期的前一天
                if (IncomeUtil.isLaterThanMagicDate(oldPayOfDate))
				{
					cal.setTime(DateUtil.getDateAddDays(cal.getTime(), -1));
					// 不是最后一年收益日期是月末
					// 修改成不是月末
					returnDate = cal.getTime();
					// 为了下次循环还原cal
					cal.setTime(DateUtil.getDateAddDays(cal.getTime(), 1));
				}
				else
				{
					// 不是2016-07-01之后的单据，收益日期是支付日期的月末
					// 修改成不是月末
					returnDate = cal.getTime();
				}
				operateItem = CountIncome.TRANSA_INCOME;
			}

			log.debug("##################" + DateUtil.date2String(returnDate, "yyyy-MM-dd") + "##################"
					+ income + "##################");

			// 如果生成收益的时间（年月，不计较日）小于生成收益的当前时间的（年月），生成收益记录的支付状态为已支付
            // payStatus =
            // DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(nowDate))
            // < 0 ? PAY_STATUS_ALREADY_PAY
            // : PAY_STATUS_NOT_PAY;

            wmsInveTransaIncomes.add(setWmsInveTransaIncomeRemarks(setWmsInveTransaIncome(new java.sql.Date(
                                                                                                            returnDate.getTime()),
                                                                                          null, new BigDecimal(0),
                                                                                          new BigDecimal(0),
                                                                                          categoryReturnRate, income,
                                                                                          daysOfCalculation,
                                                                                          PAY_STATUS_NOT_PAY,
                                                                                          protocol, category, user),
                                                                   BigDecimal.ZERO, returnDate, 12 * year));

			year++;
		}

		resMap.put("wmsInveTransaIncomes", wmsInveTransaIncomes);
		resMap.put("wmsInveTransaLogs", wmsInveTransaLogs);

		return resMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> handleIncomeAndLogForRedeem()
	{
		
		WmsInvePruductYearPaySpecial paySpecial = (WmsInvePruductYearPaySpecial) dataMap.get("paySpecial");//年付特产品利率
		List<WmsInveTransaIncome> wmsInveTransaIncomes = (List<WmsInveTransaIncome>) dataMap.get("wmsInveTransaIncomes");//初始化收益信息集合
		WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");// 获得赎回信息

        // 赎回时间
        Date redeemDate = (Date) dataMap.get("redeemDate");
        // 获得续期前支付日期，如果不是续期单据此处是本单据支付日期
        Date oldDateOfPayment = "".equals(dataMap.get("old_day_of_payment"))
                                || dataMap.get("old_day_of_payment") == null ? protocol.getDate_of_payment()
                                                                            : DateUtil.strDate(dataMap.get("old_day_of_payment")
                                                                                                       + "",
                                                                                               "yyyy-MM-dd");

		BigDecimal categoryReturnRate = BigDecimal.ZERO;//产品年化利率
		BigDecimal income = BigDecimal.ZERO;//应收收益
        BigDecimal productAccount = prod.getProduct_account() == null ? protocol.getProduct_account()
                                                                     : prod.getProduct_account();// 获得当前投资金额。

        // 标记是否生成赎回收益，false表示未生成
        boolean flag = false;
		
		//循环重新计算收益
		int year = 1;
		for (WmsInveTransaIncome wmsInveTransaIncome : wmsInveTransaIncomes) {
			//获取产品年化利率
			//如果获得年付设置产品利率为0，则获取产品年化利率
            categoryReturnRate = IncomeUtil.getCategoryReturnRate(category, paySpecial, year);
			
			//收益计算：收益 = 金额 * 年化利率 /100
			income = productAccount.multiply(categoryReturnRate).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);

			if (PAY_TYPE_NORMAL.equals(wmsInveTransaIncome.getPay_type())
					&& redeemDate.compareTo(protocol.getEnd_of_date()) < 0)
			{
                // 修改收益表
				wmsInveTransaIncome.setProduct_interest_account(income);
				wmsInveTransaIncome.setProduct_account(productAccount);
				wmsInveTransaIncome.setPayable_basic_income(income);
			}
			
			wmsInveTransaIncome.setLast_update_user_id(user.getUserId());
			wmsInveTransaIncome.setLast_update_timestamp(new Timestamp(new Date().getTime()));
			
            /**
             *  是正常收益的时候
             *  是一年产品的时候
             *  只要收益应付时间的月份大于等于赎回日期的月份就需要处理
             *  是两年产品的时候
             *  第一年收益应付日期月份赎回时，需要判断赎回日期和第一年收益应付日期
             *      2016-07-01之前上单单据收益到期日赎回，不修改该收益信息
             *      2016-07-01之后上单单据收益到期日赎回，修改该收益信息
             *  第二年收益应付日期的月份大于等于赎回日期的月份就需要处理
             *  
             */
            if (IncomeUtil.isOneYearNormalIncomeNeedHandleTransaIncomeForRedeem(wmsInveTransaIncome.getPay_type(),
                                                                                wmsInveTransaIncome.getReturn_date(),
                                                                                category.getCategory_deadline(),
                                                                                redeemDate)
                || IncomeUtil.isTwoYearNormalIncomeNeedHandleTransaIncomeForRedeem(wmsInveTransaIncome.getPay_type(),
                                                                                   wmsInveTransaIncome.getReturn_date(),
                                                                                   category.getCategory_deadline(),
                                                                                   redeemDate, year, oldDateOfPayment)
                || IncomeUtil.isPublicIncomeNeedHandleTransaIncomeForRedeem(wmsInveTransaIncome.getPay_type(),
                                                                            wmsInveTransaIncome.getReturn_date(),
                                                                            redeemDate))
            {

                if (DateUtil.getLastDayOfMonth(redeemDate)
                            .compareTo(DateUtil.getLastDayOfMonth(protocol.getEnd_of_date())) == 0)
                {
                    if (PAY_TYPE_NORMAL.equals(wmsInveTransaIncome.getPay_type()))
                    {
                        // 处理收益及生成赎回收益
                        handleAndGenerateRedeemIncome(wmsInveTransaIncome);
                        // 标记已经生成赎回收益
                        flag = true;
                    }
                    // 是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(wmsInveTransaIncome.getPay_type()))
                    {
                        // 不是已支付状态
                        if (!IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                        {
                            // 不是全部赎回
                            if (!"1".equals(redeem.getIs_fully_redeemed()))
                            {
                                // 用剩余投资金额计算公益收益
                                BigDecimal publicIncome = IncomeUtil.computePublicIncome(wmsInveTransaIncome.getDays_extend_income(),
                                                                                         productAccount,
                                                                                         IncomeUtil.getPublicProductReturnRate());
                                wmsInveTransaIncome.setProduct_account(productAccount);
                                wmsInveTransaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                                wmsInveTransaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                                IncomeUtil.setTransaIncomeUpdateData(wmsInveTransaIncome, user);
                            }
                            // 是全部赎回设置成已终止
                            else
                            {
                                // 将收益支付状态修改成已终止
                                wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);// 设置成已终止
                            }
                        }
                    }
                }
                else
                {
                    // 处理收益及生成赎回收益
                    handleAndGenerateRedeemIncome(wmsInveTransaIncome);
                    // 标记已经生成赎回收益
                    flag = true;
                }
            }
			else
			{
                // 全部赎回且不是已支付收益
                if ("1".equals(redeem.getIs_fully_redeemed()) && !IncomeUtil.isIncomeAlreadyPaid(wmsInveTransaIncome))
                {
                    // 设置成已终止
                    wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
				}
			}

            // 是正常收益
            if (PAY_TYPE_NORMAL.equals(wmsInveTransaIncome.getPay_type()))
            {
                /**
                 * 2016-07-01之前上单单据，到期日赎回即是协议到期赎回
                 * 2016-07-01（含）后上单单据，到期日赎回是未到期赎回
                 */
                // 大于等于2016-07-01上单单据
                if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    /*
                     * 第一年赎回日期小于等于收益应付日期即处理老单据收益
                     * 第二年赎回日期小于等于收益应付日期的月末最后一天处理老单据收益
                     */
                    if ((year == 1 && redeemDate.compareTo(wmsInveTransaIncome.getReturn_date()) <= 0)
                        || (year != 1 && redeemDate.compareTo(DateUtil.getLastDayOfMonth(wmsInveTransaIncome.getReturn_date())) <= 0))
                    {
                        handleExtendRedeemData(wmsInveTransaIncome);
                    }
                }
                // 小于于2016-07-01上单单据
                else
                {
                    /*
                     * 第一年赎回日期小于收益应付日期即处理老单据收益
                     * 第二年赎回日期小于收益应付日期的月末最后一天处理老单据收益 
                     */
                    if ((year == 1 && redeemDate.compareTo(wmsInveTransaIncome.getReturn_date()) < 0)
                        || (year != 1 && redeemDate.compareTo(DateUtil.getLastDayOfMonth(wmsInveTransaIncome.getReturn_date())) <= 0))
                    {
                        handleExtendRedeemData(wmsInveTransaIncome);
                    }
                }
            }
			
			year++;
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
	
    /**
     * @Title: handleExtendRedeemData
     * @Description: 年付续期后赎回，老单据数据处理
     * @param wmsInveTransaIncome 
     * @author: jinzhm
     * @time:2016年12月13日 下午2:38:33
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleExtendRedeemData(com.zx.emanage.util.gen.entity.WmsInveTransaIncome)
     * history:
     * 1、2016年12月13日 jinzhm 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    protected void handleExtendRedeemData(WmsInveTransaIncome wmsInveTransaIncome)
    {
        // 如果数据集合中没有extendDate，说明不是续期过来的单据，不需要做任何处理，直接返回。
        if (extendDate == null)
        {
            return;
        }

        // 续期时间
        // Date extendDate = (Date) dataMap.get("extendDate");
        // 赎回时间
        // Date redeemDate = (Date) dataMap.get("redeemDate");

        // 老单据对应需要修改的收益信息集合
        List<WmsInveTransaIncome> oldUpdTransaIncomeList = dataMap.containsKey("oldUpdTransaIncomeList") ? (List<WmsInveTransaIncome>) dataMap.get("oldUpdTransaIncomeList")
                                                                                                        : new ArrayList<WmsInveTransaIncome>();
        // 获得赎回信息
        WmsInveRedeem redeem = (WmsInveRedeem) dataMap.get("wmsInveRedeem");
        // 老单据对应的收益信息
        List<WmsInveTransaIncome> oldIncomeList = IncomeUtil.getTransaIncomeList(transa.getOld_last_wms_inve_transa_id());

        // 公益收益金额的和
        BigDecimal publicIncome = BigDecimal.ZERO;

        // 循环老收益
        for (int i = 0; i < oldIncomeList.size(); i++)
        {
            WmsInveTransaIncome oldTransaIncome = oldIncomeList.get(i);

            // 如果赎回当月的正常收益或公益收益是未支付状态的话设置成已终止
            if (DateUtil.getLastDayOfMonth(wmsInveTransaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) > 0
                && DateUtil.getNextYearDate(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date()))
                           .compareTo(DateUtil.getLastDayOfMonth(wmsInveTransaIncome.getReturn_date())) >= 0)
            {
                // 如果是全部赎回
                if ("1".equals(redeem.getIs_fully_redeemed()))
                {
                    // 如果收益是未支付收益，设置成已终止
                    if (IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                    {
                        oldTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        oldUpdTransaIncomeList.add(oldTransaIncome);
                    }
                }
                // 如果是部分赎回
                else
                {
                    // 如果是正常收益
                    if (PAY_TYPE_NORMAL.equals(oldTransaIncome.getPay_type()))
                    {
                        if (IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                        {
                            // 计算基本收益
                            BigDecimal oldIncome = IncomeUtil.computeIncome(wmsInveTransaIncome.getProduct_account(),
                                                                            oldTransaIncome.getIncome_rate());
                            // 投资金额
                            oldTransaIncome.setProduct_account(wmsInveTransaIncome.getProduct_account());
                            // 设置基本收益金额
                            oldTransaIncome.setPayable_basic_income(IncomeUtil.setScale(oldIncome));
                            oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(oldIncome));
                            // 修改老单据修改信息
                            IncomeUtil.setTransaIncomeUpdateData(oldTransaIncome, user);
                            oldUpdTransaIncomeList.add(oldTransaIncome);
                        }
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                    {
                        // 如果老单据公益收益未支付
                        if (IncomeUtil.isIncomeNotPaid(oldTransaIncome))
                        {
                            // 重新计算老单据公益收益
                            BigDecimal oldPublicIncome = IncomeUtil.computePublicIncome(oldTransaIncome.getDays_extend_income(),
                                                                                        wmsInveTransaIncome.getProduct_account(),
                                                                                        IncomeUtil.getPublicProductReturnRate());
                            // 修改老单据投资金额
                            oldTransaIncome.setProduct_account(wmsInveTransaIncome.getProduct_account());
                            // 修改老单据公益收益金额
                            oldTransaIncome.setExtend_income(IncomeUtil.setScale(oldPublicIncome));
                            oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(oldPublicIncome));
                            // 修改老单据修改信息
                            IncomeUtil.setTransaIncomeUpdateData(oldTransaIncome, user);
                            oldUpdTransaIncomeList.add(oldTransaIncome);
                            // 添加公益总和
                            publicIncome = publicIncome.add(oldPublicIncome);
                        }
                        // 如果老单据公益收益已经支付
                        else if (IncomeUtil.isIncomeAlreadyPaid(oldTransaIncome))
                        {
                            // 用剩余投资金额计算老单据收益
                            BigDecimal oldPublicIncome = IncomeUtil.computePublicIncome(oldTransaIncome.getDays_extend_income(),
                                                                                        wmsInveTransaIncome.getProduct_account(),
                                                                                        IncomeUtil.getPublicProductReturnRate());
                            // 添加公益总和
                            publicIncome = publicIncome.add(oldPublicIncome);
                        }
                    }
                }
            }
        }

        wmsInveTransaIncome.setPayable_basic_income(wmsInveTransaIncome.getPayable_basic_income()
                                                                       .subtract(IncomeUtil.setScale(publicIncome)));
        wmsInveTransaIncome.setProduct_interest_account(wmsInveTransaIncome.getProduct_interest_account()
                                                                           .subtract(IncomeUtil.setScale(publicIncome)));
        // 如果是全部赎回，当前收益设置成已终止
        if ("1".equals(redeem.getIs_fully_redeemed()))
        {
            wmsInveTransaIncome.setPay_status(PAY_STATUS_TERMINATE);
        }

        dataMap.put("oldUpdTransaIncomeList", oldUpdTransaIncomeList);

    }
	

	@Override
	public void packageIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
	{
		super.packageIncomeAndLogData(protocol, user);
		getFullYearOtherData();
	}

	@Override
	public void packageIncomeAndLogForRedeemData(WmsInveTransaProtocol protocol, UserBean user)
	{
		super.packageIncomeAndLogForRedeemData(protocol, user);
		getFullYearOtherData();
	}
	
	@Override
	protected void packageIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol, Date extendDate,
			UserBean user)
	{
		super.packageIncomeAndLogForAutoExtend(protocol, oldProtocol, extendDate, user);
		getFullYearOtherData();
	}
	
	private void getFullYearOtherData()
	{
        // 获得年付产品利率
        dataMap.put("paySpecial",
                    IncomeUtil.getWmsInvePruductYearPaySpecialByCategoryId(category.getWms_inve_pruduct_category_id()));
	}
	
    // /**
    // * @Title: deleteTransaLogForAutoExtend
    // * @Description: 年付续期时删除交易日志，如果删除的最后一条交易日志信息是公益交易日志，需要再删除倒数第二条的正常收益
    // * @param transaIncome
    // * @author: jinzhiming
    // * @time:2016年11月14日 下午2:51:11
    // * history:
    // * 1、2016年11月14日 jinzhiming 创建方法
    // */
    // @SuppressWarnings("unchecked")
    // @Override
    // protected void deleteTransaLogForAutoExtend(WmsInveTransaIncome
    // transaIncome)
    // {
    // // 获得交易日志dao
    // WmsInveTransaLogDao wmsInveTransaLogDao = (WmsInveTransaLogDao)
    // GlobalVal.ctx.getBean("wmsInveTransaLogDao");
    //
    // Map<String, Object> paramMap = new HashMap<String, Object>();
    // paramMap.put("wms_inve_transa_id", transaIncome.getWms_inve_transa_id());
    // paramMap.put("return_date",
    // DateUtil.getLastDayOfMonth(transaIncome.getReturn_date()));
    // // 首先删除最后一个交易日志
    // wmsInveTransaLogDao.deleteTransaLog(paramMap);
    //
    // // 年付的时候，公益交易日志是新插入的一个不是和正常收益合并的，因此如果上一个删除的是公益收益的交易日志话，需要再删除正常收益交易日志
    // if(PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
    // {
    // List<WmsInveTransaIncome> transaIncomes = (List<WmsInveTransaIncome>)
    // dataMap.get("wmsInveTransaIncomes");
    // WmsInveTransaIncome income = transaIncomes.get(transaIncomes.size() -
    // 2);//获得倒数第二个收益（正常收益）
    //
    // // 干掉交易日志
    // paramMap = new HashMap<String, Object>();
    // paramMap.put("wms_inve_transa_id", income.getWms_inve_transa_id());
    // paramMap.put("return_date",
    // DateUtil.getLastDayOfMonth(income.getReturn_date()));
    // wmsInveTransaLogDao.deleteTransaLog(paramMap);
    // }
    // }

    // /**
    // * @Title: handleExtendOldTransaIncomeData
    // * @Description: 处理续期后老单据收益信息
    // * 老单据对应的所有公益收益且是已支付的公益收益设置成已终止
    // * @author: jinzhm
    // * @time:2016年12月7日 下午1:20:36
    // * @see
    // com.zx.emanage.inve.util.CountIncomeAbstract#handleExtendOldTransaIncomeData()
    // * history:
    // * 1、2016年12月7日 jinzhm 创建方法
    // */
    // @Override
    // protected void handleExtendOldTransaIncomeData()
    // {
    // WmsInveTransaIncomeDao wmsInveTransaIncomeDao = (WmsInveTransaIncomeDao)
    // GlobalVal.ctx.getBean("wmsInveTransaIncomeDao");
    //
    // // 获得老单据多有收益信息（只包含基本收益和公益收益）
    // List<WmsInveTransaIncome> transaIncomeList = (List<WmsInveTransaIncome>)
    // dataMap.get("wmsInveTransaIncomes");
    // for (int i = 0; i < transaIncomeList.size(); i++)
    // {
    // WmsInveTransaIncome transaIncome = transaIncomeList.get(i);
    // // 如果是公益收益且是未支付状态
    // if (PAY_STATUS_NOT_PAY.equals(transaIncome.getPay_status())
    // && PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
    // {
    // // 如果是未支付的公益收益的话，设置成已终止
    // transaIncome.setPay_status(PAY_STATUS_TERMINATE);
    // wmsInveTransaIncomeDao.update(transaIncome);
    // }
    // }
    // }

    /**
     * @Title: packageAndHandleIncomeAndLogData
     * @Description: 为年付产品重新生成收益信息和交易信息准备数据
     * @param protocol 协议信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月6日 下午2:50:10
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#packageAndHandleIncomeAndLogData(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    protected void packageAndHandleIncomeAndLogData(WmsInveTransaProtocol protocol, UserBean user)
    {
        super.packageAndHandleIncomeAndLogData(protocol, user);
        
        getFullYearOtherData();
    }

    /**
     * @Title: reGenerateIncomeAndLog
     * @Description: 为年付产品重新生成收益信息
     * @return 生成的收益及交易日志集合
     * @author: jinzhm
     * @time:2017年1月6日 下午2:26:26
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#reGenerateIncomeAndLog()
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
    */
    @Override
    protected Map<String, Object> reGenerateIncomeAndLog()
    {
        // 要返回的数据集合
        Map<String, Object> rMap = new HashMap<String, Object>();
        // 生成交易日志集合
        List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<WmsInveTransaLog>();
        // 计算收益信息集合
        List<WmsInveTransaIncome> wmsInveTransaIncomes = new ArrayList<WmsInveTransaIncome>();

        // 年付产品利率
        WmsInvePruductYearPaySpecial paySpecial = (WmsInvePruductYearPaySpecial) dataMap.get("paySpecial");

        // 收益天数
        int daysOfCalculation = 0;

        // 支付日期作为理财开始日期
        Date startDate = protocol.getDate_of_payment();
        // 理财结束日期
        Date endDate = protocol.getEnd_of_date();
        // 收益应付时间
        Date returnDate = null;

        // cal作为循环用时间，初始值设置开理财开始日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        // calEnd为理财结束日期
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);

        // 产品年化利率
        BigDecimal categoryReturnRate = BigDecimal.ZERO;
        // 应收收益
        BigDecimal income = BigDecimal.ZERO;
        // 计算时金额
        BigDecimal productAccount = protocol.getProduct_account();

        // 第一条，理财上单的日志
        wmsInveTransaLogs.add(this.setWmsInveTransaLog(startDate, TRANSA_START, new BigDecimal(0), new BigDecimal(0),
                                                       protocol, user));

        int year = 1;
        while (cal.compareTo(calEnd) < 0)
        {
            // 获取产品年化利率
            // 如果获得年付设置产品利率为0，则获取产品年化利率
            categoryReturnRate = IncomeUtil.getCategoryReturnRate(category, paySpecial, year);

            // 收益计算：收益 = 金额 * 年化利率 /100
            income = productAccount.multiply(categoryReturnRate).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);

            // 收益天数
            daysOfCalculation = DateUtil.getBetweenDays(cal.getTime(), DateUtil.setDatebyCalendar(cal.getTime(), 12));
            // 将循环的cal向后移12个月
            cal.setTime(DateUtil.setDatebyCalendar(cal.getTime(), 12));

            // 如果是最后一年returnDate是endDate，其他时候returnDate是payofdate的月末
            if (cal.compareTo(calEnd) >= 0)
            {
                // 大于是因为07-01或之后单据，等于是因为07-01之前的单据
                // 最后一年收益日期是理财结束日期
                returnDate = endDate;
            }
            else
            {
                // 如果是2016-07-01之后单据，结束日期是支付日期的前一天
                if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), -1));
                    // 不是最后一年收益日期是月末
                    // 修改成不是月末
                    returnDate = cal.getTime();
                    // 为了下次循环还原cal
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), 1));
                }
                else
                {
                    // 不是2016-07-01之后的单据，收益日期是支付日期的月末
                    // 修改成不是月末
                    returnDate = cal.getTime();
                }
            }

            // 封装生成的收益信息
            wmsInveTransaIncomes.add(generateTransaIncome(returnDate, null, income, BigDecimal.ZERO,
                                                          categoryReturnRate,
                                                          BigDecimal.ZERO, daysOfCalculation, PAY_STATUS_NOT_PAY,
                                                          12 * year));
            year++;
        }

        // 将生成的收益集合和交易日志集合保存到要持久化的集合中
        rMap.put("wmsInveTransaIncomes", wmsInveTransaIncomes);
        rMap.put("wmsInveTransaLogs", wmsInveTransaLogs);
        return rMap;
    }

    /**
     * @Title: handleTransaIncomeForNewExtend
     * @Description: 年付产品新单据续期在做预约续期时处理客户收益
     *      年付新单据续期时只需要将到期当月公益收益设置成已终止即可
     * @param transaId 理财上单主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月11日 上午10:49:39
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleTransaIncomeForNewExtend(int)
     * history:
     * 1、2017年1月11日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForNewExtend(int transaId, UserBean user)
    {
        // 获得产品公益收益信息
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(transaId, "'" + PAY_TYPE_PUBLIC
                                                                                              + "'");
        // 获得产品到期时间
        Date endDate = IncomeUtil.getTransaProtocol(transaId).getEnd_of_date();
        // 循环所有公益收益
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 如果公益收益是到期当月的公益收益
            if (DateUtil.getLastDayOfMonth(endDate)
                        .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) == 0)
            {
                // 如果公益收益是未支付状态
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 将公益收益设置成已终止状态
                    transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                    IncomeUtil.setTransaIncomeUpdateData(transaIncome, user);
                    IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                }
            }
        }
    }

    /**
     * @Title: handleTransaIncomeForCancelNewExtend
     * @Description: 年付产品新单据续期在取消预约续期时处理客户收益
     * @param transaId 理财上单主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年1月11日 上午10:51:06
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleTransaIncomeForCancelNewExtend(int)
     * history:
     * 1、2017年1月11日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForCancelNewExtend(int transaId, UserBean user)
    {
        // 获得产品公益收益信息
        List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(transaId, "'" + PAY_TYPE_PUBLIC
                                                                                              + "'");
        // 获得产品到期时间
        Date endDate = IncomeUtil.getTransaProtocol(transaId).getEnd_of_date();
        // 循环所有公益收益
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 如果公益收益是到期当月的公益收益
            if (DateUtil.getLastDayOfMonth(endDate)
                        .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) == 0)
            {
                // 如果公益收益不是已支付状态
                if (!IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    // 将公益收益设置成未支付状态
                    transaIncome.setPay_status(PAY_STATUS_NOT_PAY);
                    IncomeUtil.setTransaIncomeUpdateData(transaIncome, user);
                    IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                }
            }
        }
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 年付没有奖励，当前直接返回0
     * @param month 满月数量
     * @return 0
     * @author: jinzhm
     * @time:2017年1月12日 下午4:13:08
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int)
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month)
    {
        return getFullMonthBonusReturnRate(month, null);
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 获得奖励利率
     * @param month 满月数量
     * @param rebateWayList 月付奖励设置信息集合
     * @return 奖励利率
     * @author: jinzhm
     * @time:2017年2月21日 下午1:37:35
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int, java.util.List)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month, List<WmsInvePruductRebateWay> rebateWayList)
    {
        return BigDecimal.ZERO;
    }

    /**
     * @Title: computeIncome
     * @Description: 计算某个产品投资多少金额时的收益情况
     * @param categoryId 产品id
     * @param productAccount 投资金额（单位：万元）
     * @return 返回收益情况
     * @author: jinzhm
     * @time:2017年2月21日 下午1:39:32
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#computeIncome(int, java.math.BigDecimal, java.util.Date, java.util.Date)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    public List<Map<String, Object>> computeIncome(int categoryId, BigDecimal productAccount, Date startDate,
                                                   Date endDate)
    {
        // 获得产品信息
        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryDao().get(categoryId);
        // 收益情况集合
        List<Map<String, Object>> rMapList = new ArrayList<Map<String, Object>>();
        // 年付特产品利率
        WmsInvePruductYearPaySpecial paySpecial = IncomeUtil.getWmsInvePruductYearPaySpecialByCategoryId(categoryId);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);// cal作为循环用时间，初始值设置开理财开始日期
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);// calEnd为理财结束日期

        BigDecimal categoryReturnRate = BigDecimal.ZERO;// 产品年化利率
        BigDecimal income = BigDecimal.ZERO;// 应收收益

        Map<String, Object> incomeMap = null;

        int year = 1;
        while (cal.compareTo(calEnd) < 0)
        {
            incomeMap = new HashMap<String, Object>();
            // 获取产品年化利率
            // 如果获得年付设置产品利率为0，则获取产品年化利率
            categoryReturnRate = IncomeUtil.getCategoryReturnRate(category, paySpecial, year);

            // 收益计算：收益 = 金额 * 年化利率 /100
            income = productAccount.multiply(categoryReturnRate).divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);
            
            incomeMap.put("dueIncome", IncomeUtil.setScale(income));
            incomeMap.put("bonusIncome", BigDecimal.ZERO);

            cal.setTime(DateUtil.setDatebyCalendar(cal.getTime(), 12));// 将循环的cal向后移12个月

            // 如果是最后一年returnDate是endDate，其他时候returnDate是payofdate的月末
            if (cal.compareTo(calEnd) >= 0)
            {
                // 大于是因为07-01或之后单据，等于是因为07-01之前的单据
                incomeMap.put("returnDate", endDate);
            }
            else
            {
                // 如果是2016-07-01之后单据，结束日期是支付日期的前一天
                if (IncomeUtil.isLaterThanMagicDate(startDate))
                {
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), -1));
                    // 不是最后一年收益日期是月末
                    // 修改成不是月末
                    incomeMap.put("returnDate", cal.getTime());
                    // 为了下次循环还原cal
                    cal.setTime(DateUtil.getDateAddDays(cal.getTime(), 1));
                }
                else
                {
                    // 不是2016-07-01之后的单据，收益日期是支付日期的月末
                    // 修改成不是月末
                    incomeMap.put("returnDate", cal.getTime());
                }
            }

            rMapList.add(incomeMap);
            year++;
        }

        return rMapList;
    }
}
