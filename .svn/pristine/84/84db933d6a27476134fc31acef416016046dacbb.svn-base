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

/**
 * 信贷-信息审批-个人信息-犯罪记录
 * 
 * @version 2.0
 * @author hancd
 */

public class WmsCreRevInfoCriminal implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_criminal_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String case_type;

    private java.sql.Date accreditation_date;

    private String accreditation_date_str;

    private java.sql.Date close_date;

    private String close_date_str;

    private String executive_area;

    private String executive_way;

    private String criminal_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_criminal_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_criminal_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "case_type",
                                             "accreditation_date", "accreditation_date_str", "close_date",
                                             "close_date_str", "executive_area", "executive_way", "criminal_remark" };

    public Integer getWms_cre_rev_info_criminal_id()
    {
        return wms_cre_rev_info_criminal_id;
    }

    public void setWms_cre_rev_info_criminal_id(Integer obj)
    {
        wms_cre_rev_info_criminal_id = obj;
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

    public String getCase_type()
    {
        return case_type;
    }

    public void setCase_type(String obj)
    {
        case_type = obj;
    }

    public java.sql.Date getAccreditation_date()
    {
        return accreditation_date;
    }

    public void setAccreditation_date(java.sql.Date obj)
    {
        accreditation_date = obj;
    }

    public String getAccreditation_dateStr()
    {
        return accreditation_date_str;
    }

    public void setAccreditation_dateStr(String obj)
    {
        accreditation_date_str = obj;
    }

    public java.sql.Date getClose_date()
    {
        return close_date;
    }

    public void setClose_date(java.sql.Date obj)
    {
        close_date = obj;
    }

    public String getClose_dateStr()
    {
        return close_date_str;
    }

    public void setClose_dateStr(String obj)
    {
        close_date_str = obj;
    }

    public String getExecutive_area()
    {
        return executive_area;
    }

    public void setExecutive_area(String obj)
    {
        executive_area = obj;
    }

    public String getExecutive_way()
    {
        return executive_way;
    }

    public void setExecutive_way(String obj)
    {
        executive_way = obj;
    }

    public String getCriminal_remark()
    {
        return criminal_remark;
    }

    public void setCriminal_remark(String obj)
    {
        criminal_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_info_criminal_id", this.wms_cre_rev_info_criminal_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("case_type", this.case_type);
        paramMap.put("accreditation_date", this.accreditation_date);
        paramMap.put("accreditation_date_str", this.accreditation_date_str);
        paramMap.put("close_date", this.close_date);
        paramMap.put("close_date_str", this.close_date_str);
        paramMap.put("executive_area", this.executive_area);
        paramMap.put("executive_way", this.executive_way);
        paramMap.put("criminal_remark", this.criminal_remark);
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