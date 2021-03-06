package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zx.emanage.util.gen.entity.WmsInveClerkProtocolTransaCrepkg;
import com.zx.emanage.util.gen.entity.WmsInveCreditPackage;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.sframe.util.DateUtil;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditBusiness
 * 模块名称：债权业务处理类
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月14日
 * @version V1.0
 * history:
 * 1、2017年2月14日 jinzhm 创建文件
 */
public class CreditBusiness
{
    private static final CreditBusiness cb = new CreditBusiness();

    private CreditBusiness()
    {

    }

    public static CreditBusiness getInstance()
    {
        return cb;
    }

    /**
     * @Title: matchForExtendFlow
     * @Description: 为续期流程提供债权匹配方法
     * @param transaId 上单主键
     * @param protocolId 协议主键
     * @param categoryId 产品主键
     * @param productAccount 投资金额
     * @param protocolStartDate 合同生效时间
     * @param user 登录用户信息
     * @return 是否匹配成功标志
     * @author: jinzhm
     * @time:2017年2月14日 下午5:25:41
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean matchForExtendFlow(int transaId, int protocolId, int categoryId, BigDecimal productAccount,
                                      Date protocolStartDate, UserBean user)
    {
        // 如果产品是可拆分类型产品
        if (CreditUtils.isCategoryCanMatchCredit(categoryId))
        {
            // 创建债权匹配请求对象
            CreditMatchRequestData requestData = new CreditMatchRequestData();
            // 设置流程
            requestData.setFlowName(CreditMatch.FLOW_EXTEND);
            // 设置上单主键，合同主键，投资金额，合同生效日期，登录用户信息
            requestData.setTransaId(transaId);
            requestData.setProtocolId(protocolId);
            requestData.setProductAccount(productAccount);
            requestData.setProtocolStartDate(protocolStartDate);
            requestData.setUser(user);
            // 债权匹配
            boolean flag = CreditMatch.getInstance().match(requestData);
            // 记录匹配历史
            recordHistory(transaId, protocolId, user);
            return flag;
        }
        return true;
    }

    /**
     * @Title: matchForRedeemFlow
     * @Description: 为赎回流程提供债权匹配方法
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param protocolId 产品主键
     * @param productAccount 投资金额
     * @param protocolStartDate 合同生效日期
     * @param user 登录用户信息
     * @return 匹配成功标志
     * @author: jinzhm
     * @time:2017年2月14日 下午5:23:56
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean matchForRedeemFlow(int transaId, int protocolId, int categoryId, BigDecimal productAccount,
                                      Date redeemDate, UserBean user)
    {
        // 如果产品是可拆分类型产品
        if (CreditUtils.isCategoryCanMatchCredit(categoryId))
        {
            // 创建债权匹配请求对象
            CreditMatchRequestData requestData = new CreditMatchRequestData();
            // 设置流程
            requestData.setFlowName(CreditMatch.FLOW_REDEEM);
            // 设置上单主键，合同主键，投资金额，合同生效日期，登录用户信息
            requestData.setTransaId(transaId);
            requestData.setProtocolId(protocolId);
            requestData.setProductAccount(productAccount);
            requestData.setProtocolStartDate(redeemDate);
            requestData.setUser(user);
            // 债权匹配
            boolean flag = CreditMatch.getInstance().match(requestData);
            // 记录匹配历史
            recordHistory(transaId, protocolId, user);
            return flag;
        }
        return true;
    }


    /**
     * @Title: deleteCreditMatchHistory
     * @Description: 根据上单主键和合同主键逻辑删除债权匹配历史信息
     * @param transaId 上单主键id
     * @param protocolId 合同主键id
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午5:35:13
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void deleteCreditMatchHistory(int transaId, int protocolId, UserBean user)
    {
        CreditUtils.deleteCreditMatchedHistory(transaId, protocolId, user);
    }

    /**
     * @Title: changeMatchedCredit
     * @Description: 更换到期债权包
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param user 登录用户信息
     * @return
     *  1表示匹配成功
     *  2表示产品不是可拆分类型
     *  3表示没有要到期的债权包
     *  -1表示债权匹配失败
     * @author: jinzhm
     * @time:2017年2月14日 下午4:53:30
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public int changeMatchedCredit(int transaId, int protocolId, int categoryId, UserBean user)
    {
        // 如果产品是可拆分类型产品
        if (CreditUtils.isCategoryCanMatchCredit(categoryId))
        {
            // 查询单据匹配的生效中的抵押包信息集合
            List<WmsInveCreditPackage> creditPackageList = CreditUtils.getCreditPackageList(transaId,
                                                                                            protocolId,
                                                                                            "'"
                                                                                                    + CreditMatchInterface.ACL_STATE_ENABLE
                                                                                                    + "'");
            if (CreditUtils.hasEndCreditPackage(creditPackageList))
            {
                // 创建债权匹配请求对象
                CreditMatchRequestData requestData = new CreditMatchRequestData();
                // 设置流程
                requestData.setFlowName(CreditMatch.FLOW_CHANGE_CREDIT_PACKAGE);
                // 设置上单主键，设置协议主键，设置登录用户信息
                requestData.setTransaId(transaId);
                requestData.setProtocolId(protocolId);
                requestData.setUser(user);
                requestData.setProtocolStartDate(DateUtil.getDate10(new Date()));

                boolean matched = CreditMatch.getInstance().match(requestData);

                // 匹配债权
                if (!matched)
                {
                    throw new RuntimeException("债权更换时，债权不足！");
                    // return -1;
                }
                else
                {
                    recordHistory(transaId, protocolId, user);
                    return 1;
                }
            }
            else
            {
                return 3;
            }
        }
        else
        {
            return 2;
        }
    }

    /**
     * @Title: matchHold
     * @Description: 匹配债权并占用债权
     * @param transaId 上单主键
     * @param categoryId 产品主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付时间
     * @param user 登录用户信息
     * @return 是否匹配成功（产品不是可拆分类型时直接返回true）
     * @author: jinzhm
     * @time:2017年2月14日 下午4:55:11
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean matchHold(int transaId, int categoryId, BigDecimal productAccount, Date actDateOfPayment,
                             UserBean user)
    {
        // 如果是线下合同
        if (CreditUtils.isOfflineTransa(transaId))
        {
            // 创建债权匹配请求对象
            CreditMatchRequestData requestData = new CreditMatchRequestData();
            // 设置流程
            requestData.setFlowName(CreditMatch.FLOW_TRANSA);
            // 设置上单主键
            requestData.setTransaId(transaId);
            // 设置登录人信息
            requestData.setUser(user);
            // 释放
            CreditMatch.getInstance().release(requestData);
            return true;
        }
        // 如果产品是可拆分类型产品
        if (CreditUtils.isCategoryCanMatchCredit(categoryId))
        {
            CreditMatchRequestData requestData = new CreditMatchRequestData();
            // 设置流程
            requestData.setFlowName(CreditMatch.FLOW_TRANSA);
            // 设置主键，投资金额，实际支付日期，登录用户信息
            requestData.setTransaId(transaId);
            requestData.setProductAccount(productAccount);
            requestData.setActDateOfPayment(actDateOfPayment);
            requestData.setUser(user);

            if (CreditUtils.getCreditPackageList(transaId, null, "'" + CreditMatchInterface.ACL_STATE_HOLD + "'")
                           .isEmpty())
            {
                // 匹配时需要先确认当前是否需要匹配
                // 1表示匹配正确，2表示需要释放债权，3表示需要匹配，4表示释放再匹配
                if (confirmMatch(requestData, categoryId) == 3)
                {
                    return CreditMatch.getInstance().match(requestData);
                }
            }
            else
            {
                return true;
            }
        }
        return true;
    }

    /**
     * @Title: releaseMatchedCreditForExtendFlow
     * @Description: 为续期流程释放债权
     * @param transaId 上单主键id
     * @param protocolId 合同主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午4:50:26
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void releaseMatchedCreditForExtendFlow(int transaId, int protocolId, UserBean user)
    {
        // 创建债权匹配请求数据
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程
        requestData.setFlowName(CreditMatch.FLOW_EXTEND);
        // 设置上单主键，协议主键，登录用户信息
        requestData.setTransaId(transaId);
        requestData.setProtocolId(protocolId);
        requestData.setUser(user);
        // 释放债权
        CreditMatch.getInstance().release(requestData);
    }

    /**
     * @Title: disableMatchedCreditForExtendFlow
     * @Description: 为续期流程失效债权
     * @param transaId
     * @param protocolId
     * @param user 
     * @author: jinzhm
     * @time:2017年2月21日 下午3:05:40
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public void disableMatchedCreditForExtendFlow(int transaId, int protocolId, UserBean user)
    {
        // 创建债权匹配请求数据
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程
        requestData.setFlowName(CreditMatch.FLOW_EXTEND);
        // 设置上单主键，协议主键，登录用户信息
        requestData.setTransaId(transaId);
        requestData.setProtocolId(protocolId);
        requestData.setUser(user);
        // 释放债权
        CreditMatch.getInstance().disableMatchedCredit(requestData);
    }

    /**
     * @Title: releaseMatchedCreditForRedeemFlow
     * @Description: 为赎回流程释放债权
     * @param transaId 上单主键id
     * @param protocolId 合同主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午4:50:29
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void releaseMatchedCreditForRedeemFlow(int transaId, Integer protocolId, UserBean user)
    {
        // 创建债权匹配请求数据
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程
        requestData.setFlowName(CreditMatch.FLOW_REDEEM);
        // 设置上单主键，协议主键，登录用户信息
        requestData.setTransaId(transaId);
        requestData.setProtocolId(protocolId);
        requestData.setUser(user);
        // 释放债权
        CreditMatch.getInstance().release(requestData);
    }

    /**
     * @Title: disableMatchedCreditRedeemFlow
     * @Description: 为赎回流程失效债权
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param user 登录用户
     * @author: jinzhm
     * @time:2017年2月21日 下午3:04:30
     * history:
     * 1、2017年2月21日 jinzhm 创建方法
     */
    public void disableMatchedCreditForRedeemFlow(int transaId, int protocolId, UserBean user)
    {
        // 创建债权匹配请求数据
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程
        requestData.setFlowName(CreditMatch.FLOW_REDEEM);
        // 设置上单主键，协议主键，登录用户信息
        requestData.setTransaId(transaId);
        requestData.setProtocolId(protocolId);
        requestData.setUser(user);
        // 释放债权
        CreditMatch.getInstance().disableMatchedCredit(requestData);
    }

    /**
     * @Title: releaseMatchedCreditForTransaFlow
     * @Description: 释放已经匹配的债权信息
     * @param transaId 上单主键
     * @param protocolId 合同主键（可能会没有）
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午3:57:36
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void releaseMatchedCreditForTransaFlow(int transaId, Integer protocolId, UserBean user)
    {
        // 创建债权匹配请求对象
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程
        requestData.setFlowName(CreditMatch.FLOW_TRANSA);
        // 设置上单主键
        requestData.setTransaId(transaId);
        // 设置合同主键
        requestData.setProtocolId(protocolId);
        // 设置登录人信息
        requestData.setUser(user);
        // 释放
        CreditMatch.getInstance().release(requestData);
    }

    /**
     * @Title: effectMatchedCredit
     * @Description: 生效正在占用的债权
     * @param transaId 上单主键
     * @author: jinzhm
     * @time:2017年2月14日 下午3:46:20
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void effectMatchedCredit(int transaId, UserBean user)
    {
        // 如果是线下合同
        if (CreditUtils.isOfflineTransa(transaId))
        {
            // 创建债权匹配请求对象
            CreditMatchRequestData requestData = new CreditMatchRequestData();
            // 设置流程名称
            requestData.setFlowName(CreditMatch.FLOW_TRANSA);
            // 设置上单主键
            requestData.setTransaId(transaId);
            // 设置登录人信息
            requestData.setUser(user);
            // 释放债权
            CreditMatch.getInstance().release(requestData);
            return;
        }

        // 如果是不可拆分产品直接返回
        if (!CreditUtils.isCategoryCanMatchCredit(CreditUtils.getWmsInvePruductCategroyByTransaId(transaId)))
        {
            return;
        }

        // 封装生效债权所需数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("transaId", transaId);

        // 生效债权
        CreditUtils.getWmsInveTransaCrepkgDao().updateAllHoldTransaCrepkgByTransaId(paramMap);

    }

    /**
     * @Title: changeCategoryOrPaymentAccount
     * @Description: 金额支付修改金额或修改产品后处理债权方法
     * @param origCategoryId 原始产品id
     * @param newCategoryId 新产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否匹配上债权（true表示匹配上）
     *      当不需要匹配时也返回true
     * @author: jinzhm
     * @time:2017年2月14日 下午3:16:14
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean changeCategoryOrPaymentAccount(int origCategoryId, int newCategoryId, int transaId,
                                                  BigDecimal productAccount, java.util.Date actDateOfPayment,
                                                  UserBean user)
    {
        // 创建债权匹配请求对象
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程名称
        requestData.setFlowName(CreditMatch.FLOW_TRANSA);
        // 设置上单主键
        requestData.setTransaId(transaId);
        // 设置理财投资金额
        requestData.setProductAccount(productAccount);
        // 设置实际支付时间
        requestData.setActDateOfPayment(actDateOfPayment);
        // 新产品id
        requestData.setCategoryId(newCategoryId);
        // 设置登录人信息
        requestData.setUser(user);

        // 如果是线下合同
        if (CreditUtils.isOfflineTransa(transaId))
        {
            // 释放债权
            CreditMatch.getInstance().release(requestData);
            return true;
        }
        // 判断实际支付日期是否小于当前日期
        // if (actDateOfPayment.compareTo(DateUtil.getDate10(new Date())) < 0)
        // {
        // // 释放债权
        // CreditMatch.getInstance().release(requestData);
        // return true;
        // }

        int result = isNeedHandleMatchCredit(origCategoryId, newCategoryId);
        // 返回值等于0表示可能修改的是理财金额，先释放再匹配
        if (result == 0)
        {
            // 释放债权
            CreditMatch.getInstance().release(requestData);
            // 匹配占用债权
            return CreditMatch.getInstance().match(requestData);
        }
        // 返回值等于1表示需要释放债权
        else if (result == 1)
        {
            // 释放债权
            CreditMatch.getInstance().release(requestData);
        }
        // 返回值等于2表示需要匹配占用债权
        else if (result == 2)
        {
            // 释放债权
            CreditMatch.getInstance().release(requestData);
            // 匹配占用债权
            return CreditMatch.getInstance().match(requestData);
        }
        return true;
    }

    /**
     * @Title: confirmCreditMatched
     * @Description: 确认单据债权匹配占用是否正确
     * @param categoryId 产品id
     * @param transaId 上单主键
     * @param productAccount 投资金额
     * @param actDateOfPayment 实际支付日期
     * @param user 登录用户信息
     * @return 是否债权匹配成功（不需要匹配债权时也返回true）
     * @author: jinzhm
     * @time:2017年2月14日 下午3:18:32
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public boolean confirmCreditMatched(int categoryId, int transaId, BigDecimal productAccount,
                                        java.util.Date actDateOfPayment, UserBean user)
    {
        // 创建债权匹配请求对象
        CreditMatchRequestData requestData = new CreditMatchRequestData();
        // 设置流程名称
        requestData.setFlowName(CreditMatch.FLOW_TRANSA);
        // 设置上单主键
        requestData.setTransaId(transaId);
        // 设置理财投资金额
        requestData.setProductAccount(productAccount);
        // 设置实际支付时间
        requestData.setActDateOfPayment(actDateOfPayment);
        // 设置登录人信息
        requestData.setUser(user);

        // 如果是线下合同
        if (CreditUtils.isOfflineTransa(transaId))
        {
            // 释放债权
            CreditMatch.getInstance().release(requestData);
            return true;
        }

        // 确认债权匹配是否正确，如果正确直接返回
        // 1表示匹配正确，2表示需要释放债权，3表示需要匹配，4表示释放再匹配
        switch (confirmMatch(requestData, categoryId))
        {
            case 1:
                return true;
            case 2:
                // 释放债权
                CreditMatch.getInstance().release(requestData);
                return true;
            case 3:
                // 匹配债权
                return CreditMatch.getInstance().match(requestData);
            case 4:
                // 释放再匹配
                CreditMatch.getInstance().release(requestData);
                return CreditMatch.getInstance().match(requestData);
            default:
                return true;
        }
    }

    /**
     * @Title: recordHistoryForTransa
     * @Description: 记录债权匹配历史数据
     * @param transaId 上单主键
     * @param protocolId 合同主键
     * @param user 登录用户信息
     * @author: jinzhm
     * @time:2017年2月14日 下午5:12:59
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    public void recordHistory(int transaId, int protocolId, UserBean user)
    {
        // 查询生效中的单据债权关联信息
        List<WmsInveTransaCrepkg> transaCrepkgList = CreditUtils.getTransaCrepkgList(transaId,
                                                                                     protocolId,
                                                                                     null,
                                                                                     "'"
                                                                                             + CreditMatchInterface.ACL_STATE_ENABLE
                                                                                             + "'");
        // 如果没有生效的单据债权关联信息
        if (transaCrepkgList.isEmpty())
        {
            return;
        }
        // 拼接使用的抵押包信息id
        StringBuilder builder = new StringBuilder();
        for (WmsInveTransaCrepkg transaCrepkg : transaCrepkgList)
        {
            builder.append(transaCrepkg.getWms_inve_transa_crepkg_id()).append(",");
        }
        // 封装债权匹配历史数据信息后保存
        WmsInveClerkProtocolTransaCrepkg clerkProtocolTransaCrepkg = new WmsInveClerkProtocolTransaCrepkg();
        clerkProtocolTransaCrepkg.setWms_inve_transa_id(transaId);
        clerkProtocolTransaCrepkg.setWms_inve_clerk_protocol_id(protocolId);
        clerkProtocolTransaCrepkg.setWms_inve_transa_crepkg_ids(builder.toString().substring(0,
                                                                                             builder.toString()
                                                                                                    .length() - 1));
        clerkProtocolTransaCrepkg.setCreate_user_id(user.getUserId());
        clerkProtocolTransaCrepkg.setCreate_timestamp(new Timestamp(System.currentTimeMillis()));
        clerkProtocolTransaCrepkg.setEnable_flag("1");
        CreditUtils.getWmsInveClerkProtocolTransaCrepkgDao().save(clerkProtocolTransaCrepkg);
    }

    /**
     * @Title: isNeedHandleMatchCredit
     * @Description: 判断产品变换是否需要处理债权信息
     * @param origCategoryId 原产品id
     * @param newCategoryId 新产品id
     * @return 整数型数值
     *  0：释放再匹配
     *  1：释放
     *  2：直接匹配
     *  -1： 不处理
     * @author: jinzhm
     * @time:2017年2月14日 下午2:28:10
     * history:
     * 1、2017年2月14日 jinzhm 创建方法
     */
    private int isNeedHandleMatchCredit(int origCategoryId, int newCategoryId)
    {
        // 如果传入两个值都是-1就不动
        if (origCategoryId == -1 && origCategoryId == -1)
        {
            return -1;
        }
        // 如果原产品id为-1
        if (origCategoryId == -1)
        {
            // 新产品id的产品为可拆分类型产品，需要匹配占用债权
            WmsInvePruductCategory category = CreditUtils.getWmsInvePruductCategoryDao().get(newCategoryId);
            if (CreditUtils.isCategoryCanMatchCredit(category))
            {
                return 2;
            }
            // 新产品id不是可拆分类型产品，不需要处理
            else
            {
                return -1;
            }
        }
        // 如果新产品id为-1
        if (newCategoryId == -1)
        {
            // 原产品id的产品为可拆分类型产品，需要释放债权
            WmsInvePruductCategory category = CreditUtils.getWmsInvePruductCategoryDao().get(origCategoryId);
            if (CreditUtils.isCategoryCanMatchCredit(category))
            {
                return 1;
            }
            // 原产品id不是可拆分类型产品，不需要处理
            else
            {
                return -1;
            }
        }

        // 查询原产品信息和新产品信息
        WmsInvePruductCategory origCategory = CreditUtils.getWmsInvePruductCategoryDao().get(origCategoryId);
        WmsInvePruductCategory newCategory = CreditUtils.getWmsInvePruductCategoryDao().get(newCategoryId);

        // 如果两个产品id相同
        if (origCategoryId == newCategoryId)
        {
            // 两个产品都是可拆分产品，重新匹配
            if (CreditUtils.isCategoryCanMatchCredit(origCategory) && CreditUtils.isCategoryCanMatchCredit(newCategory))
            {
                return 0;
            }
            // 两个产品都不是可拆分，不处理
            else
            {
                return -1;
            }
        }

        // 不可拆分到可拆分，直接匹配
        if (!CreditUtils.isCategoryCanMatchCredit(origCategory) && CreditUtils.isCategoryCanMatchCredit(newCategory))
        {
            return 2;
        }
        // 可拆分到不可拆分，直接释放
        else if (CreditUtils.isCategoryCanMatchCredit(origCategory)
                 && !CreditUtils.isCategoryCanMatchCredit(newCategory))
        {
            return 1;
        }
        // 可拆分到可拆分，直接重新匹配
        else if (CreditUtils.isCategoryCanMatchCredit(origCategory)
                 && CreditUtils.isCategoryCanMatchCredit(newCategory))
        {
            return 0;
        }
        // 不可拆分到不可拆分，不处理
        else
        {
            return -1;
        }

    }

    /**
     * @Title: confirmMatch
     * @Description: 确认债权匹配是否正确
     * @param requestData 债权匹配请求对象
     * @param categoryId 产品id
     * @return 1表示匹配正确，2表示需要释放债权，3表示需要匹配，4表示释放再匹配
     * @author: jinzhm
     * @time:2017年2月11日 下午12:53:56
     * history:
     * 1、2017年2月11日 jinzhm 创建方法
     */
    private int confirmMatch(CreditMatchRequestData requestData, int categoryId)
    {
        // 查询占用中的单据债权信息
        List<WmsInveTransaCrepkg> transaCrepkgList = CreditUtils.getTransaCrepkgList(requestData.getTransaId(),
                                                                                     requestData.getProtocolId(),
                                                                                     null,
                                                                                     "'"
                                                                                             + CreditMatchInterface.ACL_STATE_HOLD
                                                                                             + "'");
        // 如果实际支付日期小于当前日期说明是线下合同，不需要匹配债权
        // if
        // (requestData.getActDateOfPayment().compareTo(DateUtil.getDate10(new
        // Date())) < 0
        // && transaCrepkgList.isEmpty())
        // {
        // return 1;
        // }
        // 实际支付日期小于当前日期，但有已经匹配到的债权是错误的
        // else if
        // (requestData.getActDateOfPayment().compareTo(DateUtil.getDate10(new
        // Date())) < 0
        // && !transaCrepkgList.isEmpty())
        // {
        // return 2;
        // }

        // 获得产品信息
        WmsInvePruductCategory category = CreditUtils.getWmsInvePruductCategoryDao().get(categoryId);
        // 债权匹配有占用数据，但购买产品类型不是可拆分类产品的话表示债权匹配占用错误
        if (!transaCrepkgList.isEmpty() && !CreditUtils.isCategoryCanMatchCredit(category))
        {
            return 2;
        }
        // 产品不需要匹配债权且确实也没有匹配到的债权是正确的
        else if (transaCrepkgList.isEmpty() && !CreditUtils.isCategoryCanMatchCredit(category))
        {
            return 1;
        }

        // 产品需要匹配债权，但是没有匹配到的债权也是错误的
        // if (transaCrepkgList.isEmpty() &&
        // CreditUtils.isCategoryCanMatchCredit(category)
        // && requestData.getActDateOfPayment().compareTo(DateUtil.getDate10(new
        // Date())) >= 0)
        if (transaCrepkgList.isEmpty() && CreditUtils.isCategoryCanMatchCredit(category))
        {
            return 3;
        }

        // 总匹配金额
        BigDecimal totalMatchProductAccount = BigDecimal.ZERO;

        // 循环所有债权集合，查看产品及金额是否正确
        for (WmsInveTransaCrepkg transaCrepkg : transaCrepkgList)
        {
            // 债权匹配的产品和单据购买产品不相同说明匹配错误
            if (category.getWms_inve_pruduct_category_id() != transaCrepkg.getWms_inve_pruduct_category_id())
            {
                return 4;
            }
            // 计算总匹配金额
            totalMatchProductAccount = totalMatchProductAccount.add(transaCrepkg.getAcl_mon());
        }
        // 总匹配金额和投资金额不相同说明占用错误
        if (requestData.getProductAccount().compareTo(totalMatchProductAccount) != 0)
        {
            return 4;
        }
        return 1;
    }

}
