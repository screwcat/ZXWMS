package com.zx.emanage.util.gen.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsInveExtendInfo implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_extend_info_id;
	
	private Integer wms_inve_transa_id;
	
	private String bank_of_deposit_pro;
	
	private String bank_of_deposit_city;
	
	private String bank_branch;
	
	private String card_owner_name;
	
	private String bank_of_deposit;
	
	private String card_no;
	
	private Integer create_user_id;
	
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
	
    private String get_credit_type;
	

	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_inve_extend_info_id"
	};
	
    private static String[] columnNameArr = { "wms_inve_extend_info_id", "wms_inve_transa_id", "bank_of_deposit_pro", "bank_of_deposit_city",
                                             "bank_branch", "card_owner_name", "bank_of_deposit", "card_no", "create_user_id", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp", "last_update_timestamp_str",
                                             "enable_flag", "isExcludePKFlag", "get_credit_type" };
  
	public Integer getWms_inve_extend_info_id () {
		return wms_inve_extend_info_id;
	}
	
	public void setWms_inve_extend_info_id (Integer obj) {
		wms_inve_extend_info_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public String getBank_of_deposit_pro () {
		return bank_of_deposit_pro;
	}
	
	public void setBank_of_deposit_pro (String obj) {
		bank_of_deposit_pro = obj;
	}
	
	public String getBank_of_deposit_city () {
		return bank_of_deposit_city;
	}
	
	public void setBank_of_deposit_city (String obj) {
		bank_of_deposit_city = obj;
	}
	
	public String getBank_branch () {
		return bank_branch;
	}
	
	public void setBank_branch (String obj) {
		bank_branch = obj;
	}
	
	public String getCard_owner_name () {
		return card_owner_name;
	}
	
	public void setCard_owner_name (String obj) {
		card_owner_name = obj;
	}
	
	public String getBank_of_deposit () {
		return bank_of_deposit;
	}
	
	public void setBank_of_deposit (String obj) {
		bank_of_deposit = obj;
	}
	
	public String getCard_no () {
		return card_no;
	}
	
	public void setCard_no (String obj) {
		card_no = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public java.sql.Timestamp getCreate_timestamp () {
		return create_timestamp;
	}
	
	public void setCreate_timestamp (java.sql.Timestamp obj) {
		create_timestamp = obj;
	}
	
	public String getCreate_timestamp_str () {
		return create_timestamp_str;
	}
	
	public void setCreate_timestamp_str (String obj) {
		create_timestamp_str = obj;
	}
	
	public Integer getLast_update_user_id () {
		return last_update_user_id;
	}
	
	public void setLast_update_user_id (Integer obj) {
		last_update_user_id = obj;
	}
	
	public java.sql.Timestamp getLast_update_timestamp () {
		return last_update_timestamp;
	}
	
	public void setLast_update_timestamp (java.sql.Timestamp obj) {
		last_update_timestamp = obj;
	}
	
	public String getLast_update_timestamp_str () {
		return last_update_timestamp_str;
	}
	
	public void setLast_update_timestamp_str (String obj) {
		last_update_timestamp_str = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
    public String getGet_credit_type()
    {
        return get_credit_type;
    }

    public void setGet_credit_type(String get_credit_type)
    {
        this.get_credit_type = get_credit_type;
    }

    /**
    * put all columns into a map
    */
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_extend_info_id", this.wms_inve_extend_info_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("bank_of_deposit_pro", this.bank_of_deposit_pro);
		paramMap.put("bank_of_deposit_city", this.bank_of_deposit_city);
		paramMap.put("bank_branch", this.bank_branch);
		paramMap.put("card_owner_name", this.card_owner_name);
		paramMap.put("bank_of_deposit", this.bank_of_deposit);
		paramMap.put("card_no", this.card_no);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
        paramMap.put("get_credit_type", this.get_credit_type);
	}
	
	/**
	* return the columns map
	*/
	public Map<String, Object> getInfoMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.putInMap(paramMap);
		return paramMap;
	}
	
	/**
	* remove default value and pk if it is null
	*/
	private Map<String, Object> dealWithMap(Map<String, Object> paramMap) {
		Set<String> set = new HashSet<String>();
		for (String colName : defaultValColArr) {
			set.add(colName);
		}
		for (String colName : pkColArr) {
			set.add(colName);
		}
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String colName = iterator.next();
			if(paramMap.get(colName) == null) {
				paramMap.remove(colName);
			}
		}
		return paramMap;
	}
}