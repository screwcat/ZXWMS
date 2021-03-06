package com.zx.emanage.loancheck.vo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;

import com.zx.emanage.util.gen.entity.WmsCreCreditLineInventoryGoodMaterial;
import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreCreditLineGoodMaterialVo extends WmsCreCreditLineInventoryGoodMaterial implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_line_inventory_goods_materials_id;

    private String good_material_type = "2";

    private String good_material_name;

    private String inventory_quantity;

    private java.math.BigDecimal purchase_price;

    private String daily_consumption;

    private String storage_location;

    private java.sql.Date storage_time;

    private String storage_time_str;

    private String material_ratio;

    private Integer wms_cre_credit_head_id;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_line_inventory_goods_materials_id" };

    private static String[] columnNameArr = { "wms_cre_credit_line_inventory_goods_materials_id", "good_material_type",
                                             "good_material_name", "inventory_quantity", "purchase_price",
                                             "daily_consumption", "storage_location", "storage_time",
                                             "storage_time_str", "material_ratio", "wms_cre_credit_head_id",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag" };

    public Integer getWms_cre_credit_line_inventory_goods_materials_id()
    {
        return wms_cre_credit_line_inventory_goods_materials_id;
    }

    public void setWms_cre_credit_line_inventory_goods_materials_id(Integer obj)
    {
        wms_cre_credit_line_inventory_goods_materials_id = obj;
    }

    public String getGood_material_type()
    {
        return good_material_type;
    }

    public void setGood_material_type(String obj)
    {
        good_material_type = obj;
    }

    public String getGood_material_name()
    {
        return good_material_name;
    }

    public void setGood_material_name(String obj)
    {
        good_material_name = obj;
    }

    public String getInventory_quantity()
    {
        return inventory_quantity;
    }

    public void setInventory_quantity(String obj)
    {
        inventory_quantity = obj;
    }

    public java.math.BigDecimal getPurchase_price()
    {
        return purchase_price;
    }

    public void setPurchase_price(java.math.BigDecimal obj)
    {
        purchase_price = obj;
    }

    public String getDaily_consumption()
    {
        return daily_consumption;
    }

    public void setDaily_consumption(String obj)
    {
        daily_consumption = obj;
    }

    public String getStorage_location()
    {
        return storage_location;
    }

    public void setStorage_location(String obj)
    {
        storage_location = obj;
    }

    public java.sql.Date getStorage_time()
    {
        return storage_time;
    }

    public void setStorage_time(java.sql.Date obj)
    {
        storage_time = obj;
    }

    public String getStorage_timeStr()
    {
        return storage_time_str;
    }

    public void setStorage_timeStr(String obj)
    {
        storage_time_str = obj;
    }

    public String getMaterial_ratio()
    {
        return material_ratio;
    }

    public void setMaterial_ratio(String obj)
    {
        material_ratio = obj;
    }

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
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

    public String getCreate_timestampStr()
    {
        return create_timestamp_str;
    }

    public void setCreate_timestampStr(String obj)
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

    public String getLast_update_timestampStr()
    {
        return last_update_timestamp_str;
    }

    public void setLast_update_timestampStr(String obj)
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

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_line_inventory_goods_materials_id",
                     this.wms_cre_credit_line_inventory_goods_materials_id);
        paramMap.put("good_material_type", this.good_material_type);
        paramMap.put("good_material_name", this.good_material_name);
        paramMap.put("inventory_quantity", this.inventory_quantity);
        paramMap.put("purchase_price", this.purchase_price);
        paramMap.put("daily_consumption", this.daily_consumption);
        paramMap.put("storage_location", this.storage_location);
        paramMap.put("storage_time", this.storage_time);
        paramMap.put("storage_time_str", this.storage_time_str);
        paramMap.put("material_ratio", this.material_ratio);
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
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