package com.zx.emanage.inve.util.credit;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchAbstract
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public abstract class CreditMatchAbstract implements CreditMatchInterface
{

    /**
     * @Title: setProtocolEndDate
     * @Description: 根据实际情况设置协议到期时间
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午1:55:02
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected abstract void setProtocolEndDate(CreditMatchData matchData);

    /**
     * @Title: setMustMatch
     * @Description: 设置是否必须匹配成功
     * @param matchData 匹配债权数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午2:13:27
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
     */
    protected abstract void setMustMatch(CreditMatchData matchData);

    /**
    * @Title: getCreditList
    * @Description: 获得要用于匹配的抵押包集合
    * @param matchData 债权匹配数据对象
    * @return 用于匹配的抵押包集合
    * @author: jinzhm
    * @time:2017年3月22日 下午2:31:59
    * history:
    * 1、2017年3月22日 jinzhm 创建方法
    */
    protected abstract List<CreditData> getCreditList(CreditMatchData matchData);

    /**
     * @Title: disableMatchedCredit
     * @Description: 失效债权
     * @param creditMatchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月21日 下午3:00:27
     * @see com.zx.emanage.inve.util.credit.CreditMatchInterface#disableMatchedCredit(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
    */
    @Override
    public void disableMatchedCredit(CreditMatchData creditMatchData)
    {
        // 查询该单据所有占用或生效中的债权信息
        List<WmsInveTransaCrepkg> holdOrEnableTransaCrepkg = CreditUtils.getTransaCrepkgList(creditMatchData.getTransaId(),
                                                                                             creditMatchData.getProtocolId(),
                                                                                             null, "'"
                                                                                                   + ACL_STATE_ENABLE
                                                                                                   + "','"
                                                                                                   + ACL_STATE_HOLD
                                                                                                   + "'");
        for (WmsInveTransaCrepkg transaCrepkg : holdOrEnableTransaCrepkg)
        {
            // 要修改的单据债权关联对象
            WmsInveTransaCrepkg updTransaCrepkg = new WmsInveTransaCrepkg();
            // 设置主键
            updTransaCrepkg.setWms_inve_transa_crepkg_id(transaCrepkg.getWms_inve_transa_crepkg_id());
            // 签单流程直接设置成失效
            updTransaCrepkg.setEnable_flag("0");
            // 失效日期
            updTransaCrepkg.setLast_update_user_id(creditMatchData.getUser().getUserId());
            updTransaCrepkg.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 修改单据债权关联信息
            CreditUtils.getWmsInveTransaCrepkgDao().update(updTransaCrepkg);
            // 如果修改前是生效中的匹配债权类型，需要修改抵押包信息的总已匹配金额
            // 查询抵押包信息
            WmsInveCreditPackage creditPackage = CreditUtils.getWmsInveCreditPackageDao()
                                                            .get(transaCrepkg.getWms_inve_credit_package_id());
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

    /**
     * @Title: match
     * @Description: 匹配债权并占用或生效债权
     *  签单流程中匹配债权后占用即可，匹配不到直接返回
     *  续期和赎回流程中必须匹配到债权并生效债权
     * @param creditMatchData 债权匹配对象
     * @return 是否匹配成功
     * @author: jinzhm
     * @time:2017年2月10日 下午5:01:30
     * @see com.zx.emanage.inve.util.credit.CreditMatchInterface#match(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月10日 jinzhm 创建方法
    */
    @Override
    public boolean match(CreditMatchData creditMatchData)
    {
        // 设置协议到期时间
        setProtocolEndDate(creditMatchData);
        // 设置是否必须匹配
        setMustMatch(creditMatchData);
        // 设置所有债权包信息
        creditMatchData.setCreditDataList(getCreditList(creditMatchData));

        // 执行债权匹配
        boolean matched = CreditUtils.getCreditMatchRule().match(creditMatchData);

        // 如果匹配成功
        if (matched)
        {
            // 持久化匹配信息
            persistMatchedCredit(creditMatchData);
        }
        return matched;
    }

    /**
     * @Title: persistMatchedCredit
     * @Description: 保存匹配到的债权信息集合
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月15日 上午11:46:11
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
     */
    protected void persistMatchedCredit(CreditMatchData matchData)
    {
        List<WmsInveTransaCrepkg> updTransaCrepkgList = new ArrayList<WmsInveTransaCrepkg>();
        // 循环匹配到的债权
        for (CreditData creditData : matchData.getMatchedCreditDataList())
        {
            WmsInveTransaCrepkg transaCrepkg = CreditUtils.packageTransaCrepkg(creditData, matchData);
            // 将单据关联债权信息保留到要新增的集合中
            updTransaCrepkgList.add(transaCrepkg);
            // 查询抵押包信息
            WmsInveCreditPackage creditPackage = CreditUtils.getWmsInveCreditPackageDao()
                                                            .get(transaCrepkg.getWms_inve_credit_package_id());
            // 如果是匹配后生效状态，要修改抵押包信息表中的总已匹配金额
            // if (ACL_STATE_ENABLE.equals(transaCrepkg.getAcl_state()))
            // {
            // 2017-02-16 和关姐确认不管占用还是匹配都得更新抵押包当前已匹配金额
            // 要修改的抵押包信息
            WmsInveCreditPackage updCreditPackage = new WmsInveCreditPackage();
            // 设置主键
            updCreditPackage.setWms_inve_credit_package_id(transaCrepkg.getWms_inve_credit_package_id());
            // 更新已匹配金额
            updCreditPackage.setMatched_product_account(creditPackage.getMatched_product_account()
                                                                     .add(transaCrepkg.getAcl_mon()));
            // 设置修改信息
            updCreditPackage.setLast_update_user_id(matchData.getUser().getUserId());
            updCreditPackage.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
            // 修改抵押包信息
            CreditUtils.getWmsInveCreditPackageDao().update(updCreditPackage);
            // }
        }

        // 如果有要保存的单据债权关联信息，批量保存
        if (!updTransaCrepkgList.isEmpty())
        {
            CreditUtils.getWmsInveTransaCrepkgDao().batchInsertTransaCrepkg(updTransaCrepkgList);
        }
    }

}
