package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.CountIncomeRedeemData;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeemTimeAbstract
 * 模块名称：根据赎回时机处理客户收益抽象类
 * @Description: 内容摘要：根据赎回初级处理客户收益的通用方法抽取
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public abstract class CountIncomeRedeemTimeAbstract implements CountIncomeRedeemTime
{
    /**
     * 赎回收益
     */
    protected WmsInveTransaIncome redeemTransaIncome;

    /**
     * 理财赎回交易日志
     */
    protected WmsInveTransaLog redeemTransaLog;

    /**
     * 理财结束交易日志
     */
    protected WmsInveTransaLog endTransaLog;

    /**
     * 受赎回影响要修改的收益信息集合
     */
    protected List<WmsInveTransaIncome> updRedeemTransaIncomeList = new ArrayList<WmsInveTransaIncome>();

    /**
     * @Title: handleIncomeOrderRedeem
     * @Description: 处理预约赎回时和取消预约赎回时对收益的影响</br>
     *     1. 预约赎回是未到期赎回（不是到期月份）</br>
     *     2. 预约赎回是到期月份未到期赎回</br>
     *     3. 到期月份到期日或超期赎回</br>
     *     4. 跨月超期赎回</br>
     *     只有1和2的情况使用
     *     3和4在子类中重写了此方法
     * @param dataMap 处理数据时需要用到的准备数据对象
     * @param productAccount 投资金额
     * @author: jinzhm
     * @time:2016年12月27日 下午1:23:04
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeem(CountIncomeRedeemTimeData redeemTimeData)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 赎回时间
        Date redeemDate = redeemTimeData.getRedeem().getRedeem_date();
        // 协议开始时间
        Date startDate = redeemTimeData.getProtocol().getDate_of_payment();
        // 协议结束时间
        Date endDate = redeemTimeData.getProtocol().getEnd_of_date();

        // 收益信息
        BigDecimal income = BigDecimal.ZERO;
        // 公益收益
        BigDecimal publicIncome = BigDecimal.ZERO;
        // 奖励利率
        BigDecimal bonusReturnRate = BigDecimal.ZERO;
        // 奖励金额
        BigDecimal bonus = BigDecimal.ZERO;

        // 满月数
        int month = 0;
        // 收益天数
        int incomeDays = 0;
        // 当月自然天数
        int dayOfMonth = 0;

        // 收益信息
        WmsInveTransaIncome transaIncome = null;

        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(redeemTimeData.getTransa()
                                                                                            .getWms_inve_transa_id());

        // 循环原收益信息进行处理
        for (int i = 0; i < incomeList.size(); i++)
        {
            // 获得收益信息对象
            transaIncome = incomeList.get(i);

            // 如果是到期月份
            if (DateUtil.getLastDayOfMonth(endDate).compareTo(transaIncome.getReturn_date()) == 0)
            {
                month = redeemTimeData.getProtocol().getProduct_deadline();
            }
            // 不是到期月份
            else if (DateUtil.getLastDayOfMonth(startDate).compareTo(transaIncome.getReturn_date()) != 0)
            {
                month++;
            }

            // 只修改收益应付日期的月份大于等于赎回申请日期月份的收益信息
            if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                        .compareTo(DateUtil.getLastDayOfMonth(redeemDate)) >= 0)
            {
                // 28好打款，29号预约30号赎回时，不能重新计算当月收益，因为当月收益已经支付
                // 如果收益未支付才重新计算处理
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是基本收益
                    if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                    {
                        // 获得收益天数
                        incomeDays = transaIncome.getDays_of_calculation();
                        // 获得当月自然天数
                        dayOfMonth = DateUtil.getDaysOfMonth(transaIncome.getReturn_date());

                        // 计算收益
                        income = IncomeUtil.computeIncome(incomeDays, dayOfMonth, redeemTimeData.getProductAccount(),
                                                          redeemTimeData.getReturnRate());

                        // 设置满月数量
                        redeemTimeData.getDataMap().put("month", month);
                        // 获得奖励利率
                        bonusReturnRate = redeemTimeData.getCountIncomeGetBonusReturnRateInterface()
                                                        .getBonusReturnRate(redeemTimeData.getDataMap());
                        // 计算奖励收益
                        bonus = IncomeUtil.computeBonusIncome(bonusReturnRate, redeemTimeData.getProductAccount());

                        /*
                         * 如果是续期后的新单据，且收益的应付日期的月份小于续期时间的月份的话，不能只计算基本收益。
                         * 应该在基本收益中减去公益收益
                         */
                        // 如果是续期后预约赎回，且收益应付日期的月份小于续期日期的月份
                        if (IncomeUtil.isOrderRedeemAfterExtendInSameMonth(redeemTimeData.getExtendDate(),
                                                                           redeemTimeData.getRedeem().getRedeem_date())
                            && DateUtil.getLastDayOfMonth(redeemTimeData.getExtendDate())
                                       .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) >= 0)
                        {
                            // 计算公益收益
                            publicIncome = IncomeUtil.computePublicIncome(incomeDays,
                                                                          redeemTimeData.getProductAccount(),
                                                                          redeemTimeData.getPublicReturnRate());
                            // 基本收益=基本收益-公益收益
                            income = income.subtract(publicIncome);
                        }

                        // 设置基本收益，奖励收益，总收益，投资金额等信息
                        transaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                        transaIncome.setPayable_reward_income(IncomeUtil.setScale(bonus));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonus)));
                        transaIncome.setProduct_account(redeemTimeData.getProductAccount());
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, redeemTimeData.getUser());
                        // 将要修改收益保存到要修改的收益信息集合中
                        updIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        // 获得收益天数
                        incomeDays = transaIncome.getDays_extend_income();

                        // 计算公益收益
                        publicIncome = IncomeUtil.computePublicIncome(incomeDays, redeemTimeData.getProductAccount(),
                                                                      redeemTimeData.getPublicReturnRate());

                        // 设置公益收益，总收益和投资金额
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_account(redeemTimeData.getProductAccount());
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, redeemTimeData.getUser());
                        // 将要修改收益保存到要修改的收益信息集合中
                        updIncomeList.add(transaIncome);
                    }
                }
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: handleIncomeOrderRedeemForFullYear
     * @Description: 年付产品预约赎回时对客户收益影响处理
     *      对未支付的应付日期的月份比赎回日期月份大或等于的收益信息进行处理
     *      只有预约赎回日期是到期月份未到期赎回和一般未到期赎回时通用此方法
     *      到期月份到期日或超期赎回和跨月到期赎回需要单独在子类中重写处理
     *      
     *      不管是部分赎回或全部赎回下面处理都是根据剩余金额重新计算未支付的正常或公益收益
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午8:39:43
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        // 要修改的收益信息集合
        List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();
        // 年付特产品利率
        WmsInvePruductYearPaySpecial paySpecial = (WmsInvePruductYearPaySpecial) data.getDataMap().get("paySpecial");
        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(data.getTransa().getWms_inve_transa_id());

        // 利率
        BigDecimal returnRate = BigDecimal.ZERO;

        int year = 0;
        // 循环正常和公益收益
        for (WmsInveTransaIncome transaIncome : incomeList)
        {
            // 如果是正常收益year+1（目前只有最多两年产品，year最大只能到2）
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
             *  
             */
            if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem().getRedeem_date(),
                                                                       year, data.getCategory().getCategory_deadline(),
                                                                       data.getOldDateOfPayment()))
            {
                // 获得利率
                returnRate = getCategoryReturnRate(paySpecial, year).compareTo(BigDecimal.ZERO) == 0 ? data.getCategory()
                                                                                                           .getCategory_return_rate()
                                                                                                    : getCategoryReturnRate(paySpecial,
                                                                                                                            year);
                // 如果收益信息是未支付
                if (IncomeUtil.isIncomeNotPaid(transaIncome))
                {
                    // 如果是正常收益
                    if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                    {
                        BigDecimal income = IncomeUtil.computeIncome(data.getProductAccount(), returnRate);

                        // 设置基本收益，总收益，投资金额
                        transaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(income));
                        transaIncome.setProduct_account(data.getProductAccount());
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        // 添加到要修改的收益集合中
                        updIncomeList.add(transaIncome);
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                    {
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                 data.getProductAccount(),
                                                                                 data.getPublicReturnRate());

                        // 设置公益收益，总收益，投资金额
                        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        transaIncome.setProduct_account(data.getProductAccount());
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        // 添加到要修改的收益集合中
                        updIncomeList.add(transaIncome);
                    }
                }
            }
        }

        return updIncomeList;
    }

    /**
     * @Title: handleIncomeCancelOrderRedeem
     * @Description: 取消预约赎回时对客户收益影响处理方法
     *      如果赎回日期月份的收益已经打款
     *      需要在赎回月份新增一条收益信息
     *      该新增收益信息的投资金额应该是赎回金额
     *      收益金额应该是按照赎回金额计算的
     * @param redeemTimeData 赎回时机对象
     * @return 要修改收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午1:52:49
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeCancelOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeem(CountIncomeRedeemTimeData redeemTimeData)
    {
        // 获得取消预约后要修改的数据
        List<WmsInveTransaIncome> updIncomeList = handleIncomeOrderRedeem(redeemTimeData);

        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(redeemTimeData.getTransa()
                                                                                            .getWms_inve_transa_id());
        // 循环收益信息查看赎回日期月份收益是不是已经打款，如果是已支付状态的话需要新增一条收益信息
        for (WmsInveTransaIncome transaIncome : incomeList)
        {
            // 赎回日期和收益应付日期是同一个月
            if (DateUtil.getLastDayOfMonth(redeemTimeData.getRedeem().getRedeem_date())
                        .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) == 0)
            {
                // 如果收益是已经支付
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    /*
                     * 如果收益已经支付
                     * 判断已支付收益的投资金额和取消预约赎回后的投资金额-预约赎回时的赎回金额 进行比较
                     * 如果相等说明先做的预约赎回，之后按照剩余投资金额进行的打款
                     * 取消预约赎回时需要将已打款的差额不全
                     */
                    if (transaIncome.getProduct_account()
                                    .compareTo(redeemTimeData.getProductAccount()
                                                             .subtract(redeemTimeData.getRedeemDetail()
                                                                                     .getRedeem_amount())) == 0)
                    {
                        // 如果是正常收益
                        if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                        {
                            // 投资金额
                            BigDecimal productAccount = redeemTimeData.getRedeemDetail().getRedeem_amount();
                            // 基本收益
                            BigDecimal income = IncomeUtil.computeIncome(transaIncome.getDays_of_calculation(),
                                                                         DateUtil.getDaysOfMonth(transaIncome.getReturn_date()),
                                                                         productAccount, transaIncome.getIncome_rate());
                            // 奖励收益
                            BigDecimal bonusIncome = IncomeUtil.computeBonusIncome(transaIncome.getBonus_rate(),
                                                                                   productAccount);
                            // 公益收益
                            BigDecimal publicIncome = BigDecimal.ZERO;
                            // 生成收益信息对象
                            WmsInveTransaIncome newTransaIncome = generateTransaIncome(productAccount, income,
                                                                                       bonusIncome, publicIncome,
                                                                                       PAY_TYPE_NORMAL, transaIncome,
                                                                                       redeemTimeData);
                            // 设置备注信息
                            newTransaIncome.setRemarks(transaIncome.getRemarks());

                            updIncomeList.add(newTransaIncome);
                        }
                        // 如果是公益收益
                        else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                        {
                            // 投资金额
                            BigDecimal productAccount = redeemTimeData.getRedeemDetail().getRedeem_amount();
                            // 基本收益
                            BigDecimal income = BigDecimal.ZERO;
                            // 奖励收益
                            BigDecimal bonusIncome = BigDecimal.ZERO;
                            // 公益收益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                     productAccount,
                                                                                     redeemTimeData.getPublicReturnRate());
                            // 生成收益信息对象
                            WmsInveTransaIncome newTransaIncome = generateTransaIncome(productAccount, income,
                                                                                       bonusIncome, publicIncome,
                                                                                       PAY_TYPE_PUBLIC, transaIncome,
                                                                                       redeemTimeData);
                            // 设置备注信息
                            newTransaIncome.setRemarks(transaIncome.getRemarks());
                            updIncomeList.add(newTransaIncome);
                        }
                    }
                }
            }
        }
        return updIncomeList;
    }

    /**
     * @Title: handleIncomeCancelOrderRedeemForFullYear
     * @Description: 年付产品预约赎回取消时对客户收益影响处理
     *      还原预约时修改的收益信息。
     *      但是如果收益已经支付，不能修改，只能在新增一条赎回金额计算的收益信息
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午9:41:16
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeCancelOrderRedeemForFullYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeemForFullYear(CountIncomeRedeemTimeData data)
    {
        List<WmsInveTransaIncome> updTransaIncomeList = handleIncomeOrderRedeemForFullYear(data);

        // 查询正常和公益收益
        List<WmsInveTransaIncome> incomeList = IncomeUtil.getTransaIncomeList(data.getTransa().getWms_inve_transa_id());
        // 年数
        int year = 0;
        // 循环收益
        for (WmsInveTransaIncome transaIncome : incomeList)
        {
            // 如果是正常收益year+1
            if(PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
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
             *  
             */
            if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem().getRedeem_date(),
                                                                       year, data.getCategory().getCategory_deadline(),
                                                                       data.getOldDateOfPayment()))
            {
                // 收益如果已经支付
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    /*
                     *  判断支付收益是不是在预约赎回之后支付
                     *  如果支付收益的投资金额和取消预约赎回后的投资金额相同，说明是预约赎回之前支付不需要新增一条相当于赎回金额计算的收益
                     *  如果支付收益的投资金额等于取消预约赎回后要重新计算的投资金额减去预约赎回的金额的话说明是预约赎回后支付，这时需要新增一条相当于使用赎回金额计算的收益
                     */
                    // 如果已支付的收益金额=取消预约赎回后的投资金额-赎回金额，需要新增一条用赎回金额计算的收益信息（补已支付的差额）
                    if (transaIncome.getProduct_account().compareTo(data.getProductAccount()
                                                                        .subtract(data.getRedeemDetail()
                                                                                      .getRedeem_amount())) == 0)
                    {
                        if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                        {
                            // 投资金额
                            BigDecimal productAccount = data.getRedeemDetail().getRedeem_amount();
                            // 基本收益
                            BigDecimal income = IncomeUtil.computeIncome(productAccount, transaIncome.getIncome_rate());
                            // 奖励收益
                            BigDecimal bonusIncome = BigDecimal.ZERO;
                            // 公益收益
                            BigDecimal publicIncome = BigDecimal.ZERO;
                            // 生成收益信息对象
                            WmsInveTransaIncome newTransaIncome = generateTransaIncome(productAccount, income,
                                                                                       bonusIncome, publicIncome,
                                                                                       PAY_TYPE_NORMAL, transaIncome,
                                                                                       data);
                            // 设置备注信息
                            newTransaIncome.setRemarks(transaIncome.getRemarks());
                            updTransaIncomeList.add(newTransaIncome);
                        }
                        else
                        {
                            // 投资金额
                            BigDecimal productAccount = data.getRedeemDetail().getRedeem_amount();
                            // 基本收益
                            BigDecimal income = BigDecimal.ZERO;
                            // 奖励收益
                            BigDecimal bonusIncome = BigDecimal.ZERO;
                            // 公益收益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                     productAccount,
                                                                                     data.getPublicReturnRate());
                            // 生成收益信息对象
                            WmsInveTransaIncome newTransaIncome = generateTransaIncome(productAccount, income,
                                                                                       bonusIncome, publicIncome,
                                                                                       PAY_TYPE_PUBLIC, transaIncome,
                                                                                       data);
                            // 设置备注信息
                            newTransaIncome.setRemarks(transaIncome.getRemarks());
                            updTransaIncomeList.add(newTransaIncome);
                        }
                    }
                }
            }
        }

        return updTransaIncomeList;
    }

    /**
     * @Title: handleIncomeOrderRedeemAfterExtendForYear
     * @Description: 续期后做预约赎对老单据收益信息处理方法
     *      续期后做预约赎回，判断是不是需要处理
     *          预约赎回的赎回日期是续期当月，且老单据的收益还未支付时需要处理
     *      老单据收益需要根据剩余投资金额重新计算
     * @param data 
     * @author: jinzhm
     * @time:2016年12月29日 上午11:19:53
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeOrderRedeemAfterExtendForYear(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public void handleIncomeOrderRedeemAfterExtendForYear(CountIncomeRedeemTimeData data)
    {
        // 续期日期不是空，且续期日期和赎回日期是同一个月
        if (data.getExtendDate() != null
            && DateUtil.getLastDayOfMonth(data.getExtendDate())
                       .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) == 0)
        {
            // 查询老单据的收益信息
            List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                            .getOld_last_wms_inve_transa_id());
            // 年数
            int year = 0;
            // 循环老单据收益信息
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
                 *  
                 */
                if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem()
                                                                                             .getRedeem_date(), year,
                                                                           data.getCategory().getCategory_deadline(),
                                                                           data.getOldDateOfPayment()))
                {
                    // 如果收益是未支付收益
                    if (IncomeUtil.isIncomeNotPaid(transaIncome))
                    {
                        // 如果是正常收益
                        if (PAY_TYPE_NORMAL.equals(transaIncome.getPay_type()))
                        {
                            // 计算基本收益
                            BigDecimal income = IncomeUtil.computeIncome(data.getProductAccount(),
                                                                         transaIncome.getIncome_rate());
                            // 设置收益信息
                            transaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(income));
                            transaIncome.setProduct_account(data.getProductAccount());
                            IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                            IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                        }
                        // 如果是公益收益
                        else if (PAY_TYPE_PUBLIC.equals(transaIncome.getPay_type()))
                        {
                            // 计算公益收益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(transaIncome.getDays_extend_income(),
                                                                                     data.getProductAccount(),
                                                                                     data.getPublicReturnRate());
                            // 设置收益信息
                            transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                            transaIncome.setProduct_account(data.getProductAccount());
                            IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                            IncomeUtil.getWmsInveTransaIncomeDao().update(transaIncome);
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * @Title: handleIncomeOrderRedeemAfterExtend
     * @Description: 预约赎回时，检查是不是续期后预约赎回且老单据续期月份收益是未支付，如果是处理老单据收益信息
     * @param data 处理数据时需要用到的准备数据对象
     * @author: jinzhm
     * @time:2017年1月13日 下午4:39:14
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeOrderRedeemAfterExtend(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2017年1月13日 jinzhm 创建方法
     */
    public void handleIncomeOrderRedeemAfterExtend(CountIncomeRedeemTimeData data)
    {
        // 如果不是续期后当月预约赎回且收益是未支付状态的话，不需要处理直接返回
        if (!IncomeUtil.isOrderRedeemAfterExtendInSameMonth(data.getExtendDate(), data.getRedeem().getRedeem_date()))
        {
            return;
        }

        // 获得老单据信息
        WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao().get(data.getTransa()
                                                                           .getOld_last_wms_inve_transa_id());
        // 处理老单据收益信息
        handleOldTransaIncomeOrderRedeemAfterExtend(oldTransa, data.getRedeem().getRedeem_date(),
                                                    data.getProductAccount());
    }

    /**
     * @Title: handleOldTransaIncomeOrderRedeemAfterExtend
     * @Description: 处理老单据收益信息
     * @param transa 老单据信息
     * @param returnDate 收益日期
     * @param productAccount 投资金额
     * @author: jinzhm
     * @time:2017年1月13日 下午7:19:38
     * history:
     * 1、2017年1月13日 jinzhm 创建方法
     */
    private void handleOldTransaIncomeOrderRedeemAfterExtend(WmsInveTransa transa, Date returnDate,
                                                             BigDecimal productAccount)
    {
        // 获得单据收益信息
        List<WmsInveTransaIncome> oldTransaIncomeList = IncomeUtil.getTransaIncomeList(transa.getWms_inve_transa_id());
        // 循环老单据收益信息
        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
        {
            // 如果收益应付日期是同年同月
            if (DateUtil.getLastDayOfMonth(returnDate)
                        .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) == 0)
            {
                // 如果是未支付状态收益
                if (IncomeUtil.isIncomeNotPaid(oldTransaIncome.getPay_status()))
                {
                    // 如果是正常收益
                    if (PAY_TYPE_NORMAL.equals(oldTransaIncome.getPay_type()))
                    {
                        // 收益天数
                        int incomeDays = oldTransaIncome.getDays_of_calculation();
                        // 当月自然天数
                        int dayOfMonth = DateUtil.getDaysOfMonth(oldTransaIncome.getReturn_date());
                        // 重新计算收益
                        BigDecimal income = IncomeUtil.computeIncome(incomeDays, dayOfMonth, productAccount,
                                                                     oldTransaIncome.getIncome_rate());
                        // 奖励收益
                        BigDecimal bonus = IncomeUtil.computeBonusIncome(oldTransaIncome.getBonus_rate(),
                                                                         productAccount);

                        // 如果上一个单据收益信息中有当月的公益，需要减去公益
                        if (IncomeUtil.isOldTransaIncomeHasPublicIncome(transa, returnDate))
                        {
                            // 查找上一个单据信息
                            WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
                                                                .get(transa.getOld_last_wms_inve_transa_id());
                            // 递归调用处理上一个单据的收益信息
                            handleOldTransaIncomeOrderRedeemAfterExtend(oldTransa, returnDate, productAccount);
                            // 计算公益
                            BigDecimal publicIncome = IncomeUtil.computePublicIncome(incomeDays,
                                                                                     productAccount,
                                                                                     IncomeUtil.getPublicProductReturnRate());
                            // 正常收益减去公益收益
                            income = income.subtract(publicIncome);
                        }

                        // 投资金额
                        oldTransaIncome.setProduct_account(productAccount);
                        // 总收益
                        oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonus)));
                        // 基本收益
                        oldTransaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
                        // 奖励收益
                        oldTransaIncome.setPayable_reward_income(IncomeUtil.setScale(bonus));
                        // 修改收益信息
                        IncomeUtil.getWmsInveTransaIncomeDao().update(oldTransaIncome);
                    }
                    // 如果是公益收益
                    else if (PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
                    {
                        // 收益天数
                        int incomeDays = oldTransaIncome.getDays_extend_income();
                        // 计算公益收益
                        BigDecimal publicIncome = IncomeUtil.computePublicIncome(incomeDays, productAccount,
                                                                                 IncomeUtil.getPublicProductReturnRate());
                        // 设置公益收益
                        oldTransaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                        // 总收益
                        oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                        // 投资金额
                        oldTransaIncome.setProduct_account(productAccount);
                        // 修改收益信息
                        IncomeUtil.getWmsInveTransaIncomeDao().update(oldTransaIncome);
                    }
                }
                // 如果是续期不需要支付收益
                // else if
                // (PAY_STATUS_EXTEND_NO_NEED_PAY.equals(oldTransaIncome.getPay_status()))
                // {
                // // 查找上一个单据信息
                // WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
                // .get(transa.getOld_last_wms_inve_transa_id());
                // // 递归调用处理上一个单据的收益信息
                // handleOldTransaIncomeOrderRedeemAfterExtend(oldTransa,
                // returnDate, productAccount);
                // // 计算公益
                // BigDecimal publicIncome =
                // IncomeUtil.computePublicIncome(oldTransaIncome.getDays_extend_income(),
                // productAccount,
                // IncomeUtil.getPublicProductReturnRate());
                // // 修改投资金额
                // oldTransaIncome.setProduct_account(productAccount);
                // // 修改总收益
                // oldTransaIncome.setProduct_interest_account(IncomeUtil.setScale(publicIncome));
                // // 修改公益收益
                // oldTransaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
                // IncomeUtil.getWmsInveTransaIncomeDao().update(oldTransaIncome);
                // }
            }
        }
    }

    /**
     * @Title: handleIncomeAfterFinancePaymentForOrderRedeem
     * @Description: 在预约赎回单据财务回款后对客户收益影响处理方法
     *      根据赎回时机处理客户收益后持久化到数据库中
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月28日 下午1:11:49
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeAfterFinancePaymentForOrderRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public void handleIncomeAfterFinancePaymentForOrderRedeem(CountIncomeRedeemTimeData data)
    {
        // 处理客户收益
        handleIncomeAfterFinancePaymentForOrderRedeemAbstract(data);
        // 持久化到库中
        persistIncomeAfterFinancePaymentForOrderRedeem();
    }

    /**
     * @Title: handleYearIncomeAfterFinancePaymentForOrderRedeem
     * @Description: 年付产品在预约赎回财务回款后对客户收益影响处理方法
     * @param data 赎回时机对象
     * @author: jinzhm
     * @time:2018年1月13日 下午3:08:56
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleYearIncomeAfterFinancePaymentForOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
    */
    @Override
    public void handleYearIncomeAfterFinancePaymentForOrderRedeem(CountIncomeRedeemTimeData data)
    {
        // 处理客户收益
        handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract(data);
        // 持久化到库中
        persistIncomeAfterFinancePaymentForOrderRedeem();
    }

    /**
     * @Title: handleIncomeAfterFinancePaymentForOrderRedeemAbstract
     * @Description: 预约赎回在财务回款后，客户收益处理及赎回收益的生成
     *      部分赎回时，不需要处理收益信息
     *      全部赎回时，将收益信息设置成已终止
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月28日 下午3:29:44
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected void handleIncomeAfterFinancePaymentForOrderRedeemAbstract(CountIncomeRedeemTimeData data)
    {
        /*
         * 1. 处理原有的收益信息
         * 2. 生成赎回收益信息
         */
        // 如果是全部赎回
        if ("1".equals(data.getRedeem().getIs_fully_redeemed()))
        {
            // 查询要处理的收益信息集合（正常+公益）
            List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                            .getWms_inve_transa_id());
            // 循环收益集合处理收益
            for (WmsInveTransaIncome transaIncome : transaIncomeList)
            {
                // 如果收益应付日期月份大于等于赎回日期月份
                if (DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())
                            .compareTo(DateUtil.getLastDayOfMonth(data.getRedeem().getRedeem_date())) >= 0)
                {
                    // 不是已支付状态
                    if (!IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                    {
                        // 设置成已终止状态
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        generateRedeemLog(data);
        generateEndLog(data);
        generateRedeemIncome(data);
    }

    /**
     * @Title: handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract
     * @Description: 年付产品在预约赎回在财务回款后，客户收益处理及赎回收益的生成
     *      部分赎回时，不需要处理收益信息
     *      全部赎回时，将收益信息设置成已终止
     * @param data 
     * @author: jinzhm
     * @time:2018年1月13日 下午3:09:38
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    protected void handleYearIncomeAfterFinancePaymentForOrderRedeemAbstract(CountIncomeRedeemTimeData data)
    {
        /*
         * 1. 处理原有的收益信息
         * 2. 生成赎回收益信息
         */
        // 如果是全部赎回
        if ("1".equals(data.getRedeem().getIs_fully_redeemed()))
        {
            // 查询要处理的收益信息集合（正常+公益）
            List<WmsInveTransaIncome> transaIncomeList = IncomeUtil.getTransaIncomeList(data.getTransa()
                                                                                            .getWms_inve_transa_id());
            // 年数
            int year = 0;
            // 循环收益集合处理收益
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
                if (IncomeUtil.isYearIncomeNeedHandleTransaIncomeForRedeem(transaIncome, data.getRedeem()
                                                                                             .getRedeem_date(), year,
                                                                           data.getCategory().getCategory_deadline(),
                                                                           data.getOldDateOfPayment()))
                {
                    // 不是已支付状态
                    if (!IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                    {
                        // 设置成已终止状态
                        transaIncome.setPay_status(PAY_STATUS_TERMINATE);
                        IncomeUtil.setTransaIncomeUpdateData(transaIncome, data.getUser());
                        updRedeemTransaIncomeList.add(transaIncome);
                    }
                }
            }
        }
        generateRedeemLog(data);
        generateEndLog(data);
        generateRedeemIncome(data);
    }

    /**
     * @Title: generateRedeemLog
     * @Description: 生成理财赎回交易日志
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月30日 下午2:58:59
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    protected void generateRedeemLog(CountIncomeRedeemTimeData data)
    {
        redeemTransaLog = new WmsInveTransaLog();
        // 上单信息表主键
        redeemTransaLog.setWms_inve_transa_id(data.getTransa().getWms_inve_transa_id());
        // 本金变化
        redeemTransaLog.setProduct_account(data.getRedeemDetail().getRedeem_amount());
        // 操作事项
        redeemTransaLog.setOperate_item(CountIncome.TRANSA_REDEEM);
        // 变化日期
        redeemTransaLog.setChange_date(data.getRedeem().getRedeem_date());
        // 备注
        redeemTransaLog.setRemark("赎回单据&lt;" + data.getRedeem().getBill_code() + "&gt;");
        // 创建人
        redeemTransaLog.setCreate_user_id(data.getUser().getUserId());
        // 创建人姓名
        redeemTransaLog.setCreate_user_name(data.getUser().getRealname());
        // 创建时间
        redeemTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));
        // 赎回收益信息
        redeemTransaLog.setProduct_interest_account(getRedeemIncome(data));
    }

    /**
     * @Title: generateEndLog
     * @Description: 生成理财结束交易日志
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月30日 下午2:58:23
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    protected void generateEndLog(CountIncomeRedeemTimeData data)
    {
        endTransaLog = new WmsInveTransaLog();
        // 理财结束
        endTransaLog.setOperate_item(TRANSA_END);
        // 操作时间
        endTransaLog.setChange_date(data.getRedeem().getRedeem_date());
        // 上单主键
        endTransaLog.setWms_inve_transa_id(data.getTransa().getWms_inve_transa_id());
        // 本金变化
        endTransaLog.setProduct_account(data.getProtocol().getProduct_account());
        // 收益信息
        endTransaLog.setProduct_interest_account(BigDecimal.ZERO);
        // 创建用户id
        endTransaLog.setCreate_user_id(data.getUser().getUserId());
        // 创建用户姓名
        endTransaLog.setCreate_user_name(data.getUser().getRealname());
        // 创建时间
        endTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));
    }

    /**
     * @Title: generateRedeemIncome
     * @Description: 设置赎回收益信息
     * @param data 赎回时机对象
     * @author: jinzhm
     * @time:2016年12月29日 上午8:30:19
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    protected void generateRedeemIncome(CountIncomeRedeemTimeData data)
    {
        // 赎回收益对象
        redeemTransaIncome = new WmsInveTransaIncome();
        // 上单信息表主键
        redeemTransaIncome.setWms_inve_transa_id(data.getRedeemDetail().getWms_inve_transa_id());
        // 上单产品信息表主键
        redeemTransaIncome.setWms_inve_transa_prod_id(data.getRedeemDetail().getWms_inve_transa_prod_id());
        // 产品信息主键
        redeemTransaIncome.setWms_inve_pruduct_category_id(data.getCategory().getWms_inve_pruduct_category_id());
        // 客户身份证号
        redeemTransaIncome.setId_card(data.getProtocol().getB_id_card());
        // 客户名称
        redeemTransaIncome.setCus_name(data.getProtocol().getB_name());
        // 产品期限
        redeemTransaIncome.setProduct_deadline_month(data.getCategory().getCategory_deadline());
        // 应支付时间
        redeemTransaIncome.setReturn_date(data.getRedeem().getRedeem_date());
        // 赎回金额
        redeemTransaIncome.setProduct_account(data.getRedeemDetail().getRedeem_amount());
        // 赎回收益金额
        redeemTransaIncome.setProduct_interest_account(getRedeemIncome(data));
        // 实际支付时间
        redeemTransaIncome.setAct_return_date(data.getRedeem().getRedeem_date());
        // 支付状态为已支付
        redeemTransaIncome.setPay_status(PAY_STATUS_ALREADY_PAY);
        // 创建人
        redeemTransaIncome.setCreate_user_id(data.getUser().getUserId());
        // 创建人名称
        redeemTransaIncome.setCreate_user_name(data.getUser().getRealname());
        // 创建时间
        redeemTransaIncome.setCreate_timestamp(new Timestamp(new Date().getTime()));
        // 赎回收益天数
        redeemTransaIncome.setDays_of_calculation(data.getRedeemDetail().getDays_of_basic_income() == null ? 0
                                                                                                          : data.getRedeemDetail()
                                                                                                                .getDays_of_basic_income());
        // 奖励利率
        redeemTransaIncome.setBonus_rate(data.getRedeemDetail().getReward_income_rate() == null ? BigDecimal.ZERO
                                                                                               : data.getRedeemDetail()
                                                                                                     .getReward_income_rate());
        // 应付奖励收益
        redeemTransaIncome.setPayable_reward_income(data.getRedeemDetail().getPayable_reward_income() == null ? BigDecimal.ZERO
                                                                                                             : data.getRedeemDetail()
                                                                                                                   .getPayable_reward_income());
        // 应付基本收益
        redeemTransaIncome.setPayable_basic_income(data.getRedeemDetail().getPayable_basic_income() == null ? BigDecimal.ZERO
                                                                                                           : data.getRedeemDetail()
                                                                                                                 .getPayable_basic_income());
        // 公益收益
        redeemTransaIncome.setExtend_income(data.getRedeemDetail().getExtend_income() == null ? BigDecimal.ZERO
                                                                                             : data.getRedeemDetail()
                                                                                                   .getExtend_income());
        // 公益收益计算天数
        redeemTransaIncome.setDays_extend_income(data.getRedeemDetail().getDays_extend_income() == null ? 0
                                                                                                       : data.getRedeemDetail()
                                                                                                             .getDays_extend_income());
        // 基本收益利率
        redeemTransaIncome.setIncome_rate(data.getRedeemDetail().getBasic_inceom_rate() == null ? BigDecimal.ZERO
                                                                                               : data.getRedeemDetail()
                                                                                                     .getBasic_inceom_rate());
        // 活期利率收益天数
        redeemTransaIncome.setDays_current_income(data.getRedeemDetail().getDays_current_income() == null ? 0
                                                                                                         : data.getRedeemDetail()
                                                                                                               .getDays_current_income());
        // 活期利率
        redeemTransaIncome.setCurrent_income_rate(data.getRedeemDetail().getCurrent_income_rate() == null ? BigDecimal.ZERO
                                                                                                         : data.getRedeemDetail()
                                                                                                               .getCurrent_income_rate());
        // 活期收益
        redeemTransaIncome.setCurrent_income(data.getRedeemDetail().getCurrent_income() == null ? BigDecimal.ZERO
                                                                                               : data.getRedeemDetail()
                                                                                                     .getCurrent_income());
        // 设置收益卡主键
        redeemTransaIncome.setWms_inve_customer_card_id(data.getProd().getWms_inve_customer_card_id());
        redeemTransaIncome.setPay_type(PAY_TYPE_REDEEM);// 收益类型
    }

    /**
     * @Title: generateTransaIncome
     * @Description: 生成收益信息
     * @param productAccount 投资金额
     * @param income 基本收益
     * @param bonusIncome 奖励收益
     * @param publicIncome 公益收益
     * @param payType 收益类型
     * @param origTransaIncome 原收益信息
     * @param data 赎回时机数据对象
     * @return 生成的收益信息
     * @author: jinzhm
     * @time:2016年12月28日 下午5:55:56
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected WmsInveTransaIncome generateTransaIncome(BigDecimal productAccount, BigDecimal income,
                                                       BigDecimal bonusIncome, BigDecimal publicIncome, String payType,
                                                       WmsInveTransaIncome origTransaIncome,
                                                       CountIncomeRedeemTimeData data)
    {
        // 赎回收益对象
        WmsInveTransaIncome transaIncome = new WmsInveTransaIncome();
        // 上单信息表主键
        transaIncome.setWms_inve_transa_id(origTransaIncome.getWms_inve_transa_id());
        // 上单产品信息表主键
        transaIncome.setWms_inve_transa_prod_id(origTransaIncome.getWms_inve_transa_prod_id());
        // 产品信息主键
        transaIncome.setWms_inve_pruduct_category_id(origTransaIncome.getWms_inve_pruduct_category_id());
        // 客户身份证号
        transaIncome.setId_card(origTransaIncome.getId_card());
        // 客户名称
        transaIncome.setCus_name(origTransaIncome.getCus_name());
        // 产品期限
        transaIncome.setProduct_deadline_month(origTransaIncome.getProduct_deadline_month());
        // 应支付时间
        transaIncome.setReturn_date(origTransaIncome.getReturn_date());
        // 赎回金额
        transaIncome.setProduct_account(productAccount);
        // 赎回收益金额
        transaIncome.setProduct_interest_account(IncomeUtil.setScale(income.add(bonusIncome).add(publicIncome)));
        // 实际支付时间
        transaIncome.setAct_return_date(null);
        // 支付状态为已支付
        transaIncome.setPay_status(PAY_STATUS_NOT_PAY);
        // 创建人
        transaIncome.setCreate_user_id(data.getUser().getUserId());
        // 创建人名称
        transaIncome.setCreate_user_name(data.getUser().getRealname());
        // 创建时间
        transaIncome.setCreate_timestamp(new Timestamp(new Date().getTime()));
        // 赎回收益天数
        transaIncome.setDays_of_calculation(origTransaIncome.getDays_of_calculation());
        // 奖励利率
        transaIncome.setBonus_rate(origTransaIncome.getBonus_rate());
        // 应付奖励收益
        transaIncome.setPayable_reward_income(IncomeUtil.setScale(bonusIncome));
        // 应付基本收益
        transaIncome.setPayable_basic_income(IncomeUtil.setScale(income));
        // 公益收益
        transaIncome.setExtend_income(IncomeUtil.setScale(publicIncome));
        // 公益收益计算天数
        transaIncome.setDays_extend_income(origTransaIncome.getDays_extend_income());
        // 基本收益利率
        transaIncome.setIncome_rate(origTransaIncome.getIncome_rate());
        transaIncome.setPay_type(payType);// 收益类型
        // 设置收益卡主键
        transaIncome.setWms_inve_customer_card_id(data.getProd().getWms_inve_customer_card_id());
        return transaIncome;
    }

    /**
     * @Title: getRedeemIncome
     * @Description: 获取赎回收益金额
     * @param data 赎回时机对象
     * @return 赎回金额
     * @author: jinzhm
     * @time:2016年12月28日 下午4:30:41
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected abstract BigDecimal getRedeemIncome(CountIncomeRedeemTimeData data);

    /**
     * @Title: persistIncomeAfterFinancePaymentForOrderRedeem
     * @Description: 预约赎回在财务回款后，保存赎回收益信息和修改收益信息
     * @author: jinzhm
     * @time:2016年12月28日 下午3:31:31
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected void persistIncomeAfterFinancePaymentForOrderRedeem()
    {
        // 保存赎回收益
        IncomeUtil.getWmsInveTransaIncomeDao().save(redeemTransaIncome);
        // 循环修改收益信息
        for (WmsInveTransaIncome updTransaIncome : updRedeemTransaIncomeList)
        {
            IncomeUtil.getWmsInveTransaIncomeDao().update(updTransaIncome);
        }
        // 如果有理财赎回交易日志
        if (redeemTransaIncome != null)
        {
            IncomeUtil.getWmsInveTransaLogDao().save(redeemTransaLog);
        }
        // 如果有理财结束交易日志
        if (endTransaLog != null)
        {
            IncomeUtil.getWmsInveTransaLogDao().save(endTransaLog);
        }
    }

    /**
     * @Title: handleIncomeForRedeem
     * @Description: 正常赎回时客户收益处理
     *      目前赎回时客户收益处理还在老方法中，没有移到此处。以后会移到此处。
     * @param redeemTimeData 赎回时机数据对象
     * @return 收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午1:00:33
     * @see com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime#handleIncomeForRedeem(com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    public List<WmsInveTransaIncome> handleIncomeForRedeem(CountIncomeRedeemTimeData redeemTimeData)
    {
        return new ArrayList<WmsInveTransaIncome>();
    }

    /**
     * @Title: isRedeemMonthIncomeAlreadyPaid
     * @Description: 判断赎回日期当月收益是否已经支付
     * @param transaIncomeList 收益信息集合
     * @param redeemDate 赎回日期
     * @return 已经支付返回true，不是已支付返回false
     * @author: jinzhm
     * @time:2016年12月28日 下午5:38:33
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected boolean isRedeemMonthIncomeAlreadyPaid(List<WmsInveTransaIncome> transaIncomeList, Date redeemDate)
    {
        for (WmsInveTransaIncome transaIncome : transaIncomeList)
        {
            // 赎回日期和收益应付日期是同一个月
            if (DateUtil.getLastDayOfMonth(redeemDate)
                        .compareTo(DateUtil.getLastDayOfMonth(transaIncome.getReturn_date())) == 0)
            {
                if (IncomeUtil.isIncomeAlreadyPaid(transaIncome))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Title: getCategoryReturnRate
     * @Description: 根据年数获得年付产品利率
     * @param paySpecial 年付产品利率设置信息
     * @param year 年数
     * @return 获得第一年利率或第二年利率
     * @author: jinzhm
     * @time:2016年12月29日 上午9:13:03
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    private BigDecimal getCategoryReturnRate(WmsInvePruductYearPaySpecial paySpecial, int year)
    {
        if (paySpecial != null)
        {
            if (year == 1)
            {
                return paySpecial.getFirst_year_interest_rate() == null ? BigDecimal.ZERO
                                                                       : paySpecial.getFirst_year_interest_rate();
            }
            else
            {
                return paySpecial.getSecond_year_interest_rate() == null ? BigDecimal.ZERO
                                                                        : paySpecial.getSecond_year_interest_rate();
            }
        }
        return BigDecimal.ZERO;
    }
}
