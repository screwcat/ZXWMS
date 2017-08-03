package com.zx.emanage.util.gen.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.zx.sframe.util.mybatis.BaseEntity;

/*
 * @version 2.0
 */

public class WmsCreHousingTrialAssessment implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Expose
	private Integer wms_cre_housing_trial_assessment_id;
	
	@Expose
	private String customer_name;
	
	@Expose
	private String house_address;
	
	@Expose
	private String community_name;
	
	@Expose
	private String house_type;
	
	@Expose
	private Double house_building_area;
	
	private Double house_buy_money;
	
	@Expose
	private java.sql.Date house_buy_date;
	
	private String house_buy_date_str;
	
	@Expose
	private String vehicle_evaluation_price;
	
	@Expose
	private String remark;
	
	@Expose
	private Integer wms_cre_credit_head_id;
	
	private Integer create_user_id;
	
	private String create_user_name;
	
	@Expose
	private java.sql.Timestamp create_timestamp;
	
	private String create_timestamp_str;
	
	private Integer last_update_user_id;
	
	private java.sql.Timestamp last_update_timestamp;
	
	private String last_update_timestamp_str;
	
	private String enable_flag;
	
	private boolean isExcludePKFlag;
	
	private String vehicle_evaluation_person; 
	
	@Expose
	private String  customer_age;//客户年龄
	
	@Expose
	private Integer houses_number;//名下几处房产
	
	@Expose
	private String house_age;//房屋年龄
	@Expose
	private String house_remark;//备注
	@Expose
	private String is_compound;//是否复式
	@Expose
	private String check_pay;//核查缴费金额（元）

    private BigDecimal old_appro_limit;// 曾终审金额";

    private String is_again_appl;// "是否重新审批 1是0否";
	
	/**
	 * @return the check_pay
	 */
	public String getCheck_pay() {
		return check_pay;
	}

	/**
	 * @param check_pay the check_pay to set
	 */
	public void setCheck_pay(String check_pay) {
		this.check_pay = check_pay;
	}
	
	public String getHouse_age() {
		return house_age;
	}

	public String getHouse_remark() {
		return house_remark;
	}

	public String getIs_compound() {
		return is_compound;
	}

	public void setHouse_age(String house_age) {
		this.house_age = house_age;
	}

	public void setHouse_remark(String house_remark) {
		this.house_remark = house_remark;
	}

	public void setIs_compound(String is_compound) {
		this.is_compound = is_compound;
	}

	public String getVehicle_evaluation_person() {
		return vehicle_evaluation_person;
	}

	public void setVehicle_evaluation_person(String vehicle_evaluation_person) {
		this.vehicle_evaluation_person = vehicle_evaluation_person;
	}

	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"wms_cre_housing_trial_assessment_id"
	};
	
	private static String[] columnNameArr = {
		"wms_cre_housing_trial_assessment_id",
		"house_address",
		"community_name",
		"house_type",
		"house_building_area",
		"house_buy_money",
		"house_buy_date",
		"house_buy_date_str",
		"vehicle_evaluation_price",
		"remark",
		"wms_cre_credit_head_id",
		"create_user_id",
		"create_user_name",
		"create_timestamp",
		"create_timestamp_str",
		"last_update_user_id",
		"last_update_timestamp",
		"last_update_timestamp_str",
		"enable_flag",
		"isExcludePKFlag"
	};
  
	public Integer getWms_cre_housing_trial_assessment_id () {
		return wms_cre_housing_trial_assessment_id;
	}
	
	public void setWms_cre_housing_trial_assessment_id (Integer obj) {
		wms_cre_housing_trial_assessment_id = obj;
	}
	
	public String getHouse_address () {
		return house_address;
	}
	
	public void setHouse_address (String obj) {
		house_address = obj;
	}
	
	public String getCommunity_name () {
		return community_name;
	}
	
	public void setCommunity_name (String obj) {
		community_name = obj;
	}
	
	public String getHouse_type () {
		return house_type;
	}
	
	public void setHouse_type (String obj) {
		house_type = obj;
	}
	
	public Double getHouse_building_area () {
		return house_building_area;
	}
	
	public void setHouse_building_area (Double obj) {
		house_building_area = obj;
	}
	
	public Double getHouse_buy_money () {
		return house_buy_money;
	}
	
	public void setHouse_buy_money (Double obj) {
		house_buy_money = obj;
	}
	
	public String getVehicle_evaluation_price() {
		return vehicle_evaluation_price;
	}

	public void setVehicle_evaluation_price(String vehicle_evaluation_price) {
		this.vehicle_evaluation_price = vehicle_evaluation_price;
	}

	public java.sql.Date getHouse_buy_date () {
		return house_buy_date;
	}
	
	public void setHouse_buy_date (java.sql.Date obj) {
		house_buy_date = obj;
	}
	
	public String getHouse_buy_date_str () {
		return house_buy_date_str;
	}
	
	public void setHouse_buy_date_str (String obj) {
		house_buy_date_str = obj;
	}
	

	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
	}
	
	public Integer getWms_cre_credit_head_id () {
		return wms_cre_credit_head_id;
	}
	
	public void setWms_cre_credit_head_id (Integer obj) {
		wms_cre_credit_head_id = obj;
	}
	
	public Integer getCreate_user_id () {
		return create_user_id;
	}
	
	public void setCreate_user_id (Integer obj) {
		create_user_id = obj;
	}
	
	public String getCreate_user_name () {
		return create_user_name;
	}
	
	public void setCreate_user_name (String obj) {
		create_user_name = obj;
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
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_age() {
		return customer_age;
	}

	public void setCustomer_age(String customer_age) {
		this.customer_age = customer_age;
	}

	public Integer getHouses_number() {
		return houses_number;
	}

	public void setHouses_number(Integer houses_number) {
		this.houses_number = houses_number;
	}

    public BigDecimal getOld_appro_limit()
    {
        return old_appro_limit;
    }

    public void setOld_appro_limit(BigDecimal old_appro_limit)
    {
        this.old_appro_limit = old_appro_limit;
    }

    public String getIs_again_appl()
    {
        return is_again_appl;
    }

    public void setIs_again_appl(String is_again_appl)
    {
        this.is_again_appl = is_again_appl;
    }

    /**
    * put all columns into a map
    */
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_cre_housing_trial_assessment_id", this.wms_cre_housing_trial_assessment_id);
		paramMap.put("house_address", this.house_address);
		paramMap.put("community_name", this.community_name);
		paramMap.put("house_type", this.house_type);
		paramMap.put("house_building_area", this.house_building_area);
		paramMap.put("house_buy_money", this.house_buy_money);
		paramMap.put("house_buy_date", this.house_buy_date);
		paramMap.put("house_buy_date_str", this.house_buy_date_str);
		paramMap.put("vehicle_evaluation_price", this.vehicle_evaluation_price);
		paramMap.put("remark", this.remark);
		paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
		paramMap.put("create_user_id", this.create_user_id);
		paramMap.put("create_user_name", this.create_user_name);
		paramMap.put("create_timestamp", this.create_timestamp);
		paramMap.put("create_timestamp_str", this.create_timestamp_str);
		paramMap.put("last_update_user_id", this.last_update_user_id);
		paramMap.put("last_update_timestamp", this.last_update_timestamp);
		paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
		paramMap.put("enable_flag", this.enable_flag);
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