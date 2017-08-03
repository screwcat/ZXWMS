package com.zx.emanage.inve.util.redeem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeForFullMonthRedeem
 * 模块名称：月付-满月产品赎回时，客户收益变化功能实现类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public class CountIncomeForFullMonthRedeem extends CountIncomeRedeemAbstract implements
        CountIncomeGetBonusReturnRateInterface
{

    /**
     * @Title: packageRedeemDataForOrderRedeem
     * @Description: 查询月付-满月奖励方式预约赎回时处理客户收益需要的数据
     * @return 赎回时处理客户收益需要的数据对象
     * @author: jinzhm
     * @time:2016年12月26日 下午5:39:01
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#packageRedeemDataForOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
    */
    @Override
    protected CountIncomeRedeemTimeData packageRedeemDataForOrderRedeem()
    {
        // 先调用超类方法，获得已给通用属性赋值的对象，在下面再继续给不通用的属性赋值
        CountIncomeRedeemTimeData redeemTimeData = super.packageRedeemDataForOrderRedeem();
        // 封装准备数据map
        Map<String, Object> dataMap = new HashMap<String, Object>();
        // 根据上单产品表主键查询产品利率设置信息集合
        List<WmsInvePruductRebateWay> pruductRebateWayList = IncomeUtil.getWmsInvePruductRebateWayList(category.getWms_inve_pruduct_category_id());

        // 月基础利率（%）; 月基础利率不等于null的话取月基础利率，等于null的话取产品年华利率除12
        returnRate = category.getBasic_monthly_rate() != null ? category.getBasic_monthly_rate()
                                                             : category.getCategory_return_rate()
                                                                       .divide(new BigDecimal("12"), 8,
                                                                               RoundingMode.HALF_UP);
        redeemTimeData.setReturnRate(returnRate);
        // 将准备数据封装到返回的map中
        dataMap.put("rebateWayList", pruductRebateWayList);
        redeemTimeData.setDataMap(dataMap);
        // 给赎回时机客户收益处理数据对象设置获得奖励利率的接口
        redeemTimeData.setCountIncomeGetBonusReturnRateInterface(this);
        return redeemTimeData;
    }

    /**
     * @Title: getBonusReturnRate
     * @Description: 获得奖励利率
     *      月付-满月奖励时，根据满月数量查询奖励利率设置信息
     * @param dataMap 数据对象，map对象中必须包括满月数量month和奖励利率设置信息rebateWayList
     * @return 奖励利率
     * @author: jinzhm
     * @time:2016年12月28日 上午10:30:15
     * @see com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface#getBonusReturnRate(java.util.Map)
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    public BigDecimal getBonusReturnRate(Map<String, Object> dataMap)
    {
        List<WmsInvePruductRebateWay> rebateWayList = (List<WmsInvePruductRebateWay>) dataMap.get("rebateWayList");
        int fullMonth = (int) dataMap.get("month");
        BigDecimal bonusRate = BigDecimal.ZERO;
        for (WmsInvePruductRebateWay rebateWay : rebateWayList)
        {
            if (fullMonth == rebateWay.getFull_month())
            {
                bonusRate = rebateWay.getBonus_rate();
            }
        }
        return bonusRate;
    }
}
