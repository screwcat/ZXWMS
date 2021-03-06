package com.zx.emanage.inve.util.redeem;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.time.CountIncomeEndMonthNotOverRedeem;
import com.zx.emanage.inve.util.redeem.time.CountIncomeEndMonthOverRedeem;
import com.zx.emanage.inve.util.redeem.time.CountIncomeExtendMonthRedeem;
import com.zx.emanage.inve.util.redeem.time.CountIncomeExtendPublicMonthRedeem;
import com.zx.emanage.inve.util.redeem.time.CountIncomeNotOverRedeem;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeemFactory
 * 模块名称：赎回时客户收益变更工厂类
 * @Description: 内容摘要：根据产品类型及奖励方式返回赎回时客户收益变更功能实现类
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public class CountIncomeRedeemFactory
{
    /**
     * @Title: getCountIncomeRedeem
     * @Description: 根据产品类型及奖励方式获得客户收益变更功能实现类对象
     * @param redeemData 赎回客户收益变更请求数据对象
     * @return 赎回客户收益变更具体实现类对象
     * @author: jinzhm
     * @time:2016年12月26日 下午2:32:59
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static CountIncomeRedeem getCountIncomeRedeem(CountIncomeRedeemData redeemData)
    {
        // 赎回客户收益变更接口对象
        CountIncomeRedeem countIncomeRedeem = null;

        // 根据上单产品表主键查询产品信息对象
        WmsInvePruductCategory category = IncomeUtil.getWmsInvePruductCategoryByProdId(redeemData.getRedeemDetail()
                                                                                                 .getWms_inve_transa_prod_id());

        // 如果是月付产品
        if (category.getCategory_interest_pay_method() == 1)
        {
            // 如果是满月奖励方式
            if (category.getCategory_rebate_way() == 1)
            {
                // 创建月付-满月奖励方式实现类对象
                countIncomeRedeem = new CountIncomeForFullMonthRedeem();
            }
            // 如果是累计存量奖励方式
            else
            {
                // 创建月付-累计存量奖励方式实现类对象
                countIncomeRedeem = new CountIncomeForTotalRedeem();
            }
        }
        // 如果是年付产品
        else
        {
            // 创建年付方式实现类对象
            countIncomeRedeem = new CountIncomeForFullYearRedeem();
        }

        return countIncomeRedeem;
    }

    /**
     * @Title: getCountIncomeRedeemTime
     * @Description: 根据赎回时机返回对应的赎回影响客户收益处理类
     * @param redeemTimeData 赎回时机数据对象
     * @return 赎回时机接口对象
     * @author: jinzhm
     * @time:2016年12月27日 下午5:33:48
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    static CountIncomeRedeemTime getCountIncomeRedeemTime(CountIncomeRedeemTimeData redeemTimeData)
    {
        // 赎回时机接口对象
        CountIncomeRedeemTime redeemTime = null;

        // 赎回信息
        WmsInveRedeem redeem = redeemTimeData.getRedeem();
        // 协议信息
        WmsInveTransaProtocol protocol = redeemTimeData.getProtocol();
        
        // 原始支付日期
        Date oldDateOfPayment = redeemTimeData.getOldDateOfPayment();
        
        // 赎回日期的月份和协议到期日期的月份相同（到期月份赎回）
        if (DateUtil.getLastDayOfMonth(redeem.getRedeem_date())
                    .compareTo(DateUtil.getLastDayOfMonth(protocol.getEnd_of_date())) == 0)
        {
            /**
             * 2016-07-01之前的单据，上单当日没有收益，赎回日有收益
             * 2016-07-01（含）之后的单据，上单日有收益，赎回日没有收益
             * 2016-05-10上单，2017-05-10结束，2017-05-10赎回是到期赎回
             * 2016-07-10上单，2017-07-09结束，2017-07-09赎回是未到期赎回
             */
            /*
             * 如果原始上单时间晚于或等于2016-07-01且赎回时间大于到期时间
             * 或原始上单时间早于2016-07-01且赎回时间大于或等于到期时间
             */
            // 到期月份到期或超期赎回
            if ((IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeem.getRedeem_date()
                                                                            .compareTo(protocol.getEnd_of_date()) > 0)
                || (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment) && redeem.getRedeem_date()
                                                                                .compareTo(protocol.getEnd_of_date()) >= 0))
            {
                redeemTime = new CountIncomeEndMonthOverRedeem();
            }
            // 到期月份未到期赎回
            else
            {
                redeemTime = new CountIncomeEndMonthNotOverRedeem();
            }
        }
        // 赎回日期的月份大于协议到期日期的月份（超期赎回）
        else if (DateUtil.getLastDayOfMonth(redeem.getRedeem_date())
                         .compareTo(DateUtil.getLastDayOfMonth(protocol.getEnd_of_date())) > 0)
        {
            // 赎回日期
            Date redeemDate = redeemTimeData.getRedeem().getRedeem_date();
            /*
             * 公益收益只给到期后一个月。
             * 如4月5日到期，公益收益给到5月5日。
             * 2016-07-01之前上单单据，赎回当天有收益，5月5日或之后赎回，5天公益收益设置成赎回收益已支付，且给几天的央行活期利率；5月赎回，日期小于5号，公益收益设置成已终止，给几天赎回公益收益
             * 2016-07-01之后上单单据，赎回当天无收益，5月5日之后赎回，5天公益收益设置成赎回收益已支付，且给几天央行活期利率；5月赎回，日期小于等于5号，公益收益设置成已终止，给几天赎回公益收益
             */
            // 如果2016-07-01之前上单单据，赎回日期大于到期日期加一个月或2016-07-01之后上单单据，赎回日期大于等于到期日期加一个月
            if (DateUtil.getLastDayOfMonth(redeemDate)
                        .compareTo(DateUtil.getLastDayOfMonth(DateUtils.addMonths(protocol.getEnd_of_date(), 1))) == 0
                && ((IncomeUtil.isLaterThanMagicDate(redeemTimeData.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(protocol.getEnd_of_date(),
                                                                                                                                       1)) > 0) || (!IncomeUtil.isLaterThanMagicDate(redeemTimeData.getOldDateOfPayment()) && redeemDate.compareTo(DateUtils.addMonths(protocol.getEnd_of_date(),
                                                                                                                                                                                                                                                                       1)) >= 0)))
            {
                redeemTime = new CountIncomeExtendPublicMonthRedeem();
            }
            else
            {
                redeemTime = new CountIncomeExtendMonthRedeem();
            }
        }
        // 赎回日期的月份小于协议到期日期的月份（未到期赎回）
        else
        {
            redeemTime = new CountIncomeNotOverRedeem();
        }
        return redeemTime;
    }
}
