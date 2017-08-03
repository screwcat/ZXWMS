package com.zx.emanage.inve.util.redeem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTime;
import com.zx.emanage.inve.util.redeem.time.CountIncomeRedeemTimeData;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.platform.syscontext.util.StringUtil;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeemAbstract
 * 模块名称：客户收益在赎回时变化功能抽象类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public abstract class CountIncomeRedeemAbstract implements CountIncomeRedeem
{

    /**
     * 要修改的客户收益信息集合
     */
    protected List<WmsInveTransaIncome> updIncomeList = new ArrayList<WmsInveTransaIncome>();

    /**
     * 赎回信息
     */
    protected WmsInveRedeem redeem;

    /**
     * 赎回明细信息
     */
    protected WmsInveRedeemDetail redeemDetail;

    /**
     * 上单信息
     */
    protected WmsInveTransa transa;

    /**
     * 上单产品信息
     */
    protected WmsInveTransaProd prod;

    /**
     * 协议信息
     */
    protected WmsInveTransaProtocol protocol;

    /**
     * 产品信息
     */
    protected WmsInvePruductCategory category;

    /**
     * 产品利率
     */
    protected BigDecimal returnRate = BigDecimal.ZERO;

    /**
     * 公益产品利率
     */
    protected BigDecimal publicReturnRate = BigDecimal.ZERO;

    /**
     * 原始上单时间
     */
    protected Date oldDateOfPayment;

    /**
     * 续期时间
     */
    protected Date extendDate;

    /**
     * 登录用户信息
     */
    protected UserBean user;

    /**
     * @Title: packageRedeemDataForOrderRedeem
     * @Description: 为赎回客户收益处理准备数据
     * @return 预约赎回使用数据map对象
     * @author: jinzhm
     * @time:2016年12月26日 下午3:34:49
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    protected CountIncomeRedeemTimeData packageRedeemDataForOrderRedeem()
    {
        // 赎回时机对象
        CountIncomeRedeemTimeData redeemTimeData = new CountIncomeRedeemTimeData();

        // 将准备数据封装到赎回时机数据对象中
        redeemTimeData.setRedeem(redeem);
        // 赎回明细信息
        redeemTimeData.setRedeemDetail(redeemDetail);
        // 上单信息
        redeemTimeData.setTransa(transa);
        // 设置上单产品信息
        redeemTimeData.setProd(prod);
        // 协议信息
        redeemTimeData.setProtocol(protocol);
        // 正常收益利率
        redeemTimeData.setReturnRate(returnRate);
        // 公益收益利率
        redeemTimeData.setPublicReturnRate(publicReturnRate);
        // 续期时间
        redeemTimeData.setExtendDate(extendDate);
        // 原始支付时间
        redeemTimeData.setOldDateOfPayment(oldDateOfPayment);
        // 登录用户信息
        redeemTimeData.setUser(user);
        // 产品信息
        redeemTimeData.setCategory(category);

        return redeemTimeData;
    }

    /**
     * @Title: initRedeemData
     * @Description: 初始化赎回时客户收益处理要用到的通用数据
     * @param redeemData 赎回时客户收益处理数据对象
     * @author: jinzhm
     * @time:2016年12月28日 上午10:28:13
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    protected void initRedeemData(CountIncomeRedeemData redeemData)
    {
        // 初始化赎回信息和赎回明细信息和登录用户信息
        redeem = redeemData.getRedeem();
        redeemDetail = redeemData.getRedeemDetail();
        user = redeemData.getUserBean();

        // 获得上单产品表信息
        transa = IncomeUtil.getWmsInveTransaDao().get(redeemData.getRedeemDetail()
                                                                              .getWms_inve_transa_id());

        // 获得原始上单单据支付时间：没有原始上单的支付时间话就取当前上单单据的支付时间
        oldDateOfPayment = StringUtil.isBlank(transa.getOld_date_of_payment_str()) ? DateUtil.strDate(transa.getDate_of_payment_str(),
                                                                                                      null)
                                                                                  : DateUtil.strDate(transa.getOld_date_of_payment_str(),
                                                                                                     null);
        // 获得协议信息
        protocol = IncomeUtil.getTransaProtocol(transa.getWms_inve_transa_id());
        // 上单产品信息
        prod = IncomeUtil.getWmsInveTransaProdDao().get(protocol.getWms_inve_transa_prod_id());
        // 获得产品信息
        category = IncomeUtil.getWmsInvePruductCategoryByProdId(redeemDetail.getWms_inve_transa_prod_id());
        // 获得公益收益利率
        publicReturnRate = IncomeUtil.getPublicProductReturnRate();
        // 续期时间
        // 如果原始单据支付时间有值的话，获取续期时间。
        // 因为老产品续期该值有值，新产品续期该值没有值；新产品续期赎回时不需要考虑老产品
        if (transa.getOld_date_of_payment() != null)
        {
            extendDate = IncomeUtil.getTransaExtendDate(transa.getOld_last_wms_inve_transa_id());
        }
    }

    /**
     * @Title: persistIncomeForOrderRedeem
     * @Description: 将处理后的数据持久化到数据库中。
     * @author: jinzhm
     * @time:2016年12月26日 下午3:42:10
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    protected void persistIncomeForOrderRedeem()
    {
        // 循环要修改的收益信息集合
        for (WmsInveTransaIncome updTransaIncome : updIncomeList)
        {
            // 如果有收益主键，进行修改
            if (updTransaIncome.getWms_inve_transa_income_id() != null)
            {
                IncomeUtil.getWmsInveTransaIncomeDao().update(updTransaIncome);
            }
            // 没有收益主键，进行新增
            else
            {
                IncomeUtil.getWmsInveTransaIncomeDao().save(updTransaIncome);
            }
        }
    }


    /**
     * @Title: orderRedeem
     * @Description: 预约赎回时客户收益变更处理。
     *  将赎回月份及之后收益重新按剩余投资金额重新计算。
     *  如果是续期后当月预约赎回，也需处理续期前老单据收益。
     * @param redeemData 赎回时客户收益变更请求数据对象
     * @author: jinzhm
     * @time:2016年12月26日 下午2:45:45
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeem#orderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
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
        updIncomeList = incomeRedeemTime.handleIncomeOrderRedeem(redeemTimeData);
        // 如果有必要的话处理续期后预约赎回的老单据收益
        incomeRedeemTime.handleIncomeOrderRedeemAfterExtend(redeemTimeData);

        // 修改数据
        persistIncomeForOrderRedeem();
    }

    /**
     * @Title: cancelOrderRedeem
     * @Description: 取消预约赎回时对客户收益变更处理
     *      需要将预约赎回时处理的对客户收益的影响还原
     * @param redeemData 预约赎回取消时请求数据对象
     * @author: jinzhm
     * @time:2016年12月26日 下午2:45:50
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeem#cancelOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
    */
    @Override
    public void cancelOrderRedeem(CountIncomeRedeemData redeemData)
    {
        // 初始化客户收益处理需要用到的数据
        initRedeemData(redeemData);
        // 获得赎回时机数据对象
        CountIncomeRedeemTimeData redeemTimeData = packageRedeemDataForOrderRedeem();
        redeemTimeData.setProductAccount(protocol.getProduct_account());
        // 获得赎回时机客户收益处理接口对象
        CountIncomeRedeemTime incomeRedeemTime = CountIncomeRedeemFactory.getCountIncomeRedeemTime(redeemTimeData);
        // 根据不同赎回时机处理客户收益
        updIncomeList = incomeRedeemTime.handleIncomeCancelOrderRedeem(redeemTimeData);
        // 如果有必要的话处理续期后预约赎回的老单据收益
        incomeRedeemTime.handleIncomeOrderRedeemAfterExtend(redeemTimeData);
        // 修改数据
        persistIncomeForOrderRedeem();
    }

    /**
     * @Title: finishOrderRedeem
     * @Description: 预约赎回流程完成，也就是收预约赎回财务回款后客户收益处理方法
     * @param redeemData 赎回数据对象
     * @author: jinzhm
     * @time:2016年12月27日 下午2:07:57
     * @see com.zx.emanage.inve.util.redeem.CountIncomeRedeem#finishOrderRedeem(com.zx.emanage.inve.util.redeem.CountIncomeRedeemData)
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
    */
    @Override
    public void finishOrderRedeem(CountIncomeRedeemData redeemData)
    {
        // 初始化客户收益处理需要用到的数据
        initRedeemData(redeemData);
        // 将数据封装成赎回时机数据对象
        CountIncomeRedeemTimeData data = packageRedeemDataForOrderRedeem();
        // 根据赎回时机处理客户收益
        CountIncomeRedeemFactory.getCountIncomeRedeemTime(data).handleIncomeAfterFinancePaymentForOrderRedeem(data);
    }

}
