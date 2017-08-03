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

public class WmsCreCreditLineBankStream implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_bank_stream_id;

    private String bank_stream_type;

    private String account_name;

    private String account;

    private String belongs_bank;

    private String proposer_relation;

    private java.sql.Date start_date;

    private String start_date_str;

    private java.sql.Date end_date;

    private String end_date_str;

    private String detail_condition;

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
    private static String[] pkColArr = { "wms_cre_credit_line_bank_stream_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_bank_stream_id", "bank_stream_type", "account_name",
                                             "account", "belongs_bank", "proposer_relation", "start_date",
                                             "start_date_str", "end_date", "end_date_str", "detail_condition",
                                             "wms_cre_credit_head_id", "create_user_id", "create_user_name",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_bank_stream_id()
    {
        return wms_cre_credit_line_bank_stream_id;
    }

    public void setWms_cre_credit_line_bank_stream_id(Integer obj)
    {
        wms_cre_credit_line_bank_stream_id = obj;
    }

    public String getBank_stream_type()
    {
        return bank_stream_type;
    }

    public void setBank_stream_type(String obj)
    {
        bank_stream_type = obj;
    }

    public String getAccount_name()
    {
        return account_name;
    }

    public void setAccount_name(String obj)
    {
        account_name = obj;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String obj)
    {
        account = obj;
    }

    public String getBelongs_bank()
    {
        return belongs_bank;
    }

    public void setBelongs_bank(String obj)
    {
        belongs_bank = obj;
    }

    public String getProposer_relation()
    {
        return proposer_relation;
    }

    public void setProposer_relation(String obj)
    {
        proposer_relation = obj;
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

    public java.sql.Date getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(java.sql.Date obj)
    {
        end_date = obj;
    }

    public String getEnd_dateStr()
    {
        return end_date_str;
    }

    public void setEnd_dateStr(String obj)
    {
        end_date_str = obj;
    }

    public String getDetail_condition()
    {
        return detail_condition;
    }

    public void setDetail_condition(String obj)
    {
        detail_condition = obj;
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
        paramMap.put("wms_cre_credit_line_bank_stream_id", this.wms_cre_credit_line_bank_stream_id);
        paramMap.put("bank_stream_type", this.bank_stream_type);
        paramMap.put("account_name", this.account_name);
        paramMap.put("account", this.account);
        paramMap.put("belongs_bank", this.belongs_bank);
        paramMap.put("proposer_relation", this.proposer_relation);
        paramMap.put("start_date", this.start_date);
        paramMap.put("start_date_str", this.start_date_str);
        paramMap.put("end_date", this.end_date);
        paramMap.put("end_date_str", this.end_date_str);
        paramMap.put("detail_condition", this.detail_condition);
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