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

public class WmsCreHousingAssessment implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_housing_assessment_id;

    private Integer wms_cre_credit_head_id;

    private String house_address;

    private String house_address1;

    private java.math.BigDecimal sticker_unit_price;

    private String community_name;

    private String community_name1;

    private java.math.BigDecimal house_building_area;

    private java.math.BigDecimal house_building_area1;

    private java.math.BigDecimal contract_price;

    private java.math.BigDecimal sticker_price;

    private java.math.BigDecimal transaction_price;

    private java.math.BigDecimal tax_amount;

    private java.math.BigDecimal personal_income_tax;

    private java.math.BigDecimal deed_tax;

    private java.math.BigDecimal land_appreciation_tax;

    private java.math.BigDecimal transaction_fee;

    private java.math.BigDecimal basic_price;

    private java.math.BigDecimal total_tax_amount;

    private java.math.BigDecimal six_amount;

    private java.math.BigDecimal nine_amount;

    private java.math.BigDecimal total_amount;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_housing_assessment_id" };

    private static String[] columnNameArr = { "wms_cre_housing_assessment_id", "wms_cre_credit_head_id",
                                             "house_address", "sticker_unit_price", "community_name",
                                             "house_building_area", "contract_price", "sticker_price",
                                             "transaction_price", "tax_amount", "personal_income_tax", "deed_tax",
                                             "land_appreciation_tax", "transaction_fee", "basic_price",
                                             "total_tax_amount", "six_amount", "nine_amount", "total_amount",
                                             "isExcludePKFlag" };

    public Integer getWms_cre_housing_assessment_id()
    {
        return wms_cre_housing_assessment_id;
    }

    public void setWms_cre_housing_assessment_id(Integer obj)
    {
        wms_cre_housing_assessment_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public String getHouse_address()
    {
        return house_address;
    }

    public void setHouse_address(String obj)
    {
        house_address = obj;
    }

    public String getHouse_address1()
    {
        return house_address1;
    }

    public void setHouse_address1(String obj)
    {
        house_address1 = obj;
    }

    public java.math.BigDecimal getSticker_unit_price()
    {
        return sticker_unit_price;
    }

    public void setSticker_unit_price(java.math.BigDecimal obj)
    {
        sticker_unit_price = obj;
    }

    public String getCommunity_name()
    {
        return community_name;
    }

    public void setCommunity_name(String obj)
    {
        community_name = obj;
    }

    public String getCommunity_name1()
    {
        return community_name1;
    }

    public void setCommunity_name1(String obj)
    {
        community_name1 = obj;
    }

    public java.math.BigDecimal getHouse_building_area()
    {
        return house_building_area;
    }

    public void setHouse_building_area(java.math.BigDecimal obj)
    {
        house_building_area = obj;
    }

    public java.math.BigDecimal getHouse_building_area1()
    {
        return house_building_area1;
    }

    public void setHouse_building_area1(java.math.BigDecimal obj)
    {
        house_building_area1 = obj;
    }

    public java.math.BigDecimal getContract_price()
    {
        return contract_price;
    }

    public void setContract_price(java.math.BigDecimal obj)
    {
        contract_price = obj;
    }

    public java.math.BigDecimal getSticker_price()
    {
        return sticker_price;
    }

    public void setSticker_price(java.math.BigDecimal obj)
    {
        sticker_price = obj;
    }

    public java.math.BigDecimal getTransaction_price()
    {
        return transaction_price;
    }

    public void setTransaction_price(java.math.BigDecimal obj)
    {
        transaction_price = obj;
    }

    public java.math.BigDecimal getTax_amount()
    {
        return tax_amount;
    }

    public void setTax_amount(java.math.BigDecimal obj)
    {
        tax_amount = obj;
    }

    public java.math.BigDecimal getPersonal_income_tax()
    {
        return personal_income_tax;
    }

    public void setPersonal_income_tax(java.math.BigDecimal obj)
    {
        personal_income_tax = obj;
    }

    public java.math.BigDecimal getDeed_tax()
    {
        return deed_tax;
    }

    public void setDeed_tax(java.math.BigDecimal obj)
    {
        deed_tax = obj;
    }

    public java.math.BigDecimal getLand_appreciation_tax()
    {
        return land_appreciation_tax;
    }

    public void setLand_appreciation_tax(java.math.BigDecimal obj)
    {
        land_appreciation_tax = obj;
    }

    public java.math.BigDecimal getTransaction_fee()
    {
        return transaction_fee;
    }

    public void setTransaction_fee(java.math.BigDecimal obj)
    {
        transaction_fee = obj;
    }

    public java.math.BigDecimal getBasic_price()
    {
        return basic_price;
    }

    public void setBasic_price(java.math.BigDecimal obj)
    {
        basic_price = obj;
    }

    public java.math.BigDecimal getTotal_tax_amount()
    {
        return total_tax_amount;
    }

    public void setTotal_tax_amount(java.math.BigDecimal obj)
    {
        total_tax_amount = obj;
    }

    public java.math.BigDecimal getSix_amount()
    {
        return six_amount;
    }

    public void setSix_amount(java.math.BigDecimal obj)
    {
        six_amount = obj;
    }

    public java.math.BigDecimal getNine_amount()
    {
        return nine_amount;
    }

    public void setNine_amount(java.math.BigDecimal obj)
    {
        nine_amount = obj;
    }

    public java.math.BigDecimal getTotal_amount()
    {
        return total_amount;
    }

    public void setTotal_amount(java.math.BigDecimal obj)
    {
        total_amount = obj;
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
        paramMap.put("wms_cre_housing_assessment_id", this.wms_cre_housing_assessment_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("house_address", this.house_address);
        paramMap.put("sticker_unit_price", this.sticker_unit_price);
        paramMap.put("community_name", this.community_name);
        paramMap.put("house_building_area", this.house_building_area);
        paramMap.put("contract_price", this.contract_price);
        paramMap.put("sticker_price", this.sticker_price);
        paramMap.put("transaction_price", this.transaction_price);
        paramMap.put("tax_amount", this.tax_amount);
        paramMap.put("personal_income_tax", this.personal_income_tax);
        paramMap.put("deed_tax", this.deed_tax);
        paramMap.put("land_appreciation_tax", this.land_appreciation_tax);
        paramMap.put("transaction_fee", this.transaction_fee);
        paramMap.put("basic_price", this.basic_price);
        paramMap.put("total_tax_amount", this.total_tax_amount);
        paramMap.put("six_amount", this.six_amount);
        paramMap.put("nine_amount", this.nine_amount);
        paramMap.put("total_amount", this.total_amount);
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