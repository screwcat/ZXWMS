package com.zx.emanage.inve.util.transa;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.zx.emanage.inve.persist.WmsInveCustomerCardDao;
import com.zx.emanage.inve.persist.WmsInveCustomerDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveTransaCardDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.persist.WmsInveTransaSalesmanHisDao;
import com.zx.emanage.inve.persist.WmsInveTransaUpdateLogDao;
import com.zx.emanage.inve.service.IWmsInveExtendService;
import com.zx.emanage.inve.util.CountIncome;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.reportmanage.persist.WmsInveAchievementReportDao;
import com.zx.emanage.sysmanage.persist.PmPersonnelDao;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.sframe.util.GlobalVal;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: SignHelper
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月5日
 * @version V1.0
 * history:
 * 1、2017年6月5日 jinzhm 创建文件
 */
public class SignHelper
{

    public static final int PTP_CATEGORY_ID = -1;

    /**
     * 普通单据
     */
    public static final String NOMARL_TRANSA = "1";

    /**
     * ptp单据
     */
    public static final String PTP_TRANSA = "2";

    /**
     * 提交保存
     */
    public static final String SAVE_FLAG_1 = "1";

    /**
     * 成功
     */
    public static final String SUCCESS = "success";

    /**
     * <b>该错误废弃，股东单据签单时不可能没有crm客户主键，如果没有是系统问题，不应该给页面错误信息</b></br>
     * 创建股东单据时，传入的身份证号在wms系统中没有找到对应客户信息且也没有传入crm客户主键
     */
    // public static final String ERROR11 = "error11";

    /**
     * 归属业务员信息输入有误,请输入短工号!
     */
    public static final String ERROR9 = "error9";

    /**
     * @Title: getWmsInveTransaSalesmanHisDao
     * @Description: 获得理财上单业务员历史信息dao对象
     * @return 理财上单业务员历史信息dao对象
     * @author: jinzhm
     * @time:2017年6月21日 下午1:40:37
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
     */
    public static WmsInveTransaSalesmanHisDao getWmsInveTransaSalesmanHisDao()
    {
        return (WmsInveTransaSalesmanHisDao) GlobalVal.ctx.getBean("wmsInveTransaSalesmanHisDao");
    }

    /**
     * @Title: getWmsSysPropertyDao
     * @Description: 获得属性信息dao对象
     * @return 属性信息dao对象
     * @author: jinzhm
     * @time:2016年12月27日 上午10:16:11
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static WmsSysPropertyDao getWmsSysPropertyDao()
    {
        return (WmsSysPropertyDao) GlobalVal.ctx.getBean("wmsSysPropertyDao");
    }

    /**
     * @Title: getWmsInveTransaDao
     * @Description: 获得上单表dao对象
     * @return 返回上单表dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午3:50:17
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInveTransaDao getWmsInveTransaDao()
    {
        return (WmsInveTransaDao) GlobalVal.ctx.getBean("wmsInveTransaDao");
    }

    /**
     * @Title: getWmsInveTransaProdDao
     * @Description: 获得上单产品信息dao对象
     * @return 返回上单产品信息dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午4:49:01
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInveTransaProdDao getWmsInveTransaProdDao()
    {
        return (WmsInveTransaProdDao) GlobalVal.ctx.getBean("wmsInveTransaProdDao");
    }

    /**
     * @Title: getWmsInveTransaProtocolDao
     * @Description: 获得协议信息dao对象
     * @return 返回协议信息dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午5:27:52
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInveTransaProtocolDao getWmsInveTransaProtocolDao()
    {
        return (WmsInveTransaProtocolDao) GlobalVal.ctx.getBean("wmsInveTransaProtocolDao");
    }

    /**
     * @Title: getWmsInvePruductCategoryDao
     * @Description: 获得产品信息dao对象
     * @return 返回产品信息dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午4:50:17
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInvePruductCategoryDao getWmsInvePruductCategoryDao()
    {
        return (WmsInvePruductCategoryDao) GlobalVal.ctx.getBean("wmsInvePruductCategoryDao");
    }

    /**
     * @Title: getWmsInveCustomerDao
     * @Description: 获取wms客户信息dao
     * @return wms客户信息dao
     * @author: jinzhm
     * @time:2017年6月5日 下午4:37:36
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    public static WmsInveCustomerDao getWmsInveCustomerDao()
    {
        return (WmsInveCustomerDao) GlobalVal.ctx.getBean("wmsInveCustomerDao");
    }

    /**
     * @Title: getWmsInveCustomerCardDao
     * @Description: 获取客户收益卡dao
     * @return 客户收益卡信息dao对象
     * @author: jinzhm
     * @time:2017年6月6日 下午1:23:59
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    public static WmsInveCustomerCardDao getWmsInveCustomerCardDao()
    {
        return (WmsInveCustomerCardDao) GlobalVal.ctx.getBean("wmsInveCustomerCardDao");
    }

    /**
     * @Title: getWmsInveTransaCardDao
     * @Description: 获取单据支付信息dao
     * @return 单据支付信息dao对象
     * @author: jinzhm
     * @time:2017年6月6日 下午5:21:00
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    public static WmsInveTransaCardDao getWmsInveTransaCardDao()
    {
        return (WmsInveTransaCardDao) GlobalVal.ctx.getBean("wmsInveTransaCardDao");
    }

    /**
     * @Title: getPmPersonnelDao
     * @Description: 获得业务员信息dao对象
     * @return 业务员dao对象
     * @author: jinzhm
     * @time:2017年6月5日 下午4:43:40
     * history:
     * 1、2017年6月5日 jinzhm 创建方法
     */
    public static PmPersonnelDao getPmPersonnelDao()
    {
        return (PmPersonnelDao) GlobalVal.ctx.getBean("pmPersonnelDao");
    }
    
    /**
     * @Title: getWmsInveAchievementReportDao
     * @Description: 获得业绩计算dao对象
     * @return 业绩计算dao对象
     * @author: jinzhm
     * @time:2017年6月7日 上午9:48:56
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public static WmsInveAchievementReportDao getWmsInveAchievementReportDao()
    {
        return (WmsInveAchievementReportDao) GlobalVal.ctx.getBean("wmsInveAchievementReportDao");
    }

    /**
     * @Title: getWmsInveTransaIncomeDao
     * @Description: 获得单据收益信息dao对象
     * @return 收益信息dao对象
     * @author: jinzhm
     * @time:2017年6月7日 上午9:59:54
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public static WmsInveTransaIncomeDao getWmsInveTransaIncomeDao()
    {
        return (WmsInveTransaIncomeDao) GlobalVal.ctx.getBean("wmsInveTransaIncomeDao");
    }

    /**
     * @Title: getWmsInveExtendService
     * @Description: 获得续期处理service对象
     * @return 续期处理service对象
     * @author: jinzhm
     * @time:2017年6月7日 下午2:31:03
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public static IWmsInveExtendService getWmsInveExtendService()
    {
        return (IWmsInveExtendService) GlobalVal.ctx.getBean("wmsInveExtendService");
    }

    public static WmsInveTransaUpdateLogDao getWmsInveTransaUpdateLogDao()
    {
        return (WmsInveTransaUpdateLogDao) GlobalVal.ctx.getBean("wmsInveTransaUpdateLogDao");
    }

    /**
     * @Title: computeEndOfDate
     * @Description: 根据上单时间计算协议到期时间
     * @param oldDateOfPayment 原始上单时间
     * @param dateOfPayment 上单时间
     * @param productDeadline 产品期限
     * @return 理财到期时间
     * @author: jinzhm
     * @time:2017年6月6日 下午5:51:05
     * history:
     * 1、2017年6月6日 jinzhm 创建方法
     */
    public static Date computeEndOfDate(Date oldDateOfPayment, Date dateOfPayment, int productDeadline)
    {
        // 如果有原始签单日期使用原始签单日期
        Date sDate = oldDateOfPayment == null ? dateOfPayment : oldDateOfPayment;
        // 如果签单日期大于等于2016年7月1日
        if (sDate.compareTo(com.zx.sframe.util.DateUtil.strDate(CountIncome.MAGIC_DATE_OF_PAYMENT_STR, null)) >= 0)
        {
            // 到日时间为签单日期+产品期限-1天
            return DateUtil.changeDay(DateUtils.addMonths(dateOfPayment, productDeadline), -1);
        }
        else
        {
            // 到期日期为签单日期+产品期限
            return DateUtils.addMonths(dateOfPayment, productDeadline);
        }
    }

    /**
     * @Title: getTransaProtocol
     * @Description: 根据上单主键获得单据协议信息
     * @param transaId 上单主键
     * @return 协议信息
     * @author: jinzhm
     * @time:2017年6月7日 下午2:53:23
     * history:
     * 1、2017年6月7日 jinzhm 创建方法
     */
    public static WmsInveTransaProtocol getTransaProtocol(int transaId)
    {
        WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
        protocol.setWms_inve_transa_id(transaId);
        return getWmsInveTransaProtocolDao().getListByEntity(protocol).get(0);
    }
}
