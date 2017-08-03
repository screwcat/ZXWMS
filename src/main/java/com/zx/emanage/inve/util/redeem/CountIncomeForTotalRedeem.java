package com.zx.emanage.inve.util.redeem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import jodd.util.StringUtil;

import com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsSysProperty;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeForTotalRedeem
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public class CountIncomeForTotalRedeem extends CountIncomeRedeemAbstract
{

    /**
     * @Title: packageRedeemDataForOrderRedeem
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return 
     * @author: jinzhm
     * @time:2016年12月28日 上午10:16:11
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#packageRedeemDataForOrderRedeem()
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    protected CountIncomeRedeemTimeData packageRedeemDataForOrderRedeem()
    {
        CountIncomeRedeemTimeData redeemTimeData = super.packageRedeemDataForOrderRedeem();
        // 如果有原单据id，存储原单据id，如果不是存储现单据id
        int oldTransaId = transa.getOld_wms_inve_transa_id() == null ? transa.getWms_inve_transa_id()
                                                                    : transa.getOld_wms_inve_transa_id();

        // 卓年宝产品奖励1.5%的单据id
        WmsSysProperty property = IncomeUtil.getWmsSysPropertyDao().get(WMS_SYS_PROPERTY_SPECIAL_BONUS_ID);// 获得卓年宝产品奖励利率
        String specialTransaIds = property.getProperty_value();

        // 月基础利率（%）; 月基础利率不等于null的话取月基础利率，等于null的话取产品年华利率除12
        returnRate = category.getBasic_monthly_rate() != null ? category.getBasic_monthly_rate()
                                                             : category.getCategory_return_rate()
                                                                       .divide(new BigDecimal("12"), 8,
                                                                               RoundingMode.HALF_UP);
        redeemTimeData.setReturnRate(returnRate);

        // 封装特殊数据并设置给赎回时机对象
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("category", category);
        dataMap.put("oldTransaId", oldTransaId);
        dataMap.put("specialTransaIds", specialTransaIds);
        redeemTimeData.setDataMap(dataMap);

        redeemTimeData.setCountIncomeGetBonusReturnRateInterface(new CountIncomeGetBonusReturnRateInterface()
        {

            /**
             * @Title: getBonusReturnRate
             * @Description: 获得累计存量奖励方式的奖励利率
             * @param dataMap map数据对象
             *      map中必须包含category，specialTransaIds，oldTransaId，month
             * @return 返回奖励利率
             * @author: jinzhm
             * @time:2016年12月28日 上午10:21:56
             * @see com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface#getBonusReturnRate(java.util.Map)
             * history:
             * 1、2016年12月28日 jinzhm 创建方法
             */
            @Override
            public BigDecimal getBonusReturnRate(Map<String, Object> dataMap)
            {
                // 在map对象中将要使用的数据定义并取出
                WmsInvePruductCategory category = (WmsInvePruductCategory) dataMap.get("category");
                String specialTransaIds = String.valueOf(dataMap.get("specialTransaIds"));
                int oldTransaId = (int) dataMap.get("oldTransaId");
                int fullMonth = (int) dataMap.get("month");

                // 奖励利率
                BigDecimal bonusRate = BigDecimal.ZERO;
                // 如果是卓年宝产品且是满12个月
                if (SPECIAL_PRODUCT_ID == category.getWms_inve_pruduct_category_id() && fullMonth == 12)
                {
                    // 判断是不是特殊单据
                    if (!StringUtil.isEmpty(specialTransaIds))
                    {
                        String[] transaIdArr = specialTransaIds.split(",");
                        for (int i = 0; i < transaIdArr.length; i++)
                        {
                            if (transaIdArr[i].equals(oldTransaId))
                            {
                                // 如果是特殊单据返回奖励利率百1.5
                                bonusRate = new BigDecimal("1.5");
                                return bonusRate;
                            }
                        }
                    }
                    // 如果是不是特殊单据奖励利率返回百1
                    bonusRate = new BigDecimal("1");
                    return bonusRate;
                }
                return bonusRate;
            }
        });
        return redeemTimeData;
    }
}
