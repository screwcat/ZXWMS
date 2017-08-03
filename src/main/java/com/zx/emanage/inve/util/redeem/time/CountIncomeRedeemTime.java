package com.zx.emanage.inve.util.redeem.time;

import java.util.List;

import com.zx.emanage.inve.util.IncomeGlobal;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeemTime
 * 模块名称：根据赎回时机客户收益处理接口
 * @Description: 内容摘要：赎回或预约赎回时客户收益处理相关方法
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public interface CountIncomeRedeemTime extends IncomeGlobal
{
    /**
     * @Title: handleIncomeForRedeem
     * @Description: 不同赎回时机对客户收益影响的处理
     * @param redeemTimeData 赎回时机数据对象
     * @return 处理后的收益信息
     * @author: jinzhm
     * @time:2016年12月27日 下午5:39:22
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public List<WmsInveTransaIncome> handleIncomeForRedeem(CountIncomeRedeemTimeData redeemTimeData);

    /**
     * @Title: handleIncomeOrderRedeem
     * @Description: 预约赎回时对客户收益影响处理
     * @param redeemTimeData 赎回时机数据对象
     * @return 要修改的收益信息
     * @author: jinzhm
     * @time:2016年12月28日 下午1:03:36
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public List<WmsInveTransaIncome> handleIncomeOrderRedeem(CountIncomeRedeemTimeData redeemTimeData);

    /**
     * @Title: handleIncomeOrderRedeemForFullYear
     * @Description: 年付产品预约赎回时对客户收益影响处理
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午8:38:24
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    public List<WmsInveTransaIncome> handleIncomeOrderRedeemForFullYear(CountIncomeRedeemTimeData data);

    /**
     * @Title: handleIncomeCancelOrderRedeem
     * @Description: 预约赎回取消时客户收益影响处理
     * @param redeemTimeData 赎回时机数据对象
     * @return 要修改的收益信息
     * @author: jinzhm
     * @time:2016年12月28日 下午1:41:01
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeem(CountIncomeRedeemTimeData redeemTimeData);

    /**
     * @Title: handleIncomeCancelOrderRedeemForFullYear
     * @Description: 年付产品预约赎回取消是对客户收益影响处理
     * @param data 赎回时机数据对象
     * @return 要修改的收益信息集合
     * @author: jinzhm
     * @time:2016年12月29日 上午9:34:03
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    public List<WmsInveTransaIncome> handleIncomeCancelOrderRedeemForFullYear(CountIncomeRedeemTimeData data);

    /**
     * @Title: handleIncomeOrderRedeemAfterExtend
     * @Description: 预约赎回到续期当月时对老单据收益信息处理方法
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月28日 下午1:03:55
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public void handleIncomeOrderRedeemAfterExtend(CountIncomeRedeemTimeData data);

    /**
     * @Title: handleIncomeOrderRedeemAfterExtendForYear
     * @Description: 续期后做预约赎对老单据收益信息处理方法
     * @param data 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月29日 上午11:18:47
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    public void handleIncomeOrderRedeemAfterExtendForYear(CountIncomeRedeemTimeData data);

    /**
     * @Title: handleIncomeAfterFinancePaymentForOrderRedeem
     * @Description: 在预约赎回单据财务回款后对客户收益影响处理方法
     * @param data 赎回时机请求对象
     * @author: jinzhm
     * @time:2016年12月28日 下午1:09:54
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public void handleIncomeAfterFinancePaymentForOrderRedeem(CountIncomeRedeemTimeData data);

    /**
     * @Title: handleYearIncomeAfterFinancePaymentForOrderRedeem
     * @Description: 年付产品在预约赎回单据财务回款后对客户收益影响处理方法
     * @param data 赎回时机请求对象
     * @author: jinzhm
     * @time:2018年1月13日 下午3:07:18
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    public void handleYearIncomeAfterFinancePaymentForOrderRedeem(CountIncomeRedeemTimeData data);
}
