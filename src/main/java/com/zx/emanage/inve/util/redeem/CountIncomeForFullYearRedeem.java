package com.zx.emanage.inve.util.redeem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeForFullYearRedeem
 * 模块名称：年付赎回处理类
 * @Description: 内容摘要：年付赎回，预约赎回，赎回取消，赎回流程完成时处理方法
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public class CountIncomeForFullYearRedeem extends CountIncomeRedeemAbstract
{

    /**
     * @Title: packageRedeemDataForOrderRedeem
     * @Description: 封装年付预约赎回影响客户收益处理时需要的数据
     * @return 赎回时机数据对象
     * @author: jinzhm
     * @time:2016年12月28日 上午10:15:30
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#packageRedeemDataForOrderRedeem()
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    @Override
    protected CountIncomeRedeemTimeData packageRedeemDataForOrderRedeem()
    {
        CountIncomeRedeemTimeData data = super.packageRedeemDataForOrderRedeem();

        // 特殊数据map数据对象
        Map<String, Object> dataMap = new HashMap<String, Object>();

        // 查询年付产品利率设置信息
        WmsInvePruductYearPaySpecial paySpecial = new WmsInvePruductYearPaySpecial();
        paySpecial.setWms_inve_pruduct_category_id(category.getWms_inve_pruduct_category_id());
        List<WmsInvePruductYearPaySpecial> paySpecials = IncomeUtil.getWmsInvePruductYearPaySpecialDao()
                                                                   .getListByEntity(paySpecial);

        if (!paySpecials.isEmpty())
        {// 如果年付1年产品的话此集合为空。
            dataMap.put("paySpecial", paySpecials.get(0));
        }
        // 给赎回时机数据对象设置特殊数据map
        data.setDataMap(dataMap);
        return data;
    }

    /**
     * @Title: orderRedeem
     * @Description: 年付产品预约赎回时客户收益处理方法
     * @param redeemData 赎回数据对象
     * @author: jinzhm
     * @time:2016年12月29日 上午10:17:58
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#orderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public void orderRedeem(CountIncomeRedeemData redeemData)
    {
        // 初始化客户收益处理时需要用到的数据
        initRedeemData(redeemData);
        // 获得赎回时机数据对象
        CountIncomeRedeemTimeData redeemTimeData = packageRedeemDataForOrderRedeem();

        redeemTimeData.setProductAccount(protocol.getProduct_account().subtract(redeemDetail.getRedeem_amount()));
        // 获得赎回时机客户收益处理接口对象
        CountIncomeRedeemTime incomeRedeemTime = CountIncomeRedeemFactory.getCountIncomeRedeemTime(redeemTimeData);
        // 根据不同赎回时机处理客户收益
        updIncomeList = incomeRedeemTime.handleIncomeOrderRedeemForFullYear(redeemTimeData);
        // 需要处理续期的老单据
        incomeRedeemTime.handleIncomeOrderRedeemAfterExtendForYear(redeemTimeData);
        // 持久化
        persistIncomeForOrderRedeem();
    }

    /**
     * @Title: cancelOrderRedeem
     * @Description: 取消年付产品预约赎回时客户收益处理方法
     * @param redeemData 赎回数据对象
     * @author: jinzhm
     * @time:2016年12月29日 上午10:18:03
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#cancelOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
    */
    @Override
    public void cancelOrderRedeem(CountIncomeRedeemData redeemData)
    {
        // 初始化客户收益处理时需要用到的数据
        initRedeemData(redeemData);
        // 获得赎回时机数据对象
        CountIncomeRedeemTimeData redeemTimeData = packageRedeemDataForOrderRedeem();

        redeemTimeData.setProductAccount(protocol.getProduct_account());
        // 获得赎回时机客户收益处理接口对象
        CountIncomeRedeemTime incomeRedeemTime = CountIncomeRedeemFactory.getCountIncomeRedeemTime(redeemTimeData);
        // 根据不同赎回时机处理客户收益
        updIncomeList = incomeRedeemTime.handleIncomeCancelOrderRedeemForFullYear(redeemTimeData);
        // 需要处理续期的老单据
        incomeRedeemTime.handleIncomeOrderRedeemAfterExtendForYear(redeemTimeData);
        // 持久化
        persistIncomeForOrderRedeem();
    }

    /**
     * @Title: finishOrderRedeem
     * @Description: 年付产品，预约赎回流程在财务回款后处理客户收益
     * @param redeemData 赎回时机对象
     * @author: jinzhm
     * @time:2018年1月13日 下午5:28:51
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeemAbstract#finishOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
    */
    @Override
    public void finishOrderRedeem(CountIncomeRedeemData redeemData)
    {
        // 初始化客户收益处理需要用到的数据
        initRedeemData(redeemData);
        // 将数据封装成赎回时机数据对象
        CountIncomeRedeemTimeData data = packageRedeemDataForOrderRedeem();
        // 根据赎回时机处理客户收益
        CountIncomeRedeemFactory.getCountIncomeRedeemTime(data).handleYearIncomeAfterFinancePaymentForOrderRedeem(data);
    }

}
