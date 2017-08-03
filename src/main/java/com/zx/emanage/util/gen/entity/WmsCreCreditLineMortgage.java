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

public class WmsCreCreditLineMortgage implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_mortgage_id;

    private String mortgage_comment;

    private java.math.BigDecimal mortgage_amount;

    private java.sql.Date mortgage_date;

    private String mortgage_date_str;

    private String mortgage_cycle;

    private String right_person;

    private String mortgagee;

    private String mortgage_ratio;

    private String is_contract;

    private String is_cycle_mortgage;

    private Integer wms_cre_credit_head_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_mortgage_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_mortgage_id", "mortgage_comment", "mortgage_amount",
                                             "mortgage_date", "mortgage_date_str", "mortgage_cycle", "right_person",
                                             "mortgagee", "mortgage_ratio", "is_contract", "is_cycle_mortgage",
                                             "wms_cre_credit_head_id", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_mortgage_id()
    {
        return wms_cre_credit_line_mortgage_id;
    }

    public void setWms_cre_credit_line_mortgage_id(Integer obj)
    {
        wms_cre_credit_line_mortgage_id = obj;
    }

    public String getMortgage_comment()
    {
        return mortgage_comment;
    }

    public void setMortgage_comment(String obj)
    {
        mortgage_comment = obj;
    }

    public java.math.BigDecimal getMortgage_amount()
    {
        return mortgage_amount;
    }

    public void setMortgage_amount(java.math.BigDecimal obj)
    {
        mortgage_amount = obj;
    }

    public java.sql.Date getMortgage_date()
    {
        return mortgage_date;
    }

    public void setMortgage_date(java.sql.Date obj)
    {
        mortgage_date = obj;
    }

    public String getMortgage_dateStr()
    {
        return mortgage_date_str;
    }

    public void setMortgage_dateStr(String obj)
    {
        mortgage_date_str = obj;
    }

    public String getMortgage_cycle()
    {
        return mortgage_cycle;
    }

    public void setMortgage_cycle(String obj)
    {
        mortgage_cycle = obj;
    }

    public String getRight_person()
    {
        return right_person;
    }

    public void setRight_person(String obj)
    {
        right_person = obj;
    }

    public String getMortgagee()
    {
        return mortgagee;
    }

    public void setMortgagee(String obj)
    {
        mortgagee = obj;
    }

    public String getMortgage_ratio()
    {
        return mortgage_ratio;
    }

    public void setMortgage_ratio(String obj)
    {
        mortgage_ratio = obj;
    }

    public String getIs_contract()
    {
        return is_contract;
    }

    public void setIs_contract(String obj)
    {
        is_contract = obj;
    }

    public String getIs_cycle_mortgage()
    {
        return is_cycle_mortgage;
    }

    public void setIs_cycle_mortgage(String obj)
    {
        is_cycle_mortgage = obj;
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_mortgage_id", this.wms_cre_credit_line_mortgage_id);
        paramMap.put("mortgage_comment", this.mortgage_comment);
        paramMap.put("mortgage_amount", this.mortgage_amount);
        paramMap.put("mortgage_date", this.mortgage_date);
        paramMap.put("mortgage_date_str", this.mortgage_date_str);
        paramMap.put("mortgage_cycle", this.mortgage_cycle);
        paramMap.put("right_person", this.right_person);
        paramMap.put("mortgagee", this.mortgagee);
        paramMap.put("mortgage_ratio", this.mortgage_ratio);
        paramMap.put("is_contract", this.is_contract);
        paramMap.put("is_cycle_mortgage", this.is_cycle_mortgage);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
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