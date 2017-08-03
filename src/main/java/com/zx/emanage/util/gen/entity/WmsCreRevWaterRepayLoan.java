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

public class WmsCreRevWaterRepayLoan implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_repay_loan_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String repay_loan_type;

    private String name;

    private String bank_name;

    private String beak_card_no_tail;

    private String water_begin_month;

    private String water_end_month;

    private String account_balance;

    private String is_repay;

    private String remark;

    private String is_pay_taxes;

    private String is_pay_wages;

    private String is_late_pay_taxes;

    private String late_pay_taxes_days;

    private String is_arrears;

    private String arrears_days;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_water_repay_loan_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_repay_loan_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "repay_loan_type", "name",
                                             "bank_name", "beak_card_no_tail", "water_begin_month", "water_end_month",
                                             "account_balance", "is_repay", "remark", "is_pay_taxes", "is_pay_wages",
                                             "is_late_pay_taxes", "late_pay_taxes_days", "is_arrears", "arrears_days" };

    public Integer getWms_cre_rev_water_repay_loan_id()
    {
        return wms_cre_rev_water_repay_loan_id;
    }

    public void setWms_cre_rev_water_repay_loan_id(Integer obj)
    {
        wms_cre_rev_water_repay_loan_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_cre_credit_line_customer_change_head_id()
    {
        return wms_cre_credit_line_customer_change_head_id;
    }

    public void setWms_cre_credit_line_customer_change_head_id(Integer obj)
    {
        wms_cre_credit_line_customer_change_head_id = obj;
    }

    public String getRepay_loan_type()
    {
        return repay_loan_type;
    }

    public void setRepay_loan_type(String obj)
    {
        repay_loan_type = obj;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String obj)
    {
        name = obj;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public void setBank_name(String obj)
    {
        bank_name = obj;
    }

    public String getBeak_card_no_tail()
    {
        return beak_card_no_tail;
    }

    public void setBeak_card_no_tail(String obj)
    {
        beak_card_no_tail = obj;
    }

    public String getWater_begin_month()
    {
        return water_begin_month;
    }

    public void setWater_begin_month(String obj)
    {
        water_begin_month = obj;
    }

    public String getWater_end_month()
    {
        return water_end_month;
    }

    public void setWater_end_month(String obj)
    {
        water_end_month = obj;
    }

    public String getAccount_balance()
    {
        return account_balance;
    }

    public void setAccount_balance(String obj)
    {
        account_balance = obj;
    }

    public String getIs_repay()
    {
        return is_repay;
    }

    public void setIs_repay(String obj)
    {
        is_repay = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    public String getIs_pay_taxes()
    {
        return is_pay_taxes;
    }

    public void setIs_pay_taxes(String obj)
    {
        is_pay_taxes = obj;
    }

    public String getIs_pay_wages()
    {
        return is_pay_wages;
    }

    public void setIs_pay_wages(String obj)
    {
        is_pay_wages = obj;
    }

    public String getIs_late_pay_taxes()
    {
        return is_late_pay_taxes;
    }

    public void setIs_late_pay_taxes(String obj)
    {
        is_late_pay_taxes = obj;
    }

    public String getLate_pay_taxes_days()
    {
        return late_pay_taxes_days;
    }

    public void setLate_pay_taxes_days(String obj)
    {
        late_pay_taxes_days = obj;
    }

    public String getIs_arrears()
    {
        return is_arrears;
    }

    public void setIs_arrears(String obj)
    {
        is_arrears = obj;
    }

    public String getArrears_days()
    {
        return arrears_days;
    }

    public void setArrears_days(String obj)
    {
        arrears_days = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_water_repay_loan_id", this.wms_cre_rev_water_repay_loan_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("repay_loan_type", this.repay_loan_type);
        paramMap.put("name", this.name);
        paramMap.put("bank_name", this.bank_name);
        paramMap.put("beak_card_no_tail", this.beak_card_no_tail);
        paramMap.put("water_begin_month", this.water_begin_month);
        paramMap.put("water_end_month", this.water_end_month);
        paramMap.put("account_balance", this.account_balance);
        paramMap.put("is_repay", this.is_repay);
        paramMap.put("remark", this.remark);
        paramMap.put("is_pay_taxes", this.is_pay_taxes);
        paramMap.put("is_pay_wages", this.is_pay_wages);
        paramMap.put("is_late_pay_taxes", this.is_late_pay_taxes);
        paramMap.put("late_pay_taxes_days", this.late_pay_taxes_days);
        paramMap.put("is_arrears", this.is_arrears);
        paramMap.put("arrears_days", this.arrears_days);
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