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

public class WmsInvePruductCategory implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_inve_pruduct_category_id;

    private String category_name;

    private String category_briefing;

    private Integer category_investment_money_min;

    private Integer category_investment_money_max;

    private Integer category_additional_monery_min;

    private Integer category_additional_monery_max;

    private Integer category_account_money_min;

    private Integer category_account_money_max;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String enable_flag;

    private String is_forbidden;

    private java.sql.Date valid_begin_date;

    private String valid_begin_date_str;

    private java.sql.Date valid_end_date;

    private String valid_end_date_str;

    private boolean isExcludePKFlag;
    
    private String platform_user;

    private Integer buy_month_limit;

    private String has_paper_protocol;

    /**
     * baisong
     * 2015-8-14
     */
    private String category_code;//产品编号
    private Integer category_type;//产品类型
    private Integer category_deadline;//产品期限
    private Integer category_interest_pay_method;//付息方式
    private java.math.BigDecimal category_return_rate;//年收益率(%)
    private Integer category_closure_period;//产品封闭期
    private Integer maximum_holding_amount;//最大持有金额(针对个人上单额)
    private java.sql.Date category_over_time;//产品结束日期(针对产品使用日期最长期限)
    private Integer category_maximum_amount;//产品最大金额(针对产品使用最大金额期限)
    private String category_remak;//备注
    private Integer category_rebate_way;//返利方式
    private java.math.BigDecimal basic_monthly_rate;//基础月付利率
    private Integer category_sales_statistics;//热销产品统计(每月更新)

    /**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_inve_pruduct_category_id" };

    private static String[] columnNameArr = { "wms_inve_pruduct_category_id", "category_name", "category_briefing",
                                             "category_investment_money_min", "category_investment_money_max",
                                             "category_additional_monery_min", "category_additional_monery_max",
                                             "category_account_money_min", "category_account_money_max",
                                             "create_user_id", "create_user_name", "create_timestamp",
                                             "create_timestamp_str", "last_update_user_id", "last_update_timestamp",
                                             "last_update_timestamp_str", "enable_flag", "is_forbidden",
                                             "valid_begin_date", "valid_begin_date_str", "valid_end_date",
                                             "valid_end_date_str", "isExcludePKFlag" };

    public Integer getWms_inve_pruduct_category_id()
    {
        return wms_inve_pruduct_category_id;
    }

    public void setWms_inve_pruduct_category_id(Integer obj)
    {
        wms_inve_pruduct_category_id = obj;
    }

    public String getCategory_name()
    {
        return category_name;
    }

    public void setCategory_name(String obj)
    {
        category_name = obj;
    }

    public String getCategory_briefing()
    {
        return category_briefing;
    }

    public void setCategory_briefing(String obj)
    {
        category_briefing = obj;
    }

    public Integer getCategory_investment_money_min()
    {
        return category_investment_money_min;
    }

    public void setCategory_investment_money_min(Integer obj)
    {
        category_investment_money_min = obj;
    }

    public Integer getCategory_investment_money_max()
    {
        return category_investment_money_max;
    }

    public void setCategory_investment_money_max(Integer obj)
    {
        category_investment_money_max = obj;
    }

    public Integer getCategory_additional_monery_min()
    {
        return category_additional_monery_min;
    }

    public void setCategory_additional_monery_min(Integer obj)
    {
        category_additional_monery_min = obj;
    }

    public Integer getCategory_additional_monery_max()
    {
        return category_additional_monery_max;
    }

    public void setCategory_additional_monery_max(Integer obj)
    {
        category_additional_monery_max = obj;
    }

    public Integer getCategory_account_money_min()
    {
        return category_account_money_min;
    }

    public void setCategory_account_money_min(Integer obj)
    {
        category_account_money_min = obj;
    }

    public Integer getCategory_account_money_max()
    {
        return category_account_money_max;
    }

    public void setCategory_account_money_max(Integer obj)
    {
        category_account_money_max = obj;
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

    public String getIs_forbidden()
    {
        return is_forbidden;
    }

    public void setIs_forbidden(String obj)
    {
        is_forbidden = obj;
    }

    public java.sql.Date getValid_begin_date()
    {
        return valid_begin_date;
    }

    public void setValid_begin_date(java.sql.Date obj)
    {
        valid_begin_date = obj;
    }

    public String getValid_begin_date_str()
    {
        return valid_begin_date_str;
    }

    public void setValid_begin_date_str(String obj)
    {
        valid_begin_date_str = obj;
    }

    public java.sql.Date getValid_end_date()
    {
        return valid_end_date;
    }

    public void setValid_end_date(java.sql.Date obj)
    {
        valid_end_date = obj;
    }

    public String getValid_end_date_str()
    {
        return valid_end_date_str;
    }

    public void setValid_end_date_str(String obj)
    {
        valid_end_date_str = obj;
    }

    public boolean getgetIsExcludePKFlag()
    {
        return isExcludePKFlag;
    }

    public void setgetIsExcludePKFlag(boolean obj)
    {
        isExcludePKFlag = obj;
    }

    public boolean isExcludePKFlag() {
		return isExcludePKFlag;
	}

	public void setExcludePKFlag(boolean isExcludePKFlag) {
		this.isExcludePKFlag = isExcludePKFlag;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public Integer getCategory_type() {
		return category_type;
	}

	public void setCategory_type(Integer category_type) {
		this.category_type = category_type;
	}

	public Integer getCategory_deadline() {
		return category_deadline;
	}

	public void setCategory_deadline(Integer category_deadline) {
		this.category_deadline = category_deadline;
	}

	public Integer getCategory_interest_pay_method() {
		return category_interest_pay_method;
	}

	public void setCategory_interest_pay_method(Integer category_interest_pay_method) {
		this.category_interest_pay_method = category_interest_pay_method;
	}

	public java.math.BigDecimal getCategory_return_rate() {
		return category_return_rate;
	}

	public void setCategory_return_rate(java.math.BigDecimal category_return_rate) {
		this.category_return_rate = category_return_rate;
	}

	public Integer getCategory_closure_period() {
		return category_closure_period;
	}

	public void setCategory_closure_period(Integer category_closure_period) {
		this.category_closure_period = category_closure_period;
	}

	public Integer getMaximum_holding_amount() {
		return maximum_holding_amount;
	}

	public void setMaximum_holding_amount(Integer maximum_holding_amount) {
		this.maximum_holding_amount = maximum_holding_amount;
	}

	public java.sql.Date getCategory_over_time() {
		return category_over_time;
	}

	public void setCategory_over_time(java.sql.Date category_over_time) {
		this.category_over_time = category_over_time;
	}

	public Integer getCategory_maximum_amount() {
		return category_maximum_amount;
	}

	public void setCategory_maximum_amount(Integer category_maximum_amount) {
		this.category_maximum_amount = category_maximum_amount;
	}

	public String getCategory_remak() {
		return category_remak;
	}

	public void setCategory_remak(String category_remak) {
		this.category_remak = category_remak;
	}

	public Integer getCategory_rebate_way() {
		return category_rebate_way;
	}

	public void setCategory_rebate_way(Integer category_rebate_way) {
		this.category_rebate_way = category_rebate_way;
	}

	public java.math.BigDecimal getBasic_monthly_rate() {
		return basic_monthly_rate;
	}

	public void setBasic_monthly_rate(java.math.BigDecimal basic_monthly_rate) {
		this.basic_monthly_rate = basic_monthly_rate;
	}

	public Integer getCategory_sales_statistics() {
		return category_sales_statistics;
	}

	public void setCategory_sales_statistics(Integer category_sales_statistics) {
		this.category_sales_statistics = category_sales_statistics;
	}

	public static String[] getDefaultValColArr() {
		return defaultValColArr;
	}

	public static void setDefaultValColArr(String[] defaultValColArr) {
		WmsInvePruductCategory.defaultValColArr = defaultValColArr;
	}

	public static String[] getPkColArr() {
		return pkColArr;
	}

	public static void setPkColArr(String[] pkColArr) {
		WmsInvePruductCategory.pkColArr = pkColArr;
	}

	public static String[] getColumnNameArr() {
		return columnNameArr;
	}

	public static void setColumnNameArr(String[] columnNameArr) {
		WmsInvePruductCategory.columnNameArr = columnNameArr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public String getPlatform_user() {
		return platform_user;
	}

	public void setPlatform_user(String platform_user) {
		this.platform_user = platform_user;
	}

    public Integer getBuy_month_limit()
    {
        return buy_month_limit;
    }

    public void setBuy_month_limit(Integer buy_month_limit)
    {
        this.buy_month_limit = buy_month_limit;
    }

    public String getHas_paper_protocol()
    {
        return has_paper_protocol;
    }

    public void setHas_paper_protocol(String has_paper_protocol)
    {
        this.has_paper_protocol = has_paper_protocol;
    }

    /**
     * put all columns into a map
     */
    public void putInMap(Map<String, Object> paramMap)
    {
        paramMap.put("wms_inve_pruduct_category_id", this.wms_inve_pruduct_category_id);
        paramMap.put("category_name", this.category_name);
        paramMap.put("category_briefing", this.category_briefing);
        paramMap.put("category_investment_money_min", this.category_investment_money_min);
        paramMap.put("category_investment_money_max", this.category_investment_money_max);
        paramMap.put("category_additional_monery_min", this.category_additional_monery_min);
        paramMap.put("category_additional_monery_max", this.category_additional_monery_max);
        paramMap.put("category_account_money_min", this.category_account_money_min);
        paramMap.put("category_account_money_max", this.category_account_money_max);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("enable_flag", this.enable_flag);
        paramMap.put("is_forbidden", this.is_forbidden);
        paramMap.put("valid_begin_date", this.valid_begin_date);
        paramMap.put("valid_begin_date_str", this.valid_begin_date_str);
        paramMap.put("valid_end_date", this.valid_end_date);
        paramMap.put("valid_end_date_str", this.valid_end_date_str);
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