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

public class WmsCreCreditHeadDiffPhone implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_head_diff_phone_id;

    private String user_type;

    private Integer org_wms_cre_credit_head_id;

    private Integer org_customer_contact_id;

    private String org_name;

    private String org_phone;

    private Integer diff_wms_cre_credit_head_id;

    private Integer diff_customer_contact_id;

    private String diff_name;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String org_bill_code;

    private String diff_bill_code;

    private String diff_customer_names;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_head_diff_phone_id" };

    private static String[] columnNameArr = { "wms_cre_credit_head_diff_phone_id", "user_type",
                                             "org_wms_cre_credit_head_id", "org_customer_contact_id", "org_name",
                                             "org_phone", "diff_wms_cre_credit_head_id", "diff_customer_contact_id",
                                             "diff_name", "create_user_id", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "org_bill_code",
                                             "diff_bill_code", "diff_customer_names" };

    public Integer getWms_cre_credit_head_diff_phone_id()
    {
        return wms_cre_credit_head_diff_phone_id;
    }

    public void setWms_cre_credit_head_diff_phone_id(Integer obj)
    {
        wms_cre_credit_head_diff_phone_id = obj;
    }

    public String getUser_type()
    {
        return user_type;
    }

    public void setUser_type(String obj)
    {
        user_type = obj;
    }

    public Integer getOrg_wms_cre_credit_head_id()
    {
        return org_wms_cre_credit_head_id;
    }

    public void setOrg_wms_cre_credit_head_id(Integer obj)
    {
        org_wms_cre_credit_head_id = obj;
    }

    public Integer getOrg_customer_contact_id()
    {
        return org_customer_contact_id;
    }

    public void setOrg_customer_contact_id(Integer obj)
    {
        org_customer_contact_id = obj;
    }

    public String getOrg_name()
    {
        return org_name;
    }

    public void setOrg_name(String obj)
    {
        org_name = obj;
    }

    public String getOrg_phone()
    {
        return org_phone;
    }

    public void setOrg_phone(String obj)
    {
        org_phone = obj;
    }

    public Integer getDiff_wms_cre_credit_head_id()
    {
        return diff_wms_cre_credit_head_id;
    }

    public void setDiff_wms_cre_credit_head_id(Integer obj)
    {
        diff_wms_cre_credit_head_id = obj;
    }

    public Integer getDiff_customer_contact_id()
    {
        return diff_customer_contact_id;
    }

    public void setDiff_customer_contact_id(Integer obj)
    {
        diff_customer_contact_id = obj;
    }

    public String getDiff_name()
    {
        return diff_name;
    }

    public void setDiff_name(String obj)
    {
        diff_name = obj;
    }

    public Integer getCreate_user_id()
    {
        return create_user_id;
    }

    public void setCreate_user_id(Integer obj)
    {
        create_user_id = obj;
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

    public String getOrg_bill_code()
    {
        return org_bill_code;
    }

    public void setOrg_bill_code(String obj)
    {
        org_bill_code = obj;
    }

    public String getDiff_bill_code()
    {
        return diff_bill_code;
    }

    public void setDiff_bill_code(String obj)
    {
        diff_bill_code = obj;
    }

    public String getDiff_customer_names()
    {
        return diff_customer_names;
    }

    public void setDiff_customer_names(String obj)
    {
        diff_customer_names = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_head_diff_phone_id", this.wms_cre_credit_head_diff_phone_id);
        paramMap.put("user_type", this.user_type);
        paramMap.put("org_wms_cre_credit_head_id", this.org_wms_cre_credit_head_id);
        paramMap.put("org_customer_contact_id", this.org_customer_contact_id);
        paramMap.put("org_name", this.org_name);
        paramMap.put("org_phone", this.org_phone);
        paramMap.put("diff_wms_cre_credit_head_id", this.diff_wms_cre_credit_head_id);
        paramMap.put("diff_customer_contact_id", this.diff_customer_contact_id);
        paramMap.put("diff_name", this.diff_name);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("org_bill_code", this.org_bill_code);
        paramMap.put("diff_bill_code", this.diff_bill_code);
        paramMap.put("diff_customer_names", this.diff_customer_names);
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