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

public class WmsInveCommissionPerformanceHis implements BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private Integer wms_inve_commission_performance_his_id;
	
	private String salesman_name;
	
	private Integer compay_id;
	
	private Integer dept_id;
	
	private Integer salesman_id;
	
	private Integer salesman_post_id;
	
	private String salesman_status;
	
	private java.sql.Date date_of_payment;
	
	private String date_of_payment_str;
	
	private String statics_month;
	
	private java.sql.Timestamp statics_time;
	
	private String statics_time_str;
	
	private Integer wms_inve_transa_prod_id;
	
	private Integer wms_inve_transa_id;
	
	private Integer product_deadline;
	
	private String bill_code;
	
	private String cus_name;
	
	private java.math.BigDecimal investredemp_amount;
	
	private java.math.BigDecimal aldeal_invest_amount;
	
	private Integer commission_days;
	
	private Integer redeem_back_days;
	
	private String inve_type;
	
	private java.sql.Date add_start_date;
	
	private String add_start_date_str;
	
	private java.sql.Date add_end_date;
	
	private String add_end_date_str;
	
	private java.math.BigDecimal add_comm_coeff;
	
	private java.math.BigDecimal add_comm_mon;
	
	private java.math.BigDecimal stock_comm_coeff;
	
	private java.math.BigDecimal stock_comm_mon;
	
	private java.math.BigDecimal team_comm_coeff;
	
	private java.math.BigDecimal team_comm_mon;
	
	private Integer wms_inve_comm_rate_id_team;
	
	private Integer team_personnel_id;
	
	private java.math.BigDecimal tax_point;
	
	private java.math.BigDecimal tax_mon;
	
	private String data_status;
	
	private String enable_flag;
	
	private Integer wms_inve_pruduct_category_id;
	
	private Integer wms_inve_comm_rate_id_stock;
	
	private Integer wms_inve_comm_rate_id_add;
	
	private String trans_salesman_status;
	
	private String redeem_company_reason;
	
	private String is_take_off_damages;
	
	private String redeem_type;
	
	private java.math.BigDecimal investredemp_amount_base;
	
	private String redeem_type_detail;
	
	private java.math.BigDecimal convert_account;
	
	private String relpace_type_detail;
	
	private Integer bel_salesman_id_id;
	
    private Integer bel_salesman_dept_id;

    private Integer bel_department_manager_id;

    private Integer bel_department_manager_dept_id;

    private Integer bel_vice_general_manager_id;

    private Integer bel_vice_general_manager_dept_id;

    private Integer bel_general_manager_id;

    private Integer bel_general_manager_dept_id;

    private Integer bel_center_manager_id;

    private Integer bel_center_manager_dept_id;

    private Integer center_manager_id;

    private Integer center_manager_dept_id;

	private Integer wms_inve_redeem_id;
	
    private String is_special_rules;

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
	  	"wms_inve_commission_performance_his_id"
	};
	
	private static String[] columnNameArr = {
		"wms_inve_commission_performance_his_id",
		"salesman_name",
		"compay_id",
		"dept_id",
		"salesman_id",
		"salesman_post_id",
		"salesman_status",
		"date_of_payment",
		"date_of_payment_str",
		"statics_month",
		"statics_time",
		"statics_time_str",
		"wms_inve_transa_prod_id",
		"wms_inve_transa_id",
		"product_deadline",
		"bill_code",
		"cus_name",
		"investredemp_amount",
		"aldeal_invest_amount",
		"commission_days",
		"redeem_back_days",
		"inve_type",
		"add_start_date",
		"add_start_date_str",
		"add_end_date",
		"add_end_date_str",
		"add_comm_coeff",
		"add_comm_mon",
		"stock_comm_coeff",
		"stock_comm_mon",
		"team_comm_coeff",
		"team_comm_mon",
		"wms_inve_comm_rate_id_team",
		"team_personnel_id",
		"tax_point",
		"tax_mon",
		"data_status",
		"enable_flag",
		"wms_inve_pruduct_category_id",
		"wms_inve_comm_rate_id_stock",
		"wms_inve_comm_rate_id_add",
		"trans_salesman_status",
		"redeem_company_reason",
		"is_take_off_damages",
		"redeem_type",
		"investredemp_amount_base",
		"redeem_type_detail",
		"convert_account",
		"relpace_type_detail",
		"bel_salesman_id_id",
		"wms_inve_redeem_id",
		"isExcludePKFlag"
	};
  
	public Integer getWms_inve_commission_performance_his_id () {
		return wms_inve_commission_performance_his_id;
	}
	
	public void setWms_inve_commission_performance_his_id (Integer obj) {
		wms_inve_commission_performance_his_id = obj;
	}
	
	public String getSalesman_name () {
		return salesman_name;
	}
	
	public void setSalesman_name (String obj) {
		salesman_name = obj;
	}
	
	public Integer getCompay_id () {
		return compay_id;
	}
	
	public void setCompay_id (Integer obj) {
		compay_id = obj;
	}
	
	public Integer getDept_id () {
		return dept_id;
	}
	
	public void setDept_id (Integer obj) {
		dept_id = obj;
	}
	
	public Integer getSalesman_id () {
		return salesman_id;
	}
	
	public void setSalesman_id (Integer obj) {
		salesman_id = obj;
	}
	
	public Integer getSalesman_post_id () {
		return salesman_post_id;
	}
	
	public void setSalesman_post_id (Integer obj) {
		salesman_post_id = obj;
	}
	
	public String getSalesman_status () {
		return salesman_status;
	}
	
	public void setSalesman_status (String obj) {
		salesman_status = obj;
	}
	
	public java.sql.Date getDate_of_payment () {
		return date_of_payment;
	}
	
	public void setDate_of_payment (java.sql.Date obj) {
		date_of_payment = obj;
	}
	
	public String getDate_of_payment_str () {
		return date_of_payment_str;
	}
	
	public void setDate_of_payment_str (String obj) {
		date_of_payment_str = obj;
	}
	
	public String getStatics_month () {
		return statics_month;
	}
	
	public void setStatics_month (String obj) {
		statics_month = obj;
	}
	
	public java.sql.Timestamp getStatics_time () {
		return statics_time;
	}
	
	public void setStatics_time (java.sql.Timestamp obj) {
		statics_time = obj;
	}
	
	public String getStatics_time_str () {
		return statics_time_str;
	}
	
	public void setStatics_time_str (String obj) {
		statics_time_str = obj;
	}
	
	public Integer getWms_inve_transa_prod_id () {
		return wms_inve_transa_prod_id;
	}
	
	public void setWms_inve_transa_prod_id (Integer obj) {
		wms_inve_transa_prod_id = obj;
	}
	
	public Integer getWms_inve_transa_id () {
		return wms_inve_transa_id;
	}
	
	public void setWms_inve_transa_id (Integer obj) {
		wms_inve_transa_id = obj;
	}
	
	public Integer getProduct_deadline () {
		return product_deadline;
	}
	
	public void setProduct_deadline (Integer obj) {
		product_deadline = obj;
	}
	
	public String getBill_code () {
		return bill_code;
	}
	
	public void setBill_code (String obj) {
		bill_code = obj;
	}
	
	public String getCus_name () {
		return cus_name;
	}
	
	public void setCus_name (String obj) {
		cus_name = obj;
	}
	
	public java.math.BigDecimal getInvestredemp_amount () {
		return investredemp_amount;
	}
	
	public void setInvestredemp_amount (java.math.BigDecimal obj) {
		investredemp_amount = obj;
	}
	
	public java.math.BigDecimal getAldeal_invest_amount () {
		return aldeal_invest_amount;
	}
	
	public void setAldeal_invest_amount (java.math.BigDecimal obj) {
		aldeal_invest_amount = obj;
	}
	
	public Integer getCommission_days () {
		return commission_days;
	}
	
	public void setCommission_days (Integer obj) {
		commission_days = obj;
	}
	
	public Integer getRedeem_back_days () {
		return redeem_back_days;
	}
	
	public void setRedeem_back_days (Integer obj) {
		redeem_back_days = obj;
	}
	
	public String getInve_type () {
		return inve_type;
	}
	
	public void setInve_type (String obj) {
		inve_type = obj;
	}
	
	public java.sql.Date getAdd_start_date () {
		return add_start_date;
	}
	
	public void setAdd_start_date (java.sql.Date obj) {
		add_start_date = obj;
	}
	
	public String getAdd_start_date_str () {
		return add_start_date_str;
	}
	
	public void setAdd_start_date_str (String obj) {
		add_start_date_str = obj;
	}
	
	public java.sql.Date getAdd_end_date () {
		return add_end_date;
	}
	
	public void setAdd_end_date (java.sql.Date obj) {
		add_end_date = obj;
	}
	
	public String getAdd_end_date_str () {
		return add_end_date_str;
	}
	
	public void setAdd_end_date_str (String obj) {
		add_end_date_str = obj;
	}
	
	public java.math.BigDecimal getAdd_comm_coeff () {
		return add_comm_coeff;
	}
	
	public void setAdd_comm_coeff (java.math.BigDecimal obj) {
		add_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getAdd_comm_mon () {
		return add_comm_mon;
	}
	
	public void setAdd_comm_mon (java.math.BigDecimal obj) {
		add_comm_mon = obj;
	}
	
	public java.math.BigDecimal getStock_comm_coeff () {
		return stock_comm_coeff;
	}
	
	public void setStock_comm_coeff (java.math.BigDecimal obj) {
		stock_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getStock_comm_mon () {
		return stock_comm_mon;
	}
	
	public void setStock_comm_mon (java.math.BigDecimal obj) {
		stock_comm_mon = obj;
	}
	
	public java.math.BigDecimal getTeam_comm_coeff () {
		return team_comm_coeff;
	}
	
	public void setTeam_comm_coeff (java.math.BigDecimal obj) {
		team_comm_coeff = obj;
	}
	
	public java.math.BigDecimal getTeam_comm_mon () {
		return team_comm_mon;
	}
	
	public void setTeam_comm_mon (java.math.BigDecimal obj) {
		team_comm_mon = obj;
	}
	
	public Integer getWms_inve_comm_rate_id_team () {
		return wms_inve_comm_rate_id_team;
	}
	
	public void setWms_inve_comm_rate_id_team (Integer obj) {
		wms_inve_comm_rate_id_team = obj;
	}
	
	public Integer getTeam_personnel_id () {
		return team_personnel_id;
	}
	
	public void setTeam_personnel_id (Integer obj) {
		team_personnel_id = obj;
	}
	
	public java.math.BigDecimal getTax_point () {
		return tax_point;
	}
	
	public void setTax_point (java.math.BigDecimal obj) {
		tax_point = obj;
	}
	
	public java.math.BigDecimal getTax_mon () {
		return tax_mon;
	}
	
	public void setTax_mon (java.math.BigDecimal obj) {
		tax_mon = obj;
	}
	
	public String getData_status () {
		return data_status;
	}
	
	public void setData_status (String obj) {
		data_status = obj;
	}
	
	public String getEnable_flag () {
		return enable_flag;
	}
	
	public void setEnable_flag (String obj) {
		enable_flag = obj;
	}
	
	public Integer getWms_inve_pruduct_category_id () {
		return wms_inve_pruduct_category_id;
	}
	
	public void setWms_inve_pruduct_category_id (Integer obj) {
		wms_inve_pruduct_category_id = obj;
	}
	
	public Integer getWms_inve_comm_rate_id_stock () {
		return wms_inve_comm_rate_id_stock;
	}
	
	public void setWms_inve_comm_rate_id_stock (Integer obj) {
		wms_inve_comm_rate_id_stock = obj;
	}
	
	public Integer getWms_inve_comm_rate_id_add () {
		return wms_inve_comm_rate_id_add;
	}
	
	public void setWms_inve_comm_rate_id_add (Integer obj) {
		wms_inve_comm_rate_id_add = obj;
	}
	
	public String getTrans_salesman_status () {
		return trans_salesman_status;
	}
	
	public void setTrans_salesman_status (String obj) {
		trans_salesman_status = obj;
	}
	
	public String getRedeem_company_reason () {
		return redeem_company_reason;
	}
	
	public void setRedeem_company_reason (String obj) {
		redeem_company_reason = obj;
	}
	
	public String getIs_take_off_damages () {
		return is_take_off_damages;
	}
	
	public void setIs_take_off_damages (String obj) {
		is_take_off_damages = obj;
	}
	
	public String getRedeem_type () {
		return redeem_type;
	}
	
	public void setRedeem_type (String obj) {
		redeem_type = obj;
	}
	
	public java.math.BigDecimal getInvestredemp_amount_base () {
		return investredemp_amount_base;
	}
	
	public void setInvestredemp_amount_base (java.math.BigDecimal obj) {
		investredemp_amount_base = obj;
	}
	
	public String getRedeem_type_detail () {
		return redeem_type_detail;
	}
	
	public void setRedeem_type_detail (String obj) {
		redeem_type_detail = obj;
	}
	
	public java.math.BigDecimal getConvert_account () {
		return convert_account;
	}
	
	public void setConvert_account (java.math.BigDecimal obj) {
		convert_account = obj;
	}
	
	public String getRelpace_type_detail () {
		return relpace_type_detail;
	}
	
	public void setRelpace_type_detail (String obj) {
		relpace_type_detail = obj;
	}
	
	public Integer getBel_salesman_id_id () {
		return bel_salesman_id_id;
	}
	
	public void setBel_salesman_id_id (Integer obj) {
		bel_salesman_id_id = obj;
	}
	
	public Integer getWms_inve_redeem_id () {
		return wms_inve_redeem_id;
	}
	
	public void setWms_inve_redeem_id (Integer obj) {
		wms_inve_redeem_id = obj;
	}
	
	public boolean getgetIsExcludePKFlag () {
		return isExcludePKFlag;
	}
	
	public void setgetIsExcludePKFlag (boolean obj) {
		isExcludePKFlag = obj;
	}
	
	
	    /**
     * @return the is_special_rules
     */
    public String getIs_special_rules()
    {
        return is_special_rules;
    }

    /**
     * @param is_special_rules the is_special_rules to set
     */
    public void setIs_special_rules(String is_special_rules)
    {
        this.is_special_rules = is_special_rules;
    }

    /**
     * @return the bel_salesman_dept_id
     */
    public Integer getBel_salesman_dept_id()
    {
        return bel_salesman_dept_id;
    }

    /**
     * @param bel_salesman_dept_id the bel_salesman_dept_id to set
     */
    public void setBel_salesman_dept_id(Integer bel_salesman_dept_id)
    {
        this.bel_salesman_dept_id = bel_salesman_dept_id;
    }

    /**
     * @return the bel_department_manager_id
     */
    public Integer getBel_department_manager_id()
    {
        return bel_department_manager_id;
    }

    /**
     * @param bel_department_manager_id the bel_department_manager_id to set
     */
    public void setBel_department_manager_id(Integer bel_department_manager_id)
    {
        this.bel_department_manager_id = bel_department_manager_id;
    }

    /**
     * @return the bel_department_manager_dept_id
     */
    public Integer getBel_department_manager_dept_id()
    {
        return bel_department_manager_dept_id;
    }

    /**
     * @param bel_department_manager_dept_id the bel_department_manager_dept_id to set
     */
    public void setBel_department_manager_dept_id(Integer bel_department_manager_dept_id)
    {
        this.bel_department_manager_dept_id = bel_department_manager_dept_id;
    }

    /**
     * @return the bel_vice_general_manager_id
     */
    public Integer getBel_vice_general_manager_id()
    {
        return bel_vice_general_manager_id;
    }

    /**
     * @param bel_vice_general_manager_id the bel_vice_general_manager_id to set
     */
    public void setBel_vice_general_manager_id(Integer bel_vice_general_manager_id)
    {
        this.bel_vice_general_manager_id = bel_vice_general_manager_id;
    }

    /**
     * @return the bel_vice_general_manager_dept_id
     */
    public Integer getBel_vice_general_manager_dept_id()
    {
        return bel_vice_general_manager_dept_id;
    }

    /**
     * @param bel_vice_general_manager_dept_id the bel_vice_general_manager_dept_id to set
     */
    public void setBel_vice_general_manager_dept_id(Integer bel_vice_general_manager_dept_id)
    {
        this.bel_vice_general_manager_dept_id = bel_vice_general_manager_dept_id;
    }

    /**
     * @return the bel_general_manager_id
     */
    public Integer getBel_general_manager_id()
    {
        return bel_general_manager_id;
    }

    /**
     * @param bel_general_manager_id the bel_general_manager_id to set
     */
    public void setBel_general_manager_id(Integer bel_general_manager_id)
    {
        this.bel_general_manager_id = bel_general_manager_id;
    }

    /**
     * @return the bel_general_manager_dept_id
     */
    public Integer getBel_general_manager_dept_id()
    {
        return bel_general_manager_dept_id;
    }

    /**
     * @param bel_general_manager_dept_id the bel_general_manager_dept_id to set
     */
    public void setBel_general_manager_dept_id(Integer bel_general_manager_dept_id)
    {
        this.bel_general_manager_dept_id = bel_general_manager_dept_id;
    }

    /**
     * @return the bel_center_manager_id
     */
    public Integer getBel_center_manager_id()
    {
        return bel_center_manager_id;
    }

    /**
     * @param bel_center_manager_id the bel_center_manager_id to set
     */
    public void setBel_center_manager_id(Integer bel_center_manager_id)
    {
        this.bel_center_manager_id = bel_center_manager_id;
    }

    /**
     * @return the bel_center_manager_dept_id
     */
    public Integer getBel_center_manager_dept_id()
    {
        return bel_center_manager_dept_id;
    }

    /**
     * @param bel_center_manager_dept_id the bel_center_manager_dept_id to set
     */
    public void setBel_center_manager_dept_id(Integer bel_center_manager_dept_id)
    {
        this.bel_center_manager_dept_id = bel_center_manager_dept_id;
    }

    /**
     * @return the center_manager_id
     */
    public Integer getCenter_manager_id()
    {
        return center_manager_id;
    }

    /**
     * @param center_manager_id the center_manager_id to set
     */
    public void setCenter_manager_id(Integer center_manager_id)
    {
        this.center_manager_id = center_manager_id;
    }

    /**
     * @return the center_manager_dept_id
     */
    public Integer getCenter_manager_dept_id()
    {
        return center_manager_dept_id;
    }

    /**
     * @param center_manager_dept_id the center_manager_dept_id to set
     */
    public void setCenter_manager_dept_id(Integer center_manager_dept_id)
    {
        this.center_manager_dept_id = center_manager_dept_id;
    }

    /**
    * put all columns into a map
    */
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("wms_inve_commission_performance_his_id", this.wms_inve_commission_performance_his_id);
		paramMap.put("salesman_name", this.salesman_name);
		paramMap.put("compay_id", this.compay_id);
		paramMap.put("dept_id", this.dept_id);
		paramMap.put("salesman_id", this.salesman_id);
		paramMap.put("salesman_post_id", this.salesman_post_id);
		paramMap.put("salesman_status", this.salesman_status);
		paramMap.put("date_of_payment", this.date_of_payment);
		paramMap.put("date_of_payment_str", this.date_of_payment_str);
		paramMap.put("statics_month", this.statics_month);
		paramMap.put("statics_time", this.statics_time);
		paramMap.put("statics_time_str", this.statics_time_str);
		paramMap.put("wms_inve_transa_prod_id", this.wms_inve_transa_prod_id);
		paramMap.put("wms_inve_transa_id", this.wms_inve_transa_id);
		paramMap.put("product_deadline", this.product_deadline);
		paramMap.put("bill_code", this.bill_code);
		paramMap.put("cus_name", this.cus_name);
		paramMap.put("investredemp_amount", this.investredemp_amount);
		paramMap.put("aldeal_invest_amount", this.aldeal_invest_amount);
		paramMap.put("commission_days", this.commission_days);
		paramMap.put("redeem_back_days", this.redeem_back_days);
		paramMap.put("inve_type", this.inve_type);
		paramMap.put("add_start_date", this.add_start_date);
		paramMap.put("add_start_date_str", this.add_start_date_str);
		paramMap.put("add_end_date", this.add_end_date);
		paramMap.put("add_end_date_str", this.add_end_date_str);
		paramMap.put("add_comm_coeff", this.add_comm_coeff);
		paramMap.put("add_comm_mon", this.add_comm_mon);
		paramMap.put("stock_comm_coeff", this.stock_comm_coeff);
		paramMap.put("stock_comm_mon", this.stock_comm_mon);
		paramMap.put("team_comm_coeff", this.team_comm_coeff);
		paramMap.put("team_comm_mon", this.team_comm_mon);
		paramMap.put("wms_inve_comm_rate_id_team", this.wms_inve_comm_rate_id_team);
		paramMap.put("team_personnel_id", this.team_personnel_id);
		paramMap.put("tax_point", this.tax_point);
		paramMap.put("tax_mon", this.tax_mon);
		paramMap.put("data_status", this.data_status);
		paramMap.put("enable_flag", this.enable_flag);
		paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
		paramMap.put("wms_inve_comm_rate_id_stock", this.wms_inve_comm_rate_id_stock);
		paramMap.put("wms_inve_comm_rate_id_add", this.wms_inve_comm_rate_id_add);
		paramMap.put("trans_salesman_status", this.trans_salesman_status);
		paramMap.put("redeem_company_reason", this.redeem_company_reason);
		paramMap.put("is_take_off_damages", this.is_take_off_damages);
		paramMap.put("redeem_type", this.redeem_type);
		paramMap.put("investredemp_amount_base", this.investredemp_amount_base);
		paramMap.put("redeem_type_detail", this.redeem_type_detail);
		paramMap.put("convert_account", this.convert_account);
		paramMap.put("relpace_type_detail", this.relpace_type_detail);
		paramMap.put("bel_salesman_id_id", this.bel_salesman_id_id);
		paramMap.put("wms_inve_redeem_id", this.wms_inve_redeem_id);
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