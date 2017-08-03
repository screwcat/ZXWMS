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

public class WmsCreRevWaterMain implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_main_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String warter_type;

    private String comp_type;

    private String is_standard;

    private String warter_copies;

    private String pri_warter_copies;

    private String has_salary;

    private String has_passbook;

    private String repay_copies;

    private String evaluation;

    private String liabilities;

    private String remark;

    private String is_credit_rating_overdue;

    private String is_need_supple;

    private String supple_date;

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
    private static String[] pkColArr = { "wms_cre_rev_water_main_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_main_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "warter_type", "comp_type",
                                             "is_standard", "warter_copies", "pri_warter_copies", "has_salary",
                                             "has_passbook", "repay_copies", "evaluation", "liabilities", "remark",
                                             "is_credit_rating_overdue", "is_need_supple", "supple_date",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_rev_water_main_id()
    {
        return wms_cre_rev_water_main_id;
    }

    public void setWms_cre_rev_water_main_id(Integer obj)
    {
        wms_cre_rev_water_main_id = obj;
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

    public String getWarter_type()
    {
        return warter_type;
    }

    public void setWarter_type(String obj)
    {
        warter_type = obj;
    }

    public String getComp_type()
    {
        return comp_type;
    }

    public void setComp_type(String obj)
    {
        comp_type = obj;
    }

    public String getIs_standard()
    {
        return is_standard;
    }

    public void setIs_standard(String obj)
    {
        is_standard = obj;
    }

    public String getWarter_copies()
    {
        return warter_copies;
    }

    public void setWarter_copies(String obj)
    {
        warter_copies = obj;
    }

    public String getPri_warter_copies()
    {
        return pri_warter_copies;
    }

    public void setPri_warter_copies(String obj)
    {
        pri_warter_copies = obj;
    }

    public String getHas_salary()
    {
        return has_salary;
    }

    public void setHas_salary(String obj)
    {
        has_salary = obj;
    }

    public String getHas_passbook()
    {
        return has_passbook;
    }

    public void setHas_passbook(String obj)
    {
        has_passbook = obj;
    }

    public String getRepay_copies()
    {
        return repay_copies;
    }

    public void setRepay_copies(String obj)
    {
        repay_copies = obj;
    }

    public String getEvaluation()
    {
        return evaluation;
    }

    public void setEvaluation(String obj)
    {
        evaluation = obj;
    }

    public String getLiabilities()
    {
        return liabilities;
    }

    public void setLiabilities(String obj)
    {
        liabilities = obj;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String obj)
    {
        remark = obj;
    }

    public String getIs_credit_rating_overdue()
    {
        return is_credit_rating_overdue;
    }

    public void setIs_credit_rating_overdue(String obj)
    {
        is_credit_rating_overdue = obj;
    }

    public String getIs_need_supple()
    {
        return is_need_supple;
    }

    public void setIs_need_supple(String obj)
    {
        is_need_supple = obj;
    }

    public String getSupple_date()
    {
        return supple_date;
    }

    public void setSupple_date(String obj)
    {
        supple_date = obj;
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
        paramMap.put("wms_cre_rev_water_main_id", this.wms_cre_rev_water_main_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("warter_type", this.warter_type);
        paramMap.put("comp_type", this.comp_type);
        paramMap.put("is_standard", this.is_standard);
        paramMap.put("warter_copies", this.warter_copies);
        paramMap.put("pri_warter_copies", this.pri_warter_copies);
        paramMap.put("has_salary", this.has_salary);
        paramMap.put("has_passbook", this.has_passbook);
        paramMap.put("repay_copies", this.repay_copies);
        paramMap.put("evaluation", this.evaluation);
        paramMap.put("liabilities", this.liabilities);
        paramMap.put("remark", this.remark);
        paramMap.put("is_credit_rating_overdue", this.is_credit_rating_overdue);
        paramMap.put("is_need_supple", this.is_need_supple);
        paramMap.put("supple_date", this.supple_date);
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