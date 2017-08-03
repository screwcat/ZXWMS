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

public class WmsInvePos implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_pos_id;

    private String pos_name;

    private String pos_code;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private boolean isExcludePKFlag;

    private String pos_bank;

    private String pos_bank_card;

    private String remark;
    
    private String most_poundage;
    
    private String sigle_poundage;

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_pos_id" };

    private static String[] columnNameArr = { "wms_inve_pos_id", "pos_name", "pos_code", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "isExcludePKFlag", "pos_bank",
                                             "pos_bank_card", "remark", "most_poundage", "sigle_poundage" };

    public Integer getWms_inve_pos_id()
    {
        return wms_inve_pos_id;
    }

    public void setWms_inve_pos_id(Integer obj)
    {
        wms_inve_pos_id = obj;
    }

    public String getPos_name()
    {
        return pos_name;
    }

    public void setPos_name(String obj)
    {
        pos_name = obj;
    }

    public String getPos_code()
    {
        return pos_code;
    }

    public void setPos_code(String obj)
    {
        pos_code = obj;
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

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    public String getPos_bank()
    {
        return pos_bank;
    }

    public void setPos_bank(String pos_bank)
    {
        this.pos_bank = pos_bank;
    }

    public String getPos_bank_card()
    {
        return pos_bank_card;
    }

    public void setPos_bank_card(String pos_bank_card)
    {
        this.pos_bank_card = pos_bank_card;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public String getMost_poundage() {
		return most_poundage;
	}

	public void setMost_poundage(String most_poundage) {
		this.most_poundage = most_poundage;
	}

	public String getSigle_poundage() {
		return sigle_poundage;
	}

	public void setSigle_poundage(String sigle_poundage) {
		this.sigle_poundage = sigle_poundage;
	}

	/**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_pos_id", this.wms_inve_pos_id);
        paramMap.put("pos_name", this.pos_name);
        paramMap.put("pos_code", this.pos_code);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("pos_bank", this.pos_bank);
        paramMap.put("pos_bank_card", this.pos_bank_card);
        paramMap.put("remark", this.remark);
        paramMap.put("most_poundage", this.most_poundage);
        paramMap.put("sigle_poundage", this.sigle_poundage);
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