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
 * 投资人
 */

public class WmsCreCreditLineCompanyConditionInvestor implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_company_condition_investor_id;

    private String investor_type;

    private String investor;

    private String rfjc_money;

    private String funding_way;

    private java.sql.Date funding_date;

    private String funding_date_str;

    private String real_funding_money;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String real_funding_way;

    private java.sql.Date real_funding_date;

    private String real_funding_date_str;

    private Integer wms_cre_credit_line_company_condition_id;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_company_condition_investor_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_company_condition_investor_id", "investor_type",
                                             "investor", "rfjc_money", "funding_way", "funding_date",
                                             "funding_date_str", "real_funding_money", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "real_funding_way",
                                             "real_funding_date", "real_funding_date_str",
                                             "wms_cre_credit_line_company_condition_id" };

    public Integer getWms_cre_credit_line_company_condition_investor_id()
    {
        return wms_cre_credit_line_company_condition_investor_id;
    }

    public void setWms_cre_credit_line_company_condition_investor_id(Integer obj)
    {
        wms_cre_credit_line_company_condition_investor_id = obj;
    }

    public String getInvestor_type()
    {
        return investor_type;
    }

    public void setInvestor_type(String obj)
    {
        investor_type = obj;
    }

    public String getInvestor()
    {
        return investor;
    }

    public void setInvestor(String obj)
    {
        investor = obj;
    }

    public String getRfjc_money()
    {
        return rfjc_money;
    }

    public void setRfjc_money(String obj)
    {
        rfjc_money = obj;
    }

    public String getFunding_way()
    {
        return funding_way;
    }

    public void setFunding_way(String obj)
    {
        funding_way = obj;
    }

    public java.sql.Date getFunding_date()
    {
        return funding_date;
    }

    public void setFunding_date(java.sql.Date obj)
    {
        funding_date = obj;
    }

    public String getFunding_dateStr()
    {
        return funding_date_str;
    }

    public void setFunding_dateStr(String obj)
    {
        funding_date_str = obj;
    }

    public String getReal_funding_money()
    {
        return real_funding_money;
    }

    public void setReal_funding_money(String obj)
    {
        real_funding_money = obj;
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

    public String getReal_funding_way()
    {
        return real_funding_way;
    }

    public void setReal_funding_way(String obj)
    {
        real_funding_way = obj;
    }

    public java.sql.Date getReal_funding_date()
    {
        return real_funding_date;
    }

    public void setReal_funding_date(java.sql.Date obj)
    {
        real_funding_date = obj;
    }

    public String getReal_funding_dateStr()
    {
        return real_funding_date_str;
    }

    public void setReal_funding_dateStr(String obj)
    {
        real_funding_date_str = obj;
    }

    public Integer getWms_cre_credit_line_company_condition_id()
    {
        return wms_cre_credit_line_company_condition_id;
    }

    public void setWms_cre_credit_line_company_condition_id(Integer obj)
    {
        wms_cre_credit_line_company_condition_id = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_company_condition_investor_id",
                     this.wms_cre_credit_line_company_condition_investor_id);
        paramMap.put("investor_type", this.investor_type);
        paramMap.put("investor", this.investor);
        paramMap.put("rfjc_money", this.rfjc_money);
        paramMap.put("funding_way", this.funding_way);
        paramMap.put("funding_date", this.funding_date);
        paramMap.put("funding_date_str", this.funding_date_str);
        paramMap.put("real_funding_money", this.real_funding_money);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("real_funding_way", this.real_funding_way);
        paramMap.put("real_funding_date", this.real_funding_date);
        paramMap.put("real_funding_date_str", this.real_funding_date_str);
        paramMap.put("wms_cre_credit_line_company_condition_id", this.wms_cre_credit_line_company_condition_id);
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