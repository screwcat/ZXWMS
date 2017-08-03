package com.zx.emanage.inve.util.credit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchForTransa
 * 模块名称：签单流程中债权匹配处理类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatchForTransa extends CreditMatchAbstract
{
    /**
     * @Title: setProtocolEndDate
     * @Description: 根据实际支付金额计算协议到期时间
     * @param matchData 债权匹配对象
     * @author: jinzhm
     * @time:2017年2月11日 下午4:22:44
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    @Override
    protected void setProtocolEndDate(CreditMatchData matchData)
    {
        // 获得实际支付时间
        Date dateOfPayment = matchData.getActDateOfPayment();
        WmsInvePruductCategory category = CreditUtils.getWmsInvePruductCategroyByTransaId(matchData.getTransaId());
        // 设置协议到期时间
        matchData.setProtocolEndDate(CreditUtils.getProtocolEndDate(dateOfPayment, category.getCategory_deadline()));
    }

    /**
     * @Title: setMustMatch
     * @Description: 设置是否必须匹配债权，签单流程中不需要必须匹配
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午2:17:46
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#setMustMatch(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    protected void setMustMatch(CreditMatchData matchData)
    {
        // 设置匹配债权后的匹配状态
        matchData.setState(CreditMatchInterface.ACL_STATE_HOLD);
        matchData.setMustMatch(false);
    }

    /**
    * @Title: getCreditList
    * @Description: 签单流程获得要用于匹配债权的抵押包集合
    * 抵押包的开始还款日期-一个月需要小于等于实际支付日期
    * 抵押包的终止日期需要大于等于实际支付日期+N天（property表中配置）
    * @param matchData
    * @return 用于匹配的抵押包集合
    * @author: jinzhm
    * @time:2017年3月22日 下午2:33:21
    * @see
    com.zx.emanage.inve.util.credit.CreditMatchAbstract#getCreditList(com.zx.emanage.inve.util.credit.CreditMatchData)
    * history:
    * 1、2017年3月22日 jinzhm 创建方法
    */
    @Override
    protected List<CreditData> getCreditList(CreditMatchData matchData)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", matchData.getTransaId());
        paramMap.put("date1", matchData.getActDateOfPayment());
        paramMap.put("date2", matchData.getActDateOfPayment());
        paramMap.put("dayRange", CreditUtils.getWmsSysPropertyDao().get(CreditUtils.DAY_RANGE_PROPERTY_ID)
                                            .getProperty_value());
        return CreditUtils.getWmsInveCreditPackageDao().queryAllCreditList(paramMap);
    }

    /**
     * @Title: release
     * @Description: 签单流程中释放债权
     *      签单流程中可能将已生效的债权释放（签约退回-修改产品）
     * @param creditMatchData 
     * @author: jinzhm
     * @time:2017年2月13日 下午4:52:41
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#release(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    public void release(CreditMatchData creditMatchData)
    {
        disableMatchedCredit(creditMatchData);
    }

}
