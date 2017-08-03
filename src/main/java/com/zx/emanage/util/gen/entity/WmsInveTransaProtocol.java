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

public class WmsInveTransaProtocol implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_transa_protocol_id;

    private Integer wms_inve_transa_id;

    private Integer wms_inve_transa_prod_id;

    private String prot_code;

    private String a_name;

    private String a_id_card;

    private java.math.BigDecimal product_account;

    private java.sql.Date date_of_payment;

    private String date_of_payment_str;

    private String a_bank;

    private String a_number;

    private String b_name;

    private String b_id_card;

    private String category_name;

    private Integer product_deadline;

    private String sign_place;

    private String b_data;

    private java.sql.Date end_of_date;

    private String end_of_date_str;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String contact_address;

    private String post_code;

    private Integer wms_inve_redeem_id;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_transa_protocol_id" };

    private static String[] columnNameArr = { "wms_inve_transa_protocol_id", "wms_inve_transa_id",
                                             "wms_inve_transa_prod_id", "prot_code", "a_name", "a_id_card",
                                             "product_account", "date_of_payment", "date_of_payment_str", "a_bank",
                                             "a_number", "b_name", "b_id_card", "category_name", "product_deadline",
                                             "sign_place", "b_data", "end_of_date", "end_of_date_str",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "contact_address",
                                             "post_code", "wms_inve_redeem_id", "isExcludePKFlag" };

    public Integer getWms_inve_transa_protocol_id()
    {
        return wms_inve_transa_protocol_id;
    }

    public void setWms_inve_transa_protocol_id(Integer obj)
    {
        wms_inve_transa_protocol_id = obj;
    }

    public Integer getWms_inve_transa_id()
    {
        return wms_inve_transa_id;
    }

    public void setWms_inve_transa_id(Integer obj)
    {
        wms_inve_transa_id = obj;
    }

    public Integer getWms_inve_transa_prod_id()
    {
        return wms_inve_transa_prod_id;
    }

    public void setWms_inve_transa_prod_id(Integer obj)
    {
        wms_inve_transa_prod_id = obj;
    }

    public String getProt_code()
    {
        return prot_code;
    }

    public void setProt_code(String obj)
    {
        prot_code = obj;
    }

    public String getA_name()
    {
        return a_name;
    }

    public void setA_name(String obj)
    {
        a_name = obj;
    }

    public String getA_id_card()
    {
        return a_id_card;
    }

    public void setA_id_card(String obj)
    {
        a_id_card = obj;
    }

    public java.math.BigDecimal getProduct_account()
    {
        return product_account;
    }

    public void setProduct_account(java.math.BigDecimal obj)
    {
        product_account = obj;
    }

    public java.sql.Date getDate_of_payment()
    {
        return date_of_payment;
    }

    public void setDate_of_payment(java.sql.Date obj)
    {
        date_of_payment = obj;
    }

    public String getDate_of_payment_str()
    {
        return date_of_payment_str;
    }

    public void setDate_of_payment_str(String obj)
    {
        date_of_payment_str = obj;
    }

    public String getA_bank()
    {
        return a_bank;
    }

    public void setA_bank(String obj)
    {
        a_bank = obj;
    }

    public String getA_number()
    {
        return a_number;
    }

    public void setA_number(String obj)
    {
        a_number = obj;
    }

    public String getB_name()
    {
        return b_name;
    }

    public void setB_name(String obj)
    {
        b_name = obj;
    }

    public String getB_id_card()
    {
        return b_id_card;
    }

    public void setB_id_card(String obj)
    {
        b_id_card = obj;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String obj)
    {
        category_name = obj;
    }

    public Integer getProduct_deadline()
    {
        return product_deadline;
    }

    public void setProduct_deadline(Integer obj)
    {
        product_deadline = obj;
    }

    public String getSign_place()
    {
        return sign_place;
    }

    public void setSign_place(String obj)
    {
        sign_place = obj;
    }

    public String getB_data()
    {
        return b_data;
    }

    public void setB_data(String obj)
    {
        b_data = obj;
    }

    public java.sql.Date getEnd_of_date()
    {
        return end_of_date;
    }

    public void setEnd_of_date(java.sql.Date obj)
    {
        end_of_date = obj;
    }

    public String getEnd_of_date_str()
    {
        return end_of_date_str;
    }

    public void setEnd_of_date_str(String obj)
    {
        end_of_date_str = obj;
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

    public String getContact_address()
    {
        return contact_address;
    }

    public void setContact_address(String obj)
    {
        contact_address = obj;
    }

    public String getPost_code()
    {
        return post_code;
    }

    public void setPost_code(String obj)
    {
        post_code = obj;
    }

    public Integer getWms_inve_redeem_id()
    {
        return wms_inve_redeem_id;
    }

    public void setWms_inve_redeem_id(Integer obj)
    {
        wms_inve_redeem_id = obj;
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
        paramMap.put("wms_inve_transa_protocol_id", this.wms_inve_transa_protocol_id);
        paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
        paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
        paramMap.put("prot_code", this.prot_code);
        paramMap.put("a_name", this.a_name);
        paramMap.put("a_id_card", this.a_id_card);
        paramMap.put("product_account", this.product_account);
        paramMap.put("date_of_payment", this.date_of_payment);
        paramMap.put("date_of_payment_str", this.date_of_payment_str);
        paramMap.put("a_bank", this.a_bank);
        paramMap.put("a_number", this.a_number);
        paramMap.put("b_name", this.b_name);
        paramMap.put("b_id_card", this.b_id_card);
        paramMap.put("category_name", this.category_name);
        paramMap.put("product_deadline", this.product_deadline);
        paramMap.put("sign_place", this.sign_place);
        paramMap.put("b_data", this.b_data);
        paramMap.put("end_of_date", this.end_of_date);
        paramMap.put("end_of_date_str", this.end_of_date_str);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("contact_address", this.contact_address);
        paramMap.put("post_code", this.post_code);
        paramMap.put("wms_inve_redeem_id", this.wms_inve_redeem_id);
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