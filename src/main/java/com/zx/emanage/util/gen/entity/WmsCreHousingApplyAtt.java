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

import com.zx.platform.syscontext.vo.GridParamBean;
import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreHousingApplyAtt extends GridParamBean implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_cre_housing_apply_att_id;
	
	private String data_type_name;
	
	private String data_detail_name;
	
	private String attachment_old_name;
	
	private String attachment_new_name;
	
	private String attachment_address;
	
	private String attachment_type;
	
	private Integer wms_cre_credit_head_id;
	
	private Integer wms_cre_housing_file_info_id;
	
	private boolean isExcludePKFlag;
	
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_cre_housing_apply_att_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_housing_apply_att_id",
		"data_type_name",
		"data_detail_name",
		"attachment_old_name",
		"attachment_new_name",
		"attachment_address",
		"attachment_type",
		"wms_cre_credit_head_id",
		"wms_cre_housing_file_info_id",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_housing_apply_att_id () {
		return wms_cre_housing_apply_att_id;
	}
	
	public void setWms_cre_housing_apply_att_id (Integer obj) {
		wms_cre_housing_apply_att_id = obj;
	}
	
	public String getData_type_name () {
		return data_type_name;
	}
	
	public void setData_type_name (String obj) {
		data_type_name = obj;
	}
	
	public String getData_detail_name () {
		return data_detail_name;
	}
	
	public void setData_detail_name (String obj) {
		data_detail_name = obj;
	}
	
	public String getAttachment_old_name () {
		return attachment_old_name;
	}
	
	public void setAttachment_old_name (String obj) {
		attachment_old_name = obj;
	}
	
	public String getAttachment_new_name () {
		return attachment_new_name;
	}
	
	public void setAttachment_new_name (String obj) {
		attachment_new_name = obj;
	}
	
	public String getAttachment_address () {
		return attachment_address;
	}
	
	public void setAttachment_address (String obj) {
		attachment_address = obj;
	}
	
	public String getAttachment_type () {
		return attachment_type;
	}
	
	public void setAttachment_type (String obj) {
		attachment_type = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getWms_cre_housing_file_info_id () {
		return wms_cre_housing_file_info_id;
	}
	
	public void setWms_cre_housing_file_info_id (Integer obj) {
		wms_cre_housing_file_info_id = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_cre_housing_apply_att_id", this.wms_cre_housing_apply_att_id);
		paramMap.put("data_type_name", this.data_type_name);
		paramMap.put("data_detail_name", this.data_detail_name);
		paramMap.put("attachment_old_name", this.attachment_old_name);
		paramMap.put("attachment_new_name", this.attachment_new_name);
		paramMap.put("attachment_address", this.attachment_address);
		paramMap.put("attachment_type", this.attachment_type);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("wms_cre_housing_file_info_id", this.wms_cre_housing_file_info_id);
		paramMap.put("isExcludePKFlag", this.isExcludePKFlag);
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