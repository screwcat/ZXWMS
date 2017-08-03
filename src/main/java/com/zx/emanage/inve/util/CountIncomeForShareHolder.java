//package com.zx.emanage.inve.util;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
//import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
//import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
//import com.zx.sframe.util.vo.UserBean;
//
///**
// * 版权所有：版权所有(C) 2016，卓信金控
// * 系统名称：财富管理平台
// * @ClassName: CountIncomeForShareHolder
// * 模块名称：
// * @Description: 内容摘要：
// * @author jinzhm
// * @date 2017年6月7日
// * @version V1.0
// * history:
// * 1、2017年6月7日 jinzhm 创建文件
// */
//public class CountIncomeForShareHolder extends CountIncomeAbstract
//{
//
//    /**
//     * @Title: generateIncomeAndLogForNewExtend
//     * @Description: 股东单据续期生成交易日志按照老单据交易日志生成，但调用方法是按新单据调用
//     *      新单据生成一个理财开始日志
//     *      老单据生成一个续期日志及一个理财结束日志
//     * @param protocol 新协议信息
//     * @param oldProtocol 老协议信息
//     * @param extendDate 续期时间
//     * @param user 登录用户
//     * @author: jinzhm
//     * @time:2017年6月7日 下午3:04:14
//     * @see com.zx.emanage.inve.util.CountIncomeAbstract#generateIncomeAndLogForNewExtend(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, java.util.Date, com.zx.sframe.util.vo.UserBean)
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//    */
//    @Override
//    public void generateIncomeAndLogForNewExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
//                                                 Date extendDate, UserBean user)
//    {
//        List<WmsInveTransaLog> transaLogList = new ArrayList<WmsInveTransaLog>();
//
//        // 新单据生成理财开始日志
//        transaLogList.add(setWmsInveTransaLog(protocol.getEnd_of_date(), TRANSA_START, BigDecimal.ZERO,
//                                              BigDecimal.ZERO, protocol, user));
//
//        // 老单据生成续期日期和理财结束日志
//        WmsInveTransaLog extendLog = generateTransaLog(extendDate, oldProtocol, user, TRANSA_EXTEND);
//        extendLog.setRemark("自动续期&lt;" + transa.getBill_code() + "&gt;");
//        WmsInveTransaLog endLog = generateTransaLog(extendDate, oldProtocol, user, TRANSA_END);
//        transaLogList.add(extendLog);
//        transaLogList.add(endLog);
//
//        IncomeUtil.getWmsInveTransaLogDao().addBatchWmsInveTransaLogs(transaLogList);
//    }
//
//    /**
//     * @Title: handleIncomeAndLog
//     * @Description: 为股东单据生成收益信息
//     *      股东单据不需要生成收益信息，只生成交易日志信息
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月7日 下午1:15:53
//     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleIncomeAndLog()
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//    */
//    @Override
//    protected Map<String, Object> handleIncomeAndLog()
//    {
//        // 生成交易日志集合
//        List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<WmsInveTransaLog>();
//
//        wmsInveTransaLogs.add(setWmsInveTransaLog(protocol.getDate_of_payment(), TRANSA_START, BigDecimal.ZERO,
//                                                  BigDecimal.ZERO, protocol, user));
//
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        resMap.put("wmsInveTransaLogs", wmsInveTransaLogs);
//        return resMap;
//    }
//
//    /**
//     * @Title: handleIncomeAndLogForRedeem
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月7日 下午1:15:53
//     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleIncomeAndLogForRedeem()
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//    */
//    @Override
//    protected Map<String, Object> handleIncomeAndLogForRedeem()
//    {
//        return null;
//    }
//
//    /**
//     * @Title: getFullMonthBonusReturnRate
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @param month
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月7日 下午1:15:53
//     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int)
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//    */
//    @Override
//    protected BigDecimal getFullMonthBonusReturnRate(int month)
//    {
//        return BigDecimal.ZERO;
//    }
//
//    /**
//     * @Title: getFullMonthBonusReturnRate
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @param month
//     * @param rebateWayList
//     * @return 
//     * @author: jinzhm
//     * @time:2017年6月7日 下午1:15:53
//     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int, java.util.List)
//     * history:
//     * 1、2017年6月7日 jinzhm 创建方法
//    */
//    @Override
//    protected BigDecimal getFullMonthBonusReturnRate(int month, List<WmsInvePruductRebateWay> rebateWayList)
//    {
//        return BigDecimal.ZERO;
//    }
//
//}
