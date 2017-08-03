package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveTransaCrepkg;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatching
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public final class CreditMatchData
{

    private String flowName;

    /**
     * 上单主键
     */
    private Integer transaId;

    /**
     * 合同主键
     */
    private Integer protocolId;

    /**
     * 产品id
     */
    private Integer categoryId;

    /**
     * 登录用户信息
     */
    private UserBean user;

    /**
     * 投资金额
     */
    private BigDecimal productAccount;

    /**
     * 实际支付日期
     */
    private Date actDateOfPayment;

    /**
     * 协议生效日期
     */
    private Date protocolStartDate;

    /**
     * 协议结束日期
     */
    private Date protocolEndDate;
    
    /**
     * 债权到期日期
     */
    private Date creditEndDate;

    /**
     * 产品信息
     */
    private WmsInvePruductCategory category;

    /**
     * 参数设定系数
     */
    private BigDecimal coeff;

    /**
     * 债权匹配扩大系数最大值
     */
    private BigDecimal maxCoeff;

    /**
     * 匹配状态
     */
    private String state;

    /**
     * 是否必须匹配
     */
    private boolean isMustMatch = false;

    /**
     * 所有可匹配的债权包集合
     */
    private List<CreditData> creditDataList = new ArrayList<CreditData>();

    /**
     * 已匹配的债权包集合
     */
    private List<CreditData> matchedCreditDataList = new ArrayList<CreditData>();

    /**
     * 当前还在生效中的债权包集合
     * 在替换到期债权包时需要此数据
     */
    private List<WmsInveTransaCrepkg> matchedTransaCrepkg = new ArrayList<WmsInveTransaCrepkg>();

    /**
     * 失效原因
     */
    private String disableReason;

    /**
     * 失效状态
     */
    private String disableState;

    /**
     * 上单地区编号，沈阳为0024
     */
    private String transaAreaCode;

    public CreditMatchData(Integer transaId, Integer protocolId, Integer categoryId, BigDecimal productAccount,
                           Date actDateOfPayment, Date protocolStartDate, UserBean user)
    {
        this.user = user;
        this.transaId = transaId;
        this.protocolId = protocolId;
        this.categoryId = categoryId;
        this.protocolStartDate = protocolStartDate;
        this.productAccount = productAccount;
        this.actDateOfPayment = actDateOfPayment;
        if (this.categoryId == null)
        {
            this.category = CreditUtils.getWmsInvePruductCategroyByTransaId(this.transaId);
        }
        else
        {
            this.category = CreditUtils.getWmsInvePruductCategoryDao().get(this.categoryId);
        }
        this.transaAreaCode = CreditUtils.getSalesmanAreaCodeByTransaId(this.transaId);
        this.maxCoeff = CreditUtils.getMaxCoeff();
        this.coeff = CreditUtils.getWmsInveCreditSplitConfigHeadDao().getEnableSplitConfigHead().getEla_coeff();
    }

    // public static Builder newBuilder()
    // {
    // return new Builder();
    // }
    //
    // public static final class Builder
    // {
    // private Integer transaId;
    //
    // private Integer protocolId;
    //
    // private BigDecimal productAccount;
    //
    // private Date actDateOfPayment;
    //
    // public Builder setTransaId(Integer transaId)
    // {
    // this.transaId = transaId;
    // return this;
    // }
    //
    // public Builder setProtocolId(Integer protocolId)
    // {
    // this.protocolId = protocolId;
    // return this;
    // }
    //
    // public Builder setProductAccount(BigDecimal productAccount)
    // {
    // this.productAccount = productAccount;
    // return this;
    // }
    //
    // public CreditMatchData build()
    // {
    // if (transaId == null)
    // {
    // throw new IllegalArgumentException("上单表主键不能为null！！！");
    // }
    // return new CreditMatchData(transaId, protocolId, productAccount,
    // actDateOfPayment);
    // }
    // }


    /**
     * @return the transaId
     */
    public Integer getTransaId()
    {
        return transaId;
    }

    /**
     * @return the transaAreaCode
     */
    public String getTransaAreaCode()
    {
        return transaAreaCode;
    }

    /**
     * @param transaAreaCode the transaAreaCode to set
     */
    public void setTransaAreaCode(String transaAreaCode)
    {
        this.transaAreaCode = transaAreaCode;
    }

    /**
     * @param transaId the transaId to set
     */
    public void setTransaId(Integer transaId)
    {
        this.transaId = transaId;
    }

    /**
     * @return the protocolId
     */
    public Integer getProtocolId()
    {
        return protocolId;
    }

    /**
     * @param protocolId the protocolId to set
     */
    public void setProtocolId(Integer protocolId)
    {
        this.protocolId = protocolId;
    }

    /**
     * @return the productAccount
     */
    public BigDecimal getProductAccount()
    {
        return productAccount;
    }

    /**
     * @param productAccount the productAccount to set
     */
    public void setProductAccount(BigDecimal productAccount)
    {
        this.productAccount = productAccount;
    }

    /**
     * @return the category
     */
    public WmsInvePruductCategory getCategory()
    {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(WmsInvePruductCategory category)
    {
        this.category = category;
    }

    /**
     * @return the creditDataList
     */
    public List<CreditData> getCreditDataList()
    {
        return creditDataList;
    }

    /**
     * @param creditDataList the creditDataList to set
     */
    public void setCreditDataList(List<CreditData> creditDataList)
    {
        this.creditDataList = creditDataList;
    }

    /**
     * @return the matchedCreditDataList
     */
    public List<CreditData> getMatchedCreditDataList()
    {
        return matchedCreditDataList;
    }

    /**
     * @param matchedCreditDataList the matchedCreditDataList to set
     */
    public void setMatchedCreditDataList(List<CreditData> matchedCreditDataList)
    {
        this.matchedCreditDataList = matchedCreditDataList;
    }

    /**
     * @return the actDateOfPayment
     */
    public Date getActDateOfPayment()
    {
        return actDateOfPayment;
    }

    /**
     * @param actDateOfPayment the actDateOfPayment to set
     */
    public void setActDateOfPayment(Date actDateOfPayment)
    {
        this.actDateOfPayment = actDateOfPayment;
    }

    /**
     * @return the protocolEndDate
     */
    public Date getProtocolEndDate()
    {
        return protocolEndDate;
    }

    /**
     * @param protocolEndDate the protocolEndDate to set
     */
    public void setProtocolEndDate(Date protocolEndDate)
    {
        this.protocolEndDate = protocolEndDate;
    }

    /**
     * @return the coeff
     */
    public BigDecimal getCoeff()
    {
        return coeff;
    }

    /**
     * @param coeff the coeff to set
     */
    public void setCoeff(BigDecimal coeff)
    {
        this.coeff = coeff;
    }

    /**
     * @return the isMustMatch
     */
    public boolean isMustMatch()
    {
        return isMustMatch;
    }

    /**
     * @param isMustMatch the isMustMatch to set
     */
    public void setMustMatch(boolean isMustMatch)
    {
        this.isMustMatch = isMustMatch;
    }

    /**
     * @return the protocolStartDate
     */
    public Date getProtocolStartDate()
    {
        return protocolStartDate;
    }

    /**
     * @param protocolStartDate the protocolStartDate to set
     */
    public void setProtocolStartDate(Date protocolStartDate)
    {
        this.protocolStartDate = protocolStartDate;
    }

    /**
     * @return the disableReason
     */
    public String getDisableReason()
    {
        return disableReason;
    }

    /**
     * @param disableReason the disableReason to set
     */
    public void setDisableReason(String disableReason)
    {
        this.disableReason = disableReason;
    }

    /**
     * @return the disableState
     */
    public String getDisableState()
    {
        return disableState;
    }

    /**
     * @param disableState the disableState to set
     */
    public void setDisableState(String disableState)
    {
        this.disableState = disableState;
    }

    /**
     * @return the user
     */
    public UserBean getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user)
    {
        this.user = user;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return the matchedTransaCrepkg
     */
    public List<WmsInveTransaCrepkg> getMatchedTransaCrepkg()
    {
        return matchedTransaCrepkg;
    }

    /**
     * @param matchedTransaCrepkg the matchedTransaCrepkg to set
     */
    public void setMatchedTransaCrepkg(List<WmsInveTransaCrepkg> matchedTransaCrepkg)
    {
        this.matchedTransaCrepkg = matchedTransaCrepkg;
    }

    /**
     * @return the maxCoeff
     */
    public BigDecimal getMaxCoeff()
    {
        return maxCoeff;
    }

    /**
     * @param maxCoeff the maxCoeff to set
     */
    public void setMaxCoeff(BigDecimal maxCoeff)
    {
        this.maxCoeff = maxCoeff;
    }

    /**
     * @return the categoryId
     */
    public Integer getCategoryId()
    {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    /**
     * @return the flowName
     */
    public String getFlowName()
    {
        return flowName;
    }

    /**
     * @param flowName the flowName to set
     */
    public void setFlowName(String flowName)
    {
        this.flowName = flowName;
    }

    /**
     * @return the creditEndDate
     */
    public Date getCreditEndDate()
    {
        return creditEndDate;
    }

    /**
     * @param creditEndDate the creditEndDate to set
     */
    public void setCreditEndDate(Date creditEndDate)
    {
        this.creditEndDate = creditEndDate;
    }

}
