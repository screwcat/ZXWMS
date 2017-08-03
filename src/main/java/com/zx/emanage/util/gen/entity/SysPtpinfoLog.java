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

public class SysPtpinfoLog implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer sys_ptpinfo_log_id;

    private Integer wms_cre_appro_id;

    private String protocol_id_year_num;

    private String cre_type;

    private String payment_contract_type;

    private String credit_name;

    private String credit_id_card;

    private java.math.BigDecimal matching_creditor;

    private java.sql.Date repay_begin_date;

    private String repay_begin_date_str;

    private java.sql.Date repay_end_date;

    private String repay_end_date_str;

    private java.math.BigDecimal principal_lower;

    private Integer wms_cre_credit_head_id;

    private Integer wms_fina_cre_pay_id;

    private java.math.BigDecimal act_creditor;

    private java.sql.Date act_creditor_date;

    private String act_creditor_date_str;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "sys_ptpinfo_log_id" };

    private static String[] columnNameArr = { "sys_ptpinfo_log_id", "wms_cre_appro_id", "protocol_id_year_num",
                                             "cre_type", "payment_contract_type", "credit_name", "credit_id_card",
                                             "matching_creditor", "repay_begin_date", "repay_begin_date_str",
                                             "repay_end_date", "repay_end_date_str", "principal_lower",
                                             "wms_cre_credit_head_id", "wms_fina_cre_pay_id", "act_creditor",
                                             "act_creditor_date", "act_creditor_date_str", "isExcludePKFlag" };

    public Integer getSys_ptpinfo_log_id()
    {
        return sys_ptpinfo_log_id;
    }

    public void setSys_ptpinfo_log_id(Integer obj)
    {
        sys_ptpinfo_log_id = obj;
    }

    public Integer getWms_cre_appro_id()
    {
        return wms_cre_appro_id;
    }

    public void setWms_cre_appro_id(Integer obj)
    {
        wms_cre_appro_id = obj;
    }

    public String getProtocol_id_year_num()
    {
        return protocol_id_year_num;
    }

    public void setProtocol_id_year_num(String obj)
    {
        protocol_id_year_num = obj;
    }

    public String getCre_type()
    {
        return cre_type;
    }

    public void setCre_type(String obj)
    {
        cre_type = obj;
    }

    public String getPayment_contract_type()
    {
        return payment_contract_type;
    }

    public void setPayment_contract_type(String obj)
    {
        payment_contract_type = obj;
    }

    public String getCredit_name()
    {
        return credit_name;
    }

    public void setCredit_name(String obj)
    {
        credit_name = obj;
    }

    public String getCredit_id_card()
    {
        return credit_id_card;
    }

    public void setCredit_id_card(String obj)
    {
        credit_id_card = obj;
    }

    public java.math.BigDecimal getMatching_creditor()
    {
        return matching_creditor;
    }

    public void setMatching_creditor(java.math.BigDecimal obj)
    {
        matching_creditor = obj;
    }

    public java.sql.Date getRepay_begin_date()
    {
        return repay_begin_date;
    }

    public void setRepay_begin_date(java.sql.Date obj)
    {
        repay_begin_date = obj;
    }

    public String getRepay_begin_date_str()
    {
        return repay_begin_date_str;
    }

    public void setRepay_begin_date_str(String obj)
    {
        repay_begin_date_str = obj;
    }

    public java.sql.Date getRepay_end_date()
    {
        return repay_end_date;
    }

    public void setRepay_end_date(java.sql.Date obj)
    {
        repay_end_date = obj;
    }

    public String getRepay_end_date_str()
    {
        return repay_end_date_str;
    }

    public void setRepay_end_date_str(String obj)
    {
        repay_end_date_str = obj;
    }

    public java.math.BigDecimal getPrincipal_lower()
    {
        return principal_lower;
    }

    public void setPrincipal_lower(java.math.BigDecimal obj)
    {
        principal_lower = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getWms_fina_cre_pay_id()
    {
        return wms_fina_cre_pay_id;
    }

    public void setWms_fina_cre_pay_id(Integer obj)
    {
        wms_fina_cre_pay_id = obj;
    }

    public java.math.BigDecimal getAct_creditor()
    {
        return act_creditor;
    }

    public void setAct_creditor(java.math.BigDecimal obj)
    {
        act_creditor = obj;
    }

    public java.sql.Date getAct_creditor_date()
    {
        return act_creditor_date;
    }

    public void setAct_creditor_date(java.sql.Date obj)
    {
        act_creditor_date = obj;
    }

    public String getAct_creditor_date_str()
    {
        return act_creditor_date_str;
    }

    public void setAct_creditor_date_str(String obj)
    {
        act_creditor_date_str = obj;
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
        paramMap.put("sys_ptpinfo_log_id", this.sys_ptpinfo_log_id);
        paramMap.put("wms_cre_appro_id", this.wms_cre_appro_id);
        paramMap.put("protocol_id_year_num", this.protocol_id_year_num);
        paramMap.put("cre_type", this.cre_type);
        paramMap.put("payment_contract_type", this.payment_contract_type);
        paramMap.put("credit_name", this.credit_name);
        paramMap.put("credit_id_card", this.credit_id_card);
        paramMap.put("matching_creditor", this.matching_creditor);
        paramMap.put("repay_begin_date", this.repay_begin_date);
        paramMap.put("repay_begin_date_str", this.repay_begin_date_str);
        paramMap.put("repay_end_date", this.repay_end_date);
        paramMap.put("repay_end_date_str", this.repay_end_date_str);
        paramMap.put("principal_lower", this.principal_lower);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_fina_cre_pay_id", this.wms_fina_cre_pay_id);
        paramMap.put("act_creditor", this.act_creditor);
        paramMap.put("act_creditor_date", this.act_creditor_date);
        paramMap.put("act_creditor_date_str", this.act_creditor_date_str);
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