package com.zx.emanage.inve.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInvePruductRebateWay;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransaLog;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeForPTP
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年6月21日
 * @version V1.0
 * history:
 * 1、2017年6月21日 jinzhm 创建文件
 */
public class CountIncomeForPTP extends CountIncomeAbstract
{

    /**
     * @Title: getIncomeAndLog
     * @Description: 重写父类中生成收益的方法，ptp单据直接生成理财开始的交易日志并保存即可
     * @param protocol
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午2:00:14
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getIncomeAndLog(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @SuppressWarnings("unchecked")
    @Override
    public void getIncomeAndLog(WmsInveTransaProtocol protocol, UserBean user)
    {
        this.protocol = protocol;
        this.user = user;
        Map<String, Object> resMap = handleIncomeAndLog();

        // 保存交易日志信息
        List<WmsInveTransaLog> wmsInveTransaLogs = (List<WmsInveTransaLog>) resMap.get("wmsInveTransaLogs");
        if (wmsInveTransaLogs != null && wmsInveTransaLogs.size() > 0)
        {
            IncomeUtil.getWmsInveTransaLogDao().addBatchWmsInveTransaLogs(wmsInveTransaLogs);
        }
    }

    /**
    * @Title: handleIncomeAndLog
    * @Description: ptp单据直接生成一个理财开始的交易日志，不生成收益信息
    * @return
    * @author: jinzhm
    * @time:2017年6月21日 下午1:52:33
    * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleIncomeAndLog()
    * history:
    * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    protected Map<String, Object> handleIncomeAndLog()
    {
        // 生成交易日志集合
        List<WmsInveTransaLog> wmsInveTransaLogs = new ArrayList<WmsInveTransaLog>();

        wmsInveTransaLogs.add(setWmsInveTransaLog(protocol.getDate_of_payment(), TRANSA_START, BigDecimal.ZERO,
                                                  BigDecimal.ZERO, protocol, user));

        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("wmsInveTransaLogs", wmsInveTransaLogs);
        return resMap;
    }
    
    /**
     * @Title: getIncomeAndLogForRedeem
     * @Description: ptp单据没有收益，生成赎回收益和普通单据不同，重写父类方法直接生成赎回收益
     * @param protocol
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午2:03:54
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getIncomeAndLogForRedeem(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    public void getIncomeAndLogForRedeem(WmsInveTransaProtocol protocol, UserBean user)
    {
        // 查询赎回明细信息
        WmsInveRedeemDetail redeemDetail = new WmsInveRedeemDetail();
        redeemDetail.setWms_inve_redeem_id(protocol.getWms_inve_redeem_id());
        redeemDetail.setEnable_flag("1");
        List<WmsInveRedeemDetail> detailList = IncomeUtil.getWmsInveRedeemDetailDao().getListByEntity(redeemDetail);
        redeemDetail = detailList.get(0);

        // 查询赎回信息
        WmsInveRedeem redeem = IncomeUtil.getWmsInveRedeemDao().get(protocol.getWms_inve_redeem_id());

        WmsInveTransaLog wmsInveTransaLog = new WmsInveTransaLog();
        wmsInveTransaLog.setWms_inve_transa_id(protocol.getWms_inve_transa_id());// 上单信息表主键
        wmsInveTransaLog.setProduct_account(redeemDetail.getRedeem_amount());// 本金变化

        BigDecimal payableBasicIncome = redeemDetail.getPayable_basic_income() == null ? BigDecimal.ZERO
                                                                                      : redeemDetail.getPayable_basic_income();

        wmsInveTransaLog.setProduct_interest_account(payableBasicIncome);// 收益变化
        wmsInveTransaLog.setOperate_item(CountIncome.TRANSA_REDEEM);// 操作事项
        wmsInveTransaLog.setChange_date(redeem.getRedeem_date());// 变化日期
        wmsInveTransaLog.setRemark("赎回单据&lt;" + redeem.getBill_code() + "&gt;");
        wmsInveTransaLog.setCreate_user_id(user.getUserId());// 创建人
        wmsInveTransaLog.setCreate_user_name(user.getRealname());// 创建人姓名
        wmsInveTransaLog.setCreate_timestamp(new Timestamp(new Date().getTime()));// 创建时间

        // 保存赎回交易日志信息
        IncomeUtil.getWmsInveTransaLogDao().save(wmsInveTransaLog);

        // 如果是全部赎回，生成理财结束交易日志
        if ("1".equals(redeemDetail.getIs_fully_redeemed()))
        {
            // 生成理财结束交易日志并保存
            WmsInveTransaLog endTransaLog = generateTransaLog(redeem.getRedeem_date(), protocol, user, TRANSA_END);
            IncomeUtil.getWmsInveTransaLogDao().save(endTransaLog);
        }
    }

    /**
     * @Title: handleIncomeAndLogForRedeem
     * @Description: 重写直接返回空集合
     * @return 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:52:33
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleIncomeAndLogForRedeem()
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    protected Map<String, Object> handleIncomeAndLogForRedeem()
    {
        return new HashMap<String, Object>();
    }

    /**
     * @Title: reGenerateIncomeAndLog
     * @Description: ptp单据不计算收益，但需要生成理财开始的交易日志，直接调用生成交易日志方法
     * @return 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:57:38
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#reGenerateIncomeAndLog()
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    protected Map<String, Object> reGenerateIncomeAndLog()
    {
        return handleIncomeAndLog();
    }

    /**
     * @Title: getIncomeAndLogForAutoExtend
     * @Description: ptp单据没有自动续期，不做任何处理
     * @param protocol
     * @param oldProtocol
     * @param extendDate
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:56:36
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getIncomeAndLogForAutoExtend(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, java.util.Date, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    public void getIncomeAndLogForAutoExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                             Date extendDate, UserBean user)
    {
    }

    /**
     * @Title: generateIncomeAndLogForNewExtend
     * @Description: ptp单据没有续期不做任何处理
     * @param protocol
     * @param oldProtocol
     * @param extendDate
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:56:55
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#generateIncomeAndLogForNewExtend(com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, com.zx.emanage.util.gen.entity.WmsInveTransaProtocol, java.util.Date, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    public void generateIncomeAndLogForNewExtend(WmsInveTransaProtocol protocol, WmsInveTransaProtocol oldProtocol,
                                                 Date extendDate, UserBean user)
    {
    }


    /**
     * @Title: handleTransaIncomeForNewExtend
     * @Description: ptp单据预约续期不做任何处理，ptp单据没有预约续期
     * @param transaId
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:55:14
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleTransaIncomeForNewExtend(int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForNewExtend(int transaId, UserBean user)
    {
    }

    /**
     * @Title: handleTransaIncomeForCancelNewExtend
     * @Description: ptp单据取消预约续期不做任何处理，ptp单据没有预约续期
     * @param transaId
     * @param user 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:55:52
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#handleTransaIncomeForCancelNewExtend(int, com.zx.sframe.util.vo.UserBean)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    public void handleTransaIncomeForCancelNewExtend(int transaId, UserBean user)
    {
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 在ptp单据中奖励利率没有用处直接返回0
     * @param month
     * @return 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:52:33
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month)
    {
        return BigDecimal.ZERO;
    }

    /**
     * @Title: getFullMonthBonusReturnRate
     * @Description: 在ptp单据中奖励利率没有用处直接返回0
     * @param month
     * @param rebateWayList
     * @return 
     * @author: jinzhm
     * @time:2017年6月21日 下午1:52:33
     * @see com.zx.emanage.inve.util.CountIncomeAbstract#getFullMonthBonusReturnRate(int, java.util.List)
     * history:
     * 1、2017年6月21日 jinzhm 创建方法
    */
    @Override
    protected BigDecimal getFullMonthBonusReturnRate(int month, List<WmsInvePruductRebateWay> rebateWayList)
    {
        return BigDecimal.ZERO;
    }

}
