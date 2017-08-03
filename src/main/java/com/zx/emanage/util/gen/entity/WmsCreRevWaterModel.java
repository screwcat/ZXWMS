package com.zx.emanage.util.gen.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreRevWaterModel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_model_id;

    private Integer wms_cre_credit_head_id;

    private java.math.BigDecimal aver_balance;

    private java.math.BigDecimal aver_income;

    private java.math.BigDecimal aver_payment;

    private java.math.BigDecimal month_payment;

    private java.math.BigDecimal max_income;

    private java.math.BigDecimal max_payment;

    private java.math.BigDecimal borr_loan_balance_ratio;

    private java.math.BigDecimal assets_liabilities_ratio;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_water_model_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_model_id", "wms_cre_credit_head_id", "aver_balance",
                                             "aver_income", "aver_payment", "month_payment", "max_income",
                                             "max_payment", "borr_loan_balance_ratio", "assets_liabilities_ratio" };

    public Integer getWms_cre_rev_water_model_id()
    {
        return wms_cre_rev_water_model_id;
    }

    public void setWms_cre_rev_water_model_id(Integer obj)
    {
        wms_cre_rev_water_model_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public java.math.BigDecimal getAver_balance()
    {
        return aver_balance;
    }

    public void setAver_balance(java.math.BigDecimal obj)
    {
        aver_balance = obj;
    }

    public java.math.BigDecimal getAver_income()
    {
        return aver_income;
    }

    public void setAver_income(java.math.BigDecimal obj)
    {
        aver_income = obj;
    }

    public java.math.BigDecimal getAver_payment()
    {
        return aver_payment;
    }

    public void setAver_payment(java.math.BigDecimal obj)
    {
        aver_payment = obj;
    }

    public java.math.BigDecimal getMonth_payment()
    {
        return month_payment;
    }

    public void setMonth_payment(java.math.BigDecimal obj)
    {
        month_payment = obj;
    }

    public java.math.BigDecimal getMax_income()
    {
        return max_income;
    }

    public void setMax_income(java.math.BigDecimal obj)
    {
        max_income = obj;
    }

    public java.math.BigDecimal getMax_payment()
    {
        return max_payment;
    }

    public void setMax_payment(java.math.BigDecimal obj)
    {
        max_payment = obj;
    }

    public java.math.BigDecimal getBorr_loan_balance_ratio()
    {
        return borr_loan_balance_ratio;
    }

    public void setBorr_loan_balance_ratio(java.math.BigDecimal obj)
    {
        borr_loan_balance_ratio = obj;
    }

    public java.math.BigDecimal getAssets_liabilities_ratio()
    {
        return assets_liabilities_ratio;
    }

    public void setAssets_liabilities_ratio(java.math.BigDecimal obj)
    {
        assets_liabilities_ratio = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_water_model_id", this.wms_cre_rev_water_model_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("aver_balance", this.aver_balance);
        paramMap.put("aver_income", this.aver_income);
        paramMap.put("aver_payment", this.aver_payment);
        paramMap.put("month_payment", this.month_payment);
        paramMap.put("max_income", this.max_income);
        paramMap.put("max_payment", this.max_payment);
        paramMap.put("borr_loan_balance_ratio", this.borr_loan_balance_ratio);
        paramMap.put("assets_liabilities_ratio", this.assets_liabilities_ratio);
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMap()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMap(paramMap);
        return paramMap;
    }

    /**
     * remove default value and pk if it is null
     */
    private Map<String, Object> dealWithMap(Map<String, Object> paramMap)
    {
        Set<String> set = new HashSet<String>();
        for (String colName : defaultValColArr)
        {
            set.add(colName);
        }
        for (String colName : pkColArr)
        {
            set.add(colName);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext())
        {
            String colName = iterator.next();
            if (paramMap.get(colName) == null)
            {
                paramMap.remove(colName);
            }
        }
        return paramMap;
    }
}