package com.zx.emanage.util.gen.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

public class WmsInveCustomerRemoveHis implements BaseEntity{
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_customer_remove_his;
	
	private Integer crm_customer_id;
	
	private Integer wms_inve_customer_id;
	
	private Date change_date;
	
	private Integer base_bel_personnel_id;
	
	private Integer bel_personnel_id;
	
	private Timestamp last_update_timestamp;
	
	private Date end_of_date;
	
	private String data_type;

	public Integer getWms_inve_customer_remove_his() {
		return wms_inve_customer_remove_his;
	}

	public void setWms_inve_customer_remove_his(Integer wms_inve_customer_remove_his) {
		this.wms_inve_customer_remove_his = wms_inve_customer_remove_his;
	}

	public Integer getCrm_customer_id() {
		return crm_customer_id;
	}

	public void setCrm_customer_id(Integer crm_customer_id) {
		this.crm_customer_id = crm_customer_id;
	}

	public Integer getWms_inve_customer_id() {
		return wms_inve_customer_id;
	}

	public void setWms_inve_customer_id(Integer wms_inve_customer_id) {
		this.wms_inve_customer_id = wms_inve_customer_id;
	}

	public Date getChange_date() {
		return change_date;
	}

	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}

	public Integer getBase_bel_personnel_id() {
		return base_bel_personnel_id;
	}

	public void setBase_bel_personnel_id(Integer base_bel_personnel_id) {
		this.base_bel_personnel_id = base_bel_personnel_id;
	}

	public Integer getBel_personnel_id() {
		return bel_personnel_id;
	}

	public void setBel_personnel_id(Integer bel_personnel_id) {
		this.bel_personnel_id = bel_personnel_id;
	}

	public Timestamp getLast_update_timestamp() {
		return last_update_timestamp;
	}

	public void setLast_update_timestamp(Timestamp last_update_timestamp) {
		this.last_update_timestamp = last_update_timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getEnd_of_date()
	{
		return end_of_date;
	}

	public void setEnd_of_date(Date end_of_date)
	{
		this.end_of_date = end_of_date;
	}

	public String getData_type()
	{
		return data_type;
	}

	public void setData_type(String data_type)
	{
		this.data_type = data_type;
	}


	/**
	 * default val cols name array
	 */
	private static String[] defaultValColArr = {};

	/**
	 * pk cols name array
	 */
	private static String[] pkColArr = { "wms_inve_customer_remove_his" };

	private static String[] columnNameArr = { "wms_inve_customer_remove_his", "crm_customer_id",
			"wms_inve_customer_id", "change_date", "base_bel_personnel_id", "bel_personnel_id",
			"last_update_timestamp", "end_of_date", "data_type" };
	
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_customer_remove_his", this.wms_inve_customer_remove_his);
		paramMap.put("crm_customer_id", this.crm_customer_id);
		paramMap.put("wms_inve_customer_id", this.wms_inve_customer_id);
		paramMap.put("change_date", this.change_date);
		paramMap.put("base_bel_personnel_id", this.base_bel_personnel_id);
		paramMap.put("bel_personnel_id", this.bel_personnel_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("end_of_date", this.end_of_date);
		paramMap.put("data_type", this.data_type);
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
			if (paramMap.get(colName) == null) {
				paramMap.remove(colName);
			}
		}
		return paramMap;
	}
}
