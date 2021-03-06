package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.inve.persist.WmsInveClerkProtocolTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveCreditPackageDao;
import com.zx.emanage.inve.persist.WmsInveCreditSplitConfigHeadDao;
import com.zx.emanage.inve.persist.WmsInvePruductCategoryDao;
import com.zx.emanage.inve.persist.WmsInveTransaCrepkgDao;
import com.zx.emanage.inve.persist.WmsInveTransaDao;
import com.zx.emanage.inve.persist.WmsInveTransaProdDao;
import com.zx.emanage.inve.persist.WmsInveTransaProtocolDao;
import com.zx.emanage.inve.util.IncomeUtil;
import com.zx.emanage.loanappro.persist.WmsSysPropertyDao;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.platform.syscontext.util.DateUtil;
import com.zx.sframe.util.GlobalVal;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditUtils
 * 模块名称：债权匹配相关工具类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
public class CreditUtils
{

    /**
     * 可拆分类产品类型
     */
    public static final String CATEGORY_TYPE_SPLIT = "4";

    /**
     * 最大可扩大系数对应的配置id
     */
    public static final Integer MAX_COEFF_PROPERTY_ID = 5005;

    /**
     * 合同生效日期和实际支付日期的最大可控范围
     */
    public static final Integer DAY_RANGE_PROPERTY_ID = 5006;

    /**
     * @Title: getWmsInvePruductCategoryDao
     * @Description: 获得产品dao对象
     * @return 产品dao对象
     * @author: jinzhm
     * @time:2017年2月11日 下午1:29:10
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static WmsInvePruductCategoryDao getWmsInvePruductCategoryDao()
    {
        return (WmsInvePruductCategoryDao) GlobalVal.ctx.getBean("wmsInvePruductCategoryDao");
    }

    /**
     * @Title: getWmsInveTransaProtocolDao
     * @Description: 获得老协议dao对象
     * @return 老协议dao对象
     * @author: jinzhm
     * @time:2017年2月14日 下午12:58:42
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static WmsInveTransaProtocolDao getWmsInveTransaProtocolDao()
    {
        return (WmsInveTransaProtocolDao) GlobalVal.ctx.getBean("wmsInveTransaProtocolDao");
    }

    /**
     * @Title: getWmsSysPropertyDao
     * @Description: 获得系统属性表dao对象
     * @return 系统属性表dao对象
     * @author: jinzhm
     * @time:2017年2月20日 上午10:08:13
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    public static WmsSysPropertyDao getWmsSysPropertyDao()
    {
        return (WmsSysPropertyDao) GlobalVal.ctx.getBean("wmsSysPropertyDao");
    }

    /**
     * @Title: getWmsInveTransaDao
     * @Description: 获得上单单据dao对象
     * @return 上单单据dao对象
     * @author: jinzhm
     * @time:2017年2月16日 上午11:43:29
     * history:
     * 1、2017年2月16日 jinzhm 创建方法
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
     * @time:2017年2月11日 下午1:30:55
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static WmsInveTransaProdDao getWmsInveTransaProdDao()
    {
        return (WmsInveTransaProdDao) GlobalVal.ctx.getBean("wmsInveTransaProdDao");
    }

    /**
     * @Title: getWmsInveCreditPackageDao
     * @Description: 获得抵押包信息dao对象
     * @return 抵押包信息dao对象
     * @author: jinzhm
     * @time:2017年2月11日 下午2:44:39
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static WmsInveCreditPackageDao getWmsInveCreditPackageDao()
    {
        return (WmsInveCreditPackageDao) GlobalVal.ctx.getBean("wmsInveCreditPackageDao");
    }

    /**
     * @Title: getWmsInveTransaCrepkgDao
     * @Description: 获得单据关联债权dao对象
     * @return 单据关联债权匹配dao
     * @author: jinzhm
     * @time:2017年2月13日 下午3:00:20
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public static WmsInveTransaCrepkgDao getWmsInveTransaCrepkgDao()
    {
        return (WmsInveTransaCrepkgDao) GlobalVal.ctx.getBean("wmsInveTransaCrepkgDao");
    }

    /**
     * @Title: getWmsInveClerkProtocolTransaCrepkgDao
     * @Description: 获得债权匹配历史dao对象
     * @return 债权匹配历史dao
     * @author: jinzhm
     * @time:2017年2月14日 上午9:45:14
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static WmsInveClerkProtocolTransaCrepkgDao getWmsInveClerkProtocolTransaCrepkgDao()
    {
        return (WmsInveClerkProtocolTransaCrepkgDao) GlobalVal.ctx.getBean("wmsInveClerkProtocolTransaCrepkgDao");
    }

    /**
     * @Title: getWmsInveCreditSplitConfigHeadDao
     * @Description: 获得理财债权拆分系数dao对象
     * @return 理财债权拆分系数dao对象
     * @author: jinzhm
     * @time:2017年2月14日 上午10:37:08
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static WmsInveCreditSplitConfigHeadDao getWmsInveCreditSplitConfigHeadDao()
    {
        return (WmsInveCreditSplitConfigHeadDao) GlobalVal.ctx.getBean("wmsInveCreditSplitConfigHeadDao");
    }

    /**
     * @Title: getWmsInvePruductCategoryByProdId
     * @Description: 根据上单产品表主键获得产品信息
     * @param prodId 上单产品表主键
     * @return 返回产品信息对象
     * @author: jinzhm
     * @time:2017年2月11日 下午1:29:41
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static WmsInvePruductCategory getWmsInvePruductCategoryByProdId(int prodId)
    {
        return getWmsInvePruductCategoryDao().get(getWmsInveTransaProdDao().get(prodId)
                                                                           .getWms_inve_pruduct_category_id());
    }

    /**
     * @Title: getWmsInvePruductCategroyByTransaId
     * @Description: 根据上单表主键获得产品信息
     * @param transaId 上单表主键
     * @return 返回产品信息对象
     * @author: jinzhm
     * @time:2017年2月11日 下午1:31:57
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static WmsInvePruductCategory getWmsInvePruductCategroyByTransaId(int transaId)
    {
        // 查询上单产品信息
        WmsInveTransaProd prod = new WmsInveTransaProd();
        prod.setWms_inve_transa_id(transaId);
        List<WmsInveTransaProd> prodList = getWmsInveTransaProdDao().getListByEntity(prod);
        return getWmsInvePruductCategoryByProdId(prodList.get(0).getWms_inve_transa_prod_id());
    }

    /**
     * @Title: getSalesmanAreaCodeByTransaId
     * @Description: 根据上单主键获取业务员的所在地区编号
     *  因归属公司业务员会变成冯总，所以要用原上单业务员
     * @param transaId 上单主键
     * @return 地区编号
     * @author: jinzhm
     * @time:2017年3月16日 下午5:15:18
     * history:
     * 1、2017年3月16日 jinzhm 创建方法
     */
    public static String getSalesmanAreaCodeByTransaId(int transaId)
    {
        WmsInveTransa transa = getWmsInveTransaDao().get(transaId);
        return transa.getSalesman_city_code();
    }

    /**
     * @Title: getAllCreditDataList
     * @Description: 获得所有抵押包信息，包括分组信息
     * @param transaId 上单表主键
     * @return 抵押包信息集合
     * @author: jinzhm
     * @time:2017年2月11日 下午2:45:42
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static List<CreditData> getAllCreditDataList(int transaId)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        return getWmsInveCreditPackageDao().queryAllCreditList(paramMap);
    }

    /**
     * @Title: isOfflineTransa
     * @Description: 是否为线下合同
     * @param transaId 上单主键
     * @return true表示线下合同
     * @author: jinzhm
     * @time:2017年2月16日 上午11:42:12
     * history:
     * 1、2017年2月16日 jinzhm 创建方法
     */
    public static boolean isOfflineTransa(int transaId)
    {
        WmsInveTransa transa = getWmsInveTransaDao().get(transaId);
        if ("1".equals(transa.getContract_signing_type()))
        {
            return true;
        }
        return false;
    }

    /**
     * @Title: getCreditPackageList
     * @Description: 根据上单主键，合同主键及状态查询抵押包信息集合
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param state 匹配状态
     * @return 抵押包信息集合
     * @author: jinzhm
     * @time:2017年2月15日 上午8:43:32
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public static List<WmsInveCreditPackage> getCreditPackageList(int transaId, Integer protocolId, String state)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("protocolId", protocolId);
        paramMap.put("state", state);
        return getWmsInveCreditPackageDao().queryAllCreditPackageByTransaId(paramMap);
    }

    /**
     * @Title: hasEndCreditPackage
     * @Description: 判断抵押包信息集合中是否有到期的抵押包
     * @param creditPackageList 抵押包信息集合
     * @return 返回true表示有到期的抵押包信息集合
     * @author: jinzhm
     * @time:2017年2月15日 上午8:48:32
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public static boolean hasEndCreditPackage(List<WmsInveCreditPackage> creditPackageList)
    {
        for (WmsInveCreditPackage creditPackage : creditPackageList)
        {
            if (isEndCreditPackage(creditPackage))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: isEndCreditPackage
     * @Description: 判断抵押包是否已经到期
     * @param creditPackage 抵押包信息
     * @return 返回true表示该抵押包已经到期
     * @author: jinzhm
     * @time:2017年2月15日 上午8:49:40
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public static boolean isEndCreditPackage(WmsInveCreditPackage creditPackage)
    {
        // 实际到期时间有值或当前时间大于抵押包到期时间算作到期
        if (creditPackage.getAct_end_date() != null
            || com.zx.sframe.util.DateUtil.getDate10(new Date()).compareTo(creditPackage.getCrepg_end_date()) > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * @Title: isCategoryCanMatchCredit
     * @Description: 判断产品是不是可匹配债权类产品
     * @param category 产品信息
     * @return 产品是不是可拆分类产品
     * @author: jinzhm
     * @time:2017年2月11日 下午2:08:34
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static boolean isCategoryCanMatchCredit(WmsInvePruductCategory category)
    {
        if (CATEGORY_TYPE_SPLIT.equals(category.getCategory_type() + ""))
        {
            return true;
        }
        return false;
    }

    /**
     * @Title: isCategoryCanMatchCredit
     * @Description: 判断产品是不是可匹配债权类产品
     * @param categoryId 产品主键
     * @return 是否可拆分类产品
     * @author: jinzhm
     * @time:2017年2月14日 下午5:04:35
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static boolean isCategoryCanMatchCredit(int categoryId)
    {
        return isCategoryCanMatchCredit(getWmsInvePruductCategoryDao().get(categoryId));
    }

    /**
     * @Title: getMaxCoeff
     * @Description: 获得债权匹配系数可扩大的最大值
     * @return 债权匹配系数可扩大的最大值
     * @author: jinzhm
     * @time:2017年2月20日 上午10:10:23
     * history:
     * 1、2017年2月20日 jinzhm 创建方法
     */
    public static BigDecimal getMaxCoeff()
    {
        return new BigDecimal(getWmsSysPropertyDao().get(MAX_COEFF_PROPERTY_ID).getProperty_value());
    }

    /**
     * @Title: getCreditMatchRule
     * @Description: 获得匹配规则实现类
     * @return 
     * @author: jinzhm
     * @time:2017年2月11日 下午3:22:55
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static CreditMatchRuleInterface getCreditMatchRule()
    {
        return new CreditMatchRule1();
    }

    /**
     * @Title: getProtocolEndDate
     * @Description: 根据实际支付日期获得协议到期日期
     * @param dateOfPayment 实际支付日期
     * @param categoryDeadline 产品期限
     * @return 协议到期日期
     * @author: jinzhm
     * @time:2017年2月11日 下午4:35:06
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    public static Date getProtocolEndDate(Date dateOfPayment, int categoryDeadline)
    {
        if (IncomeUtil.isLaterThanMagicDate(dateOfPayment))
        {
            return DateUtil.changeDay(setDatebyCalendar(dateOfPayment, categoryDeadline), -1);
        }
        else
        {
            return setDatebyCalendar(dateOfPayment, categoryDeadline);
        }
    }

    /**
     * @Title: logCreditMatchError
     * @Description: 记录债权匹配失败日志信息
     * @param logText 日志内容
     * @author: jinzhm
     * @time:2017年2月24日 下午3:33:05
     * history:
     * 1、2017年2月24日 jinzhm 创建方法
     */
    public static void logCreditMatchError(String logText)
    {
        getWmsInveTransaCrepkgDao().insertCreditMatchErrorLog(logText);
    }

    /**
     * @Title: logCreditMatchError
     * @Description: 记录债权匹配失败日志信息
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param productAccount 投资金额
     * @param unMatchedProductAccount 没有匹配全的投资金额（匹配后剩余的投资金额）
     * @author: jinzhm
     * @time:2017年2月24日 下午3:35:28
     * history:
     * 1、2017年2月24日 jinzhm 创建方法
     */
    public static void logCreditMatchError(Integer transaId, Integer protocolId, BigDecimal productAccount,
                                           BigDecimal unMatchedProductAccount, String matchType)
    {
        logCreditMatchError("上单主键：" + transaId + "；协议主键：" + protocolId + "；投资金额："
                            + (productAccount == null ? null : productAccount.toString()) + "；剩余没有匹配到债权金额："
                            + (unMatchedProductAccount == null ? null : unMatchedProductAccount.toString())
                            + "债权匹配失败！ 匹配类型为：" + getCreditMatchTypeName(matchType));
    }

    /**
     * @Title: getCreditMatchTypeName
     * @Description: 根据债权匹配类型获得类型名称
     * @param matchType 匹配流程类型
     * @return 中文名称
     * @author: jinzhm
     * @time:2017年2月24日 下午3:41:49
     * history:
     * 1、2017年2月24日 jinzhm 创建方法
     */
    public static String getCreditMatchTypeName(String matchType)
    {
        if (CreditMatch.FLOW_TRANSA.equals(matchType))
        {
            return "签单匹配";
        }
        else if (CreditMatch.FLOW_REDEEM.equals(matchType))
        {
            return "赎回匹配";
        }
        else if (CreditMatch.FLOW_EXTEND.equals(matchType))
        {
            return "续期匹配";
        }
        else if (CreditMatch.FLOW_CHANGE_CREDIT_PACKAGE.equals(matchType))
        {
            return "债权更换匹配";
        }
        else
        {
            return matchType;
        }
    }

    /**
     * @Title: getProtocolEndDate
     * @Description: 通过上单主键去老协议表中查找协议到期日期
     * @param transaId 上单主键
     * @return 协议到期日期
     * @author: jinzhm
     * @time:2017年2月14日 下午12:57:36
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static Date getProtocolEndDate(int transaId)
    {
        WmsInveTransaProtocol protocol = new WmsInveTransaProtocol();
        protocol.setWms_inve_transa_id(transaId);
        return getWmsInveTransaProtocolDao().getListByEntity(protocol).get(0).getEnd_of_date();
    }

    /**
     * @Title: getTransaCrepkgList
     * @Description: 根据上单主键，合同主键和匹配状态查询单据关联债权相关信息集合
     * @param transaId 单据主键
     * @param protocolId 合同主键（可以为null）
     * @param packageId 抵押包主键（可以为null）
     * @param state 单据状态
     * @return 单据关联债权信息
     * @author: jinzhm
     * @time:2017年2月14日 上午9:25:38
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public static List<WmsInveTransaCrepkg> getTransaCrepkgList(int transaId, Integer protocolId, Integer packageId,
                                                                String state)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("protocolId", protocolId);
        paramMap.put("packageId", packageId);
        paramMap.put("state", state);
        return getWmsInveTransaCrepkgDao().queryAllTransaCrepkgByTransaId(paramMap);
    }

    /**
     * @Title: isMatchedCredit
     * @Description: 判断某个抵押包是否已经匹配过
     * @param packageId 抵押包id
     * @param transaCrepkgList 已匹配债权包集合
     * @return 匹配过返回单据债权关联信息
     * @author: jinzhm
     * @time:2017年2月15日 下午4:07:23
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    public static WmsInveTransaCrepkg isMatchedCredit(int packageId, List<WmsInveTransaCrepkg> transaCrepkgList)
    {
        for (WmsInveTransaCrepkg transaCrepkg : transaCrepkgList)
        {
            if (packageId == transaCrepkg.getWms_inve_credit_package_id())
            {
                return transaCrepkg;
            }
        }
        return null;
    }

    /**
     * @Title: isMatchedCreditPackage
     * @Description: 判断某个抵押包是否已经匹配
     * @param packageId 抵押包id
     * @param creditDataList 匹配的债权对象集合
     * @return 匹配过返回债权数据对象
     * @author: jinzhm
     * @time:2017年3月28日 下午2:44:11
     * history:
     * 1、2017年3月28日 jinzhm 创建方法
     */
    public static CreditData isMatchedCreditData(int packageId, List<CreditData> creditDataList)
    {
        for (CreditData creditData : creditDataList)
        {
            if (packageId == creditData.getId())
            {
                return creditData;
            }
        }
        return null;
    }

    /**
     * @Title: deleteCreditMatchedHistory
     * @Description: 删除债权匹配历史信息
     * @param transaId
     * @param protocolId
     * @param user 
     * @author: jinzhm
     * @time:2017年2月25日 下午2:25:32
     * history:
     * 1、2017年2月25日 jinzhm 创建方法
     */
    public static void deleteCreditMatchedHistory(int transaId, int protocolId, UserBean user)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("protocolId", protocolId);
        paramMap.put("userId", user.getUserId());
        CreditUtils.getWmsInveClerkProtocolTransaCrepkgDao().updateProtocolTransaCrepkg(paramMap);
    }

    /**
     * @Title: packageTransaCrepkg
     * @Description: 封装单据债权关联信息
     * @param creditData 债权信息
     * @param matchData 债权匹配数据对象
     * @return 单据债权关联信息
     * @author: jinzhm
     * @time:2017年2月13日 下午6:53:13
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    public static WmsInveTransaCrepkg packageTransaCrepkg(CreditData creditData, CreditMatchData matchData)
    {
        WmsInveTransaCrepkg transaCrepkg = new WmsInveTransaCrepkg();
        transaCrepkg.setWms_inve_transa_id(matchData.getTransaId());
        transaCrepkg.setWms_inve_clerk_protocol_id(matchData.getProtocolId());
        transaCrepkg.setCre_type(creditData.getCreType());
        transaCrepkg.setWms_inve_credit_package_id(creditData.getId());
        transaCrepkg.setWms_inve_pruduct_category_id(matchData.getCategory().getWms_inve_pruduct_category_id());
        transaCrepkg.setAcl_mon(creditData.getMatchedProductAccount());
        // 匹配时间
        if (matchData.getProtocolStartDate() == null)
        {
            transaCrepkg.setAcl_date(new java.sql.Date(matchData.getActDateOfPayment().getTime()));
        }
        else
        {
            transaCrepkg.setAcl_date(new java.sql.Date(matchData.getProtocolStartDate().getTime()));
        }
        // 匹配状态
        transaCrepkg.setAcl_state(matchData.getState());
        transaCrepkg.setCreate_user_id(matchData.getUser().getUserId());
        transaCrepkg.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        transaCrepkg.setEnable_flag("1");
        return transaCrepkg;
    }

    /**
     * @Title: setDatebyCalendar
     * @Description: 给传入日期增加月份
     * @param sDate 日期
     * @param i 要增加的月份
     * @return 增加月份后的日期
     * @author: jinzhm
     * @time:2017年2月11日 下午4:34:33
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    private static Date setDatebyCalendar(Date sDate, int i)
    {
        java.sql.Date date1;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(sDate);
        calendar.add(Calendar.MONTH, +i);
        java.util.Date date_ = calendar.getTime();
        date1 = new java.sql.Date(date_.getTime());
        return date1;
    }

    // public static <T> List<T> deepCopy(List<T> src) throws IOException,
    // ClassNotFoundException
    // {
    // ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    // ObjectOutputStream out = new ObjectOutputStream(byteOut);
    // out.writeObject(src);
    //
    // ByteArrayInputStream byteIn = new
    // ByteArrayInputStream(byteOut.toByteArray());
    // ObjectInputStream in = new ObjectInputStream(byteIn);
    // List<T> dest = (List<T>) in.readObject();
    // return dest;
    // }
}
