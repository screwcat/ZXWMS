package com.zx.emanage.loanappro.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.zx.sframe.util.mybatis.BaseEntity;

/**
 * 
 * 版权所有：版权所有(C) 2016，卓信金控
 * 系统名称：财富管理平台
 * @ClassName: WmsCreCreditHeadVO
 * 模块名称：主表vo
 * @Description: 内容摘要：
 * @author baisong
 * @date 2016年12月27日
 * @version V1.0
 * history:
 * 1、2016年12月27日 baisong 创建文件
 */
public class WmsCreCreditHeadVO implements BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer wms_cre_credit_head_id;

    private Double credit_limit;

    private Integer create_user_id;

    private String create_user_name;

    private java.sql.Timestamp create_timestamp;

    private String create_timestamp_str;

    private Integer last_update_user_id;

    private java.sql.Timestamp last_update_timestamp;

    private String last_update_timestamp_str;

    private String is_guarantee;

    private java.math.BigDecimal appro_limit;

    private java.math.BigDecimal visa_limit;

    // 产品类型
    private java.math.BigDecimal cre_loan_type;

    // 申请最大还款期限
    private java.math.BigDecimal max_repayment_time_limit;

    private Integer nullify_type;
	
   	private String nullify_user_name;
   	
   	private Integer nullify_user_id;
   	
   	private java.sql.Timestamp nullify_timestamp;
   	
   	private String nullify_timestamp_str;
   	
   	private String nullify_reason;
   	
   	private String final_eval;//终审评价
   	
   	private String face_trial_eval;//面审评价\
   	
   	private String credit_limit_backup;//申请金额备份
   	private BigDecimal max_repayment_time_limit_backup;//申请最大还款期限备份
   	private String cre_loan_type_backup;//产品类型备份
   	
   	private String bill_code_group;//合同编号
   	
   	private String bill_code;
    
    private java.math.BigDecimal 	loan_interest_rate;//贷款利率

    /**组合贷主键**/
    private Integer wms_cre_credit_group_id;

    /**组合贷相关数据***/
    private String arraryJson;
   	
    private Integer is_new;
    private Integer is_parent;
    
   	/**
     * default val cols name array
     */
    private static String[] defaultValColArr = {};

    /**
     * pk cols name array
     */
    private static String[] pkColArr = { "wms_cre_credit_head_id" };

    private static String[] columnNameArr = { "wms_cre_credit_head_id", "credit_limit", "create_user_id",
                                             "create_user_name", "create_timestamp", "create_timestamp_str",
                                             "last_update_user_id", "last_update_timestamp", "final_eval", "face_trial_eval",
                                             "last_update_timestamp_str", "is_guarantee", "enable_flag", "appro_limit",
                                             "postil_limit", "cre_loan_type", "max_repayment_time_limit", "credit_limit_backup"
                                             , "max_repayment_time_limit_backup", "cre_loan_type_backup"};

    public Integer getWms_cre_credit_head_id()
    {
        return wms_cre_credit_head_id;
    }

    public void setWms_cre_credit_head_id(Integer obj)
    {
        wms_cre_credit_head_id = obj;
    }

    public Double getCredit_limit()
    {
        return credit_limit;
    }

    public void setCredit_limit(Double credit_limit)
    {
        this.credit_limit = credit_limit;
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

    public String getIs_guarantee()
    {
        return is_guarantee;
    }

    public void setIs_guarantee(String is_guarantee)
    {
        this.is_guarantee = is_guarantee;
    }

    public java.math.BigDecimal getAppro_limit()
    {
        return appro_limit;
    }

    public void setAppro_limit(java.math.BigDecimal appro_limit)
    {
        this.appro_limit = appro_limit;
    }

    public java.math.BigDecimal getCre_loan_type()
    {
        return cre_loan_type;
    }

    public void setCre_loan_type(java.math.BigDecimal cre_loan_type)
    {
        this.cre_loan_type = cre_loan_type;
    }

    public java.math.BigDecimal getMax_repayment_time_limit()
    {
        return max_repayment_time_limit;
    }

    public void setMax_repayment_time_limit(java.math.BigDecimal max_repayment_time_limit)
    {
        this.max_repayment_time_limit = max_repayment_time_limit;
    }

    public Integer getNullify_type() {
		return nullify_type;
	}

	public void setNullify_type(Integer nullify_type) {
		this.nullify_type = nullify_type;
	}

	public String getNullify_user_name() {
		return nullify_user_name;
	}

	public void setNullify_user_name(String nullify_user_name) {
		this.nullify_user_name = nullify_user_name;
	}

	public Integer getNullify_user_id() {
		return nullify_user_id;
	}

	public void setNullify_user_id(Integer nullify_user_id) {
		this.nullify_user_id = nullify_user_id;
	}

	public java.sql.Timestamp getNullify_timestamp() {
		return nullify_timestamp;
	}

	public void setNullify_timestamp(java.sql.Timestamp nullify_timestamp) {
		this.nullify_timestamp = nullify_timestamp;
	}

	public String getNullify_timestamp_str() {
		return nullify_timestamp_str;
	}

	public void setNullify_timestamp_str(String nullify_timestamp_str) {
		this.nullify_timestamp_str = nullify_timestamp_str;
	}

	public String getNullify_reason() {
		return nullify_reason;
	}

	public void setNullify_reason(String nullify_reason) {
		this.nullify_reason = nullify_reason;
	}

	public String getLast_update_timestamp_str() {
		return last_update_timestamp_str;
	}

	public void setLast_update_timestamp_str(String last_update_timestamp_str) {
		this.last_update_timestamp_str = last_update_timestamp_str;
	}

	public String getFinal_eval() {
		return final_eval;
	}

	public void setFinal_eval(String final_eval) {
		this.final_eval = final_eval;
	}

	public String getFace_trial_eval() {
		return face_trial_eval;
	}

	public void setFace_trial_eval(String face_trial_eval) {
		this.face_trial_eval = face_trial_eval;
	}

	public String getCredit_limit_backup() {
		return credit_limit_backup;
	}

	public void setCredit_limit_backup(String credit_limit_backup) {
		this.credit_limit_backup = credit_limit_backup;
	}

	public BigDecimal getMax_repayment_time_limit_backup() {
		return max_repayment_time_limit_backup;
	}

	public void setMax_repayment_time_limit_backup(
	        BigDecimal max_repayment_time_limit_backup) {
		this.max_repayment_time_limit_backup = max_repayment_time_limit_backup;
	}

	public String getCre_loan_type_backup() {
		return cre_loan_type_backup;
	}

	public void setCre_loan_type_backup(String cre_loan_type_backup) {
		this.cre_loan_type_backup = cre_loan_type_backup;
	}

	public java.math.BigDecimal getLoan_interest_rate() {
		return loan_interest_rate;
	}

	public void setLoan_interest_rate(java.math.BigDecimal loan_interest_rate) {
		this.loan_interest_rate = loan_interest_rate;
	}

    public Integer getWms_cre_credit_group_id()
    {
        return wms_cre_credit_group_id;
    }

    public void setWms_cre_credit_group_id(Integer wms_cre_credit_group_id)
    {
        this.wms_cre_credit_group_id = wms_cre_credit_group_id;
    }

    public String getBill_code()
    {
        return bill_code;
    }

    public void setBill_code(String bill_code)
    {
        this.bill_code = bill_code;
    }

    public String getBill_code_group()
    {
        return bill_code_group;
    }

    public void setBill_code_group(String bill_code_group)
    {
        this.bill_code_group = bill_code_group;
    }

    public Integer getIs_new()
    {
        return is_new;
    }

    public void setIs_new(Integer is_new)
    {
        this.is_new = is_new;
    }

    public Integer getIs_parent()
    {
        return is_parent;
    }

    public void setIs_parent(Integer is_parent)
    {
        this.is_parent = is_parent;
    }

    /**
     * put all columns into a map
     */
    public void putInMapAppro(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("is_guarantee", this.is_guarantee);
        paramMap.put("appro_limit", this.appro_limit);
        paramMap.put("credit_limit", this.credit_limit);
        paramMap.put("cre_loan_type", this.cre_loan_type);
        paramMap.put("max_repayment_time_limit", this.max_repayment_time_limit);
        paramMap.put("nullify_type", this.nullify_type);
        paramMap.put("nullify_user_name", this.nullify_user_name);
        paramMap.put("nullify_user_id", this.nullify_user_id);
        paramMap.put("nullify_timestamp", this.nullify_timestamp);
        paramMap.put("nullify_timestamp_str", this.nullify_timestamp_str);
        paramMap.put("nullify_reason", this.nullify_reason);
        paramMap.put("final_eval", this.final_eval);
        paramMap.put("face_trial_eval", this.face_trial_eval);
        paramMap.put("credit_limit_backup", this.credit_limit_backup);
        paramMap.put("max_repayment_time_limit_backup", this.max_repayment_time_limit_backup);
        paramMap.put("cre_loan_type_backup", this.cre_loan_type_backup);
        paramMap.put("loan_interest_rate", this.loan_interest_rate);
        paramMap.put("wms_cre_credit_group_id", this.wms_cre_credit_group_id);
    }

    /**
     * put all columns into a map
     */
    public void putInMapVisa(Map<String, Object> paramMap)
    {
        paramMap.put("wms_cre_credit_head_id", this.wms_cre_credit_head_id);
        paramMap.put("create_user_id", this.create_user_id);
        paramMap.put("create_user_name", this.create_user_name);
        paramMap.put("create_timestamp", this.create_timestamp);
        paramMap.put("create_timestamp_str", this.create_timestamp_str);
        paramMap.put("last_update_user_id", this.last_update_user_id);
        paramMap.put("last_update_timestamp", this.last_update_timestamp);
        paramMap.put("last_update_timestamp_str", this.last_update_timestamp_str);
        paramMap.put("is_guarantee", this.is_guarantee);
        paramMap.put("visa_limit", this.visa_limit);
        paramMap.put("cre_loan_type", this.cre_loan_type);
        paramMap.put("max_repayment_time_limit", this.max_repayment_time_limit);
        paramMap.put("nullify_type", this.nullify_type);
        paramMap.put("nullify_user_name", this.nullify_user_name);
        paramMap.put("nullify_user_id", this.nullify_user_id);
        paramMap.put("nullify_timestamp", this.nullify_timestamp);
        paramMap.put("nullify_timestamp_str", this.nullify_timestamp_str);
        paramMap.put("nullify_reason", this.nullify_reason);
        paramMap.put("final_eval", this.final_eval);
        paramMap.put("face_trial_eval", this.face_trial_eval);
        paramMap.put("credit_limit_backup", this.credit_limit_backup);
        paramMap.put("max_repayment_time_limit_backup", this.max_repayment_time_limit_backup);
        paramMap.put("cre_loan_type_backup", this.cre_loan_type_backup);
    }

    public java.math.BigDecimal getVisa_limit()
    {
        return visa_limit;
    }

    public void setVisa_limit(java.math.BigDecimal visa_limit)
    {
        this.visa_limit = visa_limit;
    }

    public String getArraryJson()
    {
        return arraryJson;
    }

    public void setArraryJson(String arraryJson)
    {
        this.arraryJson = arraryJson;
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMapVisa()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMapVisa(paramMap);
        return paramMap;
    }

    /**
     * return the columns map
     */
    public Map<String, Object> getInfoMapAppro()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        this.putInMapAppro(paramMap);
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