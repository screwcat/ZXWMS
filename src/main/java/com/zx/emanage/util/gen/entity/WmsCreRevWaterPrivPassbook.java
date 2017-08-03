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

public class WmsCreRevWaterPrivPassbook implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_water_priv_passbook_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private String psbook_name;

    private String bank_name;

    private String is_check;

    private String account_balance;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_water_priv_passbook_id" };

    private static String[] columnNameArr = { "wms_cre_rev_water_priv_passbook_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id", "psbook_name", "bank_name",
                                             "is_check", "account_balance" };

    public Integer getWms_cre_rev_water_priv_passbook_id()
    {
        return wms_cre_rev_water_priv_passbook_id;
    }

    public void setWms_cre_rev_water_priv_passbook_id(Integer obj)
    {
        wms_cre_rev_water_priv_passbook_id = obj;
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

    public String getPsbook_name()
    {
        return psbook_name;
    }

    public void setPsbook_name(String obj)
    {
        psbook_name = obj;
    }

    public String getBank_name()
    {
        return bank_name;
    }

    public void setBank_name(String obj)
    {
        bank_name = obj;
    }

    public String getIs_check()
    {
        return is_check;
    }

    public void setIs_check(String obj)
    {
        is_check = obj;
    }

    public String getAccount_balance()
    {
        return account_balance;
    }

    public void setAccount_balance(String obj)
    {
        account_balance = obj;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_rev_water_priv_passbook_id", this.wms_cre_rev_water_priv_passbook_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("psbook_name", this.psbook_name);
        paramMap.put("bank_name", this.bank_name);
        paramMap.put("is_check", this.is_check);
        paramMap.put("account_balance", this.account_balance);
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