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

public class WmsCreRevInfoContact implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_info_contact_id;

    private Integer wms_cre_credit_head_id;

    private Integer wms_cre_credit_line_customer_change_head_id;

    private Integer wms_cre_customer_change_line_contact_id;

    private String is_phone_short;

    private String contact_mobile_phone_short;

    private Integer phone1_1;

    private Integer phone1_2;

    private Integer phone1_3;

    private Integer phone2_1;

    private Integer phone2_2;

    private Integer phone2_3;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_info_contact_id" };

    private static String[] columnNameArr = { "wms_cre_rev_info_contact_id", "wms_cre_credit_head_id",
                                             "wms_cre_credit_line_customer_change_head_id",
                                             "wms_cre_customer_change_line_contact_id", "is_phone_short",
                                             "contact_mobile_phone_short", "phone1_1", "phone1_2", "phone1_3",
                                             "phone2_1", "phone2_2", "phone2_3", "isExcludePKFlag" };

    public Integer getWms_cre_rev_info_contact_id()
    {
        return wms_cre_rev_info_contact_id;
    }

    public void setWms_cre_rev_info_contact_id(Integer obj)
    {
        wms_cre_rev_info_contact_id = obj;
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

    public String getIs_phone_short()
    {
        return is_phone_short;
    }

    public void setIs_phone_short(String obj)
    {
        is_phone_short = obj;
    }

    public String getContact_mobile_phone_short()
    {
        return contact_mobile_phone_short;
    }

    public void setContact_mobile_phone_short(String obj)
    {
        contact_mobile_phone_short = obj;
    }

    public Integer getPhone1_1()
    {
        return phone1_1;
    }

    public void setPhone1_1(Integer obj)
    {
        phone1_1 = obj;
    }

    public Integer getPhone1_2()
    {
        return phone1_2;
    }

    public void setPhone1_2(Integer obj)
    {
        phone1_2 = obj;
    }

    public Integer getPhone1_3()
    {
        return phone1_3;
    }

    public void setPhone1_3(Integer obj)
    {
        phone1_3 = obj;
    }

    public Integer getPhone2_1()
    {
        return phone2_1;
    }

    public void setPhone2_1(Integer obj)
    {
        phone2_1 = obj;
    }

    public Integer getPhone2_2()
    {
        return phone2_2;
    }

    public void setPhone2_2(Integer obj)
    {
        phone2_2 = obj;
    }

    public Integer getPhone2_3()
    {
        return phone2_3;
    }

    public void setPhone2_3(Integer obj)
    {
        phone2_3 = obj;
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
        paramMap.put("wms_cre_rev_info_contact_id", this.wms_cre_rev_info_contact_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("wms_cre_credit_line_customer_change_head_id", this.wms_cre_credit_line_customer_change_head_id);
        paramMap.put("wms_cre_customer_change_line_contact_id", this.wms_cre_customer_change_line_contact_id);
        paramMap.put("is_phone_short", this.is_phone_short);
        paramMap.put("contact_mobile_phone_short", this.contact_mobile_phone_short);
        paramMap.put("phone1_1", this.phone1_1);
        paramMap.put("phone1_2", this.phone1_2);
        paramMap.put("phone1_3", this.phone1_3);
        paramMap.put("phone2_1", this.phone2_1);
        paramMap.put("phone2_2", this.phone2_2);
        paramMap.put("phone2_3", this.phone2_3);
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