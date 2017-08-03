package com.zx.emanage.util.gen.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveTransaIncome implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_income_id;

    private Integer wms_inve_transa_id;

    private Integer wms_inve_transa_prod_id;

    private Integer wms_inve_pruduct_category_id;

    private String id_card;

    private String cus_name;

    private Integer product_deadline_month;

    private java.sql.Date return_date;

    private String return_date_str;

    private java.math.BigDecimal product_account;

    private java.math.BigDecimal product_interest_account;

    private java.sql.Date act_return_date;

    private String act_return_date_str;

    private String pay_status;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private Integer days_of_calculation;

    private java.math.BigDecimal bonus_rate;

    private java.math.BigDecimal payable_reward_income;

    private java.math.BigDecimal income_rate;

    private java.math.BigDecimal payable_basic_income;

    private String remarks;

    private Integer days_extend_income;

    private java.math.BigDecimal extend_income;

    private java.math.BigDecimal adjust_amount;

    private String adjust_remark;

    private java.sql.Timestamp adjust_datetime;

    private Integer pm_personnel_id;

    private String pay_type;

    private BigDecimal renewal_income;

    private Integer wms_inve_customer_card_id;
    
    private Integer days_current_income;
    
    private BigDecimal current_income_rate;
    
    private BigDecimal current_income;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_income_id" };

    private static String[] columnNameArr = { "wms_inve_transa_income_id", "wms_inve_transa_id",
                                             "wms_inve_transa_prod_id", "wms_inve_pruduct_category_id", "id_card",
                                             "cus_name", "product_deadline_month", "return_date", "return_date_str",
                                             "product_account", "product_interest_account", "act_return_date",
                                             "act_return_date_str", "pay_status", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str",
                                             "days_of_calculation", "bonus_rate", "payable_reward_income",
                                             "payable_basic_income", "remarks", "isExcludePKFlag",
                                             "days_extend_income", "extend_income", "adjust_amount", "adjust_remark",
                                             "adjust_datetime", "pm_personnel_id", "pay_type", "renewal_income",
                                             "wms_inve_customer_card_id" };

    public Integer getWms_inve_transa_income_id()
    {
        return wms_inve_transa_income_id;
    }

    public void setWms_inve_transa_income_id(Integer obj)
    {
        wms_inve_transa_income_id = obj;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer obj)
    {
        wms_inve_transa_id = obj;
    }

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer obj)
    {
        wms_inve_transa_prod_id = obj;
    }

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(Integer obj)
    {
        wms_inve_pruduct_category_id = obj;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String obj)
    {
        id_card = obj;
    }

    public String getCus_name()
    {
        return cus_name;
    }

    public void setCus_name(String obj)
    {
        cus_name = obj;
    }

    public Integer getProduct_deadline_month()
    {
        return product_deadline_month;
    }

    public void setProduct_deadline_month(Integer obj)
    {
        product_deadline_month = obj;
    }

    public java.sql.Date getReturn_date()
    {
        return return_date;
    }

    public void setReturn_date(java.sql.Date obj)
    {
        return_date = obj;
    }

    public String getReturn_date_str()
    {
        return return_date_str;
    }

    public void setReturn_date_str(String obj)
    {
        return_date_str = obj;
    }

    public java.math.BigDecimal getProduct_account()
    {
        return product_account;
    }

    public void setProduct_account(java.math.BigDecimal obj)
    {
        product_account = obj;
    }

    public java.math.BigDecimal getProduct_interest_account()
    {
        return product_interest_account;
    }

    public void setProduct_interest_account(java.math.BigDecimal obj)
    {
        product_interest_account = obj;
    }

    public java.sql.Date getAct_return_date()
    {
        return act_return_date;
    }

    public void setAct_return_date(java.sql.Date obj)
    {
        act_return_date = obj;
    }

    public String getAct_return_date_str()
    {
        return act_return_date_str;
    }

    public void setAct_return_date_str(String obj)
    {
        act_return_date_str = obj;
    }

    public String getPay_status()
    {
        return pay_status;
    }

    public void setPay_status(String obj)
    {
        pay_status = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
    }

    public String getCreate_user_name()
    {
        return create_user_name;
    }

    public void setCreate_user_name(String obj)
    {
        create_user_name = obj;
    }

    public java.sql.Timestamp getCreate_timestamp()
    {
        return create_timestamp;
    }

    public void setCreate_timestamp(java.sql.Timestamp obj)
    {
        create_timestamp = obj;
    }

    public String getCreate_timestamp_str()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestamp_str(String obj)
    {
        create_timestamp_str = obj;
    }

    public Integer getLast_update_user_id()
    {
        return last_update_user_id;
    }

    public void setLast_update_user_id(Integer obj)
    {
        last_update_user_id = obj;
    }

    public java.sql.Timestamp getLast_update_timestamp()
    {
        return last_update_timestamp;
    }

    public void setLast_update_timestamp(java.sql.Timestamp obj)
    {
        last_update_timestamp = obj;
    }

    public String getLast_update_timestamp_str()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestamp_str(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public Integer getDays_of_calculation()
    {
        return days_of_calculation;
    }

    public void setDays_of_calculation(Integer days_of_calculation)
    {
        this.days_of_calculation = days_of_calculation;
    }

    public java.math.BigDecimal getBonus_rate()
    {
        return bonus_rate;
    }

    public void setBonus_rate(java.math.BigDecimal bonus_rate)
    {
        this.bonus_rate = bonus_rate;
    }

    public java.math.BigDecimal getPayable_reward_income()
    {
        return payable_reward_income;
    }

    public void setPayable_reward_income(java.math.BigDecimal payable_reward_income)
    {
        this.payable_reward_income = payable_reward_income;
    }

    public java.math.BigDecimal getPayable_basic_income()
    {
        return payable_basic_income;
    }

    public void setPayable_basic_income(java.math.BigDecimal payable_basic_income)
    {
        this.payable_basic_income = payable_basic_income;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public Integer getDays_extend_income()
    {
        return days_extend_income;
    }

    public void setDays_extend_income(Integer days_extend_income)
    {
        this.days_extend_income = days_extend_income;
    }

    public java.math.BigDecimal getExtend_income()
    {
        return extend_income;
    }

    public void setExtend_income(java.math.BigDecimal extend_income)
    {
        this.extend_income = extend_income;
    }

    public java.math.BigDecimal getAdjust_amount()
    {
        return adjust_amount;
    }

    public void setAdjust_amount(java.math.BigDecimal adjust_amount)
    {
        this.adjust_amount = adjust_amount;
    }

    public String getAdjust_remark()
    {
        return adjust_remark;
    }

    public void setAdjust_remark(String adjust_remark)
    {
        this.adjust_remark = adjust_remark;
    }

    public java.sql.Timestamp getAdjust_datetime()
    {
        return adjust_datetime;
    }

    public void setAdjust_datetime(java.sql.Timestamp adjust_datetime)
    {
        this.adjust_datetime = adjust_datetime;
    }

    public Integer getPm_personnel_id()
    {
        return pm_personnel_id;
    }

    public void setPm_personnel_id(Integer pm_personnel_id)
    {
        this.pm_personnel_id = pm_personnel_id;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    public String getPay_type()
    {
        return pay_type;
    }

    public void setPay_type(String pay_type)
    {
        this.pay_type = pay_type;
    }

    public java.math.BigDecimal getIncome_rate()
    {
        return income_rate;
    }

    public void setIncome_rate(java.math.BigDecimal income_rate)
    {
        this.income_rate = income_rate;
    }

    public BigDecimal getRenewal_income()
    {
        return renewal_income;
    }

    public void setRenewal_income(BigDecimal renewal_income)
    {
        this.renewal_income = renewal_income;
    }

    /**
     * @return the wms_inve_customer_card_id
     */
    public Integer getWms_inve_customer_card_id()
    {
        return wms_inve_customer_card_id;
    }

    /**
     * @param wms_inve_customer_card_id the wms_inve_customer_card_id to set
     */
    public void setWms_inve_customer_card_id(Integer wms_inve_customer_card_id)
    {
        this.wms_inve_customer_card_id = wms_inve_customer_card_id;
    }

    /**
     * @return the days_current_income
     */
    public Integer getDays_current_income()
    {
        return days_current_income;
    }

    /**
     * @param days_current_income the days_current_income to set
     */
    public void setDays_current_income(Integer days_current_income)
    {
        this.days_current_income = days_current_income;
    }

    /**
     * @return the current_income_rate
     */
    public BigDecimal getCurrent_income_rate()
    {
        return current_income_rate;
    }

    /**
     * @param current_income_rate the current_income_rate to set
     */
    public void setCurrent_income_rate(BigDecimal current_income_rate)
    {
        this.current_income_rate = current_income_rate;
    }

    /**
     * @return the current_income
     */
    public BigDecimal getCurrent_income()
    {
        return current_income;
    }

    /**
     * @param current_income the current_income to set
     */
    public void setCurrent_income(BigDecimal current_income)
    {
        this.current_income = current_income;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_transa_income_id", this.wms_inve_transa_income_id);
        paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
        paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("id_card", this.id_card);
        paramMap.put("cus_name", this.cus_name);
        paramMap.put("product_deadline_month", this.product_deadline_month);
        paramMap.put("return_date", this.return_date);
        paramMap.put("return_date_str", this.return_date_str);
        paramMap.put("product_account", this.product_account);
        paramMap.put("product_interest_account", this.product_interest_account);
        paramMap.put("act_return_date", this.act_return_date);
        paramMap.put("act_return_date_str", this.act_return_date_str);
        paramMap.put("pay_status", this.pay_status);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("days_of_calculation", this.days_of_calculation);
        paramMap.put("bonus_rate", this.bonus_rate);
        paramMap.put("payable_reward_income", this.payable_reward_income);
        paramMap.put("payable_basic_income", this.payable_basic_income);
        paramMap.put("remarks", this.remarks);
        paramMap.put("days_extend_income", this.days_extend_income);
        paramMap.put("extend_income", this.extend_income);
        paramMap.put("adjust_amount", this.adjust_amount);
        paramMap.put("adjust_remark", this.adjust_remark);
        paramMap.put("adjust_datetime", this.adjust_datetime);
        paramMap.put("pm_personnel_id", this.pm_personnel_id);
        paramMap.put("pay_type", this.pay_type);
        paramMap.put("renewal_income", this.renewal_income);
        paramMap.put("wms_inve_customer_card_id", this.wms_inve_customer_card_id);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
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