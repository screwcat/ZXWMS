package com.zx.emanage.inve.util.redeem.time;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.zx.emanage.inve.util.CountIncomeGetBonusReturnRateInterface;
import com.zx.emanage.util.gen.entity.WmsInvePruductCategory;
import com.zx.emanage.util.gen.entity.WmsInveRedeem;
import com.zx.emanage.util.gen.entity.WmsInveRedeemDetail;
import com.zx.emanage.util.gen.entity.WmsInveTransa;
import com.zx.emanage.util.gen.entity.WmsInveTransaProd;
import com.zx.emanage.util.gen.entity.WmsInveTransaProtocol;
import com.zx.sframe.util.vo.UserBean;

/**
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: CountIncomeRedeemTimeData
 * 模块名称：赎回时机数据对象
 * @Description: 内容摘要：根据赎回的时机做客户收益处理时需要用到的数据属性定义
 * @author jinzhm
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 jinzhm 创建文件
 */
public class CountIncomeRedeemTimeData
{
    /**
     * 赎回信息
     */
    private WmsInveRedeem redeem;

    /**
     * 赎回明细信息
     */
    private WmsInveRedeemDetail redeemDetail;

    /**
     * 上单信息
     */
    private WmsInveTransa transa;

    /**
     * 上单产品信息
     */
    private WmsInveTransaProd prod;

    /**
     * 协议信息
     */
    private WmsInveTransaProtocol protocol;

    /**
     * 产品信息对象
     */
    private WmsInvePruductCategory category;

    /**
     * 原始上单时间
     */
    private Date oldDateOfPayment;

    /**
     * 产品利率
     */
    private BigDecimal returnRate = BigDecimal.ZERO;

    /**
     * 公益产品利率
     */
    private BigDecimal publicReturnRate = BigDecimal.ZERO;
    
    /**
     * 计算使用的投资金额
     */
    private BigDecimal productAccount = BigDecimal.ZERO;

    /**
     * 续期时间
     */
    private Date extendDate;

    /**
     * 其他不通用数据map对象
     */
    private Map<String, Object> dataMap;

    /**
     * 登录用户信息
     */
    private UserBean user;
    
    /**
     * 获得奖励利率接口对象
     */
    private CountIncomeGetBonusReturnRateInterface countIncomeGetBonusReturnRateInterface;

    /**
     * @return the oldDateOfPayment
     */
    public Date getOldDateOfPayment()
    {
        return oldDateOfPayment;
    }

    /**
     * @param oldDateOfPayment the oldDateOfPayment to set
     */
    public void setOldDateOfPayment(Date oldDateOfPayment)
    {
        this.oldDateOfPayment = oldDateOfPayment;
    }

    /**
     * @return the redeem
     */
    public WmsInveRedeem getRedeem()
    {
        return redeem;
    }

    /**
     * @param redeem the redeem to set
     */
    public void setRedeem(WmsInveRedeem redeem)
    {
        this.redeem = redeem;
    }

    /**
     * @return the redeemDetail
     */
    public WmsInveRedeemDetail getRedeemDetail()
    {
        return redeemDetail;
    }

    /**
     * @param redeemDetail the redeemDetail to set
     */
    public void setRedeemDetail(WmsInveRedeemDetail redeemDetail)
    {
        this.redeemDetail = redeemDetail;
    }

    /**
     * @return the transa
     */
    public WmsInveTransa getTransa()
    {
        return transa;
    }

    /**
     * @param transa the transa to set
     */
    public void setTransa(WmsInveTransa transa)
    {
        this.transa = transa;
    }

    /**
     * @return the prod
     */
    public WmsInveTransaProd getProd()
    {
        return prod;
    }

    /**
     * @param prod the prod to set
     */
    public void setProd(WmsInveTransaProd prod)
    {
        this.prod = prod;
    }

    /**
     * @return the protocol
     */
    public WmsInveTransaProtocol getProtocol()
    {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(WmsInveTransaProtocol protocol)
    {
        this.protocol = protocol;
    }

    /**
     * @return the dataMap
     */
    public Map<String, Object> getDataMap()
    {
        return dataMap;
    }

    /**
     * @param dataMap the dataMap to set
     */
    public void setDataMap(Map<String, Object> dataMap)
    {
        this.dataMap = dataMap;
    }

    /**
     * @return the returnRate
     */
    public BigDecimal getReturnRate()
    {
        return returnRate;
    }

    /**
     * @param returnRate the returnRate to set
     */
    public void setReturnRate(BigDecimal returnRate)
    {
        this.returnRate = returnRate;
    }

    /**
     * @return the publicReturnRate
     */
    public BigDecimal getPublicReturnRate()
    {
        return publicReturnRate;
    }

    /**
     * @param publicReturnRate the publicReturnRate to set
     */
    public void setPublicReturnRate(BigDecimal publicReturnRate)
    {
        this.publicReturnRate = publicReturnRate;
    }

    /**
     * @return the extendDate
     */
    public Date getExtendDate()
    {
        return extendDate;
    }

    /**
     * @param extendDate the extendDate to set
     */
    public void setExtendDate(Date extendDate)
    {
        this.extendDate = extendDate;
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
     * @return the countIncomeGetBonusReturnRateInterface
     */
    public CountIncomeGetBonusReturnRateInterface getCountIncomeGetBonusReturnRateInterface()
    {
        return countIncomeGetBonusReturnRateInterface;
    }

    /**
     * @param countIncomeGetBonusReturnRateInterface the countIncomeGetBonusReturnRateInterface to set
     */
    public void setCountIncomeGetBonusReturnRateInterface(CountIncomeGetBonusReturnRateInterface countIncomeGetBonusReturnRateInterface)
    {
        this.countIncomeGetBonusReturnRateInterface = countIncomeGetBonusReturnRateInterface;
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

}
