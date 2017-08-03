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

public class WmsInveTransaMatchBackups implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_match;

    private Integer wms_inve_transa_prod_id;

    private Integer wms_fina_cre_repay_id;

    private String credit_name;

    private String credit_id_card;

    private java.math.BigDecimal assign_account;

    private java.sql.Date date_of_assign;

    private String date_of_assign_str;

    private java.sql.Date repay_begin_date;

    private String repay_begin_date_str;

    private java.sql.Date repay_end_date;

    private String repay_end_date_str;

    private java.math.BigDecimal product_interest_month;

    private Integer create_user_id;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private Integer wms_inve_redeem_id;
    
    private String protocol_code;
    
    private Integer wms_inve_transa_protocol_id;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_match" };

    private static String[] columnNameArr = { "wms_inve_transa_match", "wms_inve_transa_prod_id",
                                             "wms_fina_cre_repay_id", "credit_name", "credit_id_card",
                                             "assign_account", "date_of_assign", "date_of_assign_str",
                                             "repay_begin_date", "repay_begin_date_str", "repay_end_date",
                                             "repay_end_date_str", "product_interest_month", "create_user_id",
                                             "create_timestamp", "create_timestamp_str", "last_update_user_id",
                                             "last_update_timestamp", "last_update_timestamp_str", "enable_flag",
                                             "wms_inve_redeem_id","protocol_code","wms_inve_transa_protocol_id", "isExcludePKFlag" };

    public Integer getWms_inve_transa_match()
    {
        return wms_inve_transa_match;
    }

    public void setWms_inve_transa_match(Integer obj)
    {
        wms_inve_transa_match = obj;
    }

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer obj)
    {
        wms_inve_transa_prod_id = obj;
    }

    public Integer getWms_fina_cre_repay_id()
    {
        return wms_fina_cre_repay_id;
    }

    public void setWms_fina_cre_repay_id(Integer obj)
    {
        wms_fina_cre_repay_id = obj;
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

    public java.math.BigDecimal getAssign_account()
    {
        return assign_account;
    }

    public void setAssign_account(java.math.BigDecimal obj)
    {
        assign_account = obj;
    }

    public java.sql.Date getDate_of_assign()
    {
        return date_of_assign;
    }

    public void setDate_of_assign(java.sql.Date obj)
    {
        date_of_assign = obj;
    }

    public String getDate_of_assign_str()
    {
        return date_of_assign_str;
    }

    public void setDate_of_assign_str(String obj)
    {
        date_of_assign_str = obj;
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

    public java.math.BigDecimal getProduct_interest_month()
    {
        return product_interest_month;
    }

    public void setProduct_interest_month(java.math.BigDecimal obj)
    {
        product_interest_month = obj;
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

    public Integer getWms_inve_redeem_id()
    {
        return wms_inve_redeem_id;
    }

    public void setWms_inve_redeem_id(Integer obj)
    {
        wms_inve_redeem_id = obj;
    }
    
    public String getProtocol_code() {
		return protocol_code;
	}

	public void setProtocol_code(String protocol_code) {
		this.protocol_code = protocol_code;
	}

	public Integer getWms_inve_transa_protocol_id() {
		return wms_inve_transa_protocol_id;
	}

	public void setWms_inve_transa_protocol_id(Integer wms_inve_transa_protocol_id) {
		this.wms_inve_transa_protocol_id = wms_inve_transa_protocol_id;
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
        paramMap.put("wms_inve_transa_match", this.wms_inve_transa_match);
        paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
        paramMap.put("wms_fina_cre_repay_id", this.wms_fina_cre_repay_id);
        paramMap.put("credit_name", this.credit_name);
        paramMap.put("credit_id_card", this.credit_id_card);
        paramMap.put("assign_account", this.assign_account);
        paramMap.put("date_of_assign", this.date_of_assign);
        paramMap.put("date_of_assign_str", this.date_of_assign_str);
        paramMap.put("repay_begin_date", this.repay_begin_date);
        paramMap.put("repay_begin_date_str", this.repay_begin_date_str);
        paramMap.put("repay_end_date", this.repay_end_date);
        paramMap.put("repay_end_date_str", this.repay_end_date_str);
        paramMap.put("product_interest_month", this.product_interest_month);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("wms_inve_redeem_id", this.wms_inve_redeem_id);
        paramMap.put("protocol_code", this.protocol_code);
        paramMap.put("wms_inve_transa_protocol_id", this.wms_inve_transa_protocol_id);
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