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

public class WmsCreCreditLinePersonCredit implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_person_credit_id;

    private String person_credit_type;

    private String name;

    private Integer credit_money;

    private String time_limit;

    private java.sql.Date start_date;

    private String start_date_str;

    private Double repayment_per_month;

    private Integer loan_type;

    private String is_mortgage;

    private String is_contract;

    private String is_stream;

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
    private static String[] pkColArr = { "wms_cre_credit_line_person_credit_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_person_credit_id", "person_credit_type", "name",
                                             "credit_money", "time_limit", "start_date", "start_date_str",
                                             "repayment_per_month", "loan_type", "is_mortgage", "is_contract",
                                             "is_stream", "wms_cre_credit_head_id", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_person_credit_id()
    {
        return wms_cre_credit_line_person_credit_id;
    }

    public void setWms_cre_credit_line_person_credit_id(Integer obj)
    {
        wms_cre_credit_line_person_credit_id = obj;
    }

    public String getPerson_credit_type()
    {
        return person_credit_type;
    }

    public void setPerson_credit_type(String obj)
    {
        person_credit_type = obj;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String obj)
    {
        name = obj;
    }

    public Integer getCredit_money()
    {
        return credit_money;
    }

    public void setCredit_money(Integer obj)
    {
        credit_money = obj;
    }

    public String getTime_limit()
    {
        return time_limit;
    }

    public void setTime_limit(String obj)
    {
        time_limit = obj;
    }

    public java.sql.Date getStart_date()
    {
        return start_date;
    }

    public void setStart_date(java.sql.Date obj)
    {
        start_date = obj;
    }

    public String getStart_dateStr()
    {
        return start_date_str;
    }

    public void setStart_dateStr(String obj)
    {
        start_date_str = obj;
    }

    public Double getRepayment_per_month()
    {
        return repayment_per_month;
    }

    public void setRepayment_per_month(Double obj)
    {
        repayment_per_month = obj;
    }

    public Integer getLoan_type()
    {
        return loan_type;
    }

    public void setLoan_type(Integer obj)
    {
        loan_type = obj;
    }

    public String getIs_mortgage()
    {
        return is_mortgage;
    }

    public void setIs_mortgage(String obj)
    {
        is_mortgage = obj;
    }

    public String getIs_contract()
    {
        return is_contract;
    }

    public void setIs_contract(String obj)
    {
        is_contract = obj;
    }

    public String getIs_stream()
    {
        return is_stream;
    }

    public void setIs_stream(String obj)
    {
        is_stream = obj;
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
        paramMap.put("wms_cre_credit_line_person_credit_id", this.wms_cre_credit_line_person_credit_id);
        paramMap.put("person_credit_type", this.person_credit_type);
        paramMap.put("name", this.name);
        paramMap.put("credit_money", this.credit_money);
        paramMap.put("time_limit", this.time_limit);
        paramMap.put("start_date", this.start_date);
        paramMap.put("start_date_str", this.start_date_str);
        paramMap.put("repayment_per_month", this.repayment_per_month);
        paramMap.put("loan_type", this.loan_type);
        paramMap.put("is_mortgage", this.is_mortgage);
        paramMap.put("is_contract", this.is_contract);
        paramMap.put("is_stream", this.is_stream);
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