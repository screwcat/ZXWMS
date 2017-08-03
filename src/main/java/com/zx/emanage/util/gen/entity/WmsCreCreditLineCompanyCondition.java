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
 * 企业基本信息
 */

public class WmsCreCreditLineCompanyCondition implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_company_condition_id;

    private String company_name;

    private String legal_person;

    private String company_type;

    private java.sql.Date found_date;

    private String found_date_str;

    private java.sql.Date register_date;

    private String register_date_str;

    private String business_limit_date;

    private String register_no;

    private Long register_money;

    private String year_check;

    private String org_code_no;

    private String register_address;

    private String pay_taxes_type;

    private String pay_taxes_status;

    private String tax_register_no;

    private Integer wms_cre_credit_head_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private Integer enter_level;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_company_condition_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_company_condition_id", "company_name",
                                             "legal_person", "company_type", "found_date", "found_date_str",
                                             "register_date", "register_date_str", "business_limit_date",
                                             "register_no", "register_money", "year_check", "org_code_no",
                                             "register_address", "pay_taxes_type", "pay_taxes_status",
                                             "tax_register_no", "wms_cre_credit_head_id", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "enter_level" };

    public Integer getWms_cre_credit_line_company_condition_id()
    {
        return wms_cre_credit_line_company_condition_id;
    }

    public void setWms_cre_credit_line_company_condition_id(Integer obj)
    {
        wms_cre_credit_line_company_condition_id = obj;
    }

    public String getCompany_name()
    {
        return company_name;
    }

    public void setCompany_name(String obj)
    {
        company_name = obj;
    }

    public String getLegal_person()
    {
        return legal_person;
    }

    public void setLegal_person(String obj)
    {
        legal_person = obj;
    }

    public String getCompany_type()
    {
        return company_type;
    }

    public void setCompany_type(String obj)
    {
        company_type = obj;
    }

    public java.sql.Date getFound_date()
    {
        return found_date;
    }

    public void setFound_date(java.sql.Date obj)
    {
        found_date = obj;
    }

    public String getFound_dateStr()
    {
        return found_date_str;
    }

    public void setFound_dateStr(String obj)
    {
        found_date_str = obj;
    }

    public java.sql.Date getRegister_date()
    {
        return register_date;
    }

    public void setRegister_date(java.sql.Date obj)
    {
        register_date = obj;
    }

    public String getRegister_dateStr()
    {
        return register_date_str;
    }

    public void setRegister_dateStr(String obj)
    {
        register_date_str = obj;
    }

    public String getBusiness_limit_date()
    {
        return business_limit_date;
    }

    public void setBusiness_limit_date(String obj)
    {
        business_limit_date = obj;
    }

    public String getRegister_no()
    {
        return register_no;
    }

    public void setRegister_no(String obj)
    {
        register_no = obj;
    }

    public Long getRegister_money()
    {
        return register_money;
    }

    public void setRegister_money(Long obj)
    {
        register_money = obj;
    }

    public String getYear_check()
    {
        return year_check;
    }

    public void setYear_check(String obj)
    {
        year_check = obj;
    }

    public String getOrg_code_no()
    {
        return org_code_no;
    }

    public void setOrg_code_no(String obj)
    {
        org_code_no = obj;
    }

    public String getRegister_address()
    {
        return register_address;
    }

    public void setRegister_address(String obj)
    {
        register_address = obj;
    }

    public String getPay_taxes_type()
    {
        return pay_taxes_type;
    }

    public void setPay_taxes_type(String obj)
    {
        pay_taxes_type = obj;
    }

    public String getPay_taxes_status()
    {
        return pay_taxes_status;
    }

    public void setPay_taxes_status(String obj)
    {
        pay_taxes_status = obj;
    }

    public String getTax_register_no()
    {
        return tax_register_no;
    }

    public void setTax_register_no(String obj)
    {
        tax_register_no = obj;
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

    public Integer getEnter_level()
    {
        return enter_level;
    }

    public void setEnter_level(Integer obj)
    {
        enter_level = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_company_condition_id", this.wms_cre_credit_line_company_condition_id);
        paramMap.put("company_name", this.company_name);
        paramMap.put("legal_person", this.legal_person);
        paramMap.put("company_type", this.company_type);
        paramMap.put("found_date", this.found_date);
        paramMap.put("found_date_str", this.found_date_str);
        paramMap.put("register_date", this.register_date);
        paramMap.put("register_date_str", this.register_date_str);
        paramMap.put("business_limit_date", this.business_limit_date);
        paramMap.put("register_no", this.register_no);
        paramMap.put("register_money", this.register_money);
        paramMap.put("year_check", this.year_check);
        paramMap.put("org_code_no", this.org_code_no);
        paramMap.put("register_address", this.register_address);
        paramMap.put("pay_taxes_type", this.pay_taxes_type);
        paramMap.put("pay_taxes_status", this.pay_taxes_status);
        paramMap.put("tax_register_no", this.tax_register_no);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("enter_level", this.enter_level);
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