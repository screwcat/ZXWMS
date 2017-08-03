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
 * 信贷-信息审批-法院网执行情况
 * 
 * @version 2.0
 * @author hancd
 */

public class WmsCreRevInfoCourtCase implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_court_case_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String execute_status;

    private java.sql.Date accreditation_date;

    private String accreditation_date_str;

    private String execute_target;

    private String court_case_type;

    private String court_case_remark;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_court_case_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_court_case_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "execute_status",
                                             "accreditation_date", "accreditation_date_str", "execute_target",
                                             "court_case_type", "court_case_remark" };

    public Integer getWms_cre_rev_info_court_case_id()
    {
        return wms_cre_rev_info_court_case_id;
    }

    public void setWms_cre_rev_info_court_case_id(Integer obj)
    {
        wms_cre_rev_info_court_case_id = obj;
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

    public String getExecute_status()
    {
        return execute_status;
    }

    public void setExecute_status(String obj)
    {
        execute_status = obj;
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

    public String getExecute_target()
    {
        return execute_target;
    }

    public void setExecute_target(String obj)
    {
        execute_target = obj;
    }

    public String getCourt_case_type()
    {
        return court_case_type;
    }

    public void setCourt_case_type(String obj)
    {
        court_case_type = obj;
    }

    public String getCourt_case_remark()
    {
        return court_case_remark;
    }

    public void setCourt_case_remark(String obj)
    {
        court_case_remark = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_info_court_case_id", this.wms_cre_rev_info_court_case_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("execute_status", this.execute_status);
        paramMap.put("accreditation_date", this.accreditation_date);
        paramMap.put("accreditation_date_str", this.accreditation_date_str);
        paramMap.put("execute_target", this.execute_target);
        paramMap.put("court_case_type", this.court_case_type);
        paramMap.put("court_case_remark", this.court_case_remark);
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