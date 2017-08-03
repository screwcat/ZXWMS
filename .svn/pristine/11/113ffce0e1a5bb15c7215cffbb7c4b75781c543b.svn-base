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

public class WmsCreRevPhoneModel implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_rev_phone_model_id;

    private Integer wms_cre_credit_head_id;

    private Integer house_building_area;

    private Integer house_address;

    private Integer house_area;

    private Integer car_market_value;

    private Integer car_year;

    private Integer other_market_value;

    private Integer lineal_relatives_tel;

    private Integer collateral_relatives_tel;

    private Integer ollateral_relatives_friends;

    private Integer relatives_evalu;

    private Integer friends_evalu;

    private Integer family_evalu;

    private boolean isExcludePKFlag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_rev_phone_model_id" };

    private static String[] columnNameArr = { "wms_cre_rev_phone_model_id", "wms_cre_credit_head_id",
                                             "house_building_area", "house_address", "house_area", "car_market_value",
                                             "car_year", "other_market_value", "lineal_relatives_tel",
                                             "collateral_relatives_tel", "ollateral_relatives_friends",
                                             "relatives_evalu", "friends_evalu", "family_evalu", "isExcludePKFlag" };

    public Integer getWms_cre_rev_phone_model_id()
    {
        return wms_cre_rev_phone_model_id;
    }

    public void setWms_cre_rev_phone_model_id(Integer obj)
    {
        wms_cre_rev_phone_model_id = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Integer getHouse_building_area()
    {
        return house_building_area;
    }

    public void setHouse_building_area(Integer obj)
    {
        house_building_area = obj;
    }

    public Integer getHouse_address()
    {
        return house_address;
    }

    public void setHouse_address(Integer obj)
    {
        house_address = obj;
    }

    public Integer getHouse_area()
    {
        return house_area;
    }

    public void setHouse_area(Integer obj)
    {
        house_area = obj;
    }

    public Integer getCar_market_value()
    {
        return car_market_value;
    }

    public void setCar_market_value(Integer obj)
    {
        car_market_value = obj;
    }

    public Integer getCar_year()
    {
        return car_year;
    }

    public void setCar_year(Integer obj)
    {
        car_year = obj;
    }

    public Integer getOther_market_value()
    {
        return other_market_value;
    }

    public void setOther_market_value(Integer obj)
    {
        other_market_value = obj;
    }

    public Integer getLineal_relatives_tel()
    {
        return lineal_relatives_tel;
    }

    public void setLineal_relatives_tel(Integer obj)
    {
        lineal_relatives_tel = obj;
    }

    public Integer getCollateral_relatives_tel()
    {
        return collateral_relatives_tel;
    }

    public void setCollateral_relatives_tel(Integer obj)
    {
        collateral_relatives_tel = obj;
    }

    public Integer getOllateral_relatives_friends()
    {
        return ollateral_relatives_friends;
    }

    public void setOllateral_relatives_friends(Integer obj)
    {
        ollateral_relatives_friends = obj;
    }

    public Integer getRelatives_evalu()
    {
        return relatives_evalu;
    }

    public void setRelatives_evalu(Integer obj)
    {
        relatives_evalu = obj;
    }

    public Integer getFriends_evalu()
    {
        return friends_evalu;
    }

    public void setFriends_evalu(Integer obj)
    {
        friends_evalu = obj;
    }

    public Integer getFamily_evalu()
    {
        return family_evalu;
    }

    public void setFamily_evalu(Integer obj)
    {
        family_evalu = obj;
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
        paramMap.put("wms_cre_rev_phone_model_id", this.wms_cre_rev_phone_model_id);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("house_building_area", this.house_building_area);
        paramMap.put("house_address", this.house_address);
        paramMap.put("house_area", this.house_area);
        paramMap.put("car_market_value", this.car_market_value);
        paramMap.put("car_year", this.car_year);
        paramMap.put("other_market_value", this.other_market_value);
        paramMap.put("lineal_relatives_tel", this.lineal_relatives_tel);
        paramMap.put("collateral_relatives_tel", this.collateral_relatives_tel);
        paramMap.put("ollateral_relatives_friends", this.ollateral_relatives_friends);
        paramMap.put("relatives_evalu", this.relatives_evalu);
        paramMap.put("friends_evalu", this.friends_evalu);
        paramMap.put("family_evalu", this.family_evalu);
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