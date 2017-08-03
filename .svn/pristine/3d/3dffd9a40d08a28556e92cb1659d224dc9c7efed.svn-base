package com.zx.emanage.inve.util.credit;

import java.math.BigDecimal;
import java.util.Date;

import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CreditMatchRequestData
 * 模块名称：
 * @Description: 内容摘要：
 * @author jinzhm
 * @date 2017年2月10日
 * @version V1.0
 * history:
 * 1、2017年2月10日 jinzhm 创建文件
 */
public class CreditMatchRequestData
{

    /**
     * 流程名称
     * CreditMatch.FLOW_TRANSA
     * CreditMatch.FLOW_EXTEND
     * CreditMatch.FLOW_REDEEM
     */
    private String flowName;

    /**
     * 上单单据主键
     */
    private Integer transaId;

    /**
     * 实际支付日期，签单流程时必须传入
     * 签单流程用此字段计算协议到期时间
     */
    private Date actDateOfPayment;

    /**
     * 合同主键
     */
    private Integer protocolId;
    
    /**
     * 产品主键
     */
    private Integer categoryId;

    /**
     * 合同生效日期
     */
    private Date protocolStartDate;

    /**
     * 要匹配的投资金额（赎回时应该是减去赎回金额的剩余投资金额）
     */
    private BigDecimal productAccount;

    /**
     * 登录用户信息
     */
    private UserBean user;

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
     * @return the transaId
     */
    public Integer getTransaId()
    {
        return transaId;
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

}
