package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInvePruductRebateWayDao;
import com.zx.emanage.inve.persist.WmsInvePruductYearPaySpecialDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDao;
import com.zx.emanage.inve.persist.WmsInveRedeemDetailDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaIncomeDao;
import com.zx.emanage.inve.persist.WmsInveTransaLogDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInvePruductYearPaySpecial;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaIncome;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IncomeUtil
 * 模块名称：收益相关工具类
 * @Description: 内容摘要：收益相关工具类；如计算基本收益，公益收益等方法
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public class IncomeUtil
{

    /**
     * @Title: computeExtendIncome
     * @Description: 计算公益收益<b>（没有处理小数点）</b>
     * @param publicIncomeDays 公益收益天数
     * @param productAccount 投资金额
     * @param publicRate 公益收益
     * @return 公益收益
     * @author: jinzhm
     * @time:2016年12月26日 下午3:25:56
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static BigDecimal computePublicIncome(int publicIncomeDays, BigDecimal productAccount, BigDecimal publicRate)
    {
        return new BigDecimal(publicIncomeDays).divide(new BigDecimal(365), 8, RoundingMode.HALF_UP)
                                               .multiply(productAccount).multiply(publicRate);
    }

    /**
     * @Title: computeIncome
     * @Description: 计算月付基本收益<b>（没有处理小数点）</b>
     * @param incomeDays 基本收益计算天数
     * @param dayOfMonth 当月自然天数
     * @param productAccount 投资金额
     * @param returnRate 利率
     * @return 月付基本收益
     * @author: jinzhm
     * @time:2016年12月26日 下午3:29:44
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static BigDecimal computeIncome(int incomeDays, int dayOfMonth, BigDecimal productAccount,
                                           BigDecimal returnRate)
    {
        return new BigDecimal(incomeDays).divide(new BigDecimal(dayOfMonth), 8, RoundingMode.HALF_UP)
                                         .multiply(productAccount)
                                         .multiply((returnRate.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP)));
    }

    /**
     * @Title: computeIncome
     * @Description: 计算年付基本收益<b>（没有处理小数点）</b>
     * @param productAccount 投资金额
     * @param returnRate 利率
     * @return 年付基本收益
     * @author: jinzhm
     * @time:2016年12月29日 上午9:21:55
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    public static BigDecimal computeIncome(BigDecimal productAccount, BigDecimal returnRate)
    {
        return productAccount.multiply(returnRate.divide(new BigDecimal(100), RoundingMode.HALF_UP));
    }

    /**
     * @Title: computeBonusIncome
     * @Description: 计算奖励收益<b>（没有处理小数点）</b>
     * @param bonusReturnRate 奖励收益利率
     * @param productAccount 投资金额
     * @return 奖励收益
     * @author: jinzhm
     * @time:2016年12月27日 上午9:41:33
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static BigDecimal computeBonusIncome(BigDecimal bonusReturnRate, BigDecimal productAccount)
    {
        return bonusReturnRate.divide(new BigDecimal(100), 8, RoundingMode.HALF_UP).multiply(productAccount);
    }

    /**
     * @Title: setScale
     * @Description: 设置小数点保留两位（只入不舍）
     * @param bigDecimal 数值
     * @return 处理小数点后数值
     * @author: jinzhm
     * @time:2016年12月27日 上午9:46:34
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static BigDecimal setScale(BigDecimal bigDecimal)
    {
        if (bigDecimal == null)
        {
            bigDecimal = new BigDecimal(0);
        }
        return bigDecimal.setScale(2, RoundingMode.UP);
    }

    /**
     * @Title: setScaleDown
     * @Description: 设置小数点保留两位（向下取整）
     * @param bigDecimal 数值
     * @return 处理小数点后数值
     * @author: jinzhm
     * @time:2017年2月21日 下午4:45:31
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public static BigDecimal setScaleDown(BigDecimal bigDecimal)
    {
        if (bigDecimal == null)
        {
            bigDecimal = new BigDecimal(0);
        }
        return bigDecimal.setScale(2, RoundingMode.DOWN);
    }

    /**
     * @Title: isIncomeNotPaid
     * @Description: 判断收益支付状态是不是未支付状态
     * @param payStatus 支付状态
     * @return 如果是未支付状态返回true
     * @author: jinzhm
     * @time:2016年12月26日 下午4:16:11
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static boolean isIncomeNotPaid(String payStatus)
    {
        return IncomeGlobal.PAY_STATUS_NOT_PAY.equals(payStatus);
    }

    /**
     * @Title: isIncomeNotPaid
     * @Description: 判断收益支付状态是不是未支付状态
     * @param income 收益信息对象
     * @return 如果是未支付状态返回true
     * @author: jinzhm
     * @time:2016年12月26日 下午4:18:23
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static boolean isIncomeNotPaid(WmsInveTransaIncome income)
    {
        return isIncomeNotPaid(income.getPay_status());
    }

    /**
     * @Title: isIncomeAlreadyPaid
     * @Description: 判断支付状态是否已经支付
     * @param payStatus 支付状态
     * @return 返回true表示已经支付，返回false表示还没有支付
     * @author: jinzhm
     * @time:2016年12月27日 下午4:52:12
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
    */
    public static boolean isIncomeAlreadyPaid(String payStatus)
    {
        // 是正常已支付，或续期已支付或处理年付公益已支付或续期不需要支付时都返回true
        return IncomeGlobal.PAY_STATUS_ALREADY_PAY.equals(payStatus)
               || IncomeGlobal.PAY_STATUS_EXTEND_ALREADY_PAY.equals(payStatus)
               || IncomeGlobal.PAY_STATUS_YEAR_PUBLIC_ALREADY_PAY.equals(payStatus)
               || isIncomeExtendNoNeedPaid(payStatus);
    }

    /**
     * @Title: isIncomeExtendNoNeedPaid
     * @Description: 判断是否为续期不需要支付收益状态
     * @param payStatus 支付状态
     * @return true表示是续期不需要支付状态
     * @author: jinzhm
     * @time:2017年1月16日 下午6:04:35
     * history:
     * 1、2017年1月16日 jinzhm 创建方法
     */
    public static boolean isIncomeExtendNoNeedPaid(String payStatus)
    {
        return IncomeGlobal.PAY_STATUS_EXTEND_NO_NEED_PAY.equals(payStatus);
    }

    /**
     * @Title: isIncomeExtendNoNeedPaid
     * @Description: 判断是否为续期不需要支付收益状态
     * @param income 收益信息
     * @return true表示是续期不需要支付状态
     * @author: jinzhm
     * @time:2017年1月16日 下午6:03:57
     * history:
     * 1、2017年1月16日 jinzhm 创建方法
     */
    public static boolean isIncomeExtendNoNeedPaid(WmsInveTransaIncome income)
    {
        return isIncomeExtendNoNeedPaid(income.getPay_status());
    }

    /**
     * @Title: isIncomeTerminate
     * @Description: 返回收益状态是否为已终止状态
     * @param income 收益信息
     * @return 是终止状态返回true
     * @author: jinzhm
     * @time:2017年1月17日 上午10:15:55
     * history:
     * 1、2017年1月17日 jinzhm 创建方法
     */
    public static boolean isIncomeTerminate(WmsInveTransaIncome income)
    {
        return IncomeGlobal.PAY_STATUS_TERMINATE.equals(income.getPay_status());
    }

    /**
     * @Title: isIncomeAlreadyPaid
     * @Description: 判断支付状态是否已经支付
     * @param income 收益信息
     * @return 返回true表示已经支付，返回false表示还没有支付
     * @author: jinzhm
     * @time:2016年12月27日 下午4:53:39
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static boolean isIncomeAlreadyPaid(WmsInveTransaIncome income)
    {
        return isIncomeAlreadyPaid(income.getPay_status());
    }

    /**
     * @Title: isLaterThanMagicDate
     * @Description: 判断传入时间是否等于或大于2016-07-01日期
     * @param date 时间
     * @return 大于或等于返回true
     * @author: jinzhm
     * @time:2016年12月26日 下午4:13:48
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static boolean isLaterThanMagicDate(Date date)
    {
        return date.compareTo(DateUtil.strDate(IncomeGlobal.MAGIC_DATE_OF_PAYMENT_STR, "yyyy-MM-dd")) >= 0;
    }

    /**
     * @Title: getWmsInveRedeemDao
     * @Description: 获得赎回信息dao对象
     * @return 赎回信息dao对象
     * @author: jinzhm
     * @time:2016年12月30日 上午8:51:58
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    public static WmsInveRedeemDao getWmsInveRedeemDao()
    {
        return (WmsInveRedeemDao) GlobalVal.ctx.getBean("wmsInveRedeemDao");
    }

    /**
     * @Title: getWmsInveRedeemDetailDao
     * @Description: 获得赎回明细信息dao对象
     * @return 赎回明细信息dao对象
     * @author: jinzhm
     * @time:2016年12月30日 上午9:01:08
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    public static WmsInveRedeemDetailDao getWmsInveRedeemDetailDao()
    {
        return (WmsInveRedeemDetailDao) GlobalVal.ctx.getBean("wmsInveRedeemDetailDao");
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
     * @Title: getWmsInveTransaIncomeDao
     * @Description: 获得上单单据收益信息dao对象
     * @return 返回上单单据收益信息dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午4:02:30
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInveTransaIncomeDao getWmsInveTransaIncomeDao()
    {
        return (WmsInveTransaIncomeDao) GlobalVal.ctx.getBean("wmsInveTransaIncomeDao");
    }

    /**
     * @Title: getWmsInveTransaLogDao
     * @Description: 获得上单单据交易日志信息dao对象
     * @return 上单单据交易日志信息dao对象
     * @author: jinzhm
     * @time:2016年12月30日 上午9:34:23
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    public static WmsInveTransaLogDao getWmsInveTransaLogDao()
    {
        return (WmsInveTransaLogDao) GlobalVal.ctx.getBean("wmsInveTransaLogDao");
    }

    /**
     * @Title: getWmsInvePruductRebateWayDao
     * @Description: 获得产品利率设置信息dao对象
     * @return 返回产品利率设置信息dao对象
     * @author: jinzhm
     * @time:2016年12月26日 下午4:41:27
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInvePruductRebateWayDao getWmsInvePruductRebateWayDao()
    {
        return (WmsInvePruductRebateWayDao) GlobalVal.ctx.getBean("wmsInvePruductRebateWayDao");
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
     * @Title: getWmsInvePruductYearPaySpecialDao
     * @Description: 获得产品利率设置信息dao对象
     * @return 返回产品利率设置dao对象
     * @author: jinzhm
     * @time:2016年12月29日 上午9:04:16
     * history:
     * 1、2016年12月29日 jinzhm 创建方法
     */
    public static WmsInvePruductYearPaySpecialDao getWmsInvePruductYearPaySpecialDao()
    {
        return (WmsInvePruductYearPaySpecialDao) GlobalVal.ctx.getBean("wmsInvePruductYearPaySpecialDao");
    }

    /**
     * @Title: getTransaIncomeList
     * @Description: 获得某一个单据的某些收益类型的收益信息集合
     * @param transaId 上单主键
     * @param payTypes 要查询的收益类型<b>参数要求是数据库IN内参数</b>
     * @return 返回某一个单据的某些收益类型的收益信息集合
     * @author: jinzhm
     * @time:2016年12月26日 下午4:27:50
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static List<WmsInveTransaIncome> getTransaIncomeList(int transaId, String payTypes)
    {
        // 封装查询某一个单据的正常和公益收益信息对象
        WmsInveTransaIncome transaIncome = new WmsInveTransaIncome();
        transaIncome.setWms_inve_transa_id(transaId);
        transaIncome.setPay_type(payTypes);
        // 返回查询某一个单据的正常和公益收益信息集合
        return getWmsInveTransaIncomeDao().getListByEntity(transaIncome);
    }

    /**
     * @Title: getTransaNormalAndPublicIncomeList
     * @Description: 获得某一个单据的正常和公益收益信息集合
     * @param transaId 上单主键
     * @return 返回查询某一个单据的正常和公益收益信息集合
     * @author: jinzhm
     * @time:2016年12月28日 下午2:18:49
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
    */
    public static List<WmsInveTransaIncome> getTransaIncomeList(int transaId)
    {
        return getTransaIncomeList(transaId, "'" + IncomeGlobal.PAY_TYPE_NORMAL + "','"
                                                            + IncomeGlobal.PAY_TYPE_PUBLIC + "'");
    }

    /**
     * @Title: getWmsInvePructRebateWayList
     * @Description: 获得产品的产品利率设置信息
     * @param categoryId
     * @return 返回产品利率设置查询信息
     * @author: jinzhm
     * @time:2016年12月26日 下午4:44:44
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static List<WmsInvePruductRebateWay> getWmsInvePruductRebateWayList(int categoryId)
    {
        // 封装产品利率设置查询信息
        WmsInvePruductRebateWay wmsRebateWay = new WmsInvePruductRebateWay();
        wmsRebateWay.setWms_inve_pruduct_category_id(categoryId);
        wmsRebateWay.setEnable_flag("1");
        // 返回产品利率设置查询信息
        return getWmsInvePruductRebateWayDao().getListByEntity(wmsRebateWay);
    }

    /**
     * @Title: getWmsInvePruductCategoryByProdId
     * @Description: 根据上单产品表主键获得产品信息
     * @param prodId 上单产品表主键
     * @return 返回产品信息对象
     * @author: jinzhm
     * @time:2016年12月26日 下午4:53:37
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInvePruductCategory getWmsInvePruductCategoryByProdId(int prodId)
    {
        return getWmsInvePruductCategoryDao().get(getWmsInveTransaProdDao().get(prodId)
                                                                           .getWms_inve_pruduct_category_id());
    }

    /**
     * @Title: getTransaProtocol
     * @Description: 根据上单主键获得协议到期时间
     * @param transaId 上单表主键
     * @return 返回协议到期时间
     * @author: jinzhm
     * @time:2016年12月26日 下午5:30:31
     * history:
     * 1、2016年12月26日 jinzhm 创建方法
     */
    public static WmsInveTransaProtocol getTransaProtocol(int transaId)
    {
        // 封装协议查询信息数据
        WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
        protocol.setWms_inve_transa_id(transaId);
        // 查询协议信息（根据主键倒序排序）
        List<WmsInveTransaProtocol> protocolList = getWmsInveTransaProtocolDao().getListByEntity(protocol);
        // 返回第一个协议信息（最新的协议信息）的协议到期时间
        return protocolList.get(0);
    }

    /**
     * @Title: getPublicProductReturnRate
     * @Description: 获得公益收益利率
     * @return 返回公益收益利率
     * @author: jinzhm
     * @time:2016年12月27日 上午9:22:50
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static BigDecimal getPublicProductReturnRate()
    {
        // 查询公益6产品信息
        WmsInvePruductCategory category = getWmsInvePruductCategoryDao().get(IncomeGlobal.WMS_INVE_PUBLIC_PRODCUT_ID);
        // 月付产品获得利率后需要除12
        BigDecimal returnRate = category.getCategory_return_rate().divide(new BigDecimal(100), 8, RoundingMode.HALF_UP);
        return returnRate;
    }

    /**
     * @Title: getTransaExtendDate
     * @Description: 根据上一个单据主键获得续期时间
     * @param oldLastTransaId 上一个单据主键
     * @return 如果没有续期或传入为null返回null，有续期时间返回续期时间
     * @author: jinzhm
     * @time:2016年12月27日 上午10:54:57
     * history:
     * 1、2016年12月27日 jinzhm 创建方法
     */
    public static Date getTransaExtendDate(Integer oldLastTransaId)
    {
        // 如果传入的上一个单据主键是null的话，直接返回null
        if (oldLastTransaId == null)
        {
            return null;
        }
        // 查询续期日志信息
        Map<String, Object> transaExtendLog = getWmsInveTransaDao().getTransaExtendTime(oldLastTransaId);
        // 如果有续期日志信息
        if (transaExtendLog != null && !transaExtendLog.isEmpty())
        {
            // 返回续期时间
            return DateUtil.getDate10(new Date(((Timestamp) transaExtendLog.get("act_extend_datetime")).getTime()));
        }
        return null;
    }

    /**
     * @Title: getWmsInvePruductYearPaySpecialByCategoryId
     * @Description: 根据产品id获得年付产品利率设置信息
     * @param categoryId 产品表主键
     * @return 年付产品利率设置信息
     * @author: jinzhm
     * @time:2017年1月6日 下午2:52:57
     * history:
     * 1、2017年1月6日 jinzhm 创建方法
     */
    public static WmsInvePruductYearPaySpecial getWmsInvePruductYearPaySpecialByCategoryId(Integer categoryId)
    {
        // 查询年付产品利率设置信息
        WmsInvePruductYearPaySpecial paySpecial = new WmsInvePruductYearPaySpecial();
        paySpecial.setWms_inve_pruduct_category_id(categoryId);
        List<WmsInvePruductYearPaySpecial> paySpecials = IncomeUtil.getWmsInvePruductYearPaySpecialDao()
                                                                   .getListByEntity(paySpecial);

        if (!paySpecials.isEmpty())
        {// 如果年付1年产品的话此集合为空。
            return paySpecials.get(0);
        }
        return null;
    }

    /**
     * @Title: isOrderRedeemAfterExtendInSameMonth
     * @Description: 判断是不是续期后做预约赎回，赎回日期是续期月份且当月收益是未支付状态
     * @param extendDate 续期日期
     * @param redeemDate 赎回日期
     * @return 是续期后做预约赎回，赎回日期是续期月份且当月收益是未支付状态的话返回true
     * @author: jinzhm
     * @time:2016年12月28日 上午10:06:15
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public static boolean isOrderRedeemAfterExtendInSameMonth(Date extendDate, Date redeemDate)
    {
        // 如果有续期时间
        if (extendDate != null)
        {
            // 如果续期时间和预约赎回日期是同一个月
            if (DateUtil.getLastDayOfMonth(extendDate).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: isOneYearNormalIncomeNeedHandleTransaIncomeForRedeem
     * @Description: 判断该类型，该收益应付日期的收益年付一年产品在该日期赎回时是否需要处理该收益
     *      是正常收益的时候
     *      是一年产品的时候
     *      只要收益应付时间的月份大于等于赎回日期的月份就需要处理
     * @param payType 支付类型
     * @param returnDate 应付收益日期
     * @param deadline 产品期限
     * @param redeemDate 赎回日期
     * @return 返回true表示需要处理
     * @author: jinzhm
     * @time:2018年1月13日 下午2:02:30
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    public static boolean isOneYearNormalIncomeNeedHandleTransaIncomeForRedeem(String payType, Date returnDate,
                                                                               int deadline, Date redeemDate)
    {
        return IncomeGlobal.PAY_TYPE_NORMAL.equals(payType) && deadline == 12
               && DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) >= 0;
    }

    /**
     * @Title: isTwoYearNormalIncomeNeedHandleTransaIncomeForRedeem
     * @Description: 判断两年年付产品的收益信息在某个收益类型和某个收益应付日期时在某个赎回时机下是否需要处理该收益
     *      是正常收益的时候
     *      是两年产品的时候
     *      第一年收益应付日期月份赎回时，需要判断赎回日期和第一年收益应付日期
     *          2016-07-01之前上单单据收益到期日赎回，不修改该收益信息
     *          2016-07-01之后上单单据收益到期日赎回，修改该收益信息
     *      第二年收益应付日期的月份大于等于赎回日期的月份就需要处理
     * @param payType 支付类型
     * @param returnDate 收益应付日期
     * @param deadline 产品期限
     * @param redeemDate 赎回日期
     * @param year 第几年收益
     * @param oldDateOfPayment 原始上单时间
     * @return 返回true表示需要处理
     * @author: jinzhm
     * @time:2018年1月13日 下午2:11:17
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    public static boolean isTwoYearNormalIncomeNeedHandleTransaIncomeForRedeem(String payType, Date returnDate,
                                                                               int deadline, Date redeemDate, int year,
                                                                               Date oldDateOfPayment)
    {
        // 是否需要处理标记
        boolean flag = false;
        // 是正常收益且是两年产品
        if (IncomeGlobal.PAY_TYPE_NORMAL.equals(payType) && deadline == 24)
        {
            // 是第一年收益
            if (year == 1)
            {
                // 大于2016-07-01之后上单（含）
                if (IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    // 收益应付日期大于等于赎回日期时需要处理该收益
                    if (returnDate.compareTo(redeemDate) >= 0)
                    {
                        flag = true;
                    }
                }
                // 2016-07-01之前上单
                else if (!IncomeUtil.isLaterThanMagicDate(oldDateOfPayment))
                {
                    // 收益应付日期大于赎回日期时需要处理该收益
                    if (returnDate.compareTo(redeemDate) > 0)
                    {
                        flag = true;
                    }
                }
            }
            // 是第二年收益
            else if (year == 2)
            {
                // 收益应付日期的月份大于等于赎回日期的月份时需要处理该收益
                if (DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) >= 0)
                {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * @Title: isPublicIncomeNeedHandleTransaIncomeForRedeem
     * @Description: 判断公益收益在赎回时是否需要处理
     *      是公益收益的时候
     *      只要收益应付日期的月份大于等于赎回日期的月份就需要处理
     * @param payType 收益类型
     * @param returnDate 收益应付时间
     * @param redeemDate 赎回时间
     * @return 需要处理返回true
     * @author: jinzhm
     * @time:2018年1月13日 下午2:18:12
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    public static boolean isPublicIncomeNeedHandleTransaIncomeForRedeem(String payType, Date returnDate, Date redeemDate)
    {
        return IncomeGlobal.PAY_TYPE_PUBLIC.equals(payType)
               && DateUtil.getLastDayOfMonth(returnDate).compareTo(DateUtil.getLastDayOfMonth(redeemDate)) == 0;
    }

    /**
     * @Title: isYearIncomeNeedHandleTransaIncomeForRedeem
     * @Description: 判断年付收益在某个赎回时机时是否需要处理
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
     * @param transaIncome 收益信息
     * @param redeemDate 赎回时间
     * @param year 年数
     * @param deadline 产品期限
     * @param oldDateOfPayment 原始上单时间
     * @return 返回true表示需要处理
     * @author: jinzhm
     * @time:2018年1月13日 下午2:39:36
     * history:
     * 1、2018年1月13日 jinzhm 创建方法
     */
    public static boolean isYearIncomeNeedHandleTransaIncomeForRedeem(WmsInveTransaIncome transaIncome,
                                                                      Date redeemDate, int year, int deadline,
                                                                      Date oldDateOfPayment)
    {
        return isOneYearNormalIncomeNeedHandleTransaIncomeForRedeem(transaIncome.getPay_type(),
                                                                    transaIncome.getReturn_date(), deadline, redeemDate)
               || isTwoYearNormalIncomeNeedHandleTransaIncomeForRedeem(transaIncome.getPay_type(),
                                                                       transaIncome.getReturn_date(), deadline,
                                                                       redeemDate, year, oldDateOfPayment)
               || isPublicIncomeNeedHandleTransaIncomeForRedeem(transaIncome.getPay_type(),
                                                                transaIncome.getReturn_date(), redeemDate);
    }

    /**
     * @Title: getCategoryReturnRate
     * @Description: 月付产品获得月付利率
     * @param category 产品信息
     * @return 月付利率
     * @author: jinzhm
     * @time:2017年1月12日 下午1:06:21
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
     */
    public static BigDecimal getCategoryReturnRate(WmsInvePruductCategory category)
    {
        return category.getBasic_monthly_rate() != null ? category.getBasic_monthly_rate()
                                                       : category.getCategory_return_rate()
                                                                 .divide(new BigDecimal("12"), 8, RoundingMode.HALF_UP);
    }
    
    /**
     * @Title: getCategoryReturnRate
     * @Description: 获得年付产品年付利率
     * @param category 产品信息
     * @param paySpecial 年付利率设置信息
     * @param year 年数
     * @return 年付利率
     * @author: jinzhm
     * @time:2017年1月12日 下午1:07:44
     * history:
     * 1、2017年1月12日 jinzhm 创建方法
     */
    public static BigDecimal getCategoryReturnRate(WmsInvePruductCategory category,
                                                   WmsInvePruductYearPaySpecial paySpecial, int year)
    {
        BigDecimal returnRate = BigDecimal.ZERO;
        if (paySpecial != null)
        {
            if (year == 1)
            {
                returnRate = paySpecial.getFirst_year_interest_rate() == null ? BigDecimal.ZERO
                                                                             : paySpecial.getFirst_year_interest_rate();
            }
            else
            {
                returnRate = paySpecial.getSecond_year_interest_rate() == null ? BigDecimal.ZERO
                                                                              : paySpecial.getSecond_year_interest_rate();
            }
        }
        returnRate = returnRate.compareTo(BigDecimal.ZERO) == 0 ? category.getCategory_return_rate() : returnRate;
        return returnRate;
    }

    /**
     * @Title: isOldTransaIncomeHasPublicIncome
     * @Description: 查看传入单据的上一个单据收益信息是否在指定的应付收益日期的月份有公益收益
     * @param transa 单据信息
     * @param returnDate 收益应付日期
     * @return 上一个单据收益信息中有收益应付日期的公益的话返回true，没有的话返回false
     * @author: jinzhm
     * @time:2017年1月13日 下午6:32:40
     * history:
     * 1、2017年1月13日 jinzhm 创建方法
     */
    public static boolean isOldTransaIncomeHasPublicIncome(WmsInveTransa transa, Date returnDate)
    {
        if (transa.getOld_last_wms_inve_transa_id() == null)
        {
            return false;
        }
        List<WmsInveTransaIncome> oldTransaIncomeList = IncomeUtil.getTransaIncomeList(transa.getOld_last_wms_inve_transa_id());
        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
        {
            if (DateUtil.getLastDayOfMonth(returnDate)
                        .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) == 0
                && IncomeGlobal.PAY_TYPE_PUBLIC.equals(oldTransaIncome.getPay_type()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: getOldTransaIncomeHasSpecifyMonthValidIncome
     * @Description: 查看传入单据的上一个单据收益信息是指定年月份的收益，并将不是续期不需要支付状态的收益相加返回
     * @param transa 上单信息
     * @param returnDate 收益应付日期
     * @return 收益金额
     * @author: jinzhm
     * @time:2017年1月16日 下午6:53:26
     * history:
     * 1、2017年1月16日 jinzhm 创建方法
     */
    public static BigDecimal getOldTransaIncomeHasSpecifyMonthValidIncome(WmsInveTransa transa, Date returnDate)
    {
        // 总收益
        BigDecimal totalIncome = BigDecimal.ZERO;
        // 如果传入单据信息没有上一个单据主键
        if (transa.getOld_last_wms_inve_transa_id() == null)
        {
            return BigDecimal.ZERO;
        }

        // 是否想上递过归标志
        boolean flag = false;

        // 获得老单据收益信息
        List<WmsInveTransaIncome> oldTransaIncomeList = IncomeUtil.getTransaIncomeList(transa.getOld_last_wms_inve_transa_id());
        // 循环老单据收益信息
        for (WmsInveTransaIncome oldTransaIncome : oldTransaIncomeList)
        {
            // 在老单据收益信息中找到指定月份的收益信息
            if (DateUtil.getLastDayOfMonth(returnDate)
                        .compareTo(DateUtil.getLastDayOfMonth(oldTransaIncome.getReturn_date())) == 0)
            {
                // 如果不是续期不需要支付状态也不是终止状态。
                if (!isIncomeExtendNoNeedPaid(oldTransaIncome) && !isIncomeTerminate(oldTransaIncome))
                {
                    // 如果没有向上地归过，向上递归一次
                    if (!flag)
                    {
                        WmsInveTransa oldTransa = getWmsInveTransaDao().get(transa.getOld_last_wms_inve_transa_id());
                        totalIncome = totalIncome.add(getOldTransaIncomeHasSpecifyMonthValidIncome(oldTransa,
                                                                                                   returnDate));
                        flag = true;
                    }
                    totalIncome = totalIncome.add(oldTransaIncome.getPayable_basic_income().add(oldTransaIncome.getExtend_income()));
                }
            }
        }

        return totalIncome;
    }

    // /**
    // * @Title: getOldTransaNotPaidIncome
    // * @Description: 递归向上调用查找老单据在传入收益应付日期的月份上未支付状态的收益信息集合
    // * @param transa 单据信息
    // * @param returnDate 收益应付日期
    // * @return 收益信息集合
    // * @author: jinzhm
    // * @time:2017年1月13日 下午4:29:40
    // * history:
    // * 1、2017年1月13日 jinzhm 创建方法
    // */
    // public static List<WmsInveTransaIncome>
    // getOldTransaNotPaidIncome(WmsInveTransa transa, Date returnDate)
    // {
    // // 获得老单据收益信息
    // List<WmsInveTransaIncome> oldTransaIncomeList =
    // IncomeUtil.getTransaIncomeList(transa.getOld_last_wms_inve_transa_id());
    // for (WmsInveTransaIncome oldIncome : oldTransaIncomeList)
    // {
    // // 找到当月收益
    // if (DateUtil.getLastDayOfMonth(oldIncome.getReturn_date())
    // .compareTo(DateUtil.getLastDayOfMonth(returnDate)) == 0)
    // {
    // // 是正常收益
    // if (IncomeGlobal.PAY_TYPE_NORMAL.equals(oldIncome.getPay_type()))
    // {
    // // 如果正常收益是续期已支付状态
    // if
    // (IncomeGlobal.PAY_STATUS_EXTEND_ALREADY_PAY.equals(oldIncome.getPay_status()))
    // {
    // WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
    // .get(transa.getOld_last_wms_inve_transa_id());
    // oldTransaIncomeList = getOldTransaNotPaidIncome(oldTransa, returnDate);
    // return oldTransaIncomeList;
    // }
    // // 如果是未支付状态收益，直接返回list
    // else if
    // (IncomeGlobal.PAY_STATUS_NOT_PAY.equals(oldIncome.getPay_status()))
    // {
    // return oldTransaIncomeList;
    // }
    // }
    // // 是公益收益
    // else if (IncomeGlobal.PAY_TYPE_PUBLIC.equals(oldIncome.getPay_type()))
    // {
    // // 如果公益收益是续期不需要支付状态
    // if
    // (IncomeGlobal.PAY_STATUS_EXTEND_NO_NEED_PAY.equals(oldIncome.getPay_status()))
    // {
    // WmsInveTransa oldTransa = IncomeUtil.getWmsInveTransaDao()
    // .get(transa.getOld_last_wms_inve_transa_id());
    // oldTransaIncomeList = getOldTransaNotPaidIncome(oldTransa, returnDate);
    // return oldTransaIncomeList;
    // }
    // // 如果是未支付状态收益，直接返回list
    // else if
    // (IncomeGlobal.PAY_STATUS_NOT_PAY.equals(oldIncome.getPay_status()))
    // {
    // return oldTransaIncomeList;
    // }
    // }
    // }
    // }
    // return new ArrayList<WmsInveTransaIncome>();
    // }

    /**
     * @Title: setTransaIncomeUpdateData
     * @Description: 设置收益修改时间和修改用户信息
     * @param transaIncome 客户收益信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2016年12月28日 下午1:28:23
     * history:
     * 1、2016年12月28日 jinzhm 创建方法
     */
    public static void setTransaIncomeUpdateData(WmsInveTransaIncome transaIncome, UserBean user)
    {
        transaIncome.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        transaIncome.setLast_update_user_id(user.getUserId());
    }
    
    /**
     * @Title: setTransaIncomeRemarks
     * @Description: 设置收益备注信息
     * @param transaIncome 收益对象
     * @param bonus 奖励金额
     * @param returnDate 收益应付时间
     * @param months 满月数
     * @author: jinzhm
     * @time:2016年12月30日 下午2:19:45
     * history:
     * 1、2016年12月30日 jinzhm 创建方法
     */
    public static void setTransaIncomeRemarks(WmsInveTransaIncome transaIncome, BigDecimal bonus, Date returnDate,
                                              int months, WmsInvePruductCategory category)
    {
        // 如果有奖励收益
        if (bonus.compareTo(BigDecimal.ZERO) > 0)
        {
            // 当有奖励的时候备注中正常收益和奖励收益用逗号隔开
            transaIncome.setRemarks(DateUtil.date2String(returnDate, "yyyy年MM月") + "客户正常收益" + ","
                                    + DateUtil.date2String(returnDate, "yyyy年MM月") + category.getCategory_name() + "存满"
                                    + (months == 6 ? "半年" : months == 12 ? "一年" : months + "月") + "奖励收益");
        }
        // 如果没有奖励收益
        else
        {
            transaIncome.setRemarks(DateUtil.date2String(returnDate, "yyyy年MM月") + "客户正常收益");
        }
    }

    /**
     * @Title: setTransaIncomePublicRemarks
     * @Description: 设置收益公益备注信息
     *      如果传入了产品信息，公益备注中需要有产品名称
     *      只有年付设置公益备注时需要传入产品信息
     * @param transaIncome 收益信息
     * @param category 产品信息
     * @author: jinzhm
     * @time:2017年1月13日 上午10:09:36
     * history:
     * 1、2017年1月13日 jinzhm 创建方法
     */
    public static void setTransaIncomePublicRemarks(WmsInveTransaIncome transaIncome, WmsInvePruductCategory category)
    {
        // 如果传入了产品信息
        if (category != null)
        {
            transaIncome.setRemarks(DateUtil.date2String(transaIncome.getReturn_date(), "yyyy年MM月")
                                    + category.getCategory_name() + "客户公益收益");
        }
        // 如果没有传入产品信息
        else
        {
            transaIncome.setRemarks(DateUtil.date2String(transaIncome.getReturn_date(), "yyyy年MM月") + "客户公益收益");
        }
    }

}
