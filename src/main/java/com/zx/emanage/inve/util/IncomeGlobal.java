package com.zx.emanage.inve.util;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: IncomeGlobal
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2016年12月26日
 * @version V1.0
 * history:
 * 1、2016年12月26日 jinzhm 创建文件
 */
public interface IncomeGlobal
{
    public static final String MAGIC_DATE_OF_PAYMENT_STR = "2016-07-01";

    /**
     * 卓年宝产品id
     */
    public static final Integer SPECIAL_PRODUCT_ID = 8;

    /**
     * 公益6产品id
     */
    public static final int WMS_INVE_PUBLIC_PRODCUT_ID = 44;
    
    /**
     * 满月存量奖励方式，特殊产品奖励的属性id
     */
    public static final int WMS_SYS_PROPERTY_SPECIAL_BONUS_ID = 5004;

    /**
     * 理财上单
     */
    public static final String TRANSA_START = "理财上单";

    /**
     * 支付收益
     */
    public static final String TRANSA_INCOME = "支付收益";

    /**
     * 理财结束
     */
    public static final String TRANSA_END = "理财结束";

    /**
     * 提前赎回
     */
    public static final String TRANSA_REDEEM = "提前赎回";

    /**
     * 理财续期
     */
    public static final String TRANSA_EXTEND = "理财续期";

    /**
     * 未支付
     */
    public static final String PAY_STATUS_NOT_PAY = "0";

    /**
     * 已支付
     */
    public static final String PAY_STATUS_ALREADY_PAY = "1";

    /**
     * 已终止
     */
    public static final String PAY_STATUS_TERMINATE = "2";

    /**
     * 续期已支付
     */
    public static final String PAY_STATUS_EXTEND_ALREADY_PAY = "3";

    /**
     * 处理年付公益已支付（按年支付公益收益改成按月支付公益收益时使用）
     */
    public static final String PAY_STATUS_YEAR_PUBLIC_ALREADY_PAY = "4";

    /**
     * 跨年续期生成不需要支付的收益状态
     */
    public static final String PAY_STATUS_EXTEND_NO_NEED_PAY = "5";

    /**
     * 赎回收益
     */
    public static final String PAY_TYPE_REDEEM = "1";

    /**
     * 正常收益
     */
    public static final String PAY_TYPE_NORMAL = "2";

    /**
     * 公益收益
     */
    public static final String PAY_TYPE_PUBLIC = "3";
}
