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

public class WmsCreRevWaterPrivSalary implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_priv_salary_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String name;

    private String bank_name;

    private String water_begin_month;

    private String water_end_month;

    private String account_balance;

    private String pay_type;

    private String fixed_pay_day;

    private String fixed_payment;

    private String un_fixed_pay_day;

    private String un_fixed_payment;

    private String remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_water_priv_salary_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_priv_salary_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "name", "bank_name",
                                             "water_begin_month", "water_end_month", "account_balance", "pay_type",
                                             "fixed_pay_day", "fixed_payment", "un_fixed_pay_day", "un_fixed_payment",
                                             "remark" };

    public Integer getWms_cre_rev_water_priv_salary_id()
    {
        return wms_cre_rev_water_priv_salary_id;
    }

    public void setWms_cre_rev_water_priv_salary_id(Integer obj)
    {
        wms_cre_rev_water_priv_salary_id = obj;
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

    public String getPay_type()
    {
        return pay_type;
    }

    public void setPay_type(String obj)
    {
        pay_type = obj;
    }

    public String getFixed_pay_day()
    {
        return fixed_pay_day;
    }

    public void setFixed_pay_day(String obj)
    {
        fixed_pay_day = obj;
    }

    public String getFixed_payment()
    {
        return fixed_payment;
    }

    public void setFixed_payment(String obj)
    {
        fixed_payment = obj;
    }

    public String getUn_fixed_pay_day()
    {
        return un_fixed_pay_day;
    }

    public void setUn_fixed_pay_day(String obj)
    {
        un_fixed_pay_day = obj;
    }

    public String getUn_fixed_payment()
    {
        return un_fixed_payment;
    }

    public void setUn_fixed_payment(String obj)
    {
        un_fixed_payment = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_water_priv_salary_id", this.wms_cre_rev_water_priv_salary_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("name", this.name);
        paramMap.put("bank_name", this.bank_name);
        paramMap.put("water_begin_month", this.water_begin_month);
        paramMap.put("water_end_month", this.water_end_month);
        paramMap.put("account_balance", this.account_balance);
        paramMap.put("pay_type", this.pay_type);
        paramMap.put("fixed_pay_day", this.fixed_pay_day);
        paramMap.put("fixed_payment", this.fixed_payment);
        paramMap.put("un_fixed_pay_day", this.un_fixed_pay_day);
        paramMap.put("un_fixed_payment", this.un_fixed_payment);
        paramMap.put("remark", this.remark);
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