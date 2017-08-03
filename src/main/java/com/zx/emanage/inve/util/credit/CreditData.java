package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditData
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月11日
 * @version V1.0
 * history:
 * 1、2017年2月11日 jinzhm 创建文件
 */
public class CreditData
{

    /**
     * 债权包主键
     */
    private Integer id;

    /**
     * 抵押包类型
     */
    private String creType;

    /**
     * 原始债权金额
     */
    private BigDecimal origProductAccount;

    /**
     * 本组已匹配债权金额
     */
    private BigDecimal groupUseProductAccount;

    /**
     * 总共已匹配债权金额
     */
    private BigDecimal totalUseProductAccount;

    /**
     * 最大可匹配债权金额
     */
    private BigDecimal maxProductAccount;

    /**
     * 本组剩余可匹配债权金额
     */
    private BigDecimal groupKeepProductAccount;

    /**
     * 本组已用抵押包次数
     */
    private Integer groupSplitCount;

    /**
     * 抵押结束日期
     */
    private Date crepgEndDate;
    
    /**
     * 匹配时间
     */
    private Timestamp aclMatchDate;
    
    /**
     * 地区主键
     */
    private Integer localId;

    /**
     * 地区编号：沈阳为0024
     */
    private String localNum;

    /**
     * 是否匹配到，默认都是false，当匹配到时设置成true
     */
    private boolean isMatched = false;

    /**
     * 匹配的金额
     */
    private BigDecimal matchedProductAccount = BigDecimal.ZERO;

    /**
     * 单据关联债权主键
     */
    private Integer transaCrepkgId;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the origProductAccount
     */
    public BigDecimal getOrigProductAccount()
    {
        return origProductAccount;
    }

    /**
     * @param origProductAccount the origProductAccount to set
     */
    public void setOrigProductAccount(BigDecimal origProductAccount)
    {
        this.origProductAccount = origProductAccount;
    }

    /**
     * @return the groupUseProductAccount
     */
    public BigDecimal getGroupUseProductAccount()
    {
        return groupUseProductAccount;
    }

    /**
     * @param groupUseProductAccount the groupUseProductAccount to set
     */
    public void setGroupUseProductAccount(BigDecimal groupUseProductAccount)
    {
        this.groupUseProductAccount = groupUseProductAccount;
    }

    /**
     * @return the totalUseProductAccount
     */
    public BigDecimal getTotalUseProductAccount()
    {
        return totalUseProductAccount;
    }

    /**
     * @param totalUseProductAccount the totalUseProductAccount to set
     */
    public void setTotalUseProductAccount(BigDecimal totalUseProductAccount)
    {
        this.totalUseProductAccount = totalUseProductAccount;
    }

    /**
     * @return the maxProductAccount
     */
    public BigDecimal getMaxProductAccount()
    {
        return maxProductAccount;
    }

    /**
     * @param maxProductAccount the maxProductAccount to set
     */
    public void setMaxProductAccount(BigDecimal maxProductAccount)
    {
        this.maxProductAccount = maxProductAccount;
    }

    /**
     * @return the groupKeepProductAccount
     */
    public BigDecimal getGroupKeepProductAccount()
    {
        return groupKeepProductAccount;
    }

    /**
     * @param groupKeepProductAccount the groupKeepProductAccount to set
     */
    public void setGroupKeepProductAccount(BigDecimal groupKeepProductAccount)
    {
        this.groupKeepProductAccount = groupKeepProductAccount;
    }

    /**
     * @return the groupSliptCount
     */
    public Integer getGroupSplitCount()
    {
        return groupSplitCount;
    }

    /**
     * @param groupSliptCount the groupSliptCount to set
     */
    public void setGroupSplitCount(Integer groupSplitCount)
    {
        this.groupSplitCount = groupSplitCount;
    }

    /**
     * @return the crepgEndDate
     */
    public Date getCrepgEndDate()
    {
        return crepgEndDate;
    }

    /**
     * @param crepgEndDate the crepgEndDate to set
     */
    public void setCrepgEndDate(Date crepgEndDate)
    {
        this.crepgEndDate = crepgEndDate;
    }

    /**
     * @return the aclMatchDate
     */
    public Timestamp getAclMatchDate()
    {
        return aclMatchDate;
    }

    /**
     * @param aclMatchDate the aclMatchDate to set
     */
    public void setAclMatchDate(Timestamp aclMatchDate)
    {
        this.aclMatchDate = aclMatchDate;
    }

    /**
     * @return the isMatched
     */
    public boolean isMatched()
    {
        return isMatched;
    }

    /**
     * @param isMatched the isMatched to set
     */
    public void setMatched(boolean isMatched)
    {
        this.isMatched = isMatched;
    }

    /**
     * @return the matchedProductAccount
     */
    public BigDecimal getMatchedProductAccount()
    {
        return matchedProductAccount;
    }

    /**
     * @param matchedProductAccount the matchedProductAccount to set
     */
    public void setMatchedProductAccount(BigDecimal matchedProductAccount)
    {
        this.matchedProductAccount = matchedProductAccount;
    }

    /**
     * @return the creType
     */
    public String getCreType()
    {
        return creType;
    }

    /**
     * @param creType the creType to set
     */
    public void setCreType(String creType)
    {
        this.creType = creType;
    }

    /**
     * @return the transaCrepkgId
     */
    public Integer getTransaCrepkgId()
    {
        return transaCrepkgId;
    }

    /**
     * @param transaCrepkgId the transaCrepkgId to set
     */
    public void setTransaCrepkgId(Integer transaCrepkgId)
    {
        this.transaCrepkgId = transaCrepkgId;
    }

    /**
     * @return the localNum
     */
    public String getLocalNum()
    {
        return localNum;
    }

    /**
     * @param localNum the localNum to set
     */
    public void setLocalNum(String localNum)
    {
        this.localNum = localNum;
    }

    /**
     * @return the localId
     */
    public Integer getLocalId()
    {
        return localId;
    }

    /**
     * @param localId the localId to set
     */
    public void setLocalId(Integer localId)
    {
        this.localId = localId;
    }

}
