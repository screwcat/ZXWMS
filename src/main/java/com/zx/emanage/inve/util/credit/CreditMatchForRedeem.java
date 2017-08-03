package com.zx.emanage.inve.util.credit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.sframe.util.DateUtil;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchForRedeem
 * 模块名称：赎回时债权匹配处理类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatchForRedeem extends CreditMatchAbstract
{

    /**
     * @Title: setProtocolEndDate
     * @Description: 赎回时已经传入协议到期日期，此处不需要设置
     * @param matchData 
     * @author: jinzhm
     * @time:2017年2月13日 下午1:55:33
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#setProtocolEndDate(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    protected void setProtocolEndDate(CreditMatchData matchData)
    {
        // 去老协议表中找上单主键
        matchData.setProtocolEndDate(CreditUtils.getProtocolEndDate(matchData.getTransaId()));
    }

    /**
     * @Title: setMustMatch
     * @Description: 设置是否必须匹配债权，赎回时必须匹配
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午2:17:03
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#setMustMatch(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    protected void setMustMatch(CreditMatchData matchData)
    {
        // 设置匹配债权后的匹配状态
        matchData.setState(CreditMatchInterface.ACL_STATE_ENABLE);
        matchData.setMustMatch(true);
    }

    /**
     * @Title: getCreditList
     * @Description: 赎回流程获得要用于匹配债权的抵押包集合
     *      抵押包开始还款日期 - 一个月 <= 当前时间
     *      抵押包终止日期 >= 当前时间
     * @param matchData 债权匹配数据对象
     * @return 用户匹配的抵押包集合
     * @author: jinzhm
     * @time:2017年3月23日 上午11:07:03
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#getCreditList(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年3月23日 jinzhm 创建方法
    */
    @Override
    protected List<CreditData> getCreditList(CreditMatchData matchData)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", matchData.getTransaId());
        paramMap.put("date1", DateUtil.getDate10(new Date()));
        paramMap.put("date2", DateUtil.getDate10(new Date()));
        paramMap.put("dayRange", 0);
        return CreditUtils.getWmsInveCreditPackageDao().queryAllCreditList(paramMap);
    }

    /**
     * @Title: release
     * @Description: 赎回流程释放债权信息
     * @param creditMatchData 
     * @author: jinzhm
     * @time:2017年2月13日 下午5:26:32
     * @see com.zx.emanage.inve.util.credit.CreditMatchInterface#release(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    public void release(CreditMatchData creditMatchData)
    {
        // 查询该单据所有生效中的债权信息
        List<WmsInveTransaCrepkg> holdOrEnableTransaCrepkg = CreditUtils.getTransaCrepkgList(creditMatchData.getTransaId(),
                                                                                             creditMatchData.getProtocolId(),
                                                                                             null, "'"
                                                                                                   + ACL_STATE_ENABLE
                                                                                                   + "'");
        // 循环单据债权关联信息修改单据债权关联匹配状态和抵押包信息已匹配金额等信息
        for (WmsInveTransaCrepkg transaCrepkg : holdOrEnableTransaCrepkg)
        {
            // 要修改的单据债权关联对象
            WmsInveTransaCrepkg updTransaCrepkg = new WmsInveTransaCrepkg();
            // 设置主键
            updTransaCrepkg.setWms_inve_transa_crepkg_id(transaCrepkg.getWms_inve_transa_crepkg_id());
            // 失效日期
            updTransaCrepkg.setDisacl_date(new java.sql.Date(System.currentTimeMillis()));
            updTransaCrepkg.setDisacl_type("正常赎回");
            updTransaCrepkg.setLast_update_user_id(creditMatchData.getUser().getUserId());
            updTransaCrepkg.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 匹配状态设置成已赎回
            updTransaCrepkg.setAcl_state(ACL_STATE_REDEEM);
            // 修改单据债权关联信息
            CreditUtils.getWmsInveTransaCrepkgDao().update(updTransaCrepkg);

            // 查询抵押包信息
            WmsInveCreditPackage creditPackage = CreditUtils.getWmsInveCreditPackageDao()
                                                            .get(transaCrepkg.getWms_inve_credit_package_id());
            // 要修改的抵押包信息对象
            WmsInveCreditPackage updCreditPackage = new WmsInveCreditPackage();
            // 设置主键
            updCreditPackage.setWms_inve_credit_package_id(creditPackage.getWms_inve_credit_package_id());
            // 生效状态的单据债权关联信息要释放的时候需要修改总已匹配金额
            updCreditPackage.setMatched_product_account(creditPackage.getMatched_product_account()
                                                                     .subtract(transaCrepkg.getAcl_mon()));
            updCreditPackage.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            updCreditPackage.setLast_update_user_id(creditMatchData.getUser().getUserId());
            // 修改抵押包信息
            CreditUtils.getWmsInveCreditPackageDao().update(updCreditPackage);
        }
    }

}
