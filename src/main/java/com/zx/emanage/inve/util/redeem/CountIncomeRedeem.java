package com.zx.emanage.inve.util.redeem;

import com.zx.emanage.inve.util.IncomeGlobal;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeem
 * 模块名称：客户收益在赎回时变化功能接口
 * @Description: 内容摘要：赎回时对客户收益变化相关处理抽象方法定义。
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public interface CountIncomeRedeem extends IncomeGlobal
{

    /**
     * @Title: orderRedeem
     * @Description: 预约赎回时，对客户收益影响变更处理提供方法
     * @param redeemData 赎回时客户收益变更请求数据对象
     * @author: jinzhm
     * @time:2016年12月26日 下午2:44:42
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
    */
    public void orderRedeem(CountIncomeRedeemData redeemData);

    /**
     * @Title: cancelOrderRedeem
     * @Description: 取消预约赎回时，对客户收益影响变更处理提供方法
     * @param redeemData 赎回时客户收益变更请求数据对象
     * @author: jinzhm
     * @time:2016年12月26日 下午2:39:25
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public void cancelOrderRedeem(CountIncomeRedeemData redeemData);

    /**
     * @Title: finishOrderRedeem
     * @Description: 预约赎回流程完成（财务回款完成）后，对客户收益进行处理
     * @param redeemData 赎回对客户收益变更请求数据对象
     * @author: jinzhm
     * @time:2016年12月27日 下午2:07:08
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public void finishOrderRedeem(CountIncomeRedeemData redeemData);
}
