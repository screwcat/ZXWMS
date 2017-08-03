package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchForChangeCreditPackage
 * 模块名称：更换到期债权包实现类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月13日
 * @version V1.0
 * history:
 * 1、2017年2月13日 jinzhm 创建文件
 */
public class CreditMatchForChangeCreditPackage extends CreditMatchAbstract
{

    /**
     * @Title: getEndCreditProductAccountList
     * @Description: 设置到期债权要匹配债权的投资金额
     * @param matchData 债权匹配对象
     * @author: jinzhm
     * @time:2017年2月15日 上午9:24:29
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
    */
    private List<CreditMatchChangeData> getEndCreditProductAccountList(CreditMatchData creditMatchData)
    {
        List<CreditMatchChangeData> changeDataList = new ArrayList<>();
        List<WmsInveCreditPackage> creditPackageList = CreditUtils.getCreditPackageList(creditMatchData.getTransaId(),
                                                                                        creditMatchData.getProtocolId(),
                                                                                        "'" + ACL_STATE_ENABLE + "'");

        CreditMatchChangeData changeData = null;

        for (WmsInveCreditPackage creditPackage : creditPackageList)
        {
            if (CreditUtils.isEndCreditPackage(creditPackage))
            {
                // 根据上单，合同，抵押包和匹配状态查找单据债权关联信息（只能查到一条记录）
                List<WmsInveTransaCrepkg> transaCrepkgList = CreditUtils.getTransaCrepkgList(creditMatchData.getTransaId(),
                                                                                             creditMatchData.getProtocolId(),
                                                                                             creditPackage.getWms_inve_credit_package_id(),
                                                                                             "'" + ACL_STATE_ENABLE
                                                                                                     + "'");
                // 如果集合为空跳过循环
                if (transaCrepkgList.isEmpty())
                {
                    continue;
                }
                // 获得单据债权关联信息
                WmsInveTransaCrepkg transaCrepkg = transaCrepkgList.get(0);
                // 封装更换债权数据
                changeData = new CreditMatchChangeData();
                // 设置抵押包到期时间
                changeData.setCreditEndDate(creditPackage.getAct_end_date() != null ? creditPackage.getAct_end_date()
                                                                                   : creditPackage.getCrepg_end_date());
                // 设置抵押包匹配债权金额
                changeData.setProductAccount(transaCrepkg.getAcl_mon());
                // 设置抵押包数据和债权单据关联数据
                changeData.setCreditPackage(creditPackage);
                changeData.setTransaCrepkg(transaCrepkg);
                changeDataList.add(changeData);
            }
        }
        return changeDataList;
    }

    /**
     * @Title: match
     * @Description: 替换债权匹配
     * @param matchData
     * @return 
     * @author: jinzhm
     * @time:2017年2月15日 上午9:24:29
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#match(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月15日 jinzhm 创建方法
    */
    @Override
    public boolean match(CreditMatchData matchData)
    {
        // 设置到期债权集合
        List<CreditMatchChangeData> creditChangeDataList = getEndCreditProductAccountList(matchData);
        // 设置合同到期日期
        setProtocolEndDate(matchData);
        // 设置是否必须匹配
        setMustMatch(matchData);

        // 循环要更换的抵押包集合
        for (CreditMatchChangeData changeData : creditChangeDataList)
        {
            // 获得更换债权处理实现对象
            CreditMatchRuleInterface creditMatchRule = new CreditMatchRuleForChange();
            // 取当前还生效中的债权信息
            List<WmsInveTransaCrepkg> transaCrepkgList = CreditUtils.getTransaCrepkgList(matchData.getTransaId(),
                                                                                         matchData.getProtocolId(),
                                                                                         null, "'" + ACL_STATE_ENABLE
                                                                                               + "'");
            // 当前生效中的债权包信息集合
            matchData.setMatchedTransaCrepkg(transaCrepkgList);

            // 设置当前循环要替换的债权金额
            matchData.setProductAccount(changeData.getProductAccount());
            matchData.setCreditEndDate(changeData.getCreditEndDate());
            // 设置所有债权包信息
            matchData.setCreditDataList(getCreditList(matchData));

            // 匹配债权
            boolean matched = creditMatchRule.match(matchData);
            // 如果没有匹配到债权
            if (!matched)
            {
                /**
                 *  当在
                 *  债权更换时，抵押包的开始还款日期-一个月要小于等于要更换抵押包的到期日期
                 *  且新抵押包得到期日期要大于等于当前日期
                 *  范围内没有匹配全债权
                 *  需要在
                 *  债权更换时，抵押包的开始还款日期-一个月要小于等于要更换抵押包的到期日期
                 *  且新抵押包到期日期要大于等于要更换抵押包的到期日期
                 */
                matchData.setCreditDataList(getCreditList(matchData.getTransaId(), changeData.getCreditEndDate()));
                matched = creditMatchRule.match(matchData);
                if (!matched)
                {
                    return false;
                }
            }

            // 释放到期债权
            // release(matchData);
            releaseChangeCredit(changeData, matchData.getUser());
            // 持久化新的债权匹配信息
            persistMatchedCredit(matchData);

            matchData.getMatchedCreditDataList().clear();
        }

        // 删除债权匹配历史
        CreditUtils.deleteCreditMatchedHistory(matchData.getTransaId(), matchData.getProtocolId(), matchData.getUser());
        return true;
    }

    /**
     * @Title: releaseChangeCredit
     * @Description: 释放债权
     * @param changeData 要替换的抵押包信息
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年4月18日 下午4:34:07
     * history:
     * 1、2017年4月18日 jinzhm 创建方法
     */
    private void releaseChangeCredit(CreditMatchChangeData changeData, UserBean user)
    {
        WmsInveTransaCrepkg updTransaCrepkg = new WmsInveTransaCrepkg();
        updTransaCrepkg.setWms_inve_transa_crepkg_id(changeData.getTransaCrepkg().getWms_inve_transa_crepkg_id());
        // 匹配状态设置成已到期
        updTransaCrepkg.setAcl_state(ACL_STATE_END);
        updTransaCrepkg.setDisacl_date(new java.sql.Date(System.currentTimeMillis()));
        updTransaCrepkg.setDisacl_type("已到期");
        updTransaCrepkg.setLast_update_user_id(user.getUserId());
        updTransaCrepkg.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
        // 修改单据债权关联信息
        CreditUtils.getWmsInveTransaCrepkgDao().update(updTransaCrepkg);
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
        // 循环匹配到的债权
        for (CreditData creditData : matchData.getMatchedCreditDataList())
        {
            WmsInveTransaCrepkg transaCrepkg = CreditUtils.packageTransaCrepkg(creditData, matchData);

            // 如果更换新匹配的债权不是之前已经匹配到的债权，直接保存
            if (creditData.getTransaCrepkgId() == null)
            {
                CreditUtils.getWmsInveTransaCrepkgDao().save(transaCrepkg);

                // 查询抵押包信息
                WmsInveCreditPackage creditPackage = CreditUtils.getWmsInveCreditPackageDao()
                                                                .get(transaCrepkg.getWms_inve_credit_package_id());
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
            }
            // 如果更换新匹配的债权是之前已经匹配到的债权
            else
            {
                // 循环之前已经匹配到的债权查询匹配金额是否修改了，修改了的话需要更新修改信息，没修改不保存
                for (WmsInveTransaCrepkg matchedTransaCrepkg : matchData.getMatchedTransaCrepkg())
                {
                    if (matchedTransaCrepkg.getWms_inve_transa_crepkg_id().compareTo(creditData.getTransaCrepkgId()) == 0
                        && BigDecimal.ZERO.compareTo(creditData.getMatchedProductAccount()) != 0)
                    {
                        WmsInveTransaCrepkg updTransaCrepkg = new WmsInveTransaCrepkg();
                        // 单据债权关联信息主键
                        updTransaCrepkg.setWms_inve_transa_crepkg_id(creditData.getTransaCrepkgId());
                        // 设置匹配状态
                        updTransaCrepkg.setAcl_state(ACL_STATE_CHANGE_IN_MATCHED);
                        // 修改时间及修改人
                        updTransaCrepkg.setLast_update_user_id(matchData.getUser().getUserId());
                        updTransaCrepkg.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                        // 更新
                        CreditUtils.getWmsInveTransaCrepkgDao().update(updTransaCrepkg);
                        // 设置匹配金额需要相加
                        transaCrepkg.setAcl_mon(matchedTransaCrepkg.getAcl_mon()
                                                                   .add(creditData.getMatchedProductAccount()));
                        // 保存新的单据债权关联信息
                        CreditUtils.getWmsInveTransaCrepkgDao().save(transaCrepkg);

                        // 查询抵押包信息
                        WmsInveCreditPackage creditPackage = CreditUtils.getWmsInveCreditPackageDao()
                                                                        .get(transaCrepkg.getWms_inve_credit_package_id());
                        // 2017-02-16 和关姐确认不管占用还是匹配都得更新抵押包当前已匹配金额
                        // 要修改的抵押包信息
                        WmsInveCreditPackage updCreditPackage = new WmsInveCreditPackage();
                        // 设置主键
                        updCreditPackage.setWms_inve_credit_package_id(transaCrepkg.getWms_inve_credit_package_id());
                        // 更新已匹配金额
                        // 先减去之前匹配的金额在增加本次金额（本次匹配金额中包含了之前匹配的金额）
                        updCreditPackage.setMatched_product_account(creditPackage.getMatched_product_account()
                                                                                 .subtract(matchedTransaCrepkg.getAcl_mon())
                                                                                 .add(transaCrepkg.getAcl_mon()));
                        // 设置修改信息
                        updCreditPackage.setLast_update_user_id(matchData.getUser().getUserId());
                        updCreditPackage.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                        // 修改抵押包信息
                        CreditUtils.getWmsInveCreditPackageDao().update(updCreditPackage);
                    }
                }
            }
        }
    }

    /**
     * @Title: release
     * @Description: 更换到期债权包时释放到期债权包
     * @param creditMatchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午6:27:37
     * @see com.zx.emanage.inve.util.credit.CreditMatchInterface#release(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    public void release(CreditMatchData creditMatchData)
    {
        List<WmsInveCreditPackage> creditPackageList = CreditUtils.getCreditPackageList(creditMatchData.getTransaId(),
                                                                                        creditMatchData.getProtocolId(),
                                                                                        "'" + ACL_STATE_ENABLE + "'");
        // 循环释放生效中的债权包
        for (WmsInveCreditPackage creditPackage : creditPackageList)
        {
            if (CreditUtils.isEndCreditPackage(creditPackage))
            {
                // 根据上单，合同，抵押包和匹配状态查找单据债权关联信息（只能查到一条记录）
                List<WmsInveTransaCrepkg> transaCrepkgList = CreditUtils.getTransaCrepkgList(creditMatchData.getTransaId(),
                                                                                             creditMatchData.getProtocolId(),
                                                                                             creditPackage.getWms_inve_credit_package_id(),
                                                                                             "'" + ACL_STATE_ENABLE
                                                                                                     + "'");
                // 如果集合为空跳过循环
                if (transaCrepkgList.isEmpty())
                {
                    continue;
                }
                // 获得单据债权关联信息
                WmsInveTransaCrepkg transaCrepkg = transaCrepkgList.get(0);

                WmsInveTransaCrepkg updTransaCrepkg = new WmsInveTransaCrepkg();
                updTransaCrepkg.setWms_inve_transa_crepkg_id(transaCrepkg.getWms_inve_transa_crepkg_id());
                // 匹配状态设置成已到期
                updTransaCrepkg.setAcl_state(ACL_STATE_END);
                updTransaCrepkg.setDisacl_date(new java.sql.Date(System.currentTimeMillis()));
                updTransaCrepkg.setDisacl_type("已到期");
                updTransaCrepkg.setLast_update_user_id(creditMatchData.getUser().getUserId());
                updTransaCrepkg.setLast_update_timestamp(new Timestamp(System.currentTimeMillis()));
                // 修改单据债权关联信息
                CreditUtils.getWmsInveTransaCrepkgDao().update(updTransaCrepkg);
            }
        }
    }

    /**
     * @Title: setProtocolEndDate
     * @Description: 更换到期债权包时，已传入协议到期时间不需要设置
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午6:27:37
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#setProtocolEndDate(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年2月13日 jinzhm 创建方法
    */
    @Override
    protected void setProtocolEndDate(CreditMatchData matchData)
    {
        // 单据的到期时间
        Date endDate = CreditUtils.getProtocolEndDate(matchData.getTransaId());
        // 当前时间
        Date nowDate = DateUtil.getDate10(new Date());

        // 如果产品是未到期或者是到期日期更换，债权匹配按单据到期时间进行匹配
        if (nowDate.compareTo(endDate) <= 0)
        {
            matchData.setProtocolEndDate(endDate);
        }
        // 如果是超期后进行更换
        else
        {
            // 如果当前时间小于等于单据到期时间+1个月，说明产品可能是处于公益收益期间，债权匹配使用协议到期时间需要设置成公益收益到期时间
            if (nowDate.compareTo(DateUtils.addMonths(endDate, 1)) <= 0)
            {
                matchData.setProtocolEndDate(DateUtils.addMonths(endDate, 1));
            }
            // 如果当前时间大于到期时间+1个月，说明产片正处于活期利率期间，债权匹配使用协议到期时间设置成为当前时间加一个月
            else
            {
                matchData.setProtocolEndDate(DateUtils.addMonths(nowDate, 1));
            }
        }
    }

    /**
     * @Title: setMustMatch
     * @Description: 更换到期债权包时必须匹配
     * @param matchData 债权匹配数据对象
     * @author: jinzhm
     * @time:2017年2月13日 下午6:27:37
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
     * @Description: 债权更换时获得要用于匹配债权的抵押包集合
     *  债权更换时，抵押包的开始还款日期-一个月要小于等于要更换抵押包的到期日期
     *  且新抵押包得到期日期要大于等于当前日期
     * @param matchData 债权匹配对象
     * @return 抵押包集合
     * @author: jinzhm
     * @time:2017年3月23日 下午1:23:11
     * @see com.zx.emanage.inve.util.credit.CreditMatchAbstract#getCreditList(com.zx.emanage.inve.util.credit.CreditMatchData)
     * history:
     * 1、2017年3月23日 jinzhm 创建方法
    */
    @Override
    protected List<CreditData> getCreditList(CreditMatchData matchData)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", matchData.getTransaId());
        paramMap.put("date1", matchData.getCreditEndDate());
        paramMap.put("date2", DateUtil.getDate10(new Date()));
        // 加几天范围在债权更换时是不需要的，直接设置成0
        paramMap.put("dayRange", 0);
        return CreditUtils.getWmsInveCreditPackageDao().queryAllCreditList(paramMap);
    }

    /**
     * @Title: getCreditList
     * @Description: 债权更换时获得要用于匹配债权的抵押包集合
     *  当在
     *  债权更换时，抵押包的开始还款日期-一个月要小于等于要更换抵押包的到期日期
     *  且新抵押包得到期日期要大于等于当前日期
     *  范围内没有匹配全债权
     *  需要在
     *  债权更换时，抵押包的开始还款日期-一个月要小于等于要更换抵押包的到期日期
     *  且新抵押包到期日期要大于等于要更换抵押包的到期日期
     * @param transaId
     * @param creditEndDate
     * @return 
     * @author: jinzhm
     * @time:2017年4月1日 下午2:06:45
     * history:
     * 1、2017年4月1日 jinzhm 创建方法
     */
    private List<CreditData> getCreditList(int transaId, Date creditEndDate)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);
        paramMap.put("date1", creditEndDate);
        paramMap.put("date2", DateUtil.getDateAddDays(creditEndDate, 1));
        // 加几天范围在债权更换时是不需要的，直接设置成0
        paramMap.put("dayRange", 0);
        return CreditUtils.getWmsInveCreditPackageDao().queryAllCreditList(paramMap);
    }

}
