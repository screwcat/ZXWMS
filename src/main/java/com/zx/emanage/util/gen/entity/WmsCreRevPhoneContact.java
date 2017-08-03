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

public class WmsCreRevPhoneContact implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_phone_contact_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer wms_cre_customer_change_line_contact_id;

    private String contact_name;

    private String contact_relation_type;

    private Integer is_authenticity;

    private String evalu;

    private String is_coordination;

    private String contact_quality;

    private String family_attitude;

    private Integer contact_relation_detail;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_phone_contact_id" };

    private static String[] columnNameArr = { "wms_cre_rev_phone_contact_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id",
                                             "wms_cre_customer_change_line_contact_id", "contact_name",
                                             "contact_relation_type", "is_authenticity", "evalu", "is_coordination",
                                             "contact_quality", "family_attitude", "contact_relation_detail",
                                             "isExcludePKFlag" };

    public Integer getWms_cre_rev_phone_contact_id()
    {
        return wms_cre_rev_phone_contact_id;
    }

    public void setWms_cre_rev_phone_contact_id(Integer obj)
    {
        wms_cre_rev_phone_contact_id = obj;
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

    public Integer getWms_cre_customer_change_line_contact_id()
    {
        return wms_cre_customer_change_line_contact_id;
    }

    public void setWms_cre_customer_change_line_contact_id(Integer obj)
    {
        wms_cre_customer_change_line_contact_id = obj;
    }

    public String getContact_name()
    {
        return contact_name;
    }

    public void setContact_name(String obj)
    {
        contact_name = obj;
    }

    public String getContact_relation_type()
    {
        return contact_relation_type;
    }

    public void setContact_relation_type(String obj)
    {
        contact_relation_type = obj;
    }

    public Integer getIs_authenticity()
    {
        return is_authenticity;
    }

    public void setIs_authenticity(Integer obj)
    {
        is_authenticity = obj;
    }

    public String getEvalu()
    {
        return evalu;
    }

    public void setEvalu(String obj)
    {
        evalu = obj;
    }

    public String getIs_coordination()
    {
        return is_coordination;
    }

    public void setIs_coordination(String obj)
    {
        is_coordination = obj;
    }

    public String getContact_quality()
    {
        return contact_quality;
    }

    public void setContact_quality(String obj)
    {
        contact_quality = obj;
    }

    public String getFamily_attitude()
    {
        return family_attitude;
    }

    public void setFamily_attitude(String obj)
    {
        family_attitude = obj;
    }

    public Integer getContact_relation_detail()
    {
        return contact_relation_detail;
    }

    public void setContact_relation_detail(Integer obj)
    {
        contact_relation_detail = obj;
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
        paramMap.put("wms_cre_rev_phone_contact_id", this.wms_cre_rev_phone_contact_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("wms_cre_customer_change_line_contact_id", this.wms_cre_customer_change_line_contact_id);
        paramMap.put("contact_name", this.contact_name);
        paramMap.put("contact_relation_type", this.contact_relation_type);
        paramMap.put("is_authenticity", this.is_authenticity);
        paramMap.put("evalu", this.evalu);
        paramMap.put("is_coordination", this.is_coordination);
        paramMap.put("contact_quality", this.contact_quality);
        paramMap.put("family_attitude", this.family_attitude);
        paramMap.put("contact_relation_detail", this.contact_relation_detail);
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