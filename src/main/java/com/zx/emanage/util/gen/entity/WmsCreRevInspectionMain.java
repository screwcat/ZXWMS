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

public class WmsCreRevInspectionMain implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_inspection_main_id;

    private Integer wms_cre_credit_head_id;

    private String customer_name;

    private String gender;

    private java.sql.Date birthday;

    private String birthday_str;

    private String degree;

    private String telephone;

    private String service_password;

    private String address;

    private String id_card;

    private String has_house;

    private String personal_month_income;

    private String credit_limit;

    private String credit_purpose;

    private String funding_gap;

    private String comp_industry;

    private String work_unit_full_name;

    private String company_adress;

    private java.sql.Date found_date;

    private String found_date_str;

    private String employees_number;

    private String unit_tel;

    private String annual_income;

    private String profit_margin;

    private String liquidity;

    private String fixed_money;

    private String original_loan_company;

    private String understand_company;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String check_point_audit_opinion;

    private String scope_of_business;

    private String annual_income_this_year;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_inspection_main_id" };

    private static String[] columnNameArr = { "wms_cre_rev_inspection_main_id", "wms_cre_credit_head_id",
                                             "customer_name", "gender", "birthday", "birthday_str", "degree",
                                             "telephone", "service_password", "address", "id_card", "has_house",
                                             "personal_month_income", "credit_limit", "credit_purpose", "funding_gap",
                                             "comp_industry", "work_unit_full_name", "company_adress", "found_date",
                                             "found_date_str", "employees_number", "unit_tel", "annual_income",
                                             "profit_margin", "liquidity", "fixed_money", "original_loan_company",
                                             "understand_company", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag",
                                             "check_point_audit_opinion", "scope_of_business",
                                             "annual_income_this_year", "isExcludePKFlag" };

    public Integer getWms_cre_rev_inspection_main_id()
    {
        return wms_cre_rev_inspection_main_id;
    }

    public void setWms_cre_rev_inspection_main_id(Integer obj)
    {
        wms_cre_rev_inspection_main_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getCustomer_name()
    {
        return customer_name;
    }

    public void setCustomer_name(String obj)
    {
        customer_name = obj;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String obj)
    {
        gender = obj;
    }

    public java.sql.Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(java.sql.Date obj)
    {
        birthday = obj;
    }

    public String getBirthday_str()
    {
        return birthday_str;
    }

    public void setBirthday_str(String obj)
    {
        birthday_str = obj;
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String obj)
    {
        degree = obj;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String obj)
    {
        telephone = obj;
    }

    public String getService_password()
    {
        return service_password;
    }

    public void setService_password(String obj)
    {
        service_password = obj;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String obj)
    {
        address = obj;
    }

    public String getId_card()
    {
        return id_card;
    }

    public void setId_card(String obj)
    {
        id_card = obj;
    }

    public String getHas_house()
    {
        return has_house;
    }

    public void setHas_house(String obj)
    {
        has_house = obj;
    }

    public String getPersonal_month_income()
    {
        return personal_month_income;
    }

    public void setPersonal_month_income(String obj)
    {
        personal_month_income = obj;
    }

    public String getCredit_limit()
    {
        return credit_limit;
    }

    public void setCredit_limit(String obj)
    {
        credit_limit = obj;
    }

    public String getCredit_purpose()
    {
        return credit_purpose;
    }

    public void setCredit_purpose(String obj)
    {
        credit_purpose = obj;
    }

    public String getFunding_gap()
    {
        return funding_gap;
    }

    public void setFunding_gap(String obj)
    {
        funding_gap = obj;
    }

    public String getComp_industry()
    {
        return comp_industry;
    }

    public void setComp_industry(String obj)
    {
        comp_industry = obj;
    }

    public String getWork_unit_full_name()
    {
        return work_unit_full_name;
    }

    public void setWork_unit_full_name(String obj)
    {
        work_unit_full_name = obj;
    }

    public String getCompany_adress()
    {
        return company_adress;
    }

    public void setCompany_adress(String obj)
    {
        company_adress = obj;
    }

    public java.sql.Date getFound_date()
    {
        return found_date;
    }

    public void setFound_date(java.sql.Date obj)
    {
        found_date = obj;
    }

    public String getFound_date_str()
    {
        return found_date_str;
    }

    public void setFound_date_str(String obj)
    {
        found_date_str = obj;
    }

    public String getEmployees_number()
    {
        return employees_number;
    }

    public void setEmployees_number(String obj)
    {
        employees_number = obj;
    }

    public String getUnit_tel()
    {
        return unit_tel;
    }

    public void setUnit_tel(String obj)
    {
        unit_tel = obj;
    }

    public String getAnnual_income()
    {
        return annual_income;
    }

    public void setAnnual_income(String obj)
    {
        annual_income = obj;
    }

    public String getProfit_margin()
    {
        return profit_margin;
    }

    public void setProfit_margin(String obj)
    {
        profit_margin = obj;
    }

    public String getLiquidity()
    {
        return liquidity;
    }

    public void setLiquidity(String obj)
    {
        liquidity = obj;
    }

    public String getFixed_money()
    {
        return fixed_money;
    }

    public void setFixed_money(String obj)
    {
        fixed_money = obj;
    }

    public String getOriginal_loan_company()
    {
        return original_loan_company;
    }

    public void setOriginal_loan_company(String obj)
    {
        original_loan_company = obj;
    }

    public String getUnderstand_company()
    {
        return understand_company;
    }

    public void setUnderstand_company(String obj)
    {
        understand_company = obj;
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

    public String getEnable_flag()
    {
        return enable_flag;
    }

    public void setEnable_flag(String obj)
    {
        enable_flag = obj;
    }

    public String getCheck_point_audit_opinion()
    {
        return check_point_audit_opinion;
    }

    public void setCheck_point_audit_opinion(String obj)
    {
        check_point_audit_opinion = obj;
    }

    public String getScope_of_business()
    {
        return scope_of_business;
    }

    public void setScope_of_business(String obj)
    {
        scope_of_business = obj;
    }

    public String getAnnual_income_this_year()
    {
        return annual_income_this_year;
    }

    public void setAnnual_income_this_year(String obj)
    {
        annual_income_this_year = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_inspection_main_id", this.wms_cre_rev_inspection_main_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("customer_name", this.customer_name);
        paramMap.put("gender", this.gender);
        paramMap.put("birthday", this.birthday);
        paramMap.put("birthday_str", this.birthday_str);
        paramMap.put("degree", this.degree);
        paramMap.put("telephone", this.telephone);
        paramMap.put("service_password", this.service_password);
        paramMap.put("address", this.address);
        paramMap.put("id_card", this.id_card);
        paramMap.put("has_house", this.has_house);
        paramMap.put("personal_month_income", this.personal_month_income);
        paramMap.put("credit_limit", this.credit_limit);
        paramMap.put("credit_purpose", this.credit_purpose);
        paramMap.put("funding_gap", this.funding_gap);
        paramMap.put("comp_industry", this.comp_industry);
        paramMap.put("work_unit_full_name", this.work_unit_full_name);
        paramMap.put("company_adress", this.company_adress);
        paramMap.put("found_date", this.found_date);
        paramMap.put("found_date_str", this.found_date_str);
        paramMap.put("employees_number", this.employees_number);
        paramMap.put("unit_tel", this.unit_tel);
        paramMap.put("annual_income", this.annual_income);
        paramMap.put("profit_margin", this.profit_margin);
        paramMap.put("liquidity", this.liquidity);
        paramMap.put("fixed_money", this.fixed_money);
        paramMap.put("original_loan_company", this.original_loan_company);
        paramMap.put("understand_company", this.understand_company);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("check_point_audit_opinion", this.check_point_audit_opinion);
        paramMap.put("scope_of_business", this.scope_of_business);
        paramMap.put("annual_income_this_year", this.annual_income_this_year);
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