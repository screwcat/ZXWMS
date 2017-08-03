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

public class WmsCreCreditLineCustomerInfo implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_customer_info_id;

    private Integer customer_type;

    private Double apply_limit_money;

    private Integer apply_limit_time;

    private String apply_purpose;

    private Double last_loan_money;

    private Integer repayment_status;

    private String overdue_status;

    private java.sql.Date last_apply_date;

    private String last_apply_date_str;

    private java.sql.Date last_clean_date;

    private String last_clean_date_str;

    private String info_update;

    private Integer wms_cre_credit_head_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String criminal_record;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_customer_info_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_customer_info_id", "customer_type",
                                             "apply_limit_money", "apply_limit_time", "apply_purpose",
                                             "last_loan_money", "repayment_status", "overdue_status",
                                             "last_apply_date", "last_apply_date_str", "last_clean_date",
                                             "last_clean_date_str", "info_update", "wms_cre_credit_head_id",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "criminal_record" };

    public Integer getWms_cre_credit_line_customer_info_id()
    {
        return wms_cre_credit_line_customer_info_id;
    }

    public void setWms_cre_credit_line_customer_info_id(Integer obj)
    {
        wms_cre_credit_line_customer_info_id = obj;
    }

    public Integer getCustomer_type()
    {
        return customer_type;
    }

    public void setCustomer_type(Integer obj)
    {
        customer_type = obj;
    }

    public Double getApply_limit_money()
    {
        return apply_limit_money;
    }

    public void setApply_limit_money(Double obj)
    {
        apply_limit_money = obj;
    }

    public Integer getApply_limit_time()
    {
        return apply_limit_time;
    }

    public void setApply_limit_time(Integer obj)
    {
        apply_limit_time = obj;
    }

    public String getApply_purpose()
    {
        return apply_purpose;
    }

    public void setApply_purpose(String obj)
    {
        apply_purpose = obj;
    }

    public Double getLast_loan_money()
    {
        return last_loan_money;
    }

    public void setLast_loan_money(Double obj)
    {
        last_loan_money = obj;
    }

    public Integer getRepayment_status()
    {
        return repayment_status;
    }

    public void setRepayment_status(Integer obj)
    {
        repayment_status = obj;
    }

    public String getOverdue_status()
    {
        return overdue_status;
    }

    public void setOverdue_status(String obj)
    {
        overdue_status = obj;
    }

    public java.sql.Date getLast_apply_date()
    {
        return last_apply_date;
    }

    public void setLast_apply_date(java.sql.Date obj)
    {
        last_apply_date = obj;
    }

    public String getLast_apply_dateStr()
    {
        return last_apply_date_str;
    }

    public void setLast_apply_dateStr(String obj)
    {
        last_apply_date_str = obj;
    }

    public java.sql.Date getLast_clean_date()
    {
        return last_clean_date;
    }

    public void setLast_clean_date(java.sql.Date obj)
    {
        last_clean_date = obj;
    }

    public String getLast_clean_dateStr()
    {
        return last_clean_date_str;
    }

    public void setLast_clean_dateStr(String obj)
    {
        last_clean_date_str = obj;
    }

    public String getInfo_update()
    {
        return info_update;
    }

    public void setInfo_update(String obj)
    {
        info_update = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
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

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
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

    public String getLast_update_timestampStr()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestampStr(String obj)
    {
        last_update_timestamp_str = obj;
    }

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getCriminal_record()
    {
        return criminal_record;
    }

    public void setCriminal_record(String obj)
    {
        criminal_record = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_customer_info_id", this.wms_cre_credit_line_customer_info_id);
        paramMap.put("customer_type", this.customer_type);
        paramMap.put("apply_limit_money", this.apply_limit_money);
        paramMap.put("apply_limit_time", this.apply_limit_time);
        paramMap.put("apply_purpose", this.apply_purpose);
        paramMap.put("last_loan_money", this.last_loan_money);
        paramMap.put("repayment_status", this.repayment_status);
        paramMap.put("overdue_status", this.overdue_status);
        paramMap.put("last_apply_date", this.last_apply_date);
        paramMap.put("last_apply_date_str", this.last_apply_date_str);
        paramMap.put("last_clean_date", this.last_clean_date);
        paramMap.put("last_clean_date_str", this.last_clean_date_str);
        paramMap.put("info_update", this.info_update);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("criminal_record", this.criminal_record);
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